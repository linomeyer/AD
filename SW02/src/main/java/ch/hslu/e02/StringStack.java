package ch.hslu.e02;

import java.util.EmptyStackException;

/**
 * This is a simple definition of a basic stack with basic functionality for Strings
 */
public interface StringStack<E> {

    /**
     * Retrieve and remove the element that was pushed last
     *
     * @return the elements
     * @throws EmptyStackException when stack is already empty
     */
    E pop() throws EmptyStackException;

    /**
     * Adds an element on top of the stack
     *
     * @return the element
     * @throws StackOverflowError when stack is full
     */
    E push(E element) throws StackOverflowError;

    /**
     * @return true if the stack is full
     */
    boolean isFull();

    /**
     * @return true if the stack is empty
     */
    boolean isEmpty();
}
