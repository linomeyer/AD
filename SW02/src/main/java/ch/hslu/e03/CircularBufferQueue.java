package ch.hslu.e03;

public interface CircularBufferQueue<E> {

    /**
     * Add element to end of queue
     *
     * @param e element to add
     * @return returns true on success and false if queue is already full
     */
    boolean offer(E e);

    /**
     * Increment read Sequence
     *
     * @return the next element in the read sequence or null if queue is empty
     */
    E poll();
}
