package ch.hslu.e02;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Main {

    public static final int NUM_ELEMENTS = 100_000;

    public static void main(String[] args) {
        // results: 2131900, 2093200, 2268600, 2137701, 2126799
        // average: 2151640
        System.out.println("Own stack impl: " + performanceTestOwnStack());
        // results: 3045900, 3119999, 3154601, 3557599, 3229601
        // average: 3221540
        System.out.println("Util stack impl: " + performanceTestUtilStack());
        // results: 2046201, 2108601, 2079400, 2341201, 2027999
        // average: 2120680
        System.out.println("Util deque impl: " + performanceTestDeque());
    }

    private static long performanceTestUtilStack() {
        long startTime = System.nanoTime();

        Stack<Example> exampleStack = new Stack<>();
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            exampleStack.push(new Example(i));
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long performanceTestOwnStack() {
        StackImpl<Example> exampleStack = new StackImpl<>(Example[].class, NUM_ELEMENTS);
        long startTime = System.nanoTime();

        for (int i = 0; i < exampleStack.getSize(); i++) {
            exampleStack.push(new Example(i));
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long performanceTestDeque() {
        Deque<Example> exampleDeque = new ArrayDeque<>(NUM_ELEMENTS);
        long startTime = System.nanoTime();

        for (int i = 0; i < NUM_ELEMENTS; i++) {
            exampleDeque.addFirst(new Example(i));
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

}
