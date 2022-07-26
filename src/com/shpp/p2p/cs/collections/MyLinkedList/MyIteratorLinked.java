package com.shpp.p2p.cs.collections.MyLinkedList;

import java.util.Iterator;
import java.util.function.Consumer;

public class MyIteratorLinked<E> implements Iterator<E> {
     Node<E> element;

    MyIteratorLinked(Node<E> element){
        this.element = element;
    }
    @Override
    public boolean hasNext() {
        return element.next != null;
    }

    @Override
    public E next() {
        return element.next.value;
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        Iterator.super.forEachRemaining(action);
    }
}
