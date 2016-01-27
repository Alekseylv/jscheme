package edu.lang.jscheme.interpretor;

import static edu.lang.jscheme.interpretor.EnvironmentBinding.binding;
import static edu.lang.jscheme.interpretor.internal.BooleanApplicable.boolApplicable;
import static edu.lang.jscheme.interpretor.internal.NumberApplicable.numApplicable;
import static edu.lang.jscheme.interpretor.internal.SchemeApplicable.unaryApplicable;

import java.math.BigDecimal;
import java.util.Set;

import edu.lang.jscheme.data.SchemeBoolean;
import edu.lang.jscheme.data.SchemeTerm;
import edu.lang.jscheme.util.LinkedList;

public class SchemeEnvironment {

    public static final SchemeEnvironment EMPTY = new SchemeEnvironment(LinkedList.empty())
            .addBinding(binding("+", numApplicable(BigDecimal::add)))
            .addBinding(binding("-", numApplicable(BigDecimal::subtract)))
            .addBinding(binding("*", numApplicable(BigDecimal::multiply)))
            .addBinding(binding("/", numApplicable(BigDecimal::divide)))
            .addBinding(binding("and", boolApplicable((x, y) -> x && y)))
            .addBinding(binding("or", boolApplicable((x, y) -> x || y)))
            .addBinding(binding("not", unaryApplicable(x -> new SchemeBoolean(!x.as(SchemeBoolean.class).value))));

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
