package edu.lang.jscheme.data;

public abstract class SchemeDefinition extends SchemeExpression {

    public final SchemeTerm name;

    protected SchemeDefinition(SchemeTerm name) {
        this.name = name;
    }
}
