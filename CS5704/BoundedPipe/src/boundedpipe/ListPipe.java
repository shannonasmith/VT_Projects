package boundedpipe;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * CS 5704 (Spring 2023) boundedpipe
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.17
 */

public class ListPipe<E> extends AbstractPipe<E> {
    private final List<E> list;

    public ListPipe(int max) throws IllegalArgumentException {
        super(max);
        if (max < 1) {
            throw new IllegalArgumentException();
        }
        list = new LinkedList<>();
    }

    @Override
    public void prepend(E element) throws IllegalStateException, IllegalArgumentException {
        if (length() == capacity()) {
            throw new IllegalStateException();
        }
        if (element == null) {
            throw new IllegalArgumentException();
        }
        list.add(0, element);
    }

    @Override
    public void append(E element) throws IllegalStateException, IllegalArgumentException {
        if (length() == capacity()) {
            throw new IllegalStateException();
        }
        if (element == null) {
            throw new IllegalArgumentException();
        }
        list.add(element);
    }

    @Override
    public E first() {
        return isEmpty() ? null : list.remove(0);
    }

    @Override
    public E last() {
        return isEmpty() ? null : list.remove(list.size() - 1);
    }

    @Override
    public E removeFirst() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return list.remove(0);
    }

    @Override
    public E removeLast() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return list.remove(list.size() - 1);
    }

    @Override
    public int length() {
        return list.size();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Pipe<E> newInstance() {
        return new ListPipe<>(capacity());
    }

}
