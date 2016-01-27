package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.SchemeEnvironment;

public class SchemeTerm extends SchemeExpression {
    public final String name;

    public SchemeTerm(String name) {
        this.name = name;
    }

    @Override
    public SchemeValue eval(SchemeEnvironment env) {
        return env.bindings.find(x -> x.var.name.equals(name)).orElseThrow(
                () -> new IllegalArgumentException(name + " not found in environment")).value;
    }
}
