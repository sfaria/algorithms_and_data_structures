package com.sfaria.ds.bst;

import java.util.Objects;

/**
 * @author Scott Faria
 */
final class Node<T> {

    // -------------------- Private Variables --------------------

    private final T value;
    private Node<T> left;
    private Node<T> right;

    // -------------------- Constructor --------------------

    Node(T value) {
        this.value = Objects.requireNonNull(value);
    }

    // -------------------- Default Methods --------------------

    final T getValue() {
        return value;
    }

    final Node<T> getLeft() {
        return left;
    }

    final void setLeft(Node<T> left) {
        this.left = left;
    }

    final Node<T> getRight() {
        return right;
    }

    final void setRight(Node<T> right) {
        this.right = right;
    }

    // -------------------- Overridden Methods --------------------

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
