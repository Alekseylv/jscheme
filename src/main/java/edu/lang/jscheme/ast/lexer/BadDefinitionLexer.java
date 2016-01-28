package edu.lang.jscheme.ast.lexer;

import edu.lang.jscheme.ast.ASTBlock;
import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.util.ErrorUtil;

public class BadDefinitionLexer extends Lexer {
    @Override
    public boolean matches(ASTBlock ast) {
        return isName(ast, "define");
    }

    @Override
    public SchemeExpression toExpression(ASTBlock ast) {
        throw ErrorUtil.invalidDefinition(ast.token);
    }
}
