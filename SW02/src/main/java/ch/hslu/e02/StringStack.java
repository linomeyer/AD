package ch.hslu.e02;

import java.util.EmptyStackException;

/**
 * This is a simple definition of a basic stack with basic functionality for Strings
 */
public interface StringStack<E> {
    E pop() throws EmptyStackException;

    E push(E element) throws StackOverflowError;

    boolean isFull();

    boolean isEmpty();
}
