package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.interpretor.internal.SchemeApplicable;
import edu.lang.jscheme.interpretor.internal.SchemeContinuation;
import edu.lang.jscheme.util.LinkedList;

public class SchemeFunctionApplication extends SchemeExpression {

    public final LinkedList<SchemeExpression> args;

    public SchemeFunctionApplication(LinkedList<SchemeExpression> args) {
        this.args = args;
    }

    @Override
    public SchemeContinuation eval(SchemeEnvironment env) {
        SchemeApplicable a = args.head().forceEval(env).as(SchemeApplicable.class);
        final LinkedList<SchemeValue> arguments = args.tail().map(x -> x.forceEval(env));
        return SchemeContinuation.continueWith(() -> a.apply(arguments));
    }
}
