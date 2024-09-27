package boundedqueue;

import java.util.Iterator;

/*
 * CS 5704 (Fall 2023) BoundedQueue
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.09.06
 */

/**
 * @inheritDoc
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractQueue<E> implements Queue<E> {
    private final int capacity;

    /**
     * @inheritDoc
     */
    public AbstractQueue(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int capacity() { // covered by ListQueueTest
        return capacity;
    }

    @Override
    public boolean isEmpty() { // covered by ListQueueTest
        return length() == 0;
    }

    @Override
    public boolean isFull() { // covered by ListQueueTest
        return length() == capacity();
    }

    @Override
    public void appendAll(Queue<E> that) {
        if (that == null) {
            throw new IllegalArgumentException();
        }
        if (this.isFull()) {
            throw new IllegalStateException();
        }
        E element = that.dequeue();
        this.enqueue(element);
        if (that.length() > 0) {
            appendAll(that);
        }
    }

    @Override
    public Queue<E> copy() {
        Queue<E> result = this.newInstance();
        for (E element : this) {
            result.enqueue(element);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false; // not covered
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Queue)) {
            return false; // not covered
        }
        Queue<?> that = (Queue) o;
        if (this.capacity() != that.capacity()) {
            return false;
        }
        if (this.length() != that.length()) {
            return false;
        }
        Iterator<E> thisIter = this.iterator();
        Iterator<?> thatIter = that.iterator();
        while (thisIter.hasNext()) {
            E elem = thisIter.next();
            Object obj = thatIter.next();
            if (!elem.equals(obj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (E element : this) {
            result = 31 * result + element.hashCode();
        }
        result = 31 * result + capacity();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]:");
        sb.append(capacity());
        return sb.toString();
    }

}
