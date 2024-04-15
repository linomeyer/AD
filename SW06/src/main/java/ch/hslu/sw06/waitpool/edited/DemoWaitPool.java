package ch.hslu.sw06.waitpool.edited;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration eines Wait-Pools.
 */
public final class DemoWaitPool {
    private static final Logger LOG = LoggerFactory.getLogger(DemoWaitPool.class);

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
        Object lock = new Object();
        final MyTask waiter = new MyTask(LOCK);
        Thread thread = new Thread(waiter);
        thread.start();
        Thread.sleep(1000);
        synchronized (LOCK) {
            LOCK.notify();
        }
    }
}

// c) If the whole main method is in a synchronized block, the lock on the LOCK object is acquired by
// the main thread. The newly created Thread tries to execute he run() method of myTask but doesnt get far
// because the object is already locked. It has to wait with executing the synchronized block until the lock
// is released.
// Meanwhile, the main thread sleeps for 1 second and then calls notify to wake up one thread.
// Nothing happens because no thread is waiting on that object. Then the lock gets released by the main
// thread and gets acquired by the thread thats executing the run() method it starts to wait but never gets
// notified, because the notify() was already made. The thread waits forever.

// 1.4
// a) notify wakes up a single thread that is waiting for an object. If there are multiple it just chooses
// one arbitrarily. notifyAll wakes up every single thread that is waiting on this objects monitor.

// b) notifyAll wird in der Regel empfohlen, da notify nur einen, im Monitor des Objekts, wartenden Thread weckt und
// man hat keinen Einfluss darauf welcher.

// c) Wenn es bestimmte Kondition gibt, die erfüllt werden müssen, damit das Programm weiterlaufen kann, muss darauf
// geprüft werden. Denn es kann passieren das ein Thread ohne notify aufwacht.
