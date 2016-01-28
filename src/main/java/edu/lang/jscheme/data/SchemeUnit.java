package edu.lang.jscheme.data;

public class SchemeUnit extends SchemeValue {
    private static SchemeUnit instance = new SchemeUnit();

    public static SchemeUnit getInstance() {
        return instance;
    }

    private SchemeUnit() {
    }

    @Override public SchemeString toSchemeString() {
        return new SchemeString("null");
    }
}
