package ch.hslu.e01;

import java.util.Iterator;

/**
 * This is a simple Hashset implementation using probing to handle collisions. It supports only positive Integers
 */
public interface HashSet {
    /**
     * Tries to add a number to the hashset
     *
     * @param number positive integer number
     * @return false if the Hashset is full
     */
    boolean add(Integer number);

    /**
     * Tries to remove a number, sets a tombstone (-1) in place of the number
     *
     * @param number
     * @return false if, even after probing, the number was not found
     */
    boolean remove(Integer number);

    /**
     * Searches for the given number
     *
     * @param number
     * @return false if, even after probing, the number was not found
     */
    boolean contains(Integer number);

    /**
     * @return iterator over Hashset
     */
    Iterator<Integer> iterator();

    /**
     * @return size of Hashset
     */
    int size();
}
