package ch.hslu.e01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    static int recursiveCounter = 0;
    static int taskCounter = 0;


    public static void main(String[] args) throws InterruptedException {
        int a = 16;
        int b = 68;

        System.out.println(ggtIterative1(a, b));
        System.out.println(ggtIterative2(a, b));
        ggtRecursive(a, b);
        System.out.println(recursiveCounter);

        // b) Der rekurisve Algorithmus hat den grössten Speicherbedarf und ist daher am wenigsten effizient. Die
        // anderen beiden Algorithmen sind gleich effizient.

        long startTime = System.currentTimeMillis();
        task(2);
        long endTime = System.currentTimeMillis();

        long timer1 = endTime - startTime;
        LOGGER.info("With n = 2");
        LOGGER.info("Time in ms to execute : " + timer1);
        LOGGER.info("Number of Method Calls: " + taskCounter + "\n");


        startTime = System.currentTimeMillis();
        task(4);
        endTime = System.currentTimeMillis();

        long timer2 = endTime - startTime;
        LOGGER.info("With n = 4");
        LOGGER.info("Time in ms to execute : " + timer2);
        LOGGER.info("Number of Method Calls: " + taskCounter + "\n");
        LOGGER.info("Time increase in relation to previous n value: " + ((float) Math.round(((float) timer2 / timer1) * 100) / 100));

        startTime = System.currentTimeMillis();
        task(8);
        endTime = System.currentTimeMillis();

        long timer3 = endTime - startTime;
        LOGGER.info("With n = 8");
        LOGGER.info("Time in ms to execute : " + timer3);
        LOGGER.info("Number of Method Calls: " + taskCounter + "\n");
        LOGGER.info("Time increase in relation to previous n value: " + ((float) Math.round(((float) timer3 / timer2) * 100) / 100));

        startTime = System.currentTimeMillis();
        task(16);
        endTime = System.currentTimeMillis();

        long timer4 = endTime - startTime;
        LOGGER.info("With n = 16");
        LOGGER.info("Time in ms to execute : " + timer4);
        LOGGER.info("Number of Method Calls: " + taskCounter);
        LOGGER.info("Time increase in relation to previous n value: " + ((float) Math.round((float) timer4 / timer3 * 100) / 100));
    }

    public static int ggtIterative1(int a, int b) {
        int counter = 0;
        while (a != b) {
            counter++;
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

    public static int ggtIterative2(int a, int b) {
        int counter = 0;
        while ((a != 0) && (b != 0)) {
            counter++;
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
        }
        return (a + b);
    }

    public static int ggtRecursive(int a, int b) {
        recursiveCounter++;
        if (a > b) {
            return ggtRecursive(a - b, b);
        } else {
            if (a < b) {
                return ggtRecursive(a, b - a);
            } else {
                return a;
            }
        }
    }

    public static void task(final int n) throws InterruptedException {
        taskCounter = 1;
        task1();
        task1();
        task1();
        task1();
        for (int i = 0; i < n; i++) {
            task2();
            task2();
            task2();
            for (int j = 0; j < n; j++) {
                task3();
                task3();
            }
        }
    }

    private static void task3() throws InterruptedException {
        taskCounter++;
        Thread.sleep(5);
    }

    private static void task2() throws InterruptedException {
        taskCounter++;
        Thread.sleep(3);
    }

    private static void task1() throws InterruptedException {
        taskCounter++;
        Thread.sleep(8);
    }
}