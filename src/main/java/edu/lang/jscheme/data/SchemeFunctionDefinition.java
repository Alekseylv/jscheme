package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.interpretor.internal.SchemeContinuation;
import edu.lang.jscheme.util.LinkedList;

public class SchemeFunctionDefinition extends SchemeDefinition {

    public final LinkedList<SchemeTerm> argNames;
    public final SchemeExpression body;

    public SchemeFunctionDefinition(SchemeTerm name, LinkedList<SchemeTerm> argNames, SchemeExpression body) {
        super(name);
        this.argNames = argNames;
        this.body = body;
    }

    @Override
    public SchemeContinuation eval(SchemeEnvironment env) {
        return SchemeContinuation.continueWith(new SchemeClosure(name, env, argNames, body));
    }
}
