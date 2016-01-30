package edu.lang.jscheme.ast.lexer;

import edu.lang.jscheme.ast.ASTBlock;
import edu.lang.jscheme.data.SchemeExpression;

import java.util.List;

import static edu.lang.jscheme.util.ErrorUtil.invalidDefinition;
import static java.util.Arrays.asList;

public class DefinitionLexer extends Lexer {

    private final List<Lexer> definitionLexers = asList(new FunctionDefinitionLexer(), new VariableDefinitionLexer());

    @Override
    public boolean matches(ASTBlock ast) {
        return isName(ast, "define");
    }

    @Override
    public SchemeExpression toExpression(ASTBlock ast) {
        for (Lexer lexer : definitionLexers) {
            if (lexer.matches(ast)) {
                return lexer.toExpression(ast);
            }
        }
        throw invalidDefinition(ast.token);
    }
}
