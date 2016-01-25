package edu.lang.jscheme.data;

import edu.lang.jscheme.util.LinkedList;

public class SchemeFunctionApplication extends SchemeExpression {

    public final LinkedList<SchemeExpression> args;

    public SchemeFunctionApplication(LinkedList<SchemeExpression> args) {
        this.args = args;
    }
}
