package edu.lang.jscheme.data;

public class SchemeNumber extends SchemeValue {

    public final int num;

    public SchemeNumber(int num) {
        this.num = num;
    }

    @Override public String toString() {
        return String.valueOf(num);
    }
}
