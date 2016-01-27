package edu.lang.jscheme.interpretor.internal;

import java.util.function.BiFunction;
import java.util.function.Function;

import edu.lang.jscheme.data.SchemeValue;
import edu.lang.jscheme.util.LinkedList;

public abstract class SchemeApplicable extends SchemeValue implements Applicable {

    public static SchemeApplicable unaryApplicable(Function<SchemeValue, SchemeValue> f) {
        return new SchemeApplicable() {
            @Override public SchemeValue apply(LinkedList<SchemeValue> arguments) {
                return f.apply(arguments.head());
            }
        };
    }

    public static SchemeApplicable binaryApplicable(BiFunction<SchemeValue, SchemeValue, SchemeValue> f) {
        return new SchemeApplicable() {
            @Override public SchemeValue apply(LinkedList<SchemeValue> arguments) {
                return f.apply(arguments.head(), arguments.tail().head());
            }
        };
    }

}
