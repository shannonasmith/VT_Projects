package boundedqueue;
/*
 * CS 5704 (Fall 2023) BoundedQueue
 * @author Shannon Smith (shae1223)
 * @version 2023.09.06
 */

/**
 * A Queue is a first-in first-out (FIFO) data structure.
 * It supports <em>enqueue</em> and <em>dequeue</em> operations.
 * <p>
 * This implementation has an abstract class <code>AbstractQueue</code>
 * and three concrete implementations:<code>ListQueue, CircArrayQueue,</code>
 * and <code>LinkedQueue</code>.
 * <p>
 * For queue = [A, B, C], A is the first element in the queue, and would be the
 * first element removed from the queue.
 * <p>
 * @param <E> the type of elements in this queue
 */
public interface Queue<E> extends Iterable<E> {

    /**
     * Adds the specified element to the beginning of this queue.
     *
     * @param element the element to be added to the end of this queue
     * @throws IllegalStateException    if this queue is full
     * @throws IllegalArgumentException if the element is null
     */
    void enqueue(E element);

    /**
     * Removes the specified element from the beginning of this queue.
     *
     * @throws IllegalStateException    if this queue is full
     * @throws IllegalArgumentException if the element is null
     */
    E dequeue();

    /**
     * Returns the number of elements currently this queue.
     *
     * @return the number of elements in this queue
     */
    int length();

    /**
     * Returns the maximum number of elements this queue can hold.
     *
     * @return the maximum number of elements this queue can hold
     */
    int capacity();

    /**
     * Creates a new, empty bounded queue with the same capacity as
     * the calling queue.
     *
     * @return new empty queue with the same capacity as this queue
     */
    Queue<E> newInstance();

    /**
     * Removes all the elements from this queue.
     */
    void clear();

    /**
     * Returns true if this queue contains NO elements, otherwise returns false.
     *
     * @return true if this queue is empty
     */
    boolean isEmpty();

    /**
     * Returns true if this queue is full, otherwise returns false.
     *
     * @return true if this queue is full
     */
    boolean isFull();

    /**
     * Takes another queue and empties its queue argument (that).
     *
     * @param that queue to be appended
     * @throws IllegalStateException    if this queue is full
     * @throws IllegalArgumentException if this queue is empty
     */
    void appendAll(Queue<E> that);

    /**
     * Returns a copy of this queue. The elements in the copy are
     * references to the elements in this queue.
     *
     * @return a copy of this queue
     */
    Queue<E> copy();

    /**
     * Retrieves the first element of this queue.
     *
     * @return the first element in this queue
     */
    E first();

    /**
     * Retrieves the last element of this queue.
     *
     * @return the last element in this queue
     */
    E last();

    /**
     * Returns true if the specified object is equal to this queue.
     * To be equal, the specified object must be a bounded queue object
     * with the same capacity, the same number of elements that have the
     * same values, and they must be in the same order.
     *
     * @param obj the object to be tested for equality compared to this queue
     * @return true if the specified object is equal to this queue,
     * otherwise false
     */
    @Override
    boolean equals(Object obj);

    /**
     * Returns the hash code of this queue.
     *
     * @return the hash code of this queue
     */
    @Override
    int hashCode();

    /**
     * Returns the string representation of this queue.
     * <p>
     * The string representation contains a list of this queue's elements
     * from beginning to end, And also contains the queue's capacity. The
     * list is enclosed in square brackets. Adjacent elements are separated
     * by a comma and a space. The list and capacity are separated by a colon.
     * For example, the string [A, B, C]:6 represents a queue with a bound of 6,
     * where A is the first element in the queue and C is the last element in
     * the queue.
     *
     * @return the string representation of this queue
     */
    @Override
    String toString();

}
