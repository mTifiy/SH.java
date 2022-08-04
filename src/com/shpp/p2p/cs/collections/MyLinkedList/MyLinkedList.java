package com.shpp.p2p.cs.collections.myLinkedList;

import com.shpp.p2p.cs.collections.MyDeque;
import com.shpp.p2p.cs.collections.MyQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class implements a simplified LinkedList.
 * The class stores the nodes of the linked list.
 * The class fields store information about the last and first nodes of the list,
 * also about the node to which access was last requested and the number
 * list nodes.
 * The class implements and implements two interfaces.
 * Class methods work in such a way that access to the nodes of the list is carried out by navigating
 * through each node from the beginning to the required node or the end of the list, and vice versa.
 *
 * @param <E> The type of data that the class works with.
 */
public class MyLinkedList<E> implements MyDeque<E>, MyQueue<E> {

    /**
     * List size
     */
    private int size = 0;

    /**
     * First node in the list
     */
    private Node<E> last;

    /**
     * last node in the list
     */
    private Node<E> first;

    /**
     * Last requested node.
     */
    private Node<E> lastReturnNode;

    /**
     * This method creates a new node with the specified value at the head of the list, and links it
     * with the previous one. If the list is empty, the previous value is null
     * The size of the list also increases
     *
     * @param value value passed as argument
     */
    @Override
    public void add(E value) {
        if (size == 0) {
            last = first = new Node<>(value, null, null);
        } else {
            Node<E> newNode = last;
            last = new Node<>(value, null, newNode);
            newNode.next = last;
        }
        size++;
    }

    /**
     * This method returns the first element in the list
     * if the list is empty, returns a NoSuchElementException;
     * The method implements the MyQueue interface
     *
     * @return returns the first element in the list
     */
    @Override
    public E element() {
        if (size > 0)
            return first.value;
        else throw new NoSuchElementException();
    }

    /**
     * The method removes the first node in the list by reassigning links and a variable that stores in itself
     * first node of the list
     * If the list has one value, the clear() method is called
     */
    @Override
    public void remove() {
        removeFirst();
    }

    /**
     * returns the element from the head of the queue.
     * Returns null if the queue is empty. The element is not removed.
     *
     * @return returns the element from the head of the queue.
     */
    @Override
    public E peek() {
        if (size > 0)
            return first.value;
        else return null;
    }

    /**
     * returns the element from the head of the queue and removes it.
     * Returns null if the queue is empty.
     *
     * @return returns an element from the head of the queue and removes it.
     */
    @Override
    public E poll() {
        if (size > 0) {
            return pollFirst();
        } else return null;
    }

    /**
     * tries to add value to the queue.
     * Returns true if value is added, false otherwise.
     *
     * @param value passed as argument
     * @return Returns true if value is added, false otherwise.
     */
    @Override
    public boolean offer(E value) {
        addFirst(value);
        return first.value.equals(value);
    }

    /**
     * This method adds a new node with the specified value under the specified index in the list.
     * We reach the specified index from the beginning of the list through the for loop.
     * Before the desired index and at the specified one, we save the nodes using a new link.
     * Create a node with a new value and bind it to the saved nodes.
     * Reassign node links, and increase the size.
     * If the specified index is not in the size of the list, the method throws an IndexOutOfBoundsException error.
     * If the index is zero, just create a new node for the first value, specifying anchor points,
     * and increasing the list size value.
     *
     * @param index where to add the value
     * @param value value to add
     */
    @Override
    public void add(int index, E value) {

        if (index < size && index > 0) {

            Node<E> nodePrev = first;
            Node<E> nodeNext = nodePrev.next;

            for (int i = 0; i < index - 1; i++) {
                nodePrev = nodePrev.next;
                nodeNext = nodePrev.next;
            }

            Node<E> newNode = new Node<>(value, nodeNext, nodePrev);

            nodePrev.next = newNode;
            nodeNext.previous = newNode;
            size++;

        } else if (index == 0) {
            first = new Node<>(value, first, null);
            size++;
        } else throw new IndexOutOfBoundsException();
    }

    /**
     * This method returns the value at the specified index, and saves information about the last
     * requested node.
     * The transition to the requested node occurs through the for loop.
     *
     * @param index the index of the value in the collection
     * @return the value at the requested index.
     */
    @Override
    public E get(int index) {

        if (index < size && index >= 0) {
            lastReturnNode = first;

            for (int i = 0; i <= index - 1; i++)
                lastReturnNode = lastReturnNode.next;

            return lastReturnNode.value;
        } else throw new IndexOutOfBoundsException();
    }

    /**
     * @return the size of the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * The method changes the value at the specified index.
     *
     * @param index index of the value being replaced
     * @param value value to replace
     */
    @Override
    public void set(int index, E value) {
        if (index < size && index >= 0) {

            Node<E> needNode = first;
            for (int i = 0; i < index; i++)
                needNode = needNode.next;

            needNode.value = value;
        } else throw new IndexOutOfBoundsException();

    }

    /**
     * This method removes the value in the list under the specified index.
     * If the value is at the beginning or end of the list, the removeLast() and removeFirst() methods are called.
     * If the list holds a single value, the clear() method is called.
     * Removing a value from the middle of the list occurs by going to the required node
     * through a for loop, and reassigning the links of the past and previous index node.
     *
     * @param index the index of the value to be removed
     */
    @Override
    public void remove(int index) {
        if (index == size - 1) {
            removeLast();
        } else if (index == 0) {
            removeFirst();
        } else if (index < size && index >= 0) {

            Node<E> nodePrev = first;
            Node<E> nodeNext = nodePrev.next.next;

            for (int i = 0; i < index - 1; i++) {
                nodePrev = nodePrev.next;
                nodeNext = nodePrev.next.next;
            }
            nodePrev.next = nodeNext;
            nodeNext.previous = nodePrev;
            size--;
            System.gc();
        } else if (size == 1) {
            clear();
        } else throw new IndexOutOfBoundsException();
    }

    /**
     * false - if the list is empty
     * true - if the list has elements.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * The method removes all nodes of the list
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        lastReturnNode = null;
        size = 0;
        System.gc();
    }

    /**
     * Method to add value to the head of the list.
     * If the list is empty, the method creates a new node with null links
     * If the list has nodes, the method creates a new node, and reassigns the link of the previous one.
     *
     * @param value added value to the beginning of the list
     */
    @Override
    public void addFirst(E value) {
        if (size == 0) {
            last = first = new Node<>(value, null, null);
        } else {
            Node<E> newNode = first;
            first = new Node<>(value, newNode, null);
            newNode.previous = first;
        }
        size++;
    }

    /**
     * Method to add value to the end of the list.
     * If the list is empty, the method creates a new node with null links
     * If the list has nodes, the method creates a new node, and reassigns the link to the next one.
     *
     * @param value added value to the end of the list
     */
    @Override
    public void addLast(E value) {
        if (size == 0) {
            last = first = new Node<>(value, null, null);
        } else {
            Node<E> newNode = last;
            last = new Node<>(value, null, last);
            newNode.next = last;
        }
        size++;
    }


    /**
     * The method removes the first node in the list by reassigning links and a variable that stores in itself
     * first node of the list
     * If the list has one value, the clear() method is called
     */
    @Override
    public void removeFirst() {
        if (size == 1) {
            clear();
        } else if (size > 1) {
            first = first.next;
            first.previous = null;
            size--;
            System.gc();
        }
    }

    /**
     * The method removes the last node in the list by reassigning links and a variable that stores in itself
     * last node of the list
     * If the list has one value, the clear() method is called
     */
    @Override
    public void removeLast() {
        if (size == 1) {
            clear();
        } else if (size > 1) {
            last = last.previous;
            last.next = null;
            size--;
            System.gc();
        }
    }

    /**
     * This method returns the value from the beginning of the list, and removes it from the list itself
     *
     * @return value from the beginning of the list
     */
    @Override
    public E pollFirst() {
        E value = first.value;
        removeFirst();
        return value;
    }

    /**
     * This method returns a value from the end of the list, and removes it from the list itself
     *
     * @return value from end of list
     */
    @Override
    public E pollLast() {
        E value = last.value;
        removeLast();
        return value;
    }

    /**
     * This method returns the value at the first node of the list
     *
     * @return the value at the first node of the list
     */
    @Override
    public E getFirst() {
        return first.value;
    }

    /**
     * This method returns the value at the last node of the list
     *
     * @return the value at the last node of the list
     */
    @Override
    public E getLast() {
        return last.value;
    }

    /**
     * This method is needed for the iterator to work
     * Collection user does not need it
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIteratorLinked<>(first);
    }

    /**
     * This method converts a list of values into a string
     * If the list is empty, empty cops are returned
     * If the list contains values, values are added to the string one by one, separated by commas.
     *
     * @return string with list values
     */
    @Override
    public String toString() {
        Node<E> currentNode = first;
        StringBuilder s = new StringBuilder(currentNode == null ? "[]" : "[");
        int index = 0;
        for (; index < size; index++) {
            assert currentNode != null;
            s.append(currentNode.value);
            if (index < size - 1) s.append(", ");
            else s.append("]");
            currentNode = currentNode.next;
        }
        return s.toString();
    }
}
