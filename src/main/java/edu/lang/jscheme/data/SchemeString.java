package edu.lang.jscheme.data;

public class SchemeString extends SchemeValue {
    public final String string;

    public SchemeString(String string) {
        this.string = string;
    }

    @Override
    public SchemeString toSchemeString() {
        return this;
    }
}
