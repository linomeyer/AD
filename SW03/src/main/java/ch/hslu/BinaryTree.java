package ch.hslu;

public interface BinaryTree<E extends Comparable<E>> {
    boolean search(E item);

    void add(E item);

    boolean remove(E item);
}
