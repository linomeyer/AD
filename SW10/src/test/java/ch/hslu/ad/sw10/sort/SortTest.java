package ch.hslu.ad.sw10.sort;

import ch.hslu.ad.commons.RuntimeCalculator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortTest {
    public static final String QUICK_SORT_B = "Quick Sort B";
    private static final String QUICK_SORT_C = "Quick Sort C";
    private static final String QUICK_INSERTION_SORT = "Quick Insertion Sort";

    private static final int BITS_16 = 65_536;
    public static final int LENGTH = 10_000_000;

    private static char[] masterCharArray;
    private static Map<String, RuntimeCalculator> runtimeCalculatorMap;

    private char[] chars;

    @BeforeAll
    static void setUpBeforeClass() {
        masterCharArray = randomChars();
        runtimeCalculatorMap = new HashMap<>();
        runtimeCalculatorMap.put(QUICK_SORT_B, new RuntimeCalculator());
        runtimeCalculatorMap.put(QUICK_SORT_C, new RuntimeCalculator());
        runtimeCalculatorMap.put(QUICK_INSERTION_SORT, new RuntimeCalculator());
    }

    @BeforeEach
    void setUp() {
        chars = Arrays.copyOf(masterCharArray, masterCharArray.length);
    }

    @AfterAll
    static void tearDownAfterClass() {
        runtimeCalculatorMap.forEach((name, runtimeCalculation) -> {
            System.out.println("Runtime evaluation of: " + name + " on " + LENGTH + " elements\n");
            System.out.println(runtimeCalculation.toString());
        });
    }

    @RepeatedTest(value = 10)
    void testQuickSortB() {
        long startTime = System.currentTimeMillis();
        Sort.quickSortB(chars);
        long endTime = System.currentTimeMillis();
        runtimeCalculatorMap.get(QUICK_SORT_B).addTime(endTime - startTime);

        char[] expected = Arrays.copyOf(chars, chars.length);
        Arrays.sort(expected);
        assertArrayEquals(expected, chars);
    }

    @RepeatedTest(value = 10)
    void testQuickSortC() {
        long startTime = System.currentTimeMillis();
        Sort.quickSortC(chars);
        long endTime = System.currentTimeMillis();
        runtimeCalculatorMap.get(QUICK_SORT_C).addTime(endTime - startTime);

        char[] expected = Arrays.copyOf(chars, chars.length);
        Arrays.sort(expected);
        assertArrayEquals(expected, chars);
    }

    @RepeatedTest(value = 10)
    void testQuickInsertionSort() {
        long startTime = System.currentTimeMillis();
        Sort.quickInsertionSort(chars, 0, chars.length - 1);
        long endTime = System.currentTimeMillis();
        runtimeCalculatorMap.get(QUICK_INSERTION_SORT).addTime(endTime - startTime);

        char[] expected = Arrays.copyOf(chars, chars.length);
        Arrays.sort(expected);
        assertArrayEquals(expected, chars);
    }

    private static char[] randomChars() {
        SecureRandom secureRandom = new SecureRandom();
        char[] chars = new char[SortTest.LENGTH];
        for (int i = 0; i < SortTest.LENGTH; i++) {
            chars[i] = (char) (secureRandom.nextInt(SortTest.LENGTH) % BITS_16);
        }
        return chars;
    }
}