package edu.lang.jscheme.parser;

import edu.lang.jscheme.data.SchemeExpression;

public abstract class Parser {
    public abstract boolean matches(String text);

    public abstract SchemeExpression parse(Token token);
}
