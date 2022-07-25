package com.shpp.p2p.cs.collections;

public interface MyList<E> {

    /**
     * Метод добавляет значение в конец коллекции
     * @param value значение переданное в качестве аргумента
     */
    void add(E value);

    /**
     * Метод добавляет значение в коллекцию под указанным индексом,
     * смещяя остальные значения в коллекции
     * @param index место добавления значения
     * @param value добавляемое значение
     */
    void add(int index, E value);

    /**
     * Метод возвращяет значение из коллекции под указанным индексом.
     * @param index индекс значения в коллекции
     * @return запрашиваемое значение
     */
    E get(int index);

    /**
     * @return текущий размер коллекции
     */
    int size();

    /**
     * Метод заменяет значение в коллекции под указаным индексом
     * @param index индекс заменяемого значение
     * @param value значение для замены
     */
    void set(int index, E value);

    /**
     * Метод удаляет значение из коллекции под указаным индексом
     * @param index индекс удаляемого значения
     */
    void remove(int index);

    /**
     * return true если коллекция пустая
     * return false если в коллеции находяться элементы.
     */
    boolean isEmpty();

    /**
     * Метод удаляет все данные из коллекции
     */
    void clear();

}