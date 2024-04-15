/*
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
package ch.hslu.ad.sw06.boundedbuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Demonstration des BoundedBuffers mit n Producer und m Consumer.
 */
public final class DemoBoundedBuffer {

    private static final Logger LOG = LoggerFactory.getLogger(DemoBoundedBuffer.class);

    /**
     * Privater Konstruktor.
     */
    private DemoBoundedBuffer() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn das warten unterbrochen wird.
     */
    public static void main(final String[] args) throws InterruptedException {
        final Random random = new Random();
        final int nPros = 3;
        final Producer[] producers = new Producer[nPros];
        final ThreadGroup prosGroup = new ThreadGroup("Producer-Threads");
        final int mCons = 2;
        final Consumer[] consumers = new Consumer[mCons];
        final ThreadGroup consGroup = new ThreadGroup("Consumer-Threads");
        final BoundedBuffer<Integer> queue = new BoundedBuffer<>(50);
        for (int i = 0; i < nPros; i++) {
            producers[i] = new Producer(queue, random.nextInt(10000));
            new Thread(prosGroup, producers[i], "Prod  " + (char) (i + 65)).start();
        }
        for (int i = 0; i < mCons; i++) {
            consumers[i] = new Consumer(queue);
            new Thread(consGroup, consumers[i], "Cons " + (char) (i + 65)).start();
        }
        while (prosGroup.activeCount() > 0) {
            Thread.yield();
        }
        TimeUnit.MILLISECONDS.sleep(100);
        consGroup.interrupt();
        long sumPros = 0;
        for (int i = 0; i < nPros; i++) {
            LOG.info("Prod {} = {}", (char) (i + 65), producers[i].getSum());
            sumPros += producers[i].getSum();
        }
        long sumCons = 0;
        for (int i = 0; i < mCons; i++) {
            LOG.info("Cons {} = {}", (char) (i + 65), consumers[i].getSum());
            sumCons += consumers[i].getSum();
        }
        LOG.info("{} = {}", sumPros, sumCons);
        LOG.info("queue size = {}", queue.size());
        LOG.info("queue empty? {}", queue.empty());
    }
}

// 1) Damit nicht immer der Ganze BoundedBuffer für alle ausser einen Thread gesperrt ist,
// sondern nur die Zugriffe auf die Datenstruktur.
// 2) Weil immer nur ein Element auf einmal aus dem BoundedBuffer entnommen wird, da macht es aus Performancegründen
// keinen Sinn jedes mal n wartende Threads zu wecken.
// 3) ?
// 4) Damit der Aufrufer die Möglichkeit hat, beliebig auf den Interrupt zu reagieren.
// Es kann z.B. mehr Kontext gegeben werden, was aufgrund des Interrupts nicht geklappt hat.
// z.B. Summe konnte nicht berechnet werden im Consumer.
// 5) Interrupthandling nicht im Boundedbuffer selber gehandelt, sondern wird zum Aufrufer geworfen.
// In diesem Fall ist das entweder Consumer oder Producer. Diese stoppen run einfach sobald eine Exception kommt.

