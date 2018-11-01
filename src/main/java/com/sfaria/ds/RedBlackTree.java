package com.sfaria.ds;

import java.util.Objects;

import static com.sfaria.ds.RedBlackTree.Color.BLACK;
import static com.sfaria.ds.RedBlackTree.Color.RED;

/**
 * @author Scott Faria
 */
public final class RedBlackTree<T extends Comparable<T>> {

    // -------------------- Private Variables --------------------

    private Node root;

    // -------------------- Constructors --------------------

    public RedBlackTree() {}

    // -------------------- Public Methods --------------------

    public final void insert(T value) {
        Objects.requireNonNull(value);
        if (root == null) {
            root = new Node(null, value);
        } else {
            Node node = insertImpl(value, root);
            if (node != null) {
                balanceTree(node);
            }
        }
    }

    // -------------------- Private Classes --------------------

    private Node insertImpl(T value, Node currentNode) {
        int compare = value.compareTo(currentNode.value);
        if (compare != 0) {
            if (compare < 0 && currentNode.left == null) {
                // less than and there's no left child
                Node node = new Node(currentNode, value);
                currentNode.left = node;
                return node;
            } else if (compare > 0 && currentNode.right == null) {
                Node node = new Node(currentNode, value);
                currentNode.right = node;
                return node;
            } else if (compare < 0) {
                insertImpl(value, currentNode.left);
            } else {
                insertImpl(value, currentNode.right);
            }
        }
        return null;
    }

    private void balanceTree(Node node) {
        Node uncle = uncle(node);
        Node parent = parent(node);
        Node grandParent = grandParent(node);
        if (parent == null) {
            node.color = BLACK;
        } else if (parent.color == BLACK) {
            return;
        } else if (uncle != null && uncle.color == RED){
            parent.color = BLACK;
            uncle.color = BLACK;
            grandParent.color = RED;
            balanceTree(grandParent);
        } else {
            if (node.equals(grandParent.left.right)) {
                leftRotate(parent);
                node = node.left;
            } else if (node.equals(grandParent.right.left)) {
                rightRotate(parent);
                node = node.right;
            }

            parent = parent(node);
            grandParent = grandParent(node);
            if (node.equals(parent.left)) {
                rightRotate(grandParent);
            } else {
                leftRotate(grandParent);
            }
            parent.color = BLACK;
            grandParent.color = RED;
        }
    }

    private void leftRotate(Node node) {

    }

    private void rightRotate(Node node) {

    }

    private Node parent(Node node) {
        return node.parent;
    }

    private Node grandParent(Node node) {
        Node parent = parent(node);
        if (parent != null) {
            return parent.parent;
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
        if (node.equals(parent.left)) {
            return parent.right;
        } else {
            return parent.left;
        }
    }

    // -------------------- Inner Classes --------------------

    enum Color {
        RED,
        BLACK,
        ;
    }

    private class Node {

        private T value;
        private Color color;

        private Node parent;
        private Node left;
        private Node right;

        Node(Node parent, T value) {
            this.parent = parent;
            this.value = value;
            this.color = RED;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

}
