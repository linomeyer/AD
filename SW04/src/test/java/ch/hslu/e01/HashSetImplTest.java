package ch.hslu.e01;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class HashSetImplTest {
    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Test
    void testAddElementsToHashSet() {
        HashSetImpl hashSet = new HashSetImpl();
        hashSet.add(15);
        hashSet.add(4);

        LOG.info(hashSet.toString());
    }
}