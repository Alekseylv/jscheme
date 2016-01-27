package edu.lang.jscheme.parser;

import java.util.regex.Pattern;

import edu.lang.jscheme.data.SchemeString;

public class StringParser extends Parser {
    private static Pattern pattern = Pattern.compile("^\\\"((?!\\\").)*\\\"$");

    @Override
    public boolean matches(String text) {
        return pattern.matcher(text).matches();
    }

    @Override
    public SchemeString parse(Token token) {
        return new SchemeString(token.text.substring(1, token.text.length() - 1));
    }
}
