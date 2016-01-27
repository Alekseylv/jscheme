package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.SchemeEnvironment;

public class SchemeVariableDefinition extends SchemeDefinition {

    public final SchemeExpression value;

    public SchemeVariableDefinition(SchemeTerm name, SchemeExpression value) {
        super(name);
        this.value = value;
    }

    @Override
    public SchemeValue eval(SchemeEnvironment env) {
        return value.eval(env);
    }
}
