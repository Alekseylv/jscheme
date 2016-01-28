package edu.lang.jscheme.parser;

public class Token {
    public final String text;
    public final int position;

    public Token(String text, int position) {
        this.text = text;
        this.position = position;
    }

    @Override
    public String toString() {
        return "(" + text + "," + position + ")";
    }
}
