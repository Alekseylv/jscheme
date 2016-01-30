package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.interpretor.internal.SchemeContinuation;

public abstract class SchemeValue extends SchemeExpression {
    @Override
    public SchemeContinuation eval(SchemeEnvironment env) {
        return SchemeContinuation.continueWith(this);
    }

    public SchemeString toSchemeString() {
        return new SchemeString(this.toString());
    }
}
