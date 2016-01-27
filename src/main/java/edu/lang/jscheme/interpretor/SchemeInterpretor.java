package edu.lang.jscheme.interpretor;

import static edu.lang.jscheme.interpretor.EnvironmentBinding.binding;

import edu.lang.jscheme.data.SchemeDefinition;
import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.data.SchemeExpressionSequence;
import edu.lang.jscheme.data.SchemeValue;

public class SchemeInterpretor {

    private SchemeEnvironment env = SchemeEnvironment.EMPTY;

    public SchemeValue eval(SchemeExpression exp) {
        SchemeValue r = exp.eval(env);
        if (exp.is(SchemeDefinition.class)) {
            env = env.addBinding(binding(exp.as(SchemeDefinition.class).name, r));
        }
        if (exp.is(SchemeExpressionSequence.class)) {
            env = exp.as(SchemeExpressionSequence.class).getEndEnvHck();
        }
        return r;
    }
}
