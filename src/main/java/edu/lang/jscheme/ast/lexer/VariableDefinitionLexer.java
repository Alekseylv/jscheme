package edu.lang.jscheme.ast.lexer;

import static edu.lang.jscheme.parser.SchemeParser.toBodyExpression;

import edu.lang.jscheme.ast.ASTBlock;
import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.data.SchemeTerm;
import edu.lang.jscheme.data.SchemeVariableDefinition;

public class VariableDefinitionLexer extends Lexer {
    @Override
    public boolean matches(ASTBlock ast) {
        return isName(ast, "define") && ast.size() > 2 && !ast.getLeafs().tail().head().isBlock();
    }

    @Override
    public SchemeExpression toExpression(ASTBlock ast) {
        SchemeExpression body = toBodyExpression(ast.getLeafs().tail().tail());
        return new SchemeVariableDefinition(ast.getLeafs().tail().head().getExpression().as(SchemeTerm.class), body);

    }
}
