package edu.lang.jscheme.util;

public interface Castable {

    default <T> T as(Class<T> type) {
        return type.cast(this);
    }

    default <T> boolean is(Class<T> type) {
        return type.isAssignableFrom(this.getClass());
    }
}
