package ch.hslu.e01;

/**
 * This interface is a simple definition of a basic iterator for a list implementation
 *
 * @param <E> any Object
 */
public interface ListIterator<E> {
    /**
     * Checks if the list has a next item
     *
     * @return true if it has
     */
    boolean hasNext();

    /**
     * @return the next item in the list or null if there isn't one
     */
    E next();

    /**
     * removes the current item in the list
     */
    void remove();
}
