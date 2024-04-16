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
package ch.hslu.ad.sw07.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Demonstration der Bankgeschäfte - Aufgabe 4 - N3_EX_WeiterführendeKonzepte.
 */
public final class DemoBankAccount {

    private static final Logger LOG = LoggerFactory.getLogger(DemoBankAccount.class);

    /**
     * Privater Konstruktor.
     */
    private DemoBankAccount() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn Warten unterbrochen wird.
     */
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.nanoTime();
        final ArrayList<BankAccount> source = new ArrayList<>();
        final ArrayList<BankAccount> target = new ArrayList<>();
        final int amount = 1_500_000;
        final int number = 8;
        for (int i = 0; i < number; i++) {
            source.add(new BankAccount(amount));
            target.add(new BankAccount());
        }
        
        // Account Tasks starten...
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < number; i++) {
                executor.submit(new AccountTask(source.get(i), target.get(i), amount));
            }
            for (int i = 0; i < number; i++) {
                executor.submit(new AccountTask(target.get(i), source.get(i), amount));
            }
        }

        LOG.info("Bank accounts after transfers");
        for (int i = 0; i < number; i++) {
            LOG.info("source({}) = {}; target({}) = {};", i, source.get(i).getBalance(), i, target.get(i).getBalance());
        }
        long endTime = System.nanoTime();
        long timeInMs = TimeUnit.MILLISECONDS.convert(endTime - startTime, TimeUnit.NANOSECONDS);
        LOG.info("The bank accounts have been transferred in {} ms", timeInMs);
        // average after 8 executions was 193 ms
    }
}
