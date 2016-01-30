package edu.lang.jscheme.data;

import static edu.lang.jscheme.interpretor.EnvironmentBinding.binding;

import edu.lang.jscheme.interpretor.EnvironmentBinding;
import edu.lang.jscheme.interpretor.SchemeEnvironment;
import edu.lang.jscheme.interpretor.internal.SchemeApplicable;
import edu.lang.jscheme.util.LinkedList;

public class SchemeClosure extends SchemeApplicable {

    public final SchemeTerm term;
    public final SchemeEnvironment environment;
    public final LinkedList<SchemeTerm> arguments;
    public final SchemeExpression body;

    public SchemeClosure(SchemeTerm term, SchemeEnvironment environment,
            LinkedList<SchemeTerm> arguments, SchemeExpression body) {
        this.term = term;
        this.environment = environment;
        this.arguments = arguments;
        this.body = body;
    }

    @Override
    public SchemeValue apply(LinkedList<SchemeValue> arguments) {
        SchemeEnvironment env = environment.addBinding(this.arguments.zip(arguments, EnvironmentBinding::binding));
        if (term != null) {
            env = env.addBinding(binding(term, this));
        }
        return body.eval(env);

    }

    @Override
    public SchemeString toSchemeString() {
        return new SchemeString("<closure> " + term.name);
    }
}
