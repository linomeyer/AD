package ch.hslu.sw06.signal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration eines Semaphors.
 */
public final class DemoSema {

    private static final Logger LOG = LoggerFactory.getLogger(DemoSema.class);

    /**
     * Privater Konstruktor.
     */
    private DemoSema() {
    }

    /**
     * Main-Demo.
     *
     * @param args not used.
     * @throws InterruptedException wenn das warten unterbrochen wird.
     */
    public static void main(final String[] args) throws InterruptedException {
        try {
            new Semaphore(4, 3);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
        try {
            Semaphore sema = new Semaphore(3, 3);
            sema.release();
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
        try {
            Semaphore sema = new Semaphore(0, 3);
            sema.release(4);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
        try {
            Semaphore sema = new Semaphore(3, 3);
            sema.acquire(4);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
        try {
            Semaphore sema = new Semaphore(3, 3);
            sema.acquire(-1);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
        try {
            Semaphore sema = new Semaphore(1, 3);
            sema.release(-1);
        } catch (Exception e) {
            LOG.debug(e.getMessage());
        }
    }

    // a)
    // 1) Es wäre fairer wenn es z.B. nach dem FIFO Prinzip funktionieren würde.
    // 2) Momentan wird einfach notifyAll ausgeführt, sobald das Semaphor freien Platz hat. Der Thread der sich den
    // Platz zuerst schnappt gewinnt. Es könnte sein das ein Thread sehr lange warten muss und andere gar nicht.
    // 3) Die wartenden Threads könnten nach FIFO-Prinzip nacheinander aufgerufen werden.

    // b)
    // 1) Performancetechnisch macht es hier deutlich mehr Sinn notify zu verwenden, da immer nur ein neuer Platz im
    // Semaphor frei wird, macht es keinen Sinn, n wartende Threads zu notifyen
    // 2) Einfach anstatt notifyAll, notify aufrufen.

    // c)
    // 1) Wenn der Startwert im Semaphor höher ist als die Limite. Wenn der Startwert im Semaphor kleiner als 0 ist.
    // 2) Auf den gleichen Wert wie den Startwert?
    // 3) acquire(int permits) und release
    // 4) In der Release Methode wird gewartet bis die Anzahl Passiersignale nicht mehr am Limit ist. In acquire(int
    // permits) wird eine Exception geworfen, falls mehr Passiersignale eingefügt werden sollen, als wirklich Platz
    // haben.
}
