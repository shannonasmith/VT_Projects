package boundedpipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CS 5704 (Spring 2023) boundedpipe
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.17
 */

public class CircArrayPipe<E> extends AbstractPipe<E> {
    private final E[] contents;
    private int first = -1;
    private int last = -1;
    private int length = 0;

    public CircArrayPipe(int max) {
        super(max);
        if (max < 1) {
            throw new IllegalArgumentException();
        }
        contents = (E[]) new Object[max];
    }

    @Override
    public void prepend(E element) throws IllegalArgumentException, IllegalStateException {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            throw new IllegalStateException();
        }
        if (length == 0) {
            first = capacity() - 1;
            last = capacity() - 1;
        } else {
            first--;
        }
        contents[first] = element;
        length++;
    }

    @Override
    public void append(E element) throws IllegalArgumentException, IllegalStateException {
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
    public E first() {
        return isEmpty() ? null : contents[first];
    }

    @Override
    public E last() {
        return isEmpty() ? null : contents[last % capacity()];
    }

    @Override
    public E removeFirst() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        E removedElement = contents[first];
        int secondElement = first + 1; // 0
        if (first == last) {
            first = -1; // empty
            last = -1; // empty
        } else if (secondElement == capacity()) {
            first = 0;                                  // <----------------------
        } else {
            first++;
        }
        length--;
        return removedElement;
    }

    @Override
    public E removeLast() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        E removedElement = contents[last % capacity()];
        if (first == last) {
            first = -1;
            last = -1;
        } else {
            last--;
        }
        length--;
        return removedElement;
    }

    @Override
    public int length() {
        return length;

    }

    @Override
    public Pipe<E> newInstance() {
        return new CircArrayPipe<>(capacity());
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
        return new PipeIterator();
    }

    private class PipeIterator implements Iterator<E> {
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

}
