package com.sfaria.ds.rb;

import java.util.Objects;

/**
 * @author Scott Faria
 */
final class Node<T> {

    // -------------------- Private Variables --------------------

    private T value;
    private Color color;

    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;

    // -------------------- Constructors --------------------

    Node(Node parent, T value, Color color) {
        this.parent = parent;
        this.value = value;
        this.color = color;
        this.left = null;
        this.right = null;
    }

    // -------------------- Default Methods --------------------

    final T getValue() {
        return value;
    }

    final Color getColor() {
        return color;
    }

    final Node<T> getParent() {
        return parent;
    }

    final Node<T> getLeft() {
        return left;
    }

    final Node<T> getRight() {
        return right;
    }

    final void setColor(Color color) {
        this.color = color;
    }

    final void setParent(Node<T> parent) {
        this.parent = parent;
    }

    final void setLeft(Node<T> left) {
        this.left = left;
    }

    final void setRight(Node<T> right) {
        this.right = right;
    }

    // -------------------- Overridden Methods --------------------

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public final String toString() {
        return value.toString();
    }

    // -------------------- Inner Classes --------------------

    enum Color {
        RED,
        BLACK,
        ;
    }
}
