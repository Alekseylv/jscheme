package edu.lang.jscheme.parser;

import edu.lang.jscheme.Token;
import edu.lang.jscheme.ast.AST;
import edu.lang.jscheme.ast.ASTBlock;
import edu.lang.jscheme.ast.ASTNode;
import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.data.SchemeExpressionSequence;
import edu.lang.jscheme.data.SchemeFunctionApplication;
import edu.lang.jscheme.data.SchemeFunctionDefinition;
import edu.lang.jscheme.data.SchemeTerm;
import edu.lang.jscheme.data.SchemeVariableDefinition;
import edu.lang.jscheme.util.LinkedList;
import edu.lang.jscheme.util.Try;

import java.util.Iterator;
import java.util.List;

import static edu.lang.jscheme.util.ErrorUtil.*;
import static java.util.Arrays.asList;

public class Parser {

    private static final VariableParser varParsers = new VariableParser();
    private static final List<TermParser> TERM_PARSERS = asList(new BooleanParser(), new NumberParser(), new StringParser(), varParsers);

    public static Try<LinkedList<Token>> parseTokens(final String line) {
        return Try.tryable(() -> {
            LinkedList<Token> result = LinkedList.empty();
            int last = 0;
            boolean newTerm = false;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == ' ' || line.charAt(i) == '(' || line.charAt(i) == ')') {
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

            while (iter.hasNext()) {
                Token token = iter.next();
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
            }

            return new ASTBlock(result.reverse());
        });
    }

    private static AST parseASTBlock(Iterator<Token> iter, Token upperToken) {
        LinkedList<AST> result = LinkedList.empty();
        while (iter.hasNext()) {
            Token token = iter.next();
            AST parsed;
            switch (token.text) {
                case ")":
                    assertNotEmpty(result, upperToken);
                    return new ASTBlock(result.reverse());
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
        assertAtom(token);
        return new ASTNode(parseTerm(token));
    }


    public static Try<SchemeExpression> parseExpressions(AST ast) {
        return Try.tryable(() -> toTopLevelExpression(ast));
    }

    private static SchemeExpression toTopLevelExpression(AST ast) {
        if (ast.isBlock()) {
            ASTBlock block = (ASTBlock) ast;
            return new SchemeExpressionSequence(block.getLeafs().map(Parser::toExpression));
        }

        return ast.getTerm();
    }

    private static SchemeExpression toExpression(AST ast) {
        if (!ast.isBlock()) {
            return ast.getTerm();
        }
        ASTBlock block = (ASTBlock) ast;
        if (!block.isDefinition()) {
            return new SchemeFunctionApplication(block.getLeafs().map(Parser::toExpression));
        }

        if (!block.isValidDefinition()) {
            throw invalidDefinition();
        }

        SchemeExpression body = toTopLevelExpression(block.getLeafs().tail().tail().head());
        AST secondArg = block.getLeafs().tail().head();

        if (block.isFunctionDefinition()) {
            LinkedList<String> args = secondArg.getLeafs().map(AST::getTermString);
            return new SchemeFunctionDefinition(args.head(), args.tail(), body);
        }

        if (block.isVariableDefinition()) {
            return new SchemeVariableDefinition(secondArg.getTermString(), body);
        }

        throw invalidDefinition();
    }

    private static SchemeTerm parseTerm(Token token) {
        for (TermParser termParser : TERM_PARSERS) {
            if (termParser.matches(token.text)) {
                return termParser.parse(token);
            }
        }
        throw new IllegalArgumentException("Can not parse [" + token.text + "] at 1:" + token.position);
    }


}
