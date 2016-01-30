package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.interpretor.internal.SchemeContinuation;

public class SchemeTerm extends SchemeExpression {
    public final String name;

    public SchemeTerm(String name) {
        this.name = name;
    }

    @Override
    public SchemeContinuation eval(SchemeEnvironment env) {
        return SchemeContinuation.continueWith(env.bindings.find(x -> x.var.name.equals(name)).orElseThrow(
                () -> new IllegalArgumentException(name + " not found in environment")).value);
    }
}
