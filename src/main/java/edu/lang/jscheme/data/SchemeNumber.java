package edu.lang.jscheme.data;

import java.math.BigDecimal;

public class SchemeNumber extends SchemeValue {

    public final BigDecimal num;

    public SchemeNumber(BigDecimal num) {
        this.num = num;
    }

    @Override public String toString() {
        return num.toString();
    }
}
