package ch.hslu.ad.sw06.latch;/*
 * Copyright 2024 Hochschule Luzern - Informatik.
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Eine Rennbahn für das Pferderennen.
 */
public final class Turf {

    private static final Logger LOG = LoggerFactory.getLogger(Turf.class);
    public static final CountDownLatch COUNTDOWN_LATCH = new CountDownLatch(Turf.HORSES + 1);
    public static final int HORSES = 5;

    private Turf() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     */
    public static void main(final String[] args) throws InterruptedException {
        Latch latch = new Latch(HORSES);

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= HORSES; i++) {
            threads.add(Thread.startVirtualThread(new RaceHorse("Horse " + i, latch)));
        }

        Thread.sleep(1000);
        LOG.info("Start...");
        latch.release();

        /* Logic to interrupt race
        Thread.sleep(200);
        for (Thread thread : threads) {
            thread.interrupt();
        }
         */

        // Main thread sleeps until all other threads are finished, what would be a better way?
        // Thread.sleep(3100);

        // Main thread calls join on all other threads, so program finishes only when all threads are finished
        for (Thread thread : threads) {
            thread.join();
        }
    }

    // mit dem java.util.concurrent.CountDownLatch scheint das Rennen ziemlich gerecht zu sein. Alle Pferde laufen
    // auf +-1 Millisekunde genau, gleichzeitig los und sind halt sehr unterschiedlich schnell im Ziel.
}
