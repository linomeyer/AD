package ch.hslu.sw06.latch;/*
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

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Ein Rennpferd, das durch ein Startsignal losläuft. Nach einer zufälligen Zeit
 * kommt es im Ziel an.
 */
public final class RaceHorse implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(RaceHorse.class);
    private static final CountDownLatch countDownLatch = new CountDownLatch(5);
    private final String name;
    private final Random random;

    /**
     * Erzeugt ein Rennpferd, das in die Starterbox eintritt.
     *
     * @param name Name des Pferdes.
     */
    public RaceHorse(final String name) {
        this.name = name;
        this.random = new Random();
    }

    @Override
    public void run() {
        LOG.info("Rennpferd {} geht in die Box.", name);
        try {
            countDownLatch.countDown();
            countDownLatch.await();
            LOG.info("Rennpferd {} läuft los...", name);
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException ex) {
            LOG.debug(ex.getMessage(), ex);
        }
        LOG.info("Rennpferd {} ist im Ziel.", name);
    }
}
