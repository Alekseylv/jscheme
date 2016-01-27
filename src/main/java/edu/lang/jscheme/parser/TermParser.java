package edu.lang.jscheme.parser;

import edu.lang.jscheme.data.SchemeTerm;

import java.util.regex.Pattern;

import static edu.lang.jscheme.util.ErrorUtil.assertAtom;

public class TermParser extends Parser {

    private static final Pattern pattern = Pattern.compile("^((?!(\\)\\( )).)*$");

    @Override
    public boolean matches(String text) {
        return pattern.matcher(text).matches();
    }

    @Override
    public SchemeTerm parse(Token token) {
        assertAtom(token);
        return new SchemeTerm(token.text);
    }


}
