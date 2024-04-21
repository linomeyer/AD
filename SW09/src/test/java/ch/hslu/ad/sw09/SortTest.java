package ch.hslu.ad.sw09;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortTest {
    private static Item[] itemsRandomlyGenerated100k;
    private static Item[] itemsInverseSorted100k;
    private static Item[] itemsSorted100k;
    private Item[] itemsRandomlyGenerated100kCopy;
    private Item[] itemsInverseSorted100kCopy;
    private Item[] itemsSorted100kCopy;

    @BeforeAll
    static void setUp() throws Exception {
        itemsRandomlyGenerated100k = readRandomlySortedMasterArray();
        itemsInverseSorted100k = generateInverseSortedMasterArray();
        itemsSorted100k = generateSortedMasterArray();
    }

    @BeforeEach
    void setUpEach() throws Exception {
        itemsRandomlyGenerated100kCopy = Arrays.copyOf(itemsRandomlyGenerated100k, itemsRandomlyGenerated100k.length);
        itemsInverseSorted100kCopy = Arrays.copyOf(itemsInverseSorted100k, itemsInverseSorted100k.length);
        itemsSorted100kCopy = Arrays.copyOf(itemsSorted100k, itemsSorted100k.length);
    }

    private static Item[] readRandomlySortedMasterArray() throws Exception {
        InputStream inputStream = Objects.requireNonNull(SortTest.class.getResourceAsStream("array_100k.txt"));
        String outputString = IOUtils.toString(inputStream, "UTF-8");

        return Arrays.stream(outputString.split(", ")).map(numStr ->
                new Item(Integer.parseInt(numStr))
        ).toArray(Item[]::new);
    }

    private static Item[] generateInverseSortedMasterArray() throws Exception {
        Item[] items = new Item[100_000];
        int counter = 100_000;
        for (int i = 0; i < 100_000; i++) {
            items[i] = new Item(counter--);
        }
        return items;
    }

    private static Item[] generateSortedMasterArray() throws Exception {
        Item[] items = new Item[100_000];
        for (int i = 0; i < 100_000; i++) {
            items[i] = new Item(i);
        }
        return items;
    }


    @Test
    void testInsertionSortOnRandomArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.insertionSort(itemsRandomlyGenerated100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Insertion Sort took " + (endTime - startTime) + "ms on a randomly sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testSelectionSortOnRandomArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.selectionSort(itemsRandomlyGenerated100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Selection Sort took " + (endTime - startTime) + "ms on a randomly sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testBinaryInsertionSortOnRandomArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.binaryInsertionSort(itemsRandomlyGenerated100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Binary Insertion Sort took " + (endTime - startTime) + "ms on a randomly sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testBubbleSortOnRandomArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.bubbleSort(itemsRandomlyGenerated100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Bubble Sort took " + (endTime - startTime) + "ms on a randomly sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testShellSortOnRandomArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.shellSort(itemsRandomlyGenerated100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Shell Sort took " + (endTime - startTime) + "ms on a randomly sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testInsertionSortOnInverseSortedArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.insertionSort(itemsInverseSorted100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Insertion Sort took " + (endTime - startTime) + "ms on an inverse sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testSelectionSortOnInverseSortedArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.selectionSort(itemsInverseSorted100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Selection Sort took " + (endTime - startTime) + "ms on an inverse sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testBinaryInsertionSortOnInverseSortedArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.binaryInsertionSort(itemsInverseSorted100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Binary Insertion Sort took " + (endTime - startTime) + "ms on an inverse sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testBubbleSortOnInverseSortedArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.bubbleSort(itemsInverseSorted100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Bubble Sort took " + (endTime - startTime) + "ms on an inverse sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testShellSortOnInverseSortedArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.shellSort(itemsInverseSorted100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Shell Sort took " + (endTime - startTime) + "ms on an inverse sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }


    @Test
    void testInsertionSortOnSortedArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.insertionSort(itemsSorted100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Insertion Sort took " + (endTime - startTime) + "ms on a sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testSelectionSortOnSortedArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.selectionSort(itemsSorted100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Selection Sort took " + (endTime - startTime) + "ms on a sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testBinaryInsertionSortOnSortedArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.binaryInsertionSort(itemsSorted100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Binary Insertion Sort took " + (endTime - startTime) + "ms on a sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testBubbleSortOnSortedArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.bubbleSort(itemsSorted100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Bubble Sort took " + (endTime - startTime) + "ms on a sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }

    @Test
    void testShellSortOnSortedArray100k() {
        long startTime = System.currentTimeMillis();

        Item[] result = Sort.shellSort(itemsSorted100kCopy);

        long endTime = System.currentTimeMillis();

        System.out.println("Shell Sort took " + (endTime - startTime) + "ms on a sorted array");
        Item[] expectedResult = Arrays.stream(Arrays.copyOf(result, result.length)).sorted().toArray(Item[]::new);
        assertArrayEquals(Arrays.stream(expectedResult).sorted().toArray(), result);
    }
}