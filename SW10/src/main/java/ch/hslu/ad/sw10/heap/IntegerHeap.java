package ch.hslu.ad.sw10.heap;

public interface IntegerHeap {
    int remove();

    void insert(int element);

    void heapify();
}
