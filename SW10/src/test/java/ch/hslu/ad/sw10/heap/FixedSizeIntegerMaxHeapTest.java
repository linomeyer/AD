package ch.hslu.ad.sw10.heap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FixedSizeIntegerMaxHeapTest {
    @Test
    void testInsertToHeap() {
        FixedSizeIntegerMaxHeap heap = createHeap();

        System.out.println(Arrays.toString(heap.getHeap()));
        int[] expected = {80, 40, 60, 10, 7, 3, 20, 5, 3, 3, 3};
        assertArrayEquals(expected, heap.getHeap());
    }

    @Test
    void testInsertToHeapOverLimit() {
        FixedSizeIntegerMaxHeap heap = new FixedSizeIntegerMaxHeap(3);
        heap.insert(10);
        heap.insert(20);
        heap.insert(3);

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> heap.insert(5));
    }

    @Test
    void testRemoveFromHeap() {
        FixedSizeIntegerMaxHeap heap = createHeap();

        heap.remove();
        heap.remove();
        heap.remove();

        System.out.println(Arrays.toString(heap.getHeap()));
        int[] expected = {20, 10, 3, 5, 7, 3, 3, 3, 0, 0, 0};
        assertArrayEquals(expected, heap.getHeap());
    }

    @Test
    void testRemoveFromHeapOverLimit() {
        FixedSizeIntegerMaxHeap heap = new FixedSizeIntegerMaxHeap(3);
        heap.insert(10);
        heap.insert(20);
        heap.insert(3);

        heap.remove();
        heap.remove();
        heap.remove();

        assertThrows(ArrayIndexOutOfBoundsException.class, heap::remove);
    }

    private FixedSizeIntegerMaxHeap createHeap() {
        FixedSizeIntegerMaxHeap heap = new FixedSizeIntegerMaxHeap(11);
        heap.insert(10);
        heap.insert(20);
        heap.insert(3);
        heap.insert(5);
        heap.insert(7);
        heap.insert(80);
        heap.insert(60);
        heap.insert(40);
        heap.insert(3);
        heap.insert(3);
        heap.insert(3);
        return heap;
    }
}