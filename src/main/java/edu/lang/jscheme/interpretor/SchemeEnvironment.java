package edu.lang.jscheme.interpretor;

import static edu.lang.jscheme.interpretor.EnvironmentBinding.binding;
import static edu.lang.jscheme.interpretor.internal.BooleanApplicable.boolApplicable;
import static edu.lang.jscheme.interpretor.internal.SchemeApplicable.binaryApplicable;
import static edu.lang.jscheme.interpretor.internal.SchemeApplicable.numApplicable;
import static edu.lang.jscheme.interpretor.internal.SchemeApplicable.numberTest;
import static edu.lang.jscheme.interpretor.internal.SchemeApplicable.typeCheck;
import static edu.lang.jscheme.interpretor.internal.SchemeApplicable.unaryApplicable;

import java.math.BigDecimal;
import java.util.Set;

import edu.lang.jscheme.data.SchemeBoolean;
import edu.lang.jscheme.data.SchemeCons;
import edu.lang.jscheme.data.SchemeNumber;
import edu.lang.jscheme.data.SchemeString;
import edu.lang.jscheme.data.SchemeTerm;
import edu.lang.jscheme.data.SchemeUnit;
import edu.lang.jscheme.util.LinkedList;

public class SchemeEnvironment {

    public static final SchemeEnvironment EMPTY = new SchemeEnvironment(LinkedList.empty())
            .addBinding(binding("+", numApplicable(BigDecimal::add)))
            .addBinding(binding("-", numApplicable(BigDecimal::subtract)))
            .addBinding(binding("*", numApplicable(BigDecimal::multiply)))
            .addBinding(binding("/", numApplicable(BigDecimal::divide)))
            .addBinding(binding(">", numberTest((x, y) -> x.compareTo(y) == -1)))
            .addBinding(binding("<", numberTest((x, y) -> x.compareTo(y) == 1)))
            .addBinding(binding("=", numberTest((x, y) -> x.compareTo(y) == 0)))
            .addBinding(binding("and", boolApplicable((x, y) -> x && y)))
            .addBinding(binding("or", boolApplicable((x, y) -> x || y)))
            .addBinding(binding("not", unaryApplicable(x -> new SchemeBoolean(!x.as(SchemeBoolean.class).value))))
            .addBinding(binding("cons", binaryApplicable(SchemeCons::new)))
            .addBinding(binding("cons?", typeCheck(SchemeCons.class)))
            .addBinding(binding("null?", typeCheck(SchemeUnit.class)))
            .addBinding(binding("number?", typeCheck(SchemeNumber.class)))
            .addBinding(binding("bool?", typeCheck(SchemeBoolean.class)))
            .addBinding(binding("string?", typeCheck(SchemeString.class)))
            .addBinding(binding("car", unaryApplicable(x -> x.as(SchemeCons.class).head)))
            .addBinding(binding("cdr", unaryApplicable(x -> x.as(SchemeCons.class).tail)))
            .addBinding(binding("print", unaryApplicable(x -> {
                System.out.println(x.toSchemeString().string);
                return SchemeUnit.getInstance();
            })));


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
