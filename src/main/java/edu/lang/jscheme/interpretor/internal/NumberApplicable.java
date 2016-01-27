package edu.lang.jscheme.interpretor.internal;

import java.math.BigDecimal;
import java.util.function.BiFunction;

import edu.lang.jscheme.data.SchemeNumber;
import edu.lang.jscheme.data.SchemeValue;
import edu.lang.jscheme.util.LinkedList;

public class NumberApplicable extends SchemeApplicable {

    private final BiFunction<BigDecimal, BigDecimal, BigDecimal> f;

    protected NumberApplicable(BiFunction<BigDecimal, BigDecimal, BigDecimal> f) {
        this.f = f;
    }

    @Override
    public SchemeValue apply(LinkedList<SchemeValue> arguments) {
        return new SchemeNumber(arguments.tail().fold(arguments.head().as(SchemeNumber.class).num, (r, a) ->
                f.apply(r, a.as(SchemeNumber.class).num))
        );
    }

    public static NumberApplicable numApplicable(BiFunction<BigDecimal, BigDecimal, BigDecimal> f) {
        return new NumberApplicable(f);
    }

}
