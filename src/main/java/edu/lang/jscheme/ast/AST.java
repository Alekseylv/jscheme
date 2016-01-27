package edu.lang.jscheme.ast;

import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.data.SchemeTerm;
import edu.lang.jscheme.util.LinkedList;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public abstract class AST {

    public abstract boolean isBlock();
    public abstract SchemeExpression getExpression();
    public abstract SchemeTerm getTerm();
    public abstract LinkedList<AST> getLeafs();

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
