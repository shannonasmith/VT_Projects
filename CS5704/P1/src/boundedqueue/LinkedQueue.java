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
@SuppressWarnings("rawtypes")
public class LinkedQueue<E> extends AbstractQueue<E> {
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

    /**
     * @inheritDoc
     */
    public LinkedQueue(int max) {
        super(max);
        if (max < 1) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void enqueue(E element) {
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
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        E firstToRemove = first.contents;
        first = first.next;
        length--;
        return firstToRemove;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public Queue<E> newInstance() {
        return new LinkedQueue<>(capacity());
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
        return new LinkedQueue.QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {
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

    @Override
    public E first() {
        return isEmpty() ? null : first.contents;
    }

    @Override
    public E last() {
        return isEmpty() ? null : last.contents;
    }
}