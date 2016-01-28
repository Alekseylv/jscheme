package edu.lang.jscheme.data;

public class SchemeCons extends SchemeValue {

    public final SchemeValue head;
    public final SchemeValue tail;

    public SchemeCons(SchemeValue head, SchemeValue tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public SchemeString toSchemeString() {
        return new SchemeString("(" + head.toSchemeString().string + " " + tail.toSchemeString().string + ")");
    }
}
