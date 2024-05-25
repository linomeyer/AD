package ch.hslu.ad.sw13.kmp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KmpTest {

    @Test
    void testInitNext() {
        int[] ananas = Kmp.initNext("ANANAS");
        assertArrayEquals(new int[]{-1, 0, 0, 1, 2, 3}, ananas);
    }

    @Test
    void testKmpSearch() {
        int index = Kmp.kmpSearch("ANANANDFADSFJANASANANANSSDFAKANANASFHFASKDJFWO", "ANANAS");
        int index2 = Kmp.kmpSearch("EISBIESANJAEIKALJEISBENASDKFHUEISFNJAEISBEINASKKJNEN", "EISBEIN");

        assertEquals(29, index);
        assertEquals(37, index2);
    }

}