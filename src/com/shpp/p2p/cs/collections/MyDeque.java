package com.shpp.p2p.cs.collections;

/**
 * This interface stores the methods that are described and implemented by LinkedList
 * And also extends the MyList interface
 *
 * @param <E> The type of the object that the classes work with
 */
public interface MyDeque<E> extends MyList<E> {

    /**
     * The method adds a value to the end of the list
     *
     * @param value added value to the end of the list
     */
    void addLast(E value);

    /**
     * The method adds a value to the beginning of the list
     *
     * @param value added value to the beginning of the list
     */
    void addFirst(E value);

    /**
     * The method removes a value from the end of the list
     */
    void removeLast();

    /**
     * The method removes a value from the beginning of the list
     */
    void removeFirst();

    /**
     * This method returns the value from the beginning of the list, and removes it from the list itself
     *
     * @return value from the beginning of the list
     */
    E pollFirst();

    /**
     * This method returns a value from the end of the list, and removes it from the list itself
     *
     * @return value from end of list
     */
    E pollLast();

    /**
     * This method returns the value at the last node of the list
     *
     * @return the value at the last node of the list
     */
    E getLast();

    /**
     * This method returns the value at the first node of the list
     *
     * @return the value at the first node of the list
     */
    E getFirst();

}
