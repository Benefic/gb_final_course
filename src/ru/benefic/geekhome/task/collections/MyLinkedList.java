package ru.benefic.geekhome.task.collections;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public MyLinkedList() {
        first = null;
        last = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iter();
    }

    public ListIterator<T> listIterator() {
        return new ListIter();
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void insertFirst(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.setNext(first);
        if (isEmpty()) {
            last = newNode;
        } else {
            first.setPrev(newNode);
        }
        first = newNode;
        size++;
    }

    public void insertLast(T item) {
        Node<T> newNode = new Node<>(item);
        if (isEmpty()) {
            first = newNode;
        } else {
            newNode.setPrev(last);
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return first.getValue();
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return last.getValue();
    }

    public T removeFirst() {
        T oldFirst = getFirst();
        first = first.getNext();
        if (isEmpty()) {
            last = null;
        } else {
            first.setPrev(null);
        }
        size--;
        return oldFirst;
    }

    public T removeLast() {
        T oldLast = getLast();
        if (last.getPrev() != null) {
            last.getPrev().setNext(null);
        } else {
            first = null;
        }
        last = last.getPrev();
        size--;
        return oldLast;
    }

    public final int indexOf(T item) {
        Node<T> current = first;
        int index = 0;
        while (current != null) {
            if (current.getValue().equals(item)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) > -1;
    }

    public void insert(int index, T item) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        if (index == 0) {
            insertFirst(item);
            return;
        }
        if (index == size) {
            insertLast(item);
            return;
        }
        Node<T> current = first;
        int i = 0;
        while (i < index - 1) {
            current = current.getNext();
            i++;
        }
        Node<T> newNode = new Node<>(item);
        newNode.setNext(current.getNext());
        newNode.setPrev(current);
        current.setNext(newNode);
        newNode.getNext().setPrev(newNode);
        size++;
    }

    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node<T> current = first;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        T temp = current.getValue();
        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());
        size--;
        return temp;
    }

    public boolean remove(T item) {
        if (isEmpty()) {
            return false;
        }
        if (first.getValue().equals(item)) {
            removeFirst();
            return true;
        }
        Node<T> current = first;
        while (current != null && !current.getValue().equals(item)) {
            current = current.getNext();
        }
        if (current == null) {
            return false;
        }
        if (current == last) {
            removeLast();
            return true;
        }
        current.getPrev().setNext(current.getNext());
        current.getNext().setPrev(current.getPrev());
        size--;
        return true;
    }

    @Override
    public String toString() {
        Node<T> current = first;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.getValue()).append(", ");
            current = current.getNext();
        }
        if (!isEmpty()) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(T item) {
            this.value = item;
        }

        public Node(T item, Node<T> next) {
            this.value = item;
            this.next = next;
        }

        public Node(T value, Node<T> next, Node<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }
    }

    private class Iter implements Iterator<T> {
        Node<T> current = new Node<>(null, first);

        @Override
        public boolean hasNext() {
            return current.getNext() != null;
        }

        @Override
        public T next() {
            current = current.getNext();
            return current.getValue();
        }

        @Override
        public void remove() {
            Node<T> next = current.getNext();
            current.getPrev().setNext(next);
            next.setPrev(current.getPrev());
            current = next;
            size--;
        }
    }

    private class ListIter implements ListIterator<T> {

        Node<T> next = first;
        Node<T> previous = next.prev;
        Node<T> current;
        int nextIndex;

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NullPointerException();
            }
            previous = next;
            next = next.getNext();
            nextIndex++;
            current = previous;
            return current.getValue();
        }

        @Override
        public boolean hasPrevious() {
            return previous != null;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NullPointerException();
            }
            next = previous;
            previous = previous.getPrev();
            nextIndex--;
            current = next;
            return current.getValue();
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            if (nextIndex == 0) {
                throw new IndexOutOfBoundsException("index = -1");
            }
            return nextIndex - 1;
        }

        @Override
        public void set(T item) {
            checkCurrent();
            current.setValue(item);
        }

        @Override
        public void remove() {
            if (size == 0) return;
            checkCurrent();
            if (current == previous) {
                previous = current.getPrev();
                previous.setNext(next);
                if (next != null) {
                    next.setPrev(previous);
                }
                current = previous;
            } else {
                next = current.getNext();
                next.setPrev(previous);
                if (previous != null) {
                    previous.setNext(next);
                }
                current = next;
            }
            size--;
        }

        @Override
        public void add(T item) {
            checkCurrent();
            current = new Node<>(item);
            current.setNext(next);
            current.setPrev(previous);
            if (previous != null) {
                previous.setNext(current);
            } else {
                first = current;
            }
            if (next != null) {
                next.setPrev(current);
            } else {
                last = current;
            }
            size++;
        }

        private void checkCurrent() {
            if (current == null) {
                throw new IllegalStateException();
            }
        }
    }
}
