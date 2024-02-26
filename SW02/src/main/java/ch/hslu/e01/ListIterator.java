package ch.hslu.e01;

public interface ListIterator<E> {
    boolean hasNext();

    E next();

    void remove();
}
