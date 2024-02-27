package ch.hslu.e03;

public class CircularBufferQueueImpl<E> implements CircularBufferQueue<E> {
    public static final int DEFAULT_CAPACITY = 1;
    private final E[] queue;
    private final int capacity;

    private int readSequence;
    private int writeSequence;

    @SuppressWarnings("unchecked")
    public CircularBufferQueueImpl(Class<E[]> clazz, int capacity) {
        this.capacity = capacity > 0 ? capacity : DEFAULT_CAPACITY;
        this.queue = (E[]) new Object[capacity];
        this.readSequence = 0;
        this.writeSequence = -1;
    }

    @Override
    public boolean offer(E element) {
        // if queue not full
        if (!((writeSequence - readSequence) + 1 == capacity)) {
            writeSequence++;
            queue[writeSequence % capacity] = element;
            return true;
        }
        return false;
    }

    @Override
    public E poll() {
        // if queue not empty
        if (!(writeSequence < readSequence)) {
            E readElement = queue[readSequence % capacity];
            readSequence++;
            return readElement;
        }
        return null;
    }
}
