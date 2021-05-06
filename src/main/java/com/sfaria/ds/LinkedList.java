package com.sfaria.ds;

/**
 * A doubly-linked list
 *
 * @author Scott Faria
 */
public final class LinkedList<T> {

    // -------------------- Variables --------------------

    private Node<T> head;
    private Node<T> tail;

    private int size = 0;

    // -------------------- Constructors --------------------

    public LinkedList() {
    }

    // -------------------- Public Methods --------------------

    public final int size() {
        return size;
    }

    public final T get(int index) {
        Node<T> n = getAtIndex(index);
        return n.val;
    }

    public final void add(T item) {
        Node<T> n = new Node<>(item);
        if (tail == null) {
            tail = n;
            head = n;
        }
        n.prev = tail;
        tail.next = n;
        tail = n;
        size++;
    }

    public final int indexOf(T item) {
        int i = 0;
        Node<T> n = head;
        while (i < size) {
            if (n.val.equals(item)) {
                return i;
            }
            n = n.next;
            i++;
        }
        return -1;
    }

    public final T remove(int index) {
        Node<T> n = getAtIndex(index);

        Node<T> prev = n.prev;
        Node<T> next = n.next;

        next.prev = prev;
        prev.next = next;
        size--;

        return n.val;
    }

    public final void add(T item, int index) {
        Node<T> n = getAtIndex(index);
        Node<T> newNode = new Node<>(item);
        if (n == null) {
            head = newNode;
            tail = newNode;
        } else {
            if (index == 0) {
                n.prev = newNode;
                newNode.next = n;
                head = newNode;
            } else if (index == size - 1) {
                Node<T> prev = n.prev;
                prev.next = newNode;
                newNode.next = n;
                newNode.prev = n.prev;
                n.prev = newNode;
                n.next = null;
                tail = n;
            } else {
                newNode.prev = n.prev;
                n.prev = newNode;
                newNode.next = n;
            }
        }
        size++;
    }

    public final T set(T item, int index) {
        Node<T> n = getAtIndex(index);
        T previousValue = n.val;
        n.val = item;
        return previousValue;
    }

    public final boolean contains(T item) {
        return indexOf(item) != -1;
    }

    // -------------------- Private Methods --------------------

    private Node<T> getAtIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index);
        }
        int i = 0;
        Node<T> n = head;
        while (i < index) {
            n = n.next;
            i++;
        }
        return n;
    }

    // -------------------- Inner Classes --------------------

    private static final class Node<T> {
        private Node<T> prev;
        private Node<T> next;
        private T val;

        private Node(T val) {
            this.val = val;
        }
    }
}
