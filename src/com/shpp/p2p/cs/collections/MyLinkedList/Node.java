package com.shpp.p2p.cs.collections.MyLinkedList;

public class Node<E> {

    public E value;
    public Node<E> next;
    public Node<E> previous;

    Node(E value, Node<E> next, Node<E> previous){
        this.value = value;
        this.next = next;
        this.previous = previous;
    }
}
