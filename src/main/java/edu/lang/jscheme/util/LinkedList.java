package edu.lang.jscheme.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class LinkedList<T> implements Iterable<T> {


    public abstract T head();

    public abstract LinkedList<T> tail();

    public abstract boolean isEmpty();

    public abstract <R> R fold(R r, BiFunction<R, T, R> f);

    public abstract <R> LinkedList<R> map(Function<T,R> f);

    public LinkedList<T> reverse() {
        return fold((LinkedList<T>) new Null<T>(), LinkedList::add);
    }

    public static <T> LinkedList<T> empty() {
        return new Null<>();
    }

    public LinkedList<T> add(T t) {
        return new Cons<>(t, this);
    }

    public Iterator<T> iterator() {
        return new ListIterator<>(this);
    }

    public static class Cons<T> extends LinkedList<T> {
        public final T head;
        public final LinkedList<T> tail;

        public Cons(T head, LinkedList<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public T head() {
            return head;
        }

        @Override
        public LinkedList<T> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public <R> R fold(R r, BiFunction<R, T, R> f) {
            return tail.fold(f.apply(r, head), f);
        }

        @Override
        public <R> LinkedList<R> map(Function<T, R> f) {
            return new Cons<>(f.apply(head), tail.map(f));
        }

        @Override
        public String toString() {
            return "LinkedList(" + tail.fold(new StringBuilder().append(head), StringBuilder::append).append(")").toString();
        }

    }

    public static class Null<T> extends LinkedList<T> {

        @Override
        public T head() {
            throw new NoSuchElementException();
        }

        @Override
        public LinkedList<T> tail() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public <R> R fold(R r, BiFunction<R, T, R> f) {
            return r;
        }

        @Override
        public <R> LinkedList<R> map(Function<T, R> f) {
            return LinkedList.empty();
        }

        @Override
        public String toString() {
            return "Null";
        }
    }

    private static class ListIterator<T> implements Iterator<T> {
        private LinkedList<T> list;

        public ListIterator(LinkedList<T> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return !list.isEmpty();
        }

        @Override
        public T next() {
            LinkedList<T> old = list;
            list = list.tail();
            return old.head();
        }
    }
}
