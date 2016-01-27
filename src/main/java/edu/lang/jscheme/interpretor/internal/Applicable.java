package edu.lang.jscheme.interpretor.internal;

import edu.lang.jscheme.data.SchemeValue;
import edu.lang.jscheme.util.LinkedList;

@FunctionalInterface
public interface Applicable {
    SchemeValue apply(LinkedList<SchemeValue> arguments);
}
