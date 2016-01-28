package edu.lang.jscheme.ast.lexer;

import edu.lang.jscheme.ast.ASTBlock;
import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.data.SchemeUnit;

public class UnitValueLexer extends Lexer {
    @Override public boolean matches(ASTBlock ast) {
        return ast.getLeafs().isEmpty();
    }

    @Override public SchemeExpression toExpression(ASTBlock ast) {
        return SchemeUnit.getInstance();
    }
}
