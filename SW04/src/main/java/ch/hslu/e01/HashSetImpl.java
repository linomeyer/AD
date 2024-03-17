package ch.hslu.e01;

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
        int index = number.hashCode() % size;
        if (entries[index] == null) {
            entries[index] = number;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Integer number) {
        return false;
    }

    @Override
    public boolean contains(Integer number) {
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
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