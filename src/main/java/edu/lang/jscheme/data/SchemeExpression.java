package edu.lang.jscheme.data;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import edu.lang.jscheme.interpretor.SchemeEnvironment;

public abstract class SchemeExpression {

    public abstract SchemeValue eval(SchemeEnvironment env);

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }

    public <T> T as(Class<T> type) {
        return type.cast(this);

    }
    public <T> boolean is(Class<T> type) {
        return type.isAssignableFrom(this.getClass());
    }
}
