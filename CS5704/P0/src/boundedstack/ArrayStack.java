package boundedstack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * CS 5704 (Spring 2023) boundedstack
 *
 * @author Shannon Smith (shae1223)
 */
public class ArrayStack<E> extends boundedstack.AbstractStack<E> {

    private final E[] contents; // this is the array
    private int depth;
    private final int capacity;

    // representation: contents = [ A | B | C | D | E | F ] and depth = 3
    // abstraction: stack = [ A, B, C ]: 6
    @Override
    public int capacity() {
        return capacity;
    }
    @SuppressWarnings("unchecked")
    public ArrayStack(int max) {
        // super(max);
        capacity = max;
        contents = (E[]) new Object[max];
        depth = 0;
    }

    @Override
    public void push(E element) throws IllegalStateException, IllegalArgumentException {
        if (depth() == capacity()) { // stack if full
            throw new IllegalStateException(); // type "IllStEx" and hit
            // Alt-Enter
        }
        if (element == null) {
            throw new IllegalArgumentException(); // type "IllArgEx" and hit
            // Alt-Enter
        }
        // this = [A, B, C]:6 (rep: contents = [ A | B | C | * | * | * ] and depth = 3)
        contents[depth] = element;
        depth++;
        // this = [A, B, C, X]:6 (rep: contents = [ A | B | C | X | * | * ] and depth = 4)
    }

    @Override
    public E pop() throws IllegalStateException {
        if (depth() == 0) {
            throw new IllegalStateException();
        }
        // this = [A, B, C]:6 (rep: contents = [ A | B | C | * | * | * ] and depth = 3)
        E result = contents[depth - 1];
        depth--;
        return result;
            /*
            this = [A, B]:6 (rep: contents = [ A | B | * | * | * | * ] and depth = 2)
            and result = C
            */
    }

    @Override
    public int depth() {
        return depth;
    }

    @Override
    public void clear() {
        // this = [A, B, C, X]:6 (rep: contents = [ A | B | C | X | * | * ] and depth = 3)
        depth = 0;
        // this = []:6 (rep: contents = [ * | * | * | * | * | * ] and depth = 0)
    }

    @Override
    public boundedstack.Stack<E> newInstance() {
        // this = [A, B, C, X]:6 (rep: contents = [ A | B | C | X | * | * ] and depth = 3)
        return new ArrayStack<>(capacity());
        // this = [A, B, C, X]:6 (rep: contents = [ A | B | C | X | * | * ] and depth = 3)
        // result = []:6
    }

    /* For the pipe:
     * ------------------------------------------------------------
     * 1. your starting index could be in the middle of the array
     * 2. your ending index could be less than your starting index
     * 3. you will have to use the modulo operator (%)
     * -  don't use Arrays
     * -  don't use Collections
     * -  don't use Lambda expressions
     * ------------------------------------------------------------
     */

    @Override
    public Iterator<E> iterator() {
        return new StackIterator(); // create inner class StackIterator (below)
    }

    private class StackIterator implements Iterator<E> {  // implement methods hasNext() and next()

        private int currentIndex = 0;  // we want the pipe iterator to only be associated with the current object

        @Override
        public boolean hasNext() {
            return currentIndex < depth();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = contents[currentIndex];
            currentIndex++;
            return result;
        }
    }

}
