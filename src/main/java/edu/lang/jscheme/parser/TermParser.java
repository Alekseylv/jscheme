package edu.lang.jscheme.parser;

import edu.lang.jscheme.Token;
import edu.lang.jscheme.data.SchemeTerm;

public abstract class TermParser {
    public abstract boolean matches(String text);

    public abstract SchemeTerm parse(Token token);
}
