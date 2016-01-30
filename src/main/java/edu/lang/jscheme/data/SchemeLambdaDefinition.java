package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.util.LinkedList;

public class SchemeLambdaDefinition extends SchemeExpression {

    public final LinkedList<SchemeTerm> argNames;
    public final SchemeExpression body;

    public SchemeLambdaDefinition(LinkedList<SchemeTerm> argNames, SchemeExpression body) {
        this.argNames = argNames;
        this.body = body;
    }

    @Override
    public SchemeValue eval(SchemeEnvironment env) {
        return new SchemeClosure(null, env, argNames, body);
    }
}
