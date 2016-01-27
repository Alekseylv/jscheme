package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.SchemeEnvironment;

public abstract class SchemeValue extends SchemeExpression {
    @Override
    public SchemeValue eval(SchemeEnvironment env) {
        return this;
    }
}
