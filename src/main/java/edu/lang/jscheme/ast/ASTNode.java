package edu.lang.jscheme.ast;

import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.util.LinkedList;

public class ASTNode extends AST {
    public final SchemeExpression term;

    public ASTNode(SchemeExpression term) {
        this.term = term;
    }

    @Override
    public boolean isBlock() {
        return false;
    }

    @Override
    public SchemeExpression getExpression() {
        return term;
    }

    @Override
    public LinkedList<AST> getLeafs() {
        throw new UnsupportedOperationException("Not a block");
    }
}
