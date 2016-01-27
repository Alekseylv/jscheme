package edu.lang.jscheme.parser;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import edu.lang.jscheme.data.SchemeNumber;

public class NumberParser extends Parser {
    public static final Pattern pattern = Pattern.compile("^\\d+(\\.\\d*)?$");

    @Override
    public boolean matches(String text) {
        return pattern.matcher(text).matches();
    }

    @Override
    public SchemeNumber parse(Token token) {
        try {
            return new SchemeNumber(new BigDecimal(token.text));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Can not parse [" + token.text + "] into a number");
        }
    }
}
