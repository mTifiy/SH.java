package com.shpp.p2p.cs.collections;

/**
 * This interface describes the methods that are used to create the "Queue" collection
 * The collection principle is based on the FIFO principle
 *
 * @param <E> data type that the collection works with
 */
public interface MyQueue<E> {

    /**
     * The method adds a value to the end of the collection
     *
     * @param value passed as argument
     */
    void add(E value);

    /**
     * returns the element from the head of the queue. The element is not removed.
     * If the queue is empty, a NoSuchElementException is thrown.
     * @return returns the element from the head of the queue
     */
    E element();

    /**
     * removes an element from the head of the queue, returning it.
     * Throws a NoSuchElementException if the queue is empty.
     */
    void remove();

    /**
     * returns the element from the head of the queue.
     * Returns null if the queue is empty. The element is not removed.
     * @return returns the element from the head of the queue.
     */
    E peek();

    /**
     * returns the element from the head of the queue and removes it.
     * Returns null if the queue is empty.
     * @return returns an element from the head of the queue and removes it.
     */
    E poll();

    /**
     * tries to add value to the queue.
     * Returns true if value is added, false otherwise.
     *
     * @param value passed as argument
     * @return Returns true if value is added, false otherwise.
     */
    boolean offer(E value);
}
