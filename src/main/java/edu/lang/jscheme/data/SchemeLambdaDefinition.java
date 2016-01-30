package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.interpretor.internal.SchemeContinuation;
import edu.lang.jscheme.util.LinkedList;

public class SchemeLambdaDefinition extends SchemeExpression {

    public final LinkedList<SchemeTerm> argNames;
    public final SchemeExpression body;

    public SchemeLambdaDefinition(LinkedList<SchemeTerm> argNames, SchemeExpression body) {
        this.argNames = argNames;
        this.body = body;
    }

    @Override
    public SchemeContinuation eval(SchemeEnvironment env) {
        return SchemeContinuation.continueWith(new SchemeClosure(null, env, argNames, body));
    }
}
