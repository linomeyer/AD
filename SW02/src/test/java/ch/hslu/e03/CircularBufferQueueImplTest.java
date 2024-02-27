package ch.hslu.e03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CircularBufferQueueImplTest {
    @Test
    void testCreateQueue_poll_noElementToPoll() {
        CircularBufferQueue<String> queue = new CircularBufferQueueImpl<>(String[].class, 1);

        String element = queue.poll();

        assertNull(element);
    }

    @Test
    void testCreateQueue_offerAndPoll_polledElement() {
        CircularBufferQueue<String> queue = new CircularBufferQueueImpl<>(String[].class, 1);

        queue.offer("abc");
        String element = queue.poll();

        assertEquals("abc", element);
    }

    @Test
    void testCreateQueue_offerAndPoll_queueIsFull() {
        CircularBufferQueue<String> queue = new CircularBufferQueueImpl<>(String[].class, 1);

        queue.offer("A");
        boolean isNotFull = queue.offer("B");

        assertFalse(isNotFull);
    }

    @Test
    void testCreateQueue_offerAndPoll_queueIsEmpty() {
        CircularBufferQueue<String> queue = new CircularBufferQueueImpl<>(String[].class, 3);

        queue.offer("A");

        String element1 = queue.poll();
        String element2 = queue.poll();

        assertEquals("A", element1);
        assertNull(element2);
    }
}