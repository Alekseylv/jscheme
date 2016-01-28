package edu.lang.jscheme.util;

import static java.util.Arrays.asList;

import java.util.HashSet;

import edu.lang.jscheme.parser.Token;

public class ErrorUtil {

    private static final HashSet<Character> chars = new HashSet<>(
            asList('!', '#', '%', '&', '*', '+', '-', '/', ':', '<', '=', '>', '?', '@', '^',
                    '|', '~'));

    public static boolean isAtom(Token token) {
        Character c = token.text.charAt(0);
        return Character.isJavaIdentifierPart(c) || chars.contains(c);
    }

    public static void assertAtom(Token token) {
        if (!isAtom(token)) {
            throw unexpectedToken(token);
        }
    }

    public static void assertNotEmpty(LinkedList list, Token token) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Empty expression encountered at 1:" + token.position);
        }
    }

    public static RuntimeException unexpectedToken(Token token) {
        return new IllegalArgumentException("Unexpected character [" + token.text + "] at 1:" + token.position);
    }

    public static RuntimeException unmatchedParen(Token token) {
        return new IllegalArgumentException("Unmatched \")\" at 1:" + token.position);
    }

    public static RuntimeException noMatchingParen(Token token) {
        return new IllegalArgumentException("No matching \")\" for parenthesis starting at 1:" + token.position);
    }

    public static RuntimeException invalidDefinition(Token token) {
        return new IllegalArgumentException("Not a valid definition at 1:" + token.position);
    }

}
