package edu.lang.jscheme.data;

import edu.lang.jscheme.interpretor.EnvironmentBinding;
import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.interpretor.internal.SchemeApplicable;
import edu.lang.jscheme.util.LinkedList;

public class SchemeClosure extends SchemeApplicable {

    public final SchemeTerm name;
    public final SchemeEnvironment environment;
    public final LinkedList<SchemeTerm> arguments;
    public final SchemeExpression body;

    public SchemeClosure(SchemeTerm name, SchemeEnvironment environment,
            LinkedList<SchemeTerm> arguments, SchemeExpression body) {
        this.name = name;
        this.environment = environment;
        this.arguments = arguments;
        this.body = body;
    }

    @Override
    public SchemeValue apply(LinkedList<SchemeValue> arguments) {
        return body.eval(environment.addBinding(this.arguments.zip(arguments, EnvironmentBinding::binding)));
    }

    @Override
    public String toString() {
        return "<closure> " + name.name;
    }
}
