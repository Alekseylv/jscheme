package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.interpretor.internal.SchemeContinuation;

public class SchemeConditional extends SchemeExpression {

    public final SchemeExpression test;
    public final SchemeExpression path1;
    public final SchemeExpression path2;

    public SchemeConditional(SchemeExpression test, SchemeExpression path1, SchemeExpression path2) {
        this.test = test;
        this.path1 = path1;
        this.path2 = path2;
    }

    @Override
    public SchemeContinuation eval(SchemeEnvironment env) {
        return test.forceEval(env).as(SchemeBoolean.class).value ? path1.eval(env) : path2.eval(env);
    }
}
