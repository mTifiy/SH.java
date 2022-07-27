package com.shpp.p2p.cs.collections;

public interface MyDeque<E> {
    void addLast(E value);
    void addFirst(E value);

    void removeLast();
    void removeFirst();
    E pollFirst();
    E pollLast();

    }
