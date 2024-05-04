package ch.hslu.ad.sw11.mergesort;

import ch.hslu.ad.commons.Randomizer;
import ch.hslu.ad.commons.RuntimeCalculator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DemoMergesortTest {

    public static final int LENGTH = 100_000_000;
    private static int[] originalArray;
    private static final Map<String, RuntimeCalculator> RUNTIME_CALCULATORS = new HashMap<>();
    private static int[] sortedArray;

    public static final String PARALLEL_MERGE_SORT = "Parallel MergeSort";
    public static final String SEQUENTIAL_MERGE_SORT = "Sequential MergeSort";

    private int[] array;

    @BeforeAll
    static void beforeAll() {
        originalArray = Randomizer.randomIntArray(LENGTH);
        sortedArray = Arrays.copyOf(originalArray, originalArray.length);
        Arrays.sort(sortedArray);

        RUNTIME_CALCULATORS.put(PARALLEL_MERGE_SORT, new RuntimeCalculator());
        RUNTIME_CALCULATORS.put(SEQUENTIAL_MERGE_SORT, new RuntimeCalculator());
    }

    @BeforeEach
    void setUp() {
        array = Arrays.copyOf(originalArray, originalArray.length);
    }

    @RepeatedTest(10)
    void testParallelMergeSort() {
        long startTime = System.currentTimeMillis();
        DemoMergesort.demo(array);
        long endTime = System.currentTimeMillis();
        RUNTIME_CALCULATORS.get(PARALLEL_MERGE_SORT).addTime(endTime - startTime);
        assertArrayEquals(sortedArray, array);
    }

    @RepeatedTest(10)
    void testSequentialMergeSort() {
        long startTime = System.currentTimeMillis();
        MergesortRecursive.mergeSort(array);
        long endTime = System.currentTimeMillis();
        RUNTIME_CALCULATORS.get(SEQUENTIAL_MERGE_SORT).addTime(endTime - startTime);
        assertArrayEquals(sortedArray, array);
    }

    @AfterAll
    static void afterAll() {
        RUNTIME_CALCULATORS.forEach((name, runtimeCalculation) -> {
            System.out.println("Runtime evaluation of: " + name + " on " + LENGTH + " elements\n");
            System.out.println(runtimeCalculation.toString());
        });
    }
}