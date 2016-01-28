package edu.lang.jscheme.parser;

import static edu.lang.jscheme.util.ErrorUtil.assertAtom;
import static edu.lang.jscheme.util.ErrorUtil.invalidDefinition;
import static edu.lang.jscheme.util.ErrorUtil.noMatchingParen;
import static edu.lang.jscheme.util.ErrorUtil.unmatchedParen;
import static java.lang.Character.isWhitespace;
import static java.util.Arrays.asList;

import java.util.Iterator;
import java.util.List;

import edu.lang.jscheme.ast.AST;
import edu.lang.jscheme.ast.ASTBlock;
import edu.lang.jscheme.ast.ASTNode;
import edu.lang.jscheme.ast.lexer.BadDefinitionLexer;
import edu.lang.jscheme.ast.lexer.ConditionalLexer;
import edu.lang.jscheme.ast.lexer.FunctionApplicationLexer;
import edu.lang.jscheme.ast.lexer.FunctionDefinitionLexer;
import edu.lang.jscheme.ast.lexer.Lexer;
import edu.lang.jscheme.ast.lexer.UnitValueLexer;
import edu.lang.jscheme.ast.lexer.VariableDefinitionLexer;
import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.data.SchemeExpressionSequence;
import edu.lang.jscheme.util.LinkedList;
import edu.lang.jscheme.util.Try;

public class SchemeParser {

    public static final String NEW_LINE = System.getProperty("line.separator"); //TODO add newline support (store line number in token?)
    private static final TermParser TERM_PARSER = new TermParser();
    private static final List<Parser> EXPRESSION_PARSERS = asList(new BooleanParser(), new NumberParser(), new StringParser(), TERM_PARSER);
    private static final List<Lexer> LEXERS = asList(new ConditionalLexer(), new FunctionDefinitionLexer(), new VariableDefinitionLexer(), new BadDefinitionLexer(),
            new UnitValueLexer(), new FunctionApplicationLexer());

    public static Try<LinkedList<Token>> parseTokens(final String line) {
        return Try.tryable(() -> {
            LinkedList<Token> result = LinkedList.empty();
            int last = 0;
            boolean newTerm = false;
            for (int i = 0; i < line.length(); i++) {
                if (isWhitespace(line.charAt(i)) || line.charAt(i) == '(' || line.charAt(i) == ')') {
                    if (newTerm) {
                        result = result.add(new Token(line.substring(last, i), i));
                    }
                    if (line.charAt(i) != ' ') {
                        result = result.add(new Token(String.valueOf(line.charAt(i)), i));
                    }
                    last = nextNonWhiteSpace(line, i + 1);
                    i = last - 1;
                    newTerm = false;
                } else {
                    newTerm = true;
                }
            }

            if (newTerm) {
                result = result.add(new Token(line.substring(last, line.length()), last));
            }

            return result.reverse();
        });
    }

    private static int nextNonWhiteSpace(String line, int start) {
        for (int i = start; i < line.length(); i++) {
            if (line.charAt(i) != ' ') {
                return i;
            }
        }
        return line.length();
    }

    public static Try<AST> parseAST(LinkedList<Token> tokens) {
        return Try.tryable(() -> {
            Iterator<Token> iter = tokens.iterator();
            LinkedList<AST> result = LinkedList.empty();

            Token firstToken = iter.next();
            Token token = firstToken;
            do {
                AST parsed;
                switch (token.text) {
                    case ")":
                        throw unmatchedParen(token);
                    case "(":
                        parsed = parseASTBlock(iter, token);
                        break;
                    default:
                        parsed = parseASTNode(token);
                        break;
                }
                result = result.add(parsed);
                if (iter.hasNext()) {
                    token = iter.next();
                }
            } while (iter.hasNext());

            return new ASTBlock(result.reverse(), firstToken);
        });
    }

    private static AST parseASTBlock(Iterator<Token> iter, Token upperToken) {
        LinkedList<AST> result = LinkedList.empty();
        while (iter.hasNext()) {
            Token token = iter.next();
            AST parsed;
            switch (token.text) {
                case ")":
                    return new ASTBlock(result.reverse(), upperToken);
                case "(":
                    parsed = parseASTBlock(iter, token);
                    break;
                default:
                    parsed = parseASTNode(token);
                    break;
            }
            result = result.add(parsed);
        }

        throw noMatchingParen(upperToken);
    }

    private static AST parseASTNode(Token token) {
        return new ASTNode(parseExpression(token));
    }

    public static Try<SchemeExpression> lexer(AST ast) {
        return Try.tryable(() -> toTopLevelExpression(ast));
    }

    private static SchemeExpression toTopLevelExpression(AST ast) {
        if (ast.isBlock()) {
            return toBodyExpression(ast.getLeafs());
        }

        return ast.getExpression();
    }

    public static SchemeExpression toBodyExpression(LinkedList<AST> body) {
        return new SchemeExpressionSequence(body.map(SchemeParser::toExpression));
    }

    public static SchemeExpression toExpression(AST ast) {
        if (!ast.isBlock()) {
            return ast.getExpression();
        }
        ASTBlock block = ast.as(ASTBlock.class);

        for (Lexer lexer : LEXERS) {
            if (lexer.matches(block)) {
                return lexer.toExpression(block);
            }
        }
        throw invalidDefinition(block.token);
    }

    private static SchemeExpression parseExpression(Token token) {
        for (Parser parser : EXPRESSION_PARSERS) {
            if (parser.matches(token.text)) {
                return parser.parse(token);
            }
        }
        throw new IllegalArgumentException("Can not parse [" + token.text + "] at 1:" + token.position);
    }

}
