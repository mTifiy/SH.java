package com.shpp.p2p.cs.collections.myArrayList;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * This class is used to work with an iterator.
 * It has rewritten functions for the iterator to work correctly
 *
 * @param <E> type of parameter being processed
 */
public class MyIteratorArray<E> implements Iterator<E> {

    /**
     * Current index
     */
    private int index = 0;

    /**
     * Collection size
     */
    private final int size;

    /**
     * Collection
     */
    E[] elementData;

    /**
     * class constructor that initializes class fields with arguments accepted by the constructor
     */
    public MyIteratorArray(E[] elementData, int size) {
        this.elementData = elementData;
        this.size = size;
    }

    /**
     * The method tells the iterator whether the next value exists in the array
     *
     * @return true if the next value exists
     */
    @Override
    public boolean hasNext() {
        return index < size;
    }

    /**
     * Pass a value to the iterator and increment the index
     */
    @Override
    public E next() {
        return elementData[index++];
    }

    /**
     * The method is needed for forEach to work correctly
     */
    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        Iterator.super.forEachRemaining(action);
    }
}
