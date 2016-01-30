package edu.lang.jscheme.data;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.interpretor.internal.SchemeContinuation;
import edu.lang.jscheme.util.Castable;

import java.util.LinkedList;
import java.util.List;

public abstract class SchemeExpression implements Castable {

    public abstract SchemeContinuation eval(SchemeEnvironment env);

    public final SchemeValue forceEval(SchemeEnvironment env) {
        return eval(env).eval();
    }

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }

}
