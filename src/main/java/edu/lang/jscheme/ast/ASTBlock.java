package edu.lang.jscheme.ast;

import edu.lang.jscheme.data.SchemeTerm;
import edu.lang.jscheme.data.SchemeVariable;
import edu.lang.jscheme.util.LinkedList;

public class ASTBlock extends AST {

    public final LinkedList<AST> leafs;

    public ASTBlock(LinkedList<AST> leafs) {
        this.leafs = leafs;
    }

    @Override
    public boolean isBlock() {
        return true;
    }

    @Override
    public SchemeTerm getTerm() {
        throw new UnsupportedOperationException("Not a node");
    }

    @Override
    public String getTermString() {
        throw new UnsupportedOperationException("Not a node");
    }

    @Override
    public LinkedList<AST> getLeafs() {
        return leafs;
    }

    public boolean isFunctionDefinition() {
        return isDefinition() && leafs.tail().head().isBlock();
    }

    public boolean isVariableDefinition() {
        return isDefinition() && !leafs.tail().head().isBlock();
    }

    public boolean isDefinition() {
        boolean t = !leafs.isEmpty() && leafs.head().getTerm() instanceof SchemeVariable;
        return t && "define".equals(leafs.head().getTermString());
    }

    public boolean isValidDefinition() {
        LinkedList<AST> tail = leafs.tail();
        if (tail.isEmpty()) {
            return false;
        }
        AST secondArg = tail.head();
        return !tail.tail().isEmpty() && (isVariableNameCorrect(secondArg) || areFunctionArgumentsCorrect(secondArg));
    }

    private boolean isVariableNameCorrect(AST ast) {
        return !ast.isBlock() && ast.getTerm() instanceof SchemeVariable;
    }

    public boolean areFunctionArgumentsCorrect(AST ast) {
        return ast.isBlock() && ast.getLeafs().fold(true, (r, t) -> r && isVariableNameCorrect(t));
    }

}
