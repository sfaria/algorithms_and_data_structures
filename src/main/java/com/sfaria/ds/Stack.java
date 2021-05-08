package com.sfaria.ds;

public final class Stack<T> {

    // -------------------- Private Variables --------------------

    private Node<T> top;

    // -------------------- Constructors --------------------

    public Stack() { }

    // -------------------- Public Methods --------------------

    public final T peek() {
        if (top != null) {
            return top.value;
        }
        return null;
    }

    public final void push(T item) {
        Node<T> n = new Node<>(item);
        n.next = top;
        top = n;
    }

    public final T pop() {
        if (top == null) {
            return null;
        }
        Node<T> n = top;
        top = n.next;
        return n.value;
    }

    // -------------------- Private Classes --------------------

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }
}
