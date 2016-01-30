package edu.lang.jscheme.interpretor.internal;

import edu.lang.jscheme.data.SchemeValue;
import edu.lang.jscheme.util.LinkedList;

@FunctionalInterface
public interface Applicable {
    SchemeContinuation apply(LinkedList<SchemeValue> arguments);
}
