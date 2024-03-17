package ch.hslu.e01;

import java.util.Iterator;

public interface HashSet {
    boolean add(Integer number);

    boolean remove(Integer number);

    boolean contains(Integer number);

    Iterator<Integer> iterator();

    int size();
}
