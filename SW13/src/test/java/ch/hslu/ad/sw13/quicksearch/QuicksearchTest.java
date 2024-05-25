package ch.hslu.ad.sw13.quicksearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuicksearchTest {

    @Test
    void testQuickSearch() {
        final String input = "asdfjkqeiubhasdfsjkjiuhuananaanannjkbnjnaskhhabjanashadfktqertiojklafnkjbhananasgafjnajbhl";
        final String input2 = "ananasnaanananasasdfsd";
        final String input3 = "adfajsdhfjkasdfnaskdjfhasdjkfhasdk";
        final String pattern = "ananas";

        int index = Quicksearch.quickSearch(input, pattern);
        int index2 = Quicksearch.quickSearch(input2, pattern);
        int index3 = Quicksearch.quickSearch(input3, pattern);

        assertEquals(74, index);
        assertEquals(0, index2);
        assertEquals(-1, index3);
    }

    @Test
    void testQuickSearchOpptimalMismatch() {
        final String input = "asdfjkqeiubhasdfsjkjiuhuananaanannjkbnjnaskhhabjanashadfktqertiojklafnkjbhananasgafjnajbhl";
        final String input2 = "ananasnaanananasasdfsd";
        final String input3 = "adfajsdhfjkasdfnaskdjfhasdjkfhasdk";
        final String pattern = "ananas";

        int index = Quicksearch.quickSearchOptimalMismatch(input, pattern);
        int index2 = Quicksearch.quickSearchOptimalMismatch(input2, pattern);
        int index3 = Quicksearch.quickSearchOptimalMismatch(input3, pattern);

        assertEquals(74, index);
        assertEquals(0, index2);
        assertEquals(-1, index3);
    }
}