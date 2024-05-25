package ch.hslu.ad.sw13;

import ch.hslu.ad.commons.RuntimeCalculator;
import ch.hslu.ad.sw13.kmp.Kmp;
import ch.hslu.ad.sw13.quicksearch.Quicksearch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchAlgorithmPerformanceTest {
    static final String QUICKSEARCH = "QUICKSEARCH";
    static final String SIMPLE_SEARCH = "SIMPLE_SEARCH";
    static final String KMP = "KMP";
    static final String QUICKSEARCH_OPTIMAL_MISMATCH = "QUICKSEARCH_OPTIMAL_MISMATCH";

    static final String PATTERN = "Babylon hath been a golden cup in the LORD’s hand";

    static String input;
    static final HashMap<String, RuntimeCalculator> RUNTIME_CALCULATORS = new HashMap<>();

    @BeforeAll
    static void beforeAll() throws Exception {
        RUNTIME_CALCULATORS.put(QUICKSEARCH, new RuntimeCalculator());
        RUNTIME_CALCULATORS.put(QUICKSEARCH_OPTIMAL_MISMATCH, new RuntimeCalculator());
        RUNTIME_CALCULATORS.put(SIMPLE_SEARCH, new RuntimeCalculator());
        RUNTIME_CALCULATORS.put(KMP, new RuntimeCalculator());

        input = Files.readString(Paths.get(Objects.requireNonNull(SearchAlgorithmPerformanceTest.class.getResource(
                "bible.txt")).toURI()));
    }

    @RepeatedTest(10)
    void testQuickSearch() {
        long startTime = System.currentTimeMillis();
        int index = Quicksearch.quickSearch(input, PATTERN);
        long endTime = System.currentTimeMillis();

        RUNTIME_CALCULATORS.get(QUICKSEARCH).addTime(endTime - startTime);
        assertEquals(2926586, index);
    }

    @RepeatedTest(10)
    void testSimpleSearch() {
        long startTime = System.currentTimeMillis();
        int index = SimpleSearch.simpleSearch(input, PATTERN);
        long endTime = System.currentTimeMillis();

        RUNTIME_CALCULATORS.get(SIMPLE_SEARCH).addTime(endTime - startTime);
        assertEquals(2926586, index);
    }

    @RepeatedTest(10)
    void testKmpSearch() {
        long startTime = System.currentTimeMillis();
        int index = Kmp.kmpSearch(input, PATTERN);
        long endTime = System.currentTimeMillis();

        RUNTIME_CALCULATORS.get(KMP).addTime(endTime - startTime);
        assertEquals(2926586, index);
    }

    @RepeatedTest(10)
    void testQuickSearchOptimalMismatch() {
        long startTime = System.currentTimeMillis();
        int index = Quicksearch.quickSearchOptimalMismatch(input, PATTERN);
        long endTime = System.currentTimeMillis();

        RUNTIME_CALCULATORS.get(QUICKSEARCH_OPTIMAL_MISMATCH).addTime(endTime - startTime);
        assertEquals(2926586, index);
    }

    @AfterAll
    static void afterAll() {
        RUNTIME_CALCULATORS.forEach((name, runtimeCalculation) -> {
            System.out.println("Runtime evaluation of: " + name + " on " + input.length() + " elements\n");
            System.out.println(runtimeCalculation.toString());
        });
    }
}
