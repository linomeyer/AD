package ch.hslu.ad.sw11.quicksort;

import ch.hslu.ad.commons.Randomizer;
import ch.hslu.ad.commons.RuntimeCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuicksortTest {
    public static final int LENGTH = 100_000_000;
    private static int[] originalArray;
    private static int[] sortedArray;
    private static final Map<String, RuntimeCalculator> RUNTIME_CALCULATORS = new HashMap<>();

    public static final String PARALLEL_QUICKSORT = "Parallel QuickSort";
    public static final String SEQUENTIAL_QUICKSORT = "Sequential QuickSort";
    public static final String JAVA_QUICKSORT = "Java Sort";

    private int[] array;

    @BeforeAll
    static void beforeAll() {
        originalArray = Randomizer.randomIntArray(LENGTH);
        sortedArray = Arrays.copyOf(originalArray, originalArray.length);
        Arrays.sort(sortedArray);

        RUNTIME_CALCULATORS.put(PARALLEL_QUICKSORT, new RuntimeCalculator());
        RUNTIME_CALCULATORS.put(SEQUENTIAL_QUICKSORT, new RuntimeCalculator());
        RUNTIME_CALCULATORS.put(JAVA_QUICKSORT, new RuntimeCalculator());
    }

    @BeforeEach
    void setUp() {
        array = Arrays.copyOf(originalArray, originalArray.length);
    }

    @RepeatedTest(10)
    void testParallelQuickSort() {
        long startTime = System.currentTimeMillis();
        Quicksort.quickSortParallel(array);
        long endTime = System.currentTimeMillis();
        RUNTIME_CALCULATORS.get(PARALLEL_QUICKSORT).addTime(endTime - startTime);
        assertArrayEquals(sortedArray, array);
    }

    @RepeatedTest(10)
    void testSequentialQuickSort() {
        long startTime = System.currentTimeMillis();
        Quicksort.quicksortSequential(array, 0, array.length - 1);
        long endTime = System.currentTimeMillis();
        RUNTIME_CALCULATORS.get(SEQUENTIAL_QUICKSORT).addTime(endTime - startTime);
        assertArrayEquals(sortedArray, array);
    }

    @RepeatedTest(10)
    void testJavaSort() {
        long startTime = System.currentTimeMillis();
        Arrays.sort(array);
        long endTime = System.currentTimeMillis();
        RUNTIME_CALCULATORS.get(JAVA_QUICKSORT).addTime(endTime - startTime);
        assertArrayEquals(sortedArray, array);
    }
}