package ch.hslu.e01;

import ch.hslu.HashSet;

import java.util.Arrays;
import java.util.Iterator;

public class HashSetImpl implements HashSet {
    private final Integer[] entries;
    private final int size;

    public HashSetImpl() {
        size = 10;
        entries = new Integer[10];
    }

    @Override
    public boolean add(Integer number) {
        int index = getIndex(number);
        if (entries[index] == null || entries[index] == -1) {
            entries[index] = number;
            return true;
        } else if (!entries[index].equals(number)) {
            for (int i = index; i < size; i++) {
                if (entries[i] == null) {
                    entries[i] = number;
                    return true;
                }
            }
            // rotate to start of array and search up to index
            for (int i = 0; i < index; i++) {
                if (entries[i] == null) {
                    entries[i] = number;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean remove(Integer number) {
        int index = findIndex(number);
        if (index != -1) {
            entries[index] = -1; // tombstone
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Integer number) {
        return findIndex(number) != -1;
    }

    private int findIndex(Integer number) {
        int index = getIndex(number);
        if (entries[index].equals(number)) {
            return index;
        } else if (entries[index] != null || entries[index].equals(-1)) {
            for (int i = index; i < size; i++) {
                if (entries[i] == null) {
                    return -1;
                }
                if (entries[i].equals(number)) {
                    return i;
                }
            }
            // rotate to start of array and search up to index
            for (int i = 0; i < index; i++) {
                if (entries[i] == null) {
                    return -1;
                }
                if (entries[i].equals(number)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getIndex(Integer number) {
        return number.hashCode() % size;
    }

    @Override
    public Iterator<Integer> iterator() {
        return Arrays.stream(entries).iterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "HashSetImpl{" +
                "entries=" + Arrays.toString(entries) +
                '}';
    }
}