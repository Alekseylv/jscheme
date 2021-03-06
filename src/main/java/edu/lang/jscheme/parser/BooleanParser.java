package edu.lang.jscheme.parser;

import edu.lang.jscheme.data.SchemeBoolean;

public class BooleanParser extends Parser {
    @Override
    public boolean matches(String text) {
        return "#t".equals(text) || "#f".equals(text);
    }

    @Override
    public SchemeBoolean parse(Token token) {
        boolean value;
        if ("#t".equals(token.text)) {
            value = true;
        } else if ("#f".equals(token.text)) {
            value = false;
        } else {
            throw new IllegalArgumentException("Can not parse [" + token.text + "] into a boolean");
        }
        return new SchemeBoolean(value);
    }
}
