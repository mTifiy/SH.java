package com.shpp.p2p.cs.collections.MyLinkedList;

import java.util.Iterator;
import java.util.function.Consumer;

public class MyIteratorLinked<E> extends MyLinkedList<E> implements Iterator<E> {
    int index = 0;
    int size;
    Node<E> myLink;

    MyIteratorLinked(Node<E> myLink){
        this.myLink = myLink;
    }

    @Override
    public boolean hasNext() {
        return myLink != null;
    }

    @Override
    public E next() {
        E value = myLink.value;
        myLink = myLink.next;
        return value;
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        Iterator.super.forEachRemaining(action);
    }
}
