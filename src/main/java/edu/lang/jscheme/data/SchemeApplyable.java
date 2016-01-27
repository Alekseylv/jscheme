package edu.lang.jscheme.data;

import edu.lang.jscheme.util.LinkedList;

public abstract class SchemeApplyable extends SchemeValue {

    public abstract SchemeValue apply(LinkedList<SchemeValue> arguments);
}
