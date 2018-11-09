package com.sfaria.ds.rb;

import java.util.Objects;

/**
 * @author Scott Faria
 */
public final class RedBlackTree<T extends Comparable<T>> {

    // -------------------- Private Variables --------------------

    private Node<T> root;

    // -------------------- Constructors --------------------

    public RedBlackTree() {}

    // -------------------- Public Methods --------------------

    public final void insert(T value) {
        Objects.requireNonNull(value);
        if (root == null) {
            root = new Node<>(null, value, Node.Color.BLACK);
        } else {
            Node node = insertImpl(value, root);
            if (node != null) {
                balanceTree(node);
            }
        }
    }

    // -------------------- Private Classes --------------------

    private Node insertImpl(T value, Node<T> currentNode) {
        int compare = value.compareTo(currentNode.getValue());
        if (compare != 0) {
            if (compare < 0 && currentNode.getLeft() == null) {
                // less than and there's no left child
                Node<T> node = new Node<>(currentNode, value, Node.Color.RED);
                currentNode.setLeft(node);
                return node;
            } else if (compare > 0 && currentNode.getRight() == null) {
                Node<T> node = new Node<>(currentNode, value, Node.Color.RED);
                currentNode.setRight(node);
                return node;
            } else if (compare < 0) {
                insertImpl(value, currentNode.getLeft());
            } else {
                insertImpl(value, currentNode.getRight());
            }
        }
        return null;
    }

    private void balanceTree(Node node) {

    }

    private void leftRotate(Node node) {

    }

    private void rightRotate(Node node) {

    }

    private Node parent(Node node) {
        return node.getParent();
    }

    private Node grandParent(Node node) {
        Node parent = parent(node);
        if (parent != null) {
            return parent.getParent();
        }
        return null;
    }

    private Node uncle(Node node) {
        Node grandParent = grandParent(node);
        Node parent = parent(node);
        if (grandParent == null) {
            return null;
        }
        return sibling(parent);
    }

    private Node sibling(Node node) {
        Node parent = parent(node);
        if (parent == null) {
            return null;
        }
        if (node.equals(parent.getLeft())) {
            return parent.getRight();
        } else {
            return parent.getLeft();
        }
    }

}
