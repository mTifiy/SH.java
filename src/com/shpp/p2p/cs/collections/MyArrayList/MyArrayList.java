package com.shpp.p2p.cs.collections.MyArrayList;

import com.shpp.p2p.cs.collections.MyList;

import java.util.Iterator;

/**
 * Данный клас представляет собой упрощенный класс ArrayList из java.util
 * Данный клас являеться колекцией, которая подстраивает размер массива под потребности пользователя.
 * Клас хранит в себе массив с типом Object который потом приводиться к требуемому типу при помощи generify.
 * Хранение данный происходит в примитивном массиве. доступ к данным осуществляеться путем вызова методов
 * с указанием индекса.
 * Массив данных в колекции имеет больший размер чем доступно пользователю коллекции. Сделано это с целью
 * ускорить работу класса.
 * При изминении размера коллекции (добавлении или удалении данных) если размер коллекции становиться равным размеру
 * массива создаеться клон коллекции после чего он копируеться в заново обьявленный массив с увеличеным размером,
 * и добавлением нового элемента.
 * @param <E> Тип данных с которым будет работать коллекция
 */
public class MyArrayList<E> implements MyList<E> {

    /**
     * Размер вместительности массива по дефолту
     */
    private final static int DEFAULT_VOLUME = 10;
    /**
     * текущий размер вместимости массива
     */
    private int volume;
    /**
     * текущий размер коллекции
     */
    private int size = 0;
    /**
     * обьявление массива
     */
    private E[] elementData;

    /**
     * Конструктор класса который устанавливает размер массива по дефолту, инициализирует текущее значение
     * вместительности массива и инициализирует сам массив.
     */
    public MyArrayList() {
        volume = DEFAULT_VOLUME;
        elementData = (E[]) new Object[DEFAULT_VOLUME];
    }

    /**
     * Конструктор класса который устанавливает размер массива от пользователя, инициализирует текущее значение
     * вместительности массива и инициализирует сам массив.
     * @param volume размер массива устанавливаемый пользователем
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
     * Данный метод добавляет новое значение в начало коллекции
     * Сперва проверяеться размер массива, и если он не удовлетворяет требованию, размер
     * массива увеличиваеться.
     * Затем создаеться временная копия массива, заново инициализируеться массив колекции с указаным размером,
     * в него копируються данные из временного массива, и в конец коллекции добавляеться новый елемент.
     * Временному массиву присваиваеться значение null и вызываеться garbed collector
     * @param value елемент который требуеться добавить в конец коллекции
     */
    @Override
    public void add(E value) {
        checkTheVolume();
        E[] temporaryArray = elementData;
        elementData = (E[]) new Object[volume];
        System.arraycopy(temporaryArray, 0, elementData, 0, temporaryArray.length);
        elementData[size++] = value;
        temporaryArray = null;
        System.gc();
    }

    /**
     * Данный метод добавляет елемент в указанный пользователем индекс.
     * Сперва проверяеться размер массива, и если он не удовлетворяет требованию, размер
     * массива увеличиваеться.
     * Затем создаеться временная копия массива, заново инициализируеться массив колекции с указаным размером,
     * в него копируються данные из временного массива до указанного индекса, затем добавляеться указаный елемент,
     * затем копируеться оставшаяся часть массива, и увеличиваеться размер коллекции.
     * Метод может вызвать IndexOutOfBoundsException если указываемые индекс находиться в не размера коллекции
     * @param index индекс местоположения добовляемого элемента в массиве
     * @param value добавляемый елемент
     */
    @Override
    public void add(int index, E value) {
        checkTheVolume();
        if (size >= index) {

            E[] temporaryArray = elementData;
            elementData = (E[]) new Object[volume];

            for (int i = 0, z = 0; i < temporaryArray.length; i++, z++) {
                if (i == index) {
                    elementData[i] = value;
                    i++;
                    if (index == size) continue;
                }
                elementData[i] = temporaryArray[z];
            }
            size++;
        } else throw new IndexOutOfBoundsException();
    }

    /**
     * Данный метод удаляет значение зи массива под указаным индексом.
     * создаеться временная копия массива, заново инициализируеться массив колекции с указаным размером,
     * в него копируються данные из временного массива до указанного индекса, затем пропускаеться указаный елемент,
     * затем копируеться оставшаяся часть массива, и размер коллекции уменьшаеться.
     * Метод может вызвать IndexOutOfBoundsException если указываемые индекс находиться в не размера коллекции
     * @param index индекс значения в массиве, который необходимо удалить
     */
    @Override
    public void remove(int index) {
        if (size > 0 && index < size) {
            E[] temporaryArray = elementData;
            elementData = (E[]) new Object[temporaryArray.length];
            for (int i = 0, z = 0; i < temporaryArray.length; i++, z++) {
                if (i == index) {
                    z--;
                    continue;
                }
                elementData[z] = temporaryArray[i];
            }
            size--;
        } else throw new IndexOutOfBoundsException();
    }

    /**
     * Данный метод изменяет значение в массиве под указонным индексом на значение введенное пользователем.
     * Метод может вызвать IndexOutOfBoundsException если указываемые индекс находиться в не размера коллекции
     * @param index индекс заменяемого значения
     * @param value новое значение
     */
    @Override
    public void set(int index, E value) {
        if (size > 0 && index < size)
            elementData[index] = value;
        else throw new IndexOutOfBoundsException();
    }

    /**
     * Данный метод возвращяет значение под указанным пользователем индексом.
     * Метод может вызвать IndexOutOfBoundsException если указываемые индекс находиться в не размера коллекции
     * @param index индекс запрашеваемого значения
     * @return запрашивоемое значение
     */
    @Override
    public E get(int index) {
        if (size > 0 && index < size)
            return elementData[index];
        else throw new IndexOutOfBoundsException();

    }

    /**
     * Данный метод заново инициализирует массив, тем самым удаляя все данный с него.
     */
    @Override
    public void clear() {
        elementData = (E[]) new Object[DEFAULT_VOLUME];
        size = 0;
    }

    /**
     * @return текущий размер коллекции
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * return true если коллекция пустая
     * return false если в коллеции находяться элементы.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Данный метод проверяет вместительность массива, и при необходимости увеличивает его.
     */
    private void checkTheVolume() {
        if (size == volume) volume = (size * 3) / 2 + 1;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIteratorArray<E>(elementData, size);
    }
}
