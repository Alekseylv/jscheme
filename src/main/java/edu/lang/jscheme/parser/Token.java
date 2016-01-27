package edu.lang.jscheme.parser;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SIMPLE_STYLE;

public class Token {
    public final String text;
    public final int position;

    public Token(String text, int position) {
        this.text = text;
        this.position = position;
    }

    @Override
    public String toString() {
        return reflectionToString(this, SIMPLE_STYLE);
    }
}
