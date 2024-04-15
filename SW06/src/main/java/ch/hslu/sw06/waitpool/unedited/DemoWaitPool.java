package ch.hslu.sw06.waitpool.unedited;/*
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

/**
 * Demonstration eines Wait-Pools.
 */
public final class DemoWaitPool {

    private static final Object LOCK = new Object();

    /**
     * Privater Konstruktor.
     */
    private DemoWaitPool() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn das warten unterbrochen wird.
     */
    public static void main(final String args[]) throws InterruptedException {
        final MyTask waiter = new MyTask(LOCK);
        new Thread(waiter).start();
        Thread.sleep(1000);
        LOCK.notify();
    }

    // a)
    // 1) Es entstehen "current thread is not owner" Fehler, sowohl in der main-Methode, beim Aufruf von Notify auf
    // dem LOCK, wie auch in der Klasse MyTask, beim aufruf von wait().

    // 2) Der Fehler in der main-Methode entsteht, da notify nicht in einem synchronized Block aufgerufen wird. Wie
    // in der Javadoc beschrieben, wird eine IllegalMontiorStateException geworfen, wenn der aufrufende Thread nicht
    // owner des Montiors des Objekts ist.
    // Dies ist generell so, weil es wichtig ist, dass der wartende Thread und der Notify'ende Thread den gleichen
    // Stand des Objekts haben müssen.
    // Die Ursache für den Fehler in MyTask ist das Gleiche. Es wird wait() in einem Thread aufgerufen, der gar nicht
    // owner des Montiors dieses Objekts ist. Hier ist der Fehler aber etwas versteckt, denn wait() wird zwar in
    // einem synchronized Block aufgerufen, aber ruft wait() nicht auf dem synchronisierten Objekt auf. Eigentlich
    // wird this.wait() ausgeführt, also auf dem MyTask Objekt, wovon der Thread nicht Owner ist. Stattdessen sollte
    // lock.wait() aufgerufen werden.

    // b)
    // 1) Die Definition der main-Methode mit dem Parameter "String args[]" ist nicht nach Java Konvention. In java
    // sollte "String[] args" geschrieben werden.
    // 2) Mein Intellij hat nichts zu sagen.
}
