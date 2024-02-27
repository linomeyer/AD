package ch.hslu.e02;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StringStackImplTest {

    @Test
    void testNewStackIsEmpty() {
        StringStackImpl<String> stringStack = new StringStackImpl<>(String[].class, 5);
        assertTrue(stringStack.isEmpty());
    }

    @Test
    void testNewStackIsNotFull() {
        StringStackImpl<String> stringStack = new StringStackImpl<>(String[].class, 5);
        assertFalse(stringStack.isFull());
    }

    @Test
    void testStackSize1_push_checkIfFullAndNotEmpty() throws StackOverflowError {
        StringStackImpl<String> stringStack = new StringStackImpl<>(String[].class, 1);
        stringStack.push("A");
        assertTrue(stringStack.isFull());
        assertFalse(stringStack.isEmpty());
    }

    @Test
    void testStackSize1_pushAndpop_checkNotFull() {
        StringStackImpl<String> stringStack = new StringStackImpl<>(String[].class, 9);
        stringStack.push("A");
        stringStack.pop();
        assertFalse(stringStack.isFull());
    }

    @Test
    void testStackSize1_pushTwice_stackOverflowException() {
        StringStackImpl<String> stringStack = new StringStackImpl<>(String[].class, 1);
        stringStack.push("A");
        assertThrows(StackOverflowError.class, () -> stringStack.push("B"));
    }

    @Test
    void testStack_pop_emptyStackException() {
        StringStackImpl<String> stringStack = new StringStackImpl<>(String[].class, 5);

        assertThrows(EmptyStackException.class, stringStack::pop);
    }
}