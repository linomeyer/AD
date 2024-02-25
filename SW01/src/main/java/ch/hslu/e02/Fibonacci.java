package ch.hslu.e02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fibonacci {
    private static Map<Integer, Integer> fibonacciNumbersMap = new HashMap<>();

    public static List<Integer> fibonacciRowbyRecursion(int n, int implementationUsed) {
        ArrayList<Integer> fibonacciNumbers = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            if (implementationUsed == 1) {
                fibonacciNumbers.add(fibonacciRecursive1(i));
            } else if (implementationUsed == 2) {
                fibonacciNumbers.add(fibonacciRecursive2(i));
            }
        }
        return fibonacciNumbers;
    }

    public static int fibonacciRecursive1(int n) {
        // recursion base
        if (n < 2) {
            return n;
        }
        // recursion rule
        return fibonacciRecursive1(n - 2) + fibonacciRecursive1(n - 1);
    }

    public static int fibonacciRecursive2(int n) {
        // recursion base
        if (n < 2) {
            return n;
        }

        // recursion rule
        if (fibonacciNumbersMap.containsKey(n)) {
            return fibonacciNumbersMap.get(n);
        } else {
            int result = fibonacciRecursive1(n - 2) + fibonacciRecursive1(n - 1);
            fibonacciNumbersMap.put(n, result);
            return result;
        }
    }

    /**
     * This variant gives the same result as the recursive method with n - 2, so iterative(n - 2) == recursive(n)
     */
    public static List<Integer> fibonacciIterative(int n) {
        ArrayList<Integer> fibonacciRow = new ArrayList<>();
        int preprev = 0;
        int prev = 1;
        for (int i = 0; i < n; i++) {
            int sum = preprev + prev;
            fibonacciRow.add(sum);
            preprev = prev;
            prev = sum;
        }
        return fibonacciRow;
    }
}
