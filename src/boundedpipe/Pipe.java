package boundedpipe;

/*
  CS 5704 (Spring 2023) boundedpipe

  @author Shannon Smith (shae1223)
 * @version 2023.02.17
 */

/**
 * <p>
 * A pipe is a bounded data structure. Elements in the pipe may
 * not be null. The pipe is a double ended queue: like a list, but
 * you can only insert and remove elements at the beginning and at
 * the end. You cannot insert elements into or remove elements from
 * the middle of the pipe.
 * </p>
 * <p>
 * Implementations of this interface should have a one-argument constructor that
 * takes the desired capacity and creates an empty pipe. The capacity must be
 * strictly greater than zero.
 * </p>
 * <p>
 * Pipe has three different implementations:
 * 1) List
 * 2) Circular Array (bounded pipe)
 * 3) Linked Pipe (with nodes) -> doubly-linked list
 * </p>
 * <p>
 * A pipe iterator iterates through the pipe from first/start to last/end.
 * </p>
 * <p>
 *
 * @param <E> the type of elements in this pipe
 * </p>
 */
public interface Pipe<E> extends Iterable<E> {

    /**
     * Adds the specified element to the beginning of this pipe.
     *
     * @param element the element to be added to the beginning of this pipe
     * @throws IllegalStateException    if this pipe is full
     * @throws IllegalArgumentException if the element is null
     */
    void prepend(E element);

    /**
     * Adds the specified element to the end of this pipe.
     *
     * @param element the element to be added to the end of this pipe
     * @throws IllegalStateException    if this pipe is full
     * @throws IllegalArgumentException if the element is null
     */
    void append(E element);

    /**
     * Retrieves and removes the first element in this pipe.
     *
     * @return remaining elements in this pipe from first to last
     * @throws IllegalStateException if the index is out of range (index < 0 || index >= length())
     */
    E removeFirst();

    /**
     * Retrieves and removes the last element in this pipe.
     *
     * @return remaining elements in this pipe from first to last
     * @throws IllegalStateException if the index is out of range (index < 0 || index >= length())
     */
    E removeLast();

    /**
     * Returns the number of elements currently this pipe.
     *
     * @return the number of elements in this pipe
     */
    int length();

    /**
     * Returns the maximum number of elements this pipe can hold.
     *
     * @return the maximum number of elements this pipe can hold
     */
    int capacity();

    /**
     * Creates a new, empty bounded pipe with the same capacity as the calling pipe.
     *
     * @return new empty pipe with the same capacity as this pipe
     */
    Pipe<E> newInstance();

    /**
     * Removes all the elements from this pipe.
     */
    void clear();

    /**
     * Returns true if this pipe contains NO elements, otherwise returns false.
     * // You can call clear on an empty pipe. It has no effect (and it does *not* throw an exception)
     *
     * @return true if this pipe is empty
     */
    boolean isEmpty();

    /**
     * Returns true if this pipe is full, otherwise returns false.
     *
     * @return true if this pipe is full
     */
    boolean isFull();

    /**
     * Moves all elements currently in this pipe into another pipe, and then empties that pipe.
     *
     * @param that pipe to be appended
     * @throws IllegalStateException    if this pipe is full
     * @throws IllegalArgumentException if this pipe is empty
     */
    void appendAll(Pipe<E> that);

    /**
     * Returns a copy of this pipe. The elements in the copy are
     * references to the elements in this pipe.
     *
     * @return a copy of this pipe
     */
    Pipe<E> copy();

    /**
     * Retrieves the first element of this pipe.
     *
     * @return the first element in this pipe
     */
    E first();

    /**
     * eRetrieves the last element of this pipe.
     *
     * @return the last element in this pipe
     */
    E last();

    /**
     * Returns true if the specified object is equal to this pipe.
     * To be equal, the specified object must be a bounded pipe object
     * with the same capacity, the same number of elements that have
     * the same values,and they must be in the same order.
     *
     * @param obj the object to be tested for equality compared to this pipe
     * @return true if the specified object is equal to this pipe, otherwise false
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns the hash code of this pipe.
     *
     * @return the hash code of this pipe
     */
    @Override
    int hashCode();

    /**
     * <p> Returns the string representation of this pipe. </p>
     * <p> The string representation contains a list of this pipe's elements
     * from beginning to end, and also contains the pipe's capacity.
     * The list is enclosed in square brackets. Adjacent elements are
     * separated by a comma and a space. The list and capacity are separated
     * by a colon. </p>
     * <p>For example, the string <code>[A, B, C]:6</code> represents a pipe
     * with 3 elements, where A is first element, C is the last element,
     * and the pipe has a capacity of 6. </p>
     *
     * @return the string representation of this pipe
     */
    @Override
    String toString();

}