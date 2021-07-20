package ru.benefic.geekhome.task.collections;

import java.util.Arrays;

public class MyArrayList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] list;
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        list = (T[]) new Object[capacity];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void add(T item) {
        resize();
        list[size] = item;
        size++;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index: " + index);
        }
    }

    private void resize() {
        if ((float) size / list.length > 0.9) {
            int newCapacity = list.length + list.length / 2;
            list = Arrays.copyOf(list, newCapacity);
        }
    }

    public void trim() {
        if (list.length > size) {
            list = Arrays.copyOf(list, size);
        }
    }

    public void remove(int index) {
        checkIndex(index);
        if (size - 1 - index >= 0) System.arraycopy(list, index + 1, list, index, size - 1 - index);
        size--;
        list[size] = null;
    }

    public boolean remove(T item) {
        int k = indexOf(item);
        if (k == -1) {
            return false;
        }
        remove(k);
        return true;
    }

    public void add(int index, T item) {
        resize();
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index: " + index);
        }

        System.arraycopy(list, index, list, index + 1, size - index);

        list[index] = item;
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        return list[index];
    }

    public void set(int index, T item) {
        checkIndex(index);
        list[index] = item;
    }

    public int size() {
        return size;
    }

    public final int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(list[i]).append(", ");
        }
        if (size > 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("]");
        return sb.toString();
    }

}
