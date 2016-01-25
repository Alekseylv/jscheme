package edu.lang.jscheme.parser;

import edu.lang.jscheme.Token;
import edu.lang.jscheme.data.SchemeString;
import edu.lang.jscheme.data.SchemeTerm;

import java.util.regex.Pattern;

public class StringParser extends TermParser {
    private static Pattern pattern = Pattern.compile("^\\\"((?!\\\").)*\\\"$");

    @Override
    public boolean matches(String text) {
        return pattern.matcher(text).matches();
    }

    @Override
    public SchemeTerm parse(Token token) {
        return new SchemeString(token.text.substring(1, token.text.length() - 1));
    }
}
