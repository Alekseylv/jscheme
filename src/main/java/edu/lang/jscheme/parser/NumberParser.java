package edu.lang.jscheme.parser;

import edu.lang.jscheme.data.SchemeExpression;
import edu.lang.jscheme.data.SchemeNumber;
import edu.lang.jscheme.data.SchemeTerm;

import java.util.regex.Pattern;

public class NumberParser extends Parser {
    public static final Pattern pattern = Pattern.compile("^\\d{1,32}$");

    @Override
    public boolean matches(String text) {
        return pattern.matcher(text).matches();
    }

    @Override
    public SchemeNumber parse(Token token) {
        try {
            return new SchemeNumber(Integer.parseInt(token.text));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Can not parse [" + token.text + "] into a number");
        }
    }
}
