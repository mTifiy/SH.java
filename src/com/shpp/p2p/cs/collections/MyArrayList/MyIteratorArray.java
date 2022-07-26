package com.shpp.p2p.cs.collections.MyArrayList;

import java.util.Iterator;
import java.util.function.Consumer;

public class MyIteratorArray<E> implements Iterator<E> {
    private int index = 0;
    private final int size;
    E[] elementData;

    public MyIteratorArray(E[] elementData, int size){
        this.elementData = elementData;
        this.size = size;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public E next() {
        return elementData[index++];
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        Iterator.super.forEachRemaining(action);
    }
}
