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
package ch.hslu.ad.sw11.mergesort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;

/**
 * Performance Vergleich der Mergesort-Implementation.
 */
public final class DemoMergesort {

    private static final Logger LOG = LoggerFactory.getLogger(DemoMergesort.class);

    /**
     * Privater Konstruktor.
     */
    private DemoMergesort() {
    }

    /**
     * Main-Demo.
     */
    public static void demo(int[] array) {
        try (final ForkJoinPool pool = new ForkJoinPool()) {
            final MergesortTask sortTask = new MergesortTask(array);
            pool.invoke(sortTask);
        }
    }
}
