package com.shpp.p2p.cs.collections.myLinkedList;

/**
 * This class is an element of a linked list.
 * it stores information about the value, as well as about the next and previous element
 *
 * @param <E> The type of data that the class works with.
 */
public class Node<E> {

    /**
     * the value that is stored in the node
     */
    public E value;

    /**
     * Storing the next element of the list
     */
    public Node<E> next;

    /**
     * Storing the previous element of the list
     */
    public Node<E> previous;

    /**
     * Class constructor that initializes class fields
     * @param value the value that is stored in the class
     * @param next Store the next item in the list
     * @param previous Stores the previous item in the list
     */
    Node(E value, Node<E> next, Node<E> previous) {
        this.value = value;
        this.next = next;
        this.previous = previous;
    }
}
