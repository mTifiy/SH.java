package com.shpp.p2p.cs.collections.myStack;

import com.shpp.p2p.cs.collections.myArrayList.MyArrayList;

import java.util.EmptyStackException;

/**
 * This class is a simplified implementation of the Stack class from the java.
 * This class creates an array MyArrayList and uses its methods to implement the tasks of the class.
 * The principle of the collection is based on the principle of LIFO
 *
 * @param <E> the type of the value that the collection works with
 */
public class MyStack<E> {

    /**
     * A collection of the MyArrayList class, which is used to implement the methods of this class.
     */
    private final MyArrayList<E> array = new MyArrayList<>();

    /**
     * The method calls the add() method of the MyArrayList class.
     * thereby adds the element to the head of the list.
     *
     * @param value adds an element to the beginning of the list
     */
    public void push(E value) {
        array.add(value);
    }

    /**
     * The method returns the value at the head of the list and removes it
     * the value is removed by calling the remove() method of the remove class
     * If the stack is empty, throws an EmptyStackException
     *
     * @return returns the value at the head of the list
     */
    public E pop() {
        try {
            E returnValue = array.get(array.size() - 1);
            array.remove(array.size() - 1);
            return returnValue;
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }

    /**
     * The method returns the values at the head of the list
     * If the stack is empty, throws an EmptyStackException
     *
     * @return returns the values at the head of the list
     */
    public E peek() {
        try {
            return array.get(array.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }

    /**
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return array.isEmpty();
    }


    /**
     * This method converts an array of values into a string
     * If the array is empty, empty cops are returned
     * If the array contains values, the values are added to the string one by one, separated by commas.
     *
     * @return string with array values
     */
    public String toString() {

        int index = 0;
        StringBuilder s = new StringBuilder(index >= array.size() ? "[]" : "[");

        for (; index < array.size(); index++) {
            s.append(array.get(index));
            if (index < array.size() - 1) s.append(", ");
            else s.append("]");
        }
        return s.toString();
    }

}
