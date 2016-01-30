package edu.lang.jscheme.interpretor.internal;

import edu.lang.jscheme.data.SchemeValue;
import edu.lang.jscheme.util.Castable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class SchemeContinuation implements Castable {

    protected Consumer<SchemeValue> callback;

    public abstract SchemeContinuation nextContinuation();

    public abstract boolean isDone();

    public abstract SchemeValue eval();

    public SchemeContinuation after(Consumer<SchemeValue> v) {
        callback = v;
        return this;
    }

    public static SchemeContinuation continueWith(Supplier<SchemeContinuation> s) {
        return new ActionContinuation(s);
    }

    public static SchemeContinuation continueWith(RuntimeException ex) {
        return new ErrorContinuation(ex);
    }

    public static SchemeContinuation continueWith(SchemeValue value) {
        return new CompletedContinuation(value);
    }

    private static class CompletedContinuation extends SchemeContinuation {

        private final SchemeValue value;

        private CompletedContinuation(SchemeValue value) {
            this.value = value;
        }

        @Override
        public SchemeContinuation nextContinuation() {
            return this;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public SchemeValue eval() {
            if (callback != null) {
                callback.accept(value);
            }
            return value;
        }
    }

    private static class ActionContinuation extends SchemeContinuation {

        private final Supplier<SchemeContinuation> next;

        private ActionContinuation(Supplier<SchemeContinuation> next) {
            this.next = next;
        }

        @Override
        public SchemeContinuation nextContinuation() {
            return next.get();
        }

        @Override
        public boolean isDone() {
            return false;
        }

        @Override
        public SchemeValue eval() {
            SchemeContinuation next = this.nextContinuation();
            while (!next.isDone()) {
                next = next.nextContinuation();
            }
            next.callback = callback;
            return next.eval();
        }
    }

    private static class ErrorContinuation extends SchemeContinuation {

        private final RuntimeException ex;

        private ErrorContinuation(RuntimeException ex) {
            this.ex = ex;
        }

        @Override
        public SchemeContinuation nextContinuation() {
            return this;
        }

        @Override
        public boolean isDone() {
            return true;
        }

        @Override
        public SchemeValue eval() {
            throw ex;
        }
    }

}
