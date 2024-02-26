package ch.hslu.e01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimplyLinkedListTest {
    SimplyLinkedList<Example> list;

    @BeforeEach
    void setUp() {
        list = new SimplyLinkedList<>();
    }

    @Test
    void testAddMultipleItemsCheckFirstItem() {
        list.addFirst(new Example(1));
        list.addFirst(new Example(4));
        list.addFirst(new Example(3));

        assertEquals(new Example(3), list.getFirst());
    }

    @Test
    void testListContains() {
        list.addFirst(new Example(7));
        list.addFirst(new Example(4));
        list.addFirst(new Example(3));

        assertTrue(list.contains(new Example(7)));
        assertTrue(list.contains(new Example(4)));
        assertTrue(list.contains(new Example(3)));
    }

    @Test
    void testPopItemIsNoLongerInList() {
        list.addFirst(new Example(9));
        list.addFirst(new Example(2));
        list.addFirst(new Example(5));

        assertEquals(new Example(5), list.pop());
        assertFalse(list.contains(new Example(5)));
    }

    @Test
    void testRemoveSpecificItemFromList() {
        list.addFirst(new Example(9));
        list.addFirst(new Example(2));
        list.addFirst(new Example(5));

        assertTrue(list.remove(new Example(2)));
        assertFalse(list.contains(new Example(2)));
        assertTrue(list.contains(new Example(9)));
        assertTrue(list.contains(new Example(5)));
    }
}