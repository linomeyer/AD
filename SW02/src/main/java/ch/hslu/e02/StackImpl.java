package ch.hslu.e02;

import java.util.EmptyStackException;

public class StackImpl<E> implements Stack<E> {
    private E[] stack;
    private int currentIndex;

    @SuppressWarnings("unchecked")
    public StackImpl(Class<E[]> clazz, int size) {
        stack = (E[]) new Object[size];
        currentIndex = 0;
    }


    @Override
    public E pop() throws EmptyStackException {
        if (stack[currentIndex] == null) {
            throw new EmptyStackException();
        }
        E removedItem = stack[currentIndex];
        stack[currentIndex] = null;
        if (currentIndex != 0) {
            currentIndex--;
        }

        return removedItem;
    }

    @Override
    public E push(E element) throws StackOverflowError {
        if (stack[currentIndex] != null) {
            if (currentIndex == stack.length - 1) {
                throw new StackOverflowError("Stack is already full");
            }
            currentIndex++;
        }
        stack[currentIndex] = element;
        return element;
    }

    @Override
    public boolean isFull() {
        return stack[stack.length - 1] != null;
    }

    @Override
    public boolean isEmpty() {
        return stack[0] == null;
    }

    public int getSize() {
        return stack.length;
    }
}
