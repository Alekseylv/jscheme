package edu.lang.jscheme.ast;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.util.Castable;
import edu.lang.jscheme.util.LinkedList;

public abstract class AST implements Castable {

    public abstract boolean isBlock();

    public abstract SchemeExpression getExpression();

    public abstract LinkedList<AST> getLeafs();

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
