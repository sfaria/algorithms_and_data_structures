package com.sfaria.ds;

import java.util.Objects;

/**
 * @author Scott Faria
 */
public final class BinarySearchTree<T extends Comparable<T>> {

    // -------------------- Constructor --------------------

    private Node root;

    // -------------------- Public Methods --------------------

    public final void insert(T value) {
        Objects.requireNonNull(value);
        if (root == null) {
            root = new Node(value);
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

    private boolean searchImpl(T value, Node currentNode) {
        if (currentNode == null) {
            return false;
        }

        int compare = value.compareTo(currentNode.value);
        if (compare == 0) {
            return true;
        } else if (compare < 0) {
            return searchImpl(value, currentNode.left);
        } else {
            return searchImpl(value, currentNode.right);
        }
    }

    private void insertImpl(T value, Node currentNode) {
        int compare = value.compareTo(currentNode.value);
        if (compare != 0) {
            if (compare < 0 && currentNode.left == null) {
               // less than and there's no left child
                currentNode.left = new Node(value);
            } else if (compare > 0 && currentNode.right == null) {
                currentNode.right = new Node(value);
            } else if (compare < 0) {
                insertImpl(value, currentNode.left);
            } else {
                insertImpl(value, currentNode.right);
            }
        }
    }

    // -------------------- Inner Classes --------------------

    private class Node {
        private T value;
        private Node left;
        private Node right;

        Node(T value) {
            this.value = Objects.requireNonNull(value);
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

}
