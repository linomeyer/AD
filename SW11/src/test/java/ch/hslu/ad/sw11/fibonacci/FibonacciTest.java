package ch.hslu.ad.sw11.fibonacci;

import ch.hslu.ad.commons.RuntimeCalculator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest {
    private static final int N = 45;
    private static final Map<String, RuntimeCalculator> RUNTIME_CALCULATORS = new HashMap<>();
    public static final String FIBONACCI_ITERATIVE = "Fibonacci Iterative";
    public static final String FIBONACCI_RECURSIVE = "Fibonacci Recursive";
    public static final String FIBONACCI_PARALLEL = "Fibonacci Parallel";

    @BeforeAll
    static void beforeAll() {
        RUNTIME_CALCULATORS.put(FIBONACCI_ITERATIVE, new RuntimeCalculator());
        RUNTIME_CALCULATORS.put(FIBONACCI_RECURSIVE, new RuntimeCalculator());
        RUNTIME_CALCULATORS.put(FIBONACCI_PARALLEL, new RuntimeCalculator());
    }

    @RepeatedTest(10)
    void testFibonacciIterative() {
        long startTime = System.currentTimeMillis();

        long result = Fibonacci.fiboIterative(N);

        long endTime = System.currentTimeMillis();
        RUNTIME_CALCULATORS.get(FIBONACCI_ITERATIVE).addTime(endTime - startTime);

        assertEquals(1134903170L, result);
    }

    @RepeatedTest(10)
    void testFibonacciRecursive() {
        long startTime = System.currentTimeMillis();

        long result = Fibonacci.fiboRecursive(N);

        long endTime = System.currentTimeMillis();
        RUNTIME_CALCULATORS.get(FIBONACCI_RECURSIVE).addTime(endTime - startTime);

        assertEquals(1134903170L, result);
    }

    @RepeatedTest(10)
    void testFibonacciParallel() {
        long startTime = System.currentTimeMillis();

        long result = Fibonacci.fibonacciParallel(N);

        long endTime = System.currentTimeMillis();
        RUNTIME_CALCULATORS.get(FIBONACCI_PARALLEL).addTime(endTime - startTime);

        assertEquals(1134903170L, result);
    }

    @AfterAll
    static void afterAll() {
        RUNTIME_CALCULATORS.forEach((name, runtimeCalculation) -> {
            System.out.println("Runtime evaluation of: " + name + " on " + N + " elements\n");
            System.out.println(runtimeCalculation.toString());
        });
    }
}