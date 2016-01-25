package edu.lang.jscheme.data;

public class SchemeVariableDefinition extends SchemeExpression {

    public final String name;
    public final SchemeExpression value;

    public SchemeVariableDefinition(String name, SchemeExpression value) {
        this.name = name;
        this.value = value;
    }
}
