package edu.lang.jscheme.parser;

import edu.lang.jscheme.Token;
import edu.lang.jscheme.data.SchemeBoolean;
import edu.lang.jscheme.data.SchemeTerm;

public class BooleanParser extends TermParser {
    @Override
    public boolean matches(String text) {
        return "#t".equals(text) || "#f".equals(text);
    }

    @Override
    public SchemeTerm parse(Token token) {
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
