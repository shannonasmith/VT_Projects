package boundedqueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * CS 5704 (Fall 2023) BoundedQueue
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.09.06
 */

/**
 * @inheritDoc
 */
public class CircArrayQueue<E> extends AbstractQueue<E> {
    private final E[] contents;
    private int first = -1;
    private int last = -1;
    private int length = 0;

    /**
     * @inheritDoc
     */
    public CircArrayQueue(int max) {
        super(max);
        if (max < 1) {
            throw new IllegalArgumentException();
        }
        contents = (E[]) new Object[max];
    }

    @Override
    public void enqueue(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            throw new IllegalStateException();
        }
        if (length == 0) {
            first++;
        }
        contents[++last % capacity()] = element;
        length++;
    }

    @Override
    public E dequeue() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        E removedElement = contents[first];
        int secondElement = first + 1;
        if (first == last) {
            first = -1;
            last = -1;
        } else if (secondElement == capacity()) {
            first = 0; // no coverage
        } else {
            first++;
        }
        length--;
        return removedElement;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public Queue<E> newInstance() {
        return new CircArrayQueue<>(capacity());
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            contents[last--] = null;
            length--;
            clear();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < length;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return contents[(first + currentIndex++) % capacity()];
        }
    }

    @Override
    public E first() {
        return isEmpty() ? null : contents[first];
    }

    @Override
    public E last() {
        return isEmpty() ? null : contents[last % capacity()];
    }
}
