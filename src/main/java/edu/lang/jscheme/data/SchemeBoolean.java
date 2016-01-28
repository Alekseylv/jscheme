package edu.lang.jscheme.data;

public class SchemeBoolean extends SchemeValue {

    public final boolean value;

    public SchemeBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public SchemeString toSchemeString() {
        return new SchemeString(String.valueOf(value));
    }

}
