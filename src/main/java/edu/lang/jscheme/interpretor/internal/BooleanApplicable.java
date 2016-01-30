package edu.lang.jscheme.interpretor.internal;

import java.util.function.BiFunction;

import edu.lang.jscheme.data.SchemeBoolean;
import edu.lang.jscheme.data.SchemeValue;
import edu.lang.jscheme.util.LinkedList;

public class BooleanApplicable extends SchemeApplicable {

    private final BiFunction<Boolean, Boolean, Boolean> f;

    protected BooleanApplicable(BiFunction<Boolean, Boolean, Boolean> f) {
        this.f = f;
    }

    @Override
    public SchemeContinuation apply(LinkedList<SchemeValue> arguments) {
        return SchemeContinuation.continueWith(new SchemeBoolean(arguments.tail().fold(arguments.head().as(SchemeBoolean.class).value, (r, a) ->
                f.apply(r, a.as(SchemeBoolean.class).value)))
        );
    }

    public static BooleanApplicable boolApplicable(BiFunction<Boolean, Boolean, Boolean> f) {
        return new BooleanApplicable(f);
    }

}
