package com.sfaria.ds.bst;

import java.util.Objects;

/**
 * @author Scott Faria
 */
public final class BinarySearchTree<T extends Comparable<T>> {

    // -------------------- Constructor --------------------

    private Node<T> root;

    // -------------------- Public Methods --------------------

    public final Node<T> getRoot() {
        return root;
    }

    public final void insert(T value) {
        Objects.requireNonNull(value);
        if (root == null) {
            root = new Node<>(value);
        } else {
            insertImpl(value, root);
        }
    }

    public final boolean contains(T value) {
        if (value == null || root == null) {
            return false;
        } else {
            return searchImpl(value, root);
        }
    }

    // -------------------- Private Methods --------------------

    private boolean searchImpl(T value, Node<T> currentNode) {
        if (currentNode == null) {
            return false;
        }

        int compare = value.compareTo(currentNode.getValue());
        if (compare == 0) {
            return true;
        } else if (compare < 0) {
            return searchImpl(value, currentNode.getLeft());
        } else {
            return searchImpl(value, currentNode.getRight());
        }
    }

    private void insertImpl(T value, Node<T> currentNode) {
        int compare = value.compareTo(currentNode.getValue());
        if (compare != 0) {
            if (compare < 0 && currentNode.getLeft() == null) {
               // less than and there's no left child
                currentNode.setLeft(new Node<>(value));
            } else if (compare > 0 && currentNode.getRight() == null) {
                currentNode.setRight(new Node<>(value));
            } else if (compare < 0) {
                insertImpl(value, currentNode.getLeft());
            } else {
                insertImpl(value, currentNode.getRight());
            }
        }
    }

}
