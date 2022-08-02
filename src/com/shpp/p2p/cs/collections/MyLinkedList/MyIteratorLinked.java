package com.shpp.p2p.cs.collections.myLinkedList;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * This class is used to work with an iterator.
 * It has rewritten functions for the iterator to work correctly
 *
 * @param <E> type of parameter being processed
 */
public class MyIteratorLinked<E> implements Iterator<E> {

    /**
     * last LinkedList node
     */
    Node<E> myLink;

    MyIteratorLinked(Node<E> myLink) {
        this.myLink = myLink;
    }

    /**
     * the method passes to the iterator information about the existence of the next node in the list
     * true - if the next node exists in the list
     * false - if the next node does not exist in the list
     */
    @Override
    public boolean hasNext() {
        return myLink != null;
    }

    /**
     * The method returns to the iterator the value that is stored in the node,
     * and move on to the next node
     *
     * @return the value that is stored in the node
     */
    @Override
    public E next() {
        E value = myLink.value;
        myLink = myLink.next;
        return value;
    }

    /**
     * The method is needed for forEach to work correctly
     */
    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        Iterator.super.forEachRemaining(action);
    }
}
