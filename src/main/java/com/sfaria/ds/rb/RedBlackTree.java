package com.sfaria.ds.rb;

import com.sfaria.ds.BinarySearchTree;

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

    public final Node<T> getRoot() {
        return root;
    }

    public final void insert(T value) {
        Objects.requireNonNull(value);
        if (root == null) {
            root = new Node<>(null, value, Node.Color.BLACK);
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

    // -------------------- Private Classes --------------------

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
                Node<T> node = new Node<>(currentNode, value, Node.Color.RED);
                currentNode.setLeft(node);
                balanceTree(node);
            } else if (compare > 0 && currentNode.getRight() == null) {
                Node<T> node = new Node<>(currentNode, value, Node.Color.RED);
                currentNode.setRight(node);
                balanceTree(node);
            } else if (compare < 0) {
                insertImpl(value, currentNode.getLeft());
            } else {
                insertImpl(value, currentNode.getRight());
            }
        }
    }

    private void balanceTree(Node<T> node) {
        while (node.getParent() != null && node.getParent().getColor() == Node.Color.RED) {
            Node<T> parent = node.getParent();
            Node<T> grandParent = grandParent(node);
            assert grandParent != null;
            if (parent == grandParent.getLeft()) {
                Node<T> rightUncle = grandParent.getRight();
                if (rightUncle != null && rightUncle.getColor() == Node.Color.RED) {
                    parent.setColor(Node.Color.BLACK);
                    rightUncle.setColor(Node.Color.BLACK);
                    grandParent.setColor(Node.Color.RED);
                    node = grandParent;
                } else if (node == parent.getRight()) {
                    node = parent;
                    leftRotate(node);
                }
                parent.setColor(Node.Color.BLACK);
                grandParent.setColor(Node.Color.RED);
                rightRotate(grandParent);
            } else {
                Node<T> leftUncle = grandParent.getLeft();
                if (leftUncle != null && leftUncle.getColor() == Node.Color.RED) {
                    parent.setColor(Node.Color.BLACK);
                    leftUncle.setColor(Node.Color.BLACK);
                    grandParent.setColor(Node.Color.RED);
                    node = grandParent;
                } else if (node == parent.getLeft()) {
                    node = parent;
                    rightRotate(node);
                }
                parent.setColor(Node.Color.BLACK);
                grandParent.setColor(Node.Color.RED);
                leftRotate(grandParent);
            }
        }
        root.setColor(Node.Color.BLACK);
    }

    private void leftRotate(Node<T> x) {
        Node<T> y = x.getRight();
        Node<T> yLeft = y.getLeft();
        x.setRight(yLeft);
        if (yLeft != null) {
            yLeft.setParent(x);
        }
        Node<T> yParent = y.getParent();
        Node<T> xParent = x.getParent();
        yParent.setParent(xParent);
        if (xParent == null) {
            root = y;
        } else if (x == xParent.getLeft()) {
            xParent.setLeft(y);
        } else {
            xParent.setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    private void rightRotate(Node<T> x) {
        Node<T> y = x.getLeft();
        Node<T> yRight = y.getRight();
        x.setLeft(yRight);
        if (yRight != null) {
            yRight.setParent(x);
        }
        Node<T> yParent = y.getParent();
        Node<T> xParent = x.getParent();
        yParent.setParent(xParent);
        if (xParent == null) {
            root = y;
        } else if (x == xParent.getRight()) {
            xParent.setRight(y);
        } else {
            xParent.setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
    }

    private Node<T> grandParent(Node<T> node) {
        Node<T> parent = node.getParent();
        if (parent != null) {
            return parent.getParent();
        }
        return null;
    }

}
