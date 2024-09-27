package boundedstack;

import java.util.LinkedList;
import java.util.List;

/*
 * CS 5704 (Spring 2023) boundedstack2.0
 *
 * @author Shannon Smith (shae1223)
 */

public class ListStack<E> implements Stack<E> {

    // representation/declarations
    private final List<E> contents; // can be null bc it's a list

    private final int capacity;

    @Override
    public int capacity() {
        return capacity;
    }

    // contents = <> and capacity = 3 ==> stack = []:3
    // contents = <A> and capacity = 10 ==> stack = [A]:10
    // contents  = <A, B, C> and capacity = 6 ==> stack = [A, B, C]:6

    public ListStack(int max) throws IllegalArgumentException {
        // super(max);
        capacity = max;
        if (max < 1) {
            throw new IllegalArgumentException();
        }
        contents = new LinkedList<>();
    }

    @Override
    public void push(E element) throws IllegalStateException, IllegalArgumentException {
        if (depth() == capacity()) { // stack is full
            throw new IllegalStateException();
        }
        if (element == null) { // stack is empty
            throw new IllegalArgumentException();
        }
        // pre: stack = [A] and element = B (contents = [A])
        contents.add(element);
        // post: stack = [A, B] and element = B (contents = [A, B])
    }

    // stack: first element in list represents bottom element in stack
    // queue: first element in list represents first element in the queue

    @Override
    public E pop() throws IllegalStateException {
        if (depth() == 0) {
            throw new IllegalStateException();
        }
        return contents.remove(contents.size() - 1);
    }

    @Override
    public int depth() {
        return contents.size();
    }

}

