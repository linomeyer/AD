package ch.hslu.ad.sw10.heap;

public class FixedSizeIntegerMaxHeap implements IntegerHeap {
    private final int[] heap;
    private int size;

    public FixedSizeIntegerMaxHeap(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        size = 0;
        heap = new int[length];
    }

    @Override
    public int remove() {
        if (size > 0) {
            int removedElement = heap[0];
            heap[0] = heap[--size];
            heap[size] = 0;
            maxHeapify(0);

            return removedElement;
        } else {
            throw new ArrayIndexOutOfBoundsException("Heap is empty");
        }
    }

    @Override
    public void insert(int element) throws ArrayIndexOutOfBoundsException {
        if (size < heap.length) {
            heap[size] = element;

            int cursor = size;
            while (heap[cursor] > heap[parent(cursor)]) {
                swap(cursor, parent(cursor));
                cursor = parent(cursor);
            }
            size++;
        } else {
            throw new ArrayIndexOutOfBoundsException("Heap is full");
        }

    }

    @Override
    public void heapify() {
        maxHeapify(0);
    }

    private void swap(int child, int parent) {
        int temp = heap[child];
        heap[child] = heap[parent];
        heap[parent] = temp;
    }

    private void maxHeapify(int pos) {
        if (isLeaf(pos))
            return;
        if (heap[pos] < heap[leftChild(pos)] || heap[pos] < heap[rightChild(pos)]) {
            if (heap[leftChild(pos)] > heap[rightChild(pos)]) {
                swap(pos, leftChild(pos));
                maxHeapify(leftChild(pos));
            } else {
                swap(pos, rightChild(pos));
                maxHeapify(rightChild(pos));
            }
        }
    }

    public int[] getHeap() {
        return heap;
    }

    private int rightChild(int pos) {
        return (2 * pos) + 2;
    }

    private int leftChild(int pos) {
        return (2 * pos) + 1;
    }

    private int parent(int pos) {
        return (pos - 1) / 2;
    }

    private boolean isLeaf(int pos) {
        return pos >= (size / 2) && pos <= size;
    }
}
