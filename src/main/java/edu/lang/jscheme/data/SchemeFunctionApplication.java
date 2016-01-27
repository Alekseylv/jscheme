package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.util.LinkedList;

public class SchemeFunctionApplication extends SchemeExpression {

    public final LinkedList<SchemeExpression> args;

    public SchemeFunctionApplication(LinkedList<SchemeExpression> args) {
        this.args = args;
    }

    @Override
    public SchemeValue eval(SchemeEnvironment env) {
        SchemeApplyable a = args.head().eval(env).as(SchemeApplyable.class);
        return a.apply(args.tail().map(x -> x.eval(env)));
    }
}
