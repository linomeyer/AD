package ch.hslu.e02;

public interface BinaryTree<E extends Comparable<E>> {
    boolean search(E item);

    void add(E item);

    boolean remove(E item);
}
