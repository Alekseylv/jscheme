package edu.lang.jscheme.ast;

import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.parser.Token;
import edu.lang.jscheme.util.LinkedList;

public class ASTBlock extends AST {

    public final LinkedList<AST> leafs;
    public final Token token;

    public ASTBlock(LinkedList<AST> leafs, Token token) {
        this.leafs = leafs;
        this.token = token;
    }

    @Override
    public boolean isBlock() {
        return true;
    }

    @Override
    public SchemeExpression getExpression() {
        throw new UnsupportedOperationException("Not a node");
    }

    @Override
    public LinkedList<AST> getLeafs() {
        return leafs;
    }

    public int size() {
        return leafs.fold(0, (x, y) -> x + 1);
    }

}
