package edu.lang.jscheme.interpretor;

import java.util.Set;

import edu.lang.jscheme.data.SchemeTerm;
import edu.lang.jscheme.util.LinkedList;

public class SchemeEnvironment {

    public static final SchemeEnvironment EMPTY = new SchemeEnvironment(LinkedList.empty());

    public final LinkedList<EnvironmentBinding> bindings;

    public SchemeEnvironment(LinkedList<EnvironmentBinding> bindings) {
        this.bindings = bindings;
    }

    public SchemeEnvironment snapshot(Set<SchemeTerm> terms) {
        return new SchemeEnvironment(bindings.filter(x -> terms.contains(x.var)));
    }

    public SchemeEnvironment addBinding(EnvironmentBinding b) {
        return new SchemeEnvironment(bindings.add(b));
    }

    public SchemeEnvironment addBinding(LinkedList<EnvironmentBinding> b) {
        return new SchemeEnvironment(b.fold(bindings, LinkedList::add));
    }

}
