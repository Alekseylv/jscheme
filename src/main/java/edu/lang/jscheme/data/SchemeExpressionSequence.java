package edu.lang.jscheme.data;

import static edu.lang.jscheme.interpretor.EnvironmentBinding.binding;

import edu.lang.jscheme.interpretor.internal.SchemeContinuation;
import org.apache.commons.lang3.tuple.Pair;

import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.util.LinkedList;

public class SchemeExpressionSequence extends SchemeExpression {

    public final LinkedList<SchemeExpression> expressions;
    private SchemeEnvironment endEnvHck;

    public SchemeExpressionSequence(LinkedList<SchemeExpression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public SchemeContinuation eval(SchemeEnvironment env) {
        Pair<SchemeExpression, SchemeEnvironment> r = forceEvalStartOfSequence(env, expressions);

        return r.getKey().eval(r.getValue()).after(x -> {
            endEnvHck = addToEnv(r.getKey(), x, r.getValue());
        });
    }

    private Pair<SchemeExpression, SchemeEnvironment> forceEvalStartOfSequence(SchemeEnvironment env, LinkedList<SchemeExpression> exp) {
        if (exp.tail().isEmpty()) {
            return Pair.of(exp.head(), env);
        }
        return forceEvalStartOfSequence(addToEnv(exp.head(), exp.head().forceEval(env), env), exp.tail());
    }

    private SchemeEnvironment addToEnv(SchemeExpression exp, SchemeValue v, SchemeEnvironment env) {
        if (exp.is(SchemeDefinition.class)) {
            return env.addBinding(binding(exp.as(SchemeDefinition.class).name, v));
        }
        return env;
    }

    public SchemeEnvironment getEndEnvHck() {
        return endEnvHck;
    }
}
