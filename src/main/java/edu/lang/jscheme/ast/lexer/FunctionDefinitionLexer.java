package edu.lang.jscheme.ast.lexer;

import static edu.lang.jscheme.parser.SchemeParser.toBodyExpression;

import edu.lang.jscheme.ast.ASTBlock;
import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.data.SchemeFunctionDefinition;
import edu.lang.jscheme.data.SchemeTerm;
import edu.lang.jscheme.util.LinkedList;

public class FunctionDefinitionLexer extends Lexer {
    @Override
    public boolean matches(ASTBlock ast) {
        return isName(ast, "define") && ast.size() > 2 && ast.getLeafs().tail().head().isBlock();
    }

    @Override
    public SchemeExpression toExpression(ASTBlock ast) {
        SchemeExpression body = toBodyExpression(ast.getLeafs().tail().tail());

        LinkedList<SchemeTerm> args = ast.getLeafs().tail().head().getLeafs().map(x -> x.getExpression().as(SchemeTerm.class));
        return new SchemeFunctionDefinition(args.head(), args.tail(), body);
    }
}
