package boundedqueue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * CS 5704 (Fall 2023) BoundedQueue
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.09.06
 */

/**
 * @inheritDoc
 */
public class ListQueue<E> extends AbstractQueue<E> {
    private final List<E> list;

    /**
     * @inheritDoc
     */
    public ListQueue(int max) {
        super(max);
        if (max < 1) {
            throw new IllegalArgumentException();
        }
        list = new LinkedList<>();
    }

    @Override
    public void enqueue(E element) {
        if (length() == capacity()) {
            throw new IllegalStateException();
        }
        if (element == null) {
            throw new IllegalArgumentException();
        }
        list.add(element);
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return list.remove(0);

    }


    @Override
    public int length() {
        return list.size();
    }

    @Override
    public Queue<E> newInstance() {
        return new ListQueue<>(capacity());
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public E first() {
        return isEmpty() ? null : list.remove(0);
    }

    @Override
    public E last() {
        return isEmpty() ? null : list.remove(list.size() - 1);
    }
}

