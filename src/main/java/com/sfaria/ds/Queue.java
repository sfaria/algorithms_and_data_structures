package com.sfaria.ds;

public final class Queue<T> {

    // -------------------- Private Variables --------------------

    private Node<T> first;
    private Node<T> last;

    // -------------------- Constructors --------------------

    public Queue() { }

    // -------------------- Public Methods --------------------

    public final void enqueue(T item) {
        Node<T> n = new Node<>(item);
        if (first == null) {
            first = n;
        } else {
            last.next = n;
        }
        last = n;
    }

    public final T dequeue() {
        if (first == null) {
            return null;
        }
        Node<T> n = first;
        first = first.next;
        return n.value;
    }

    // -------------------- Private Classes --------------------

    private static final class Node<T> {
        private T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }
}
