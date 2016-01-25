package edu.lang.jscheme.data;


import edu.lang.jscheme.util.LinkedList;

public class SchemeExpressionSequence extends SchemeExpression {

    public final LinkedList<SchemeExpression> expressions;

    public SchemeExpressionSequence(LinkedList<SchemeExpression> expressions) {
        this.expressions = expressions;
    }
}
