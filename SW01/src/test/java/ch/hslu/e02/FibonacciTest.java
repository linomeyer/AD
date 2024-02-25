package ch.hslu.e02;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest {

    @Test
    void testFibonacciRecursive1() {
        assertEquals(13, Fibonacci.fibonacciRecursive1(7));
    }

    @Test
    void testFibonacciRecursive2() {
        assertEquals(21, Fibonacci.fibonacciRecursive2(8));
    }

    @Test
    void testFibonacciRowByRecursion1() {
        List<Integer> fibonacciRow = Arrays.asList(1, 2, 3, 5, 8);
        assertEquals(fibonacciRow, Fibonacci.fibonacciRowbyRecursion(7, 1));
    }

    @Test
    void testFibonacciRowByRecursion2() {
        List<Integer> fibonacciRow = Arrays.asList(1, 2, 3, 5, 8, 13);
        assertEquals(fibonacciRow, Fibonacci.fibonacciRowbyRecursion(8, 2));
    }

    @Test
    void testFibonacciRowIteration() {
        List<Integer> fibonacciRow = Arrays.asList(1, 2, 3, 5, 8, 13);
        assertEquals(fibonacciRow, Fibonacci.fibonacciIterative(6));
    }
}