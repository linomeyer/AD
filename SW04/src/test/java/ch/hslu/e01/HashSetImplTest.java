package ch.hslu.e01;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HashSetImplTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Test
    void testAddElementsToHashSet() {
        HashSetImpl hashSet = new HashSetImpl();
        hashSet.add(15);
        hashSet.add(4);

        LOG.info(hashSet.toString());
        assertTrue(hashSet.contains(15));
        assertTrue(hashSet.contains(4));
    }

    @Test
    void testAddDifferentElementsWithEqualHashcode() {
        HashSetImpl hashSet = new HashSetImpl();
        hashSet.add(15);
        hashSet.add(5);

        LOG.info(hashSet.toString());
        assertTrue(hashSet.contains(5));
    }

    @Test
    void testAddDifferentElementsWithEqualHashCodeAndRemoveSaidElement() {
        HashSetImpl hashSet = new HashSetImpl();
        hashSet.add(18);
        hashSet.add(8);
        hashSet.remove(8);

        LOG.info(hashSet.toString());
        assertFalse(hashSet.contains(8));
    }

    @Test
    void testRemoveElementUsingRotationToFindIndexAndAddElement() {
        HashSetImpl hashSet = new HashSetImpl();
        hashSet.add(0);
        hashSet.add(1);
        hashSet.add(19);

        hashSet.add(18);
        hashSet.add(8);
        hashSet.remove(8);

        LOG.info(hashSet.toString());
        assertFalse(hashSet.contains(8));
    }

    @Test
    void testAddToFullHashset() {
        HashSetImpl hashSet = new HashSetImpl();
        hashSet.add(0);
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);
        hashSet.add(5);
        hashSet.add(6);
        hashSet.add(7);
        hashSet.add(8);
        hashSet.add(9);

        LOG.info(hashSet.toString());
        assertFalse(hashSet.add(5));
    }
}