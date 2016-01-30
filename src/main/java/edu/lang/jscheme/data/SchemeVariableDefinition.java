package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.interpretor.internal.SchemeContinuation;

public class SchemeVariableDefinition extends SchemeDefinition {

    public final SchemeExpression value;

    public SchemeVariableDefinition(SchemeTerm name, SchemeExpression value) {
        super(name);
        this.value = value;
    }

    @Override
    public SchemeContinuation eval(SchemeEnvironment env) {
        return value.eval(env);
    }
}
