package edu.lang.jscheme.interpretor;

import edu.lang.jscheme.data.SchemeValue;
import edu.lang.jscheme.data.SchemeTerm;

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
}
