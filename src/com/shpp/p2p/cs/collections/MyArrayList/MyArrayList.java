package com.shpp.p2p.cs.collections.myArrayList;

import com.shpp.p2p.cs.collections.MyList;

import java.util.Iterator;

/**
 * This class is a simplified ArrayList class from java.util
 * This class is a collection that adjusts the size of the array to the needs of the user.
 * The class stores an array of type Object which is then converted to the required type using generify.
 * Data is stored in a primitive array. data is accessed by calling methods
 * with index.
 * The data array in the collection has a larger size than is available to the user of the collection. It was done for the purpose
 * speed up the class.
 * When changing the size of the collection (adding or deleting data), if the size of the collection becomes equal to the size
 * of the array, a clone of the collection is created, after which it is copied into a newly declared array with an increased size,
 * and adding a new element.
 *
 * @param <E> The data type that the collection will work with
 */
public class MyArrayList<E> implements MyList<E> {

    /**
     * Default array capacity size
     */
    private final static int DEFAULT_VOLUME = 10;

    /**
     * Current array capacity size
     */
    private int volume;

    /**
     * Current collection size
     */
    private int size = 0;

    /**
     * Array declaration
     */
    private E[] elementData;

    /**
     * The class constructor, which sets the default size of the array, initializes the current value
     * the capacity of the array and initializes the array itself.
     */
    public MyArrayList() {
        volume = DEFAULT_VOLUME;
        elementData = (E[]) new Object[DEFAULT_VOLUME];
    }

    /**
     * Class constructor that sets the size of the array from the user, initializes the current value
     * the capacity of the array and initializes the array itself.
     *
     * @param volume array size set by the user
     */
    MyArrayList(int volume) {
        this.volume = volume;
        try {
            elementData = (E[]) new Object[volume];
        } catch (IllegalArgumentException e) {
            e.getStackTrace();
        }

    }

    /**
     * This method adds a new value to the beginning of the collection
     * First, the size of the array is checked, and if it does not satisfy the requirement, the size
     * array is increasing.
     * Then a temporary copy of the array is created, the collection array is re-initialized with the specified size,
     * The data from the temporary array is copied into it, and a new element is added to the end of the collection.
     * Temporary array is set to null and called garbed collector
     *
     * @param value element to be added to the end of the collection
     */
    @Override
    public void add(E value) {

        checkTheVolume();

        E[] temporaryArray = elementData;
        elementData = (E[]) new Object[volume];

        System.arraycopy(temporaryArray, 0, elementData, 0, temporaryArray.length);

        elementData[size++] = value;

        temporaryArray = null; // for gc
        System.gc();
    }

    /**
     * This method adds an element to a user-specified index.
     * First, the size of the array is checked, and if it does not satisfy the requirement, the size
     * array is increasing.
     * Then a temporary copy of the array is created, the collection array is re-initialized with the specified size,
     * data from the temporary array is copied to it up to the specified index, then the specified element is added,
     * then copy the rest of the array and increase the size of the collection.
     * Temporary array is set to null and called garbed collector
     * The method may throw an IndexOutOfBoundsException if the specified index is not in the size of the collection
     *
     * @param index index of the location of the added element in the array
     * @param value element to add
     */
    @Override
    public void add(int index, E value) {

        checkTheVolume();

        if (size >= index) {

            int oldSize = size;
            size++;
            E[] temporaryArray = elementData;
            elementData = (E[]) new Object[volume];

            for (int newIndex = 0, oldIndex = 0; oldIndex < oldSize; newIndex++, oldIndex++) {
                if (newIndex == index) {
                    elementData[newIndex] = value;
                    newIndex++;
                }
                elementData[newIndex] = temporaryArray[oldIndex];
            }

            temporaryArray = null; // for gc
            System.gc();

        } else throw new IndexOutOfBoundsException();

    }

    /**
     * This method removes the value of the array at the specified index.
     * a temporary copy of the array is created, the collection array is re-initialized with the specified size,
     * data is copied into it from the temporary array up to the specified index, then the specified element is skipped,
     * then the rest of the array is copied, and the size of the collection is reduced.
     * Temporary array is set to null and called garbed collector
     * The method may throw an IndexOutOfBoundsException if the specified index is not in the size of the collection
     *
     * @param index the index of the value in the array to be removed
     */
    @Override
    public void remove(int index) {

        if (size > 0 && index < size) {

            E[] temporaryArray = elementData;
            elementData = (E[]) new Object[temporaryArray.length];

            for (int oldIndex = 0, newIndex = 0; oldIndex < size; oldIndex++, newIndex++) {
                if (oldIndex == index) {
                    newIndex--;
                    continue;
                }
                elementData[newIndex] = temporaryArray[oldIndex];
            }

            size--;
            temporaryArray = null; // for gc
            System.gc();

        } else throw new IndexOutOfBoundsException();
    }

    /**
     * This method changes the value in the array under the specified index to the value entered by the user.
     * The method may throw an IndexOutOfBoundsException if the specified index is not in the size of the collection
     *
     * @param index index of value to replace
     * @param value new value
     */
    @Override
    public void set(int index, E value) {
        if (size > 0 && index < size)
            elementData[index] = value;
        else throw new IndexOutOfBoundsException();
    }

    /**
     * This method returns the value at the index specified by the user.
     * The method may throw an IndexOutOfBoundsException if the specified index is not in the size of the collection
     *
     * @param index index of requested value
     * @return requested value
     */
    @Override
    public E get(int index) {
        if (size > 0 && index < size)
            return elementData[index];
        else throw new IndexOutOfBoundsException();

    }

    /**
     * This method reinitialized the array, thereby deleting all data from it.
     */
    @Override
    public void clear() {
        elementData = (E[]) new Object[DEFAULT_VOLUME];
        size = 0;
    }

    /**
     * @return current collection size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * return true if the collection is empty * return false if the collection contains elements.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This method checks the capacity of the array and increases it if necessary.
     */
    private void checkTheVolume() {
        if (size == volume) volume = (size * 3) / 2 + 1;
    }

    /**
     * This method is needed for the iterator to work
     * Collection user does not need it
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIteratorArray<>(elementData, size);
    }

    /**
     * This method converts an array of values into a string
     * If the array is empty, empty cops are returned
     * If the array contains values, the values are added to the string one by one, separated by commas.
     *
     * @return string with array values
     */
    @Override
    public String toString() {

        int index = 0;
        StringBuilder s = new StringBuilder(index >= size ? "[]" : "[");

        for (; index < size; index++) {
            s.append(elementData[index]);
            if (index < size - 1) s.append(", ");
            else s.append("]");
        }
        return s.toString();
    }
}

