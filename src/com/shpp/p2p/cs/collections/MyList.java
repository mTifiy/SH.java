package com.shpp.p2p.cs.collections;

/**
 * This interface stores the methods that are described and implemented in ArrayList and LinkedList
 *
 * @param <E> The type of the object that the classes work with
 */
public interface MyList<E> extends Iterable<E> {

    /**
     * The method adds a value to the end of the collection
     *
     * @param value value passed as argument
     */
    void add(E value);

    /**
     * The method adds a value to the collection at the specified index,
     * offsetting the rest of the values in the collection
     *
     * @param index where to add the value
     * @param value value to add
     */
    void add(int index, E value);

    /**
     * The method returns a value from the collection at the specified index.
     *
     * @param index the index of the value in the collection
     * @return requested value
     */
    E get(int index);

    /**
     * @return current collection size
     */
    int size();

    /**
     * The method replaces the value in the collection at the specified index
     *
     * @param index index of the value being replaced
     * @param value value to replace
     */
    void set(int index, E value);

    /**
     * The method removes the value from the collection at the specified index
     *
     * @param index the index of the value to be removed
     */
    void remove(int index);

    /**
     * return true if the collection is empty
     * return false if the collection contains elements.
     */
    boolean isEmpty();

    /**
     * The method removes all data from the collection
     */
    void clear();

}
