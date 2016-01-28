package edu.lang.jscheme.ast.lexer;

import edu.lang.jscheme.ast.ASTBlock;
import edu.lang.jscheme.data.SchemeConditional;
import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.parser.SchemeParser;
import edu.lang.jscheme.util.ErrorUtil;

public class ConditionalLexer extends Lexer {
    @Override
    public boolean matches(ASTBlock ast) {
        return ast.size() > 0 && isName(ast, "if");
    }

    @Override
    public SchemeExpression toExpression(ASTBlock ast) {
        if (ast.size() != 4) {
            throw ErrorUtil.invalidDefinition(ast.token);
        }
        SchemeExpression test = SchemeParser.toExpression(ast.getLeafs().tail().head());
        SchemeExpression path1 = SchemeParser.toExpression(ast.getLeafs().tail().tail().head());
        SchemeExpression path2 = SchemeParser.toExpression(ast.getLeafs().tail().tail().tail().head());
        return new SchemeConditional(test, path1, path2);
    }
}
