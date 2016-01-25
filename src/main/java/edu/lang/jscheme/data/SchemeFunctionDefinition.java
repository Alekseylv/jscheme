package edu.lang.jscheme.data;

import edu.lang.jscheme.util.LinkedList;

public class SchemeFunctionDefinition extends SchemeExpression {

    public final String name;
    public final LinkedList<String> argNames;
    public final SchemeExpression body;

    public SchemeFunctionDefinition(String name, LinkedList<String> argNames, SchemeExpression body) {
        this.name = name;
        this.argNames = argNames;
        this.body = body;
    }
}
