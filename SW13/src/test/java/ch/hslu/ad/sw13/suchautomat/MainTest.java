package ch.hslu.ad.sw13.suchautomat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    @Test
    void testFindAnanasInString() {
        String inputString = "ananas".toUpperCase();
        String inputString2 = "xcvabantanananananananangananganasnanananananaskhlxioa".toUpperCase();

        int result = Main.stateSearch(inputString);
        int result2 = Main.stateSearch(inputString2);

        assertEquals(0, result);
        assertEquals(41, result2);
    }

    @Test
    void testCannotFindAnanasInString() {
        String inputString = "anasanasnananananananananans".toUpperCase();

        int result = Main.stateSearch(inputString);

        assertEquals(-1, result);
    }
}