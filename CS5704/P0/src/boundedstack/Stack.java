package boundedstack;

/*
 * CS 5704 (Spring 2023) boundedstack
 *
 * @author Shannon Smith (shae1223)
 */

/**
 * A bounded stack is a first-in-last-out data structure. Elements in the stack
 * may not be null. This stack is bounded,
 * therefore it should have a single-argument constructor that takes an
 * integer that represents the maximum number of elements this stack can hold.
 * <p>
 * A typical string representation of a bounded stack is
 * [e_1, e_2, ..., e_n-1, e_n]:c
 * where e_1 is the bottom element of the stack
 * and e_n is the top element, and c is the capacity.
 * <p>
 * Implementations of this interface should have a one-argument constructor that
 * takes the desired capacity and creates an empty stack. The capacity must be
 * strictly greater than zero.
 * <p>
 * public Stack(int max)
 * <p>
 * A stack iterator iterates through the stack from bottom to top.
 *
 * @param <E> the type of elements in this stack
 */
public interface Stack<E> extends Iterable<E> {

    /**
     * Adds the specified element to the top of this stack.
     * <p>
     * Example:
     *      { s = [A, B, C]:6 and x = X }
     *      s.push(x)
     *      { s = [A, B, C, X]:6 and x = X }
     *
     * @param element the element to be pushed onto this stack
     * @throws IllegalStateException if this stack is full
     * @throws IllegalArgumentException if the specified element is null
     */
    void push(E element) throws IllegalStateException, IllegalArgumentException;

    /**
     * Removes and returns the top element from this stack
     *
     * @return the top element removed from this stack
     * @throws IllegalStateException if this stack is empty
     */
    E pop() throws IllegalStateException;

    /**
     * Returns the depth of this stack
     * @return the number of elements currently in this stack
     */
    int depth();

    /**
     * Returns the capacity of this stack
     * @return the maximum number of elements this stack can hold
     */
    int capacity();

    /**
     * Returns the string representation of this stack. The string
     * representation contains a list of this stack's elements from bottom to
     * top and the stack's capacity. The list is enclosed in square brackets
     * and adjacent elements are separated by a command and a space. The list
     * and capacity are separated by a colon. For example, the string
     * [A, B, C]:6 represents a stack with 3 elements, where
     * A is the bottom element, C is the top element, and the stack has a
     * capacity of 6.
     *
     * @return the string representation of this stack
     */
    @Override
    String toString();

    /**
     * Returns true if the specified object is equal to this stack.
     * be equal, the specified object must be a bounded stack with the
     * same capacity, and it must contain the same number of elements with
     * the same value in the same order.
     *
     * @param obj the object to be tested for equality to this stack
     * @return true if the specified object is equal to this stack, false otherwise
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns the hash code of this stack.
     * @return the hash code of this stack
     */
    @Override
    int hashCode();

    /**
     * Clears this stack.
     */
    void clear();

    /**
     * Returns a new empty stack with the same capacity as this stack.
     *
     * @return new empty stack
     */
    Stack<E> newInstance();

    /**
     * Returns a copy of this stack. The elements in the copy are
     * references to the elements in this stack.
     *
     * @return copy
     */
    Stack<E> copy();

    /**
     * Reverses the elements in this stack.
     */
    void reverse();

}