package edu.lang.jscheme.ast.lexer;

import edu.lang.jscheme.ast.ASTBlock;
import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.data.SchemeFunctionApplication;
import edu.lang.jscheme.parser.SchemeParser;

public class FunctionApplicationLexer extends Lexer {

    @Override
    public boolean matches(ASTBlock ast) {
        return ast.size() > 0;
    }

    @Override
    public SchemeExpression toExpression(ASTBlock ast) {
        return new SchemeFunctionApplication(ast.getLeafs().map(SchemeParser::toExpression));
    }

}
