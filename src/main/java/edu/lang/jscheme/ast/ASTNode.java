package edu.lang.jscheme.ast;

import edu.lang.jscheme.data.SchemeTerm;
import edu.lang.jscheme.data.SchemeVariable;
import edu.lang.jscheme.util.LinkedList;

public class ASTNode extends AST {
    public final SchemeTerm term;

    public ASTNode(SchemeTerm term) {
        this.term = term;
    }

    @Override
    public boolean isBlock() {
        return false;
    }

    @Override
    public SchemeTerm getTerm() {
        return term;
    }

    @Override
    public String getTermString() {
        return ((SchemeVariable) term).name;
    }

    @Override
    public LinkedList<AST> getLeafs() {
        throw new UnsupportedOperationException("Not a block");
    }
}
