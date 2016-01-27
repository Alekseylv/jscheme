package edu.lang.jscheme.data;

import static edu.lang.jscheme.interpretor.EnvironmentBinding.binding;

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
    public SchemeValue eval(SchemeEnvironment env) {
        Pair<SchemeValue, SchemeEnvironment> result = expressions.fold(Pair.of((SchemeValue) SchemeUnit.getInstance(), env), (r, exp) -> {
            SchemeValue v = exp.eval(r.getRight());
            if (exp.is(SchemeDefinition.class)) {
                return Pair.of(v, r.getRight().addBinding(binding(exp.as(SchemeDefinition.class).name, v)));
            }

            return Pair.of(v, r.getRight());
        });
        endEnvHck = result.getValue();
        return result.getKey();
    }

    public SchemeEnvironment getEndEnvHck() {
        return endEnvHck;
    }
}
