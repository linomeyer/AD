/*
 * Copyright 2024 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.ad.sw11.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;

/**
 * Codevorlage für die Verwendung von RecursiveTask mit einem Fork-Join-Pool.
 */
public final class Fibonacci {

    private static final Logger LOG = LoggerFactory.getLogger(Fibonacci.class);

    /**
     * Privater Konstruktor.
     */
    private Fibonacci() {
    }

    /**
     * Berechnet den Fibonacci Wert für n.
     *
     * @param n für die Fibonacci Berechnung.
     * @return Resultat der Fibonacci Berechnung.
     */
    public static long fiboIterative(final int n) {
        long f = 0;
        long g = 1;
        for (int i = 1; i <= n; i++) {
            f = f + g;
            g = f - g;
        }
        return f;
    }

    /**
     * Berechnet den Fibonacci Wert für n.
     *
     * @param n für die Fibonacci Berechnung.
     * @return Resultat der Fibonacci Berechnung.
     */
    public static long fiboRecursive(final int n) {
        return n > 1 ? fiboRecursive(n - 1) + fiboRecursive(n - 2) : n;
    }

    public static long fibonacciParallel(final int n) {
        try (final ForkJoinPool pool = ForkJoinPool.commonPool()) {
            final FibonacciTask sortTask = new FibonacciTask(n);
            return pool.invoke(sortTask);
        }
    }
}
