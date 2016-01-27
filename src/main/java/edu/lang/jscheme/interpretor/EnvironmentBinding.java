package edu.lang.jscheme.interpretor;

import edu.lang.jscheme.data.SchemeTerm;
import edu.lang.jscheme.data.SchemeValue;

public class EnvironmentBinding {

    public final SchemeTerm var;
    public final SchemeValue value;

    public EnvironmentBinding(SchemeTerm var, SchemeValue value) {
        this.var = var;
        this.value = value;
    }

    public static EnvironmentBinding binding(SchemeTerm var, SchemeValue value) {
        return new EnvironmentBinding(var, value);
    }

    public static EnvironmentBinding binding(String var, SchemeValue value) {
        return new EnvironmentBinding(new SchemeTerm(var), value);
    }
}
