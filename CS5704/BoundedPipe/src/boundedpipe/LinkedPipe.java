package boundedpipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CS 5704 (Spring 2023) boundedpipe
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.17
 */

public class LinkedPipe<E> extends AbstractPipe<E> {
    private Node first;
    private Node last;
    private int length;

    private class Node {
        private final E contents;
        private Node prev = null;
        private Node next = null;

        Node(E contents) {
            this.contents = contents;
        }
    }

    public LinkedPipe(int max) throws IllegalArgumentException {
        super(max);
        if (max < 1) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void prepend(E element) throws IllegalStateException, IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            throw new IllegalStateException();
        }
        Node newNode = new Node(element);
        if (isEmpty()) {
            first = newNode;
            last = newNode;
        } else {
            first.prev = newNode;
            newNode.next = first;
            first = newNode;
        }
        length++;
    }

    @Override
    public void append(E element) throws IllegalStateException, IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException();
        }
        if (isFull()) {
            throw new IllegalStateException();
        }
        Node newNode = new Node(element);
        if (isEmpty()) {
            last = newNode;
            first = newNode;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        length++;
    }

    @Override
    public E first() {
        return isEmpty() ? null : first.contents;
    }

    @Override
    public E last() {
        return isEmpty() ? null : last.contents;
    }

    @Override
    public E removeFirst() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        E firstToRemove = first.contents;
        first = first.next;
        length--;
        return firstToRemove;
    }

    @Override
    public E removeLast() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        E lastToRemove = last.contents;
        last = last.prev;
        length--;
        return lastToRemove;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public Pipe<E> newInstance() {
        return new LinkedPipe<>(capacity());
    }

    @Override
    public void clear() {
        if (!isEmpty()) {
            first = first.next;
            length--;
            clear();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedPipe.PipeIterator();
    }

    private class PipeIterator implements Iterator<E> {
        private int currentIndex = 0;
        private final Node iterNode = first;

        @Override
        public boolean hasNext() {
            return currentIndex < length;
        }

        @Override
        public E next() {
            if (hasNext()) {
                E currentNodeElem = iterNode.contents;
                currentIndex++;
                return currentNodeElem;
            }
            throw new NoSuchElementException();
        }
    }

}
