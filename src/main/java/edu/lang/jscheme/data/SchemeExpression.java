package edu.lang.jscheme.data;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.util.Castable;

public abstract class SchemeExpression implements Castable {

    public abstract SchemeValue eval(SchemeEnvironment env);

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }

}
