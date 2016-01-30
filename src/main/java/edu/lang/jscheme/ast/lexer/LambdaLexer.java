package edu.lang.jscheme.ast.lexer;

import edu.lang.jscheme.ast.ASTBlock;
import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.data.SchemeLambdaDefinition;
import edu.lang.jscheme.data.SchemeTerm;
import edu.lang.jscheme.util.ErrorUtil;
import edu.lang.jscheme.util.LinkedList;

import static edu.lang.jscheme.parser.SchemeParser.toBodyExpression;

public class LambdaLexer extends Lexer {
    @Override
    public boolean matches(ASTBlock ast) {
        return isName(ast, "lambda") || isName(ast, "#λ");
    }

    @Override
    public SchemeExpression toExpression(ASTBlock ast) {
        if (ast.size() != 3 || !ast.getLeafs().tail().head().isBlock()) {
            throw ErrorUtil.invalidDefinition(ast.token);
        }

        LinkedList<SchemeTerm> args = ast.getLeafs().tail().head().getLeafs().map(x -> x.getExpression().as(SchemeTerm.class));
        return new SchemeLambdaDefinition(args, toBodyExpression(ast.getLeafs().tail().tail()));
    }
}
