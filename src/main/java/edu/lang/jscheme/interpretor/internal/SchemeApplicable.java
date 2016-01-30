package edu.lang.jscheme.interpretor.internal;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;

import edu.lang.jscheme.data.SchemeBoolean;
import edu.lang.jscheme.data.SchemeNumber;
import edu.lang.jscheme.data.SchemeValue;
import edu.lang.jscheme.util.LinkedList;

public abstract class SchemeApplicable extends SchemeValue implements Applicable {

    public static <T extends SchemeValue> SchemeApplicable typeCheck(Class<T> t) {
        return new SchemeApplicable() {
            @Override
            public SchemeContinuation apply(LinkedList<SchemeValue> arguments) {
                return SchemeContinuation.continueWith(new SchemeBoolean(arguments.head().is(t)));
            }
        };
    }

    public static SchemeApplicable unaryApplicable(Function<SchemeValue, SchemeValue> f) {
        return new SchemeApplicable() {
            @Override
            public SchemeContinuation apply(LinkedList<SchemeValue> arguments) {
                return SchemeContinuation.continueWith(f.apply(arguments.head()));
            }
        };
    }

    public static SchemeApplicable binaryApplicable(BiFunction<SchemeValue, SchemeValue, SchemeValue> f) {
        return new SchemeApplicable() {
            @Override
            public SchemeContinuation apply(LinkedList<SchemeValue> arguments) {
                return SchemeContinuation.continueWith(f.apply(arguments.head(), arguments.tail().head()));
            }
        };
    }

    public static SchemeApplicable numberTest(BiFunction<BigDecimal, BigDecimal, Boolean> f) {
        return new SchemeApplicable() {
            @Override
            public SchemeContinuation apply(LinkedList<SchemeValue> arguments) {
                LinkedList<SchemeNumber> n = arguments.map(x -> x.as(SchemeNumber.class));
                return SchemeContinuation.continueWith(new SchemeBoolean(f.apply(n.head().num, n.tail().head().num)));
            }
        };
    }

    public static SchemeApplicable numApplicable(BiFunction<BigDecimal, BigDecimal, BigDecimal> f) {
        return new SchemeApplicable() {
            @Override
            public SchemeContinuation apply(LinkedList<SchemeValue> arguments) {
                return SchemeContinuation.continueWith(new SchemeNumber(arguments.tail().fold(arguments.head().as(SchemeNumber.class).num, (r, a) ->
                        f.apply(r, a.as(SchemeNumber.class).num)))
                );
            }
        };
    }

    public static SchemeApplicable boolApplicable(BiFunction<Boolean, Boolean, Boolean> f) {
        return new SchemeApplicable() {
            @Override
            public SchemeContinuation apply(LinkedList<SchemeValue> arguments) {
                return SchemeContinuation.continueWith(new SchemeBoolean(arguments.tail().fold(arguments.head().as(SchemeBoolean.class).value, (r, a) ->
                        f.apply(r, a.as(SchemeBoolean.class).value)))
                );
            }
        };
    }

}
