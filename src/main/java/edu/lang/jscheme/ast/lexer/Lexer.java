package edu.lang.jscheme.ast.lexer;

import edu.lang.jscheme.ast.AST;
import edu.lang.jscheme.ast.ASTBlock;
import edu.lang.jscheme.ast.ASTNode;
import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.data.SchemeTerm;

public abstract class Lexer {

    public abstract boolean matches(ASTBlock ast);

    public abstract SchemeExpression toExpression(ASTBlock ast);

    protected boolean isName(ASTBlock ast, String name) {
        boolean isFirstNodeTerm = !ast.getLeafs().isEmpty() && isTerm(ast.getLeafs().head());
        return isFirstNodeTerm && name.equals(getTermName(ast.getLeafs().head()));
    }

    protected boolean isTerm(AST node) {
        return node.is(ASTNode.class) && node.getExpression().is(SchemeTerm.class);
    }

    protected String getTermName(AST node) {
        return node.getExpression().as(SchemeTerm.class).name;
    }
}
