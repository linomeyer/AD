package ch.hslu.e03;

import ch.hslu.HashSet;

import java.util.Arrays;
import java.util.Iterator;

public class BucketHashSet implements HashSet {
    private final SimplyLinkedList<Integer>[] entries;
    private final int size;

    @SuppressWarnings("unchecked")
    public BucketHashSet() {
        size = 10;
        entries = (SimplyLinkedList<Integer>[]) new SimplyLinkedList[size];
    }

    @Override
    public boolean add(Integer number) {
        int index = getIndex(number);
        if (entries[index] == null) {
            SimplyLinkedList<Integer> entryList = new SimplyLinkedList<>();
            entryList.addFirst(number);
            entries[index] = entryList;
        } else {
            entries[index].addFirst(number);
        }
        return true;
    }

    @Override
    public boolean remove(Integer number) {
        boolean removed = false;
        int index = getIndex(number);
        SimplyLinkedList<Integer> entryList = entries[index];
        if (entryList != null) {
            removed = entryList.remove(number);
        }
        return removed;
    }

    @Override
    public boolean contains(Integer number) {
        boolean contains = false;
        int index = getIndex(number);
        SimplyLinkedList<Integer> entryList = entries[index];
        if (entryList != null) {
            contains = entryList.contains(number);
        }
        return contains;
    }

    @Override
    public Iterator<Integer> iterator() {
        SimplyLinkedList<Integer> flattenedHashSetAsList = new SimplyLinkedList<>();
        for (int i = 0; i < size; i++) {
            if (entries[i] != null) {
                ListIterator<Integer> iterator = entries[i].iterator();
                while (iterator.hasNext()) {
                    flattenedHashSetAsList.addFirst(iterator.next());
                }
            }
        }
        return flattenedHashSetAsList.iterator();
    }

    @Override
    public int size() {
        return size;
    }

    private int getIndex(Integer number) {
        return number.hashCode() % size;
    }

    @Override
    public String toString() {
        return "BucketHashSet{" +
                "entries=" + Arrays.toString(entries) +
                '}';
    }
}
