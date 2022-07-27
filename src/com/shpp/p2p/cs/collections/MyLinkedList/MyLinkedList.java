package com.shpp.p2p.cs.collections.MyLinkedList;

import com.shpp.p2p.cs.collections.MyDeque;
import com.shpp.p2p.cs.collections.MyList;

import java.util.Iterator;

public class MyLinkedList<E> implements MyDeque<E>, MyList<E> {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;
    private Node<E> lastReturnNode;

    @Override
    public void add(E value) {
        if (size == 0) {
            first = last = new Node<>(value, null, null);
        } else {
            Node<E> newNode = first;
            first = new Node<>(value, null, newNode);
            newNode.next = first;
        }
        size++;
    }

    @Override
    public void add(int index, E value) {

        if (index < size && index > 0) {

            Node<E> nodePrev = last;
            Node<E> nodeNext = nodePrev.next;

            for (int i = 0; i < index - 1; i++) {
                nodePrev = nodePrev.next;
                nodeNext = nodePrev.next;
            }

            Node<E> newNode = new Node<>(value, nodeNext, nodePrev);

            nodePrev.next = newNode;
            nodeNext.previous = newNode;
            size++;

        } else if (index == 0){
            Node<E> newNode = new Node<>(value, last, null);
            last = newNode;
            size++;
        }
        else throw new IndexOutOfBoundsException();
    }

    @Override
    public E get(int index) {

        if (index < size && index >= 0) {
            lastReturnNode = last;

            for (int i = 0; i <= index - 1; i++)
                lastReturnNode = lastReturnNode.next;

            return lastReturnNode.value;
        } else throw new IndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void set(int index, E value) {
        if (index < size && index >= 0) {

            Node<E> needNode = last;
            for (int i = 0; i < index; i++)
                needNode = needNode.next;

            needNode.value = value;
        } else throw new IndexOutOfBoundsException();

    }

    @Override
    public void remove(int index) {
        if (index == size - 1) {
            removeLast();
        } else if (index == 0) {
            removeFirst();
        } else if (index < size && index >= 0) {

            Node<E> nodePrev = last;
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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        last = null;
        first = null;
        lastReturnNode = null;
        size = 0;
        System.gc();
    }

    @Override
    public void addLast(E value) {
        if (size == 0) {
            first = last = new Node<>(value, null, null);
        } else {
            Node<E> newNode = first;
            first = new Node<>(value, null, first);
            newNode.next = first;
        }
        size++;
    }

    @Override
    public void addFirst(E value) {
        if (size == 0) {
            first = last = new Node<>(value, null, null);
        } else {
            Node<E> newNode = last;
            last = new Node<>(value, newNode, null);
            newNode.previous = last;
        }
        size++;
    }


    @Override
    public void removeLast() {
        if (size == 1) {
            clear();
        } else {
            first = first.previous;
            first.next = null;
            size--;
            System.gc();
        }
    }

    @Override
    public void removeFirst() {
        if (size == 1) {
            clear();
        } else {
            last = last.next;
            last.previous = null;
            size--;
            System.gc();
        }
    }

    @Override
    public E pollLast() {
        E value = first.value;
        removeLast();
        return value;
    }

    @Override
    public E pollFirst() {
        E value = last.value;
        removeFirst();
        return value;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIteratorLinked<E>(last);
    }

    @Override
    public String toString() {
        Node<E> currentNode = last;
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
