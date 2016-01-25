package edu.lang.jscheme.util;

import java.util.function.Function;
import java.util.function.Supplier;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;


public abstract class Try<T> {

    public abstract boolean isSuccess();

    public abstract boolean isFailure();

    public abstract T get();

    public abstract <R> Try<R> map(Function<T, R> f);

    public abstract <R> Try<R> flatMap(Function<T, Try<R>> f);

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }

    public static <T> Try<T> tryable(Supplier<T> s) {
        try {
            return new Success<>(s.get());
        } catch (RuntimeException e) {
            return new Failure<>(e);
        }
    }

    private static class Success<T> extends Try<T> {

        private final T result;

        public Success(T result) {
            this.result = result;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public boolean isFailure() {
            return false;
        }

        @Override
        public T get() {
            return result;
        }

        @Override
        public <R> Try<R> map(Function<T, R> f) {
            return new Success<>(f.apply(result));
        }

        @Override
        public <R> Try<R> flatMap(Function<T, Try<R>> f) {
            return f.apply(result);
        }
    }

    private static class Failure<T> extends Try<T> {

        private final RuntimeException e;

        private Failure(RuntimeException e) {
            this.e = e;
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public boolean isFailure() {
            return true;
        }

        @Override
        public T get() {
            throw e;
        }

        @Override
        public <R> Try<R> map(Function<T, R> f) {
            return new Failure<>(e);
        }

        @Override
        public <R> Try<R> flatMap(Function<T, Try<R>> f) {
            return new Failure<>(e);
        }
    }
}
