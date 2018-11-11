package com.sfaria.ds.bst;

import java.util.ArrayDeque;
import java.util.function.Consumer;

/**
 * @author Scott Faria
 */
final class BSTTreeVisitor {

    // -------------------- Default Statics --------------------

    static <T> void visitDepthFirstInOrder(Node<T> node, Consumer<Node<T>> nodeVisitor) {
        if (node.getLeft() != null) {
            visitDepthFirstInOrder(node.getLeft(), nodeVisitor);
        }
        nodeVisitor.accept(node);
        if (node.getRight() != null) {
            visitDepthFirstInOrder(node.getRight(), nodeVisitor);
        }
    }

    static <T> void visitDepthFirstPreOrder(Node<T> node, Consumer<Node<T>> nodeVisitor) {
        nodeVisitor.accept(node);
        if (node.getLeft() != null) {
            visitDepthFirstPreOrder(node.getLeft(), nodeVisitor);
        }
        if (node.getRight() != null) {
            visitDepthFirstPreOrder(node.getRight(), nodeVisitor);
        }
    }

    static <T> void visitDepthFirstPostOrder(Node<T> node, Consumer<Node<T>> nodeVisitor) {
        if (node.getLeft() != null) {
            visitDepthFirstPostOrder(node.getLeft(), nodeVisitor);
        }
        if (node.getRight() != null) {
            visitDepthFirstPostOrder(node.getRight(), nodeVisitor);
        }
        nodeVisitor.accept(node);
    }

    static <T> void visitBreadthFirst(Node<T> node, Consumer<Node<T>> nodeVisitor) {
        ArrayDeque<Node<T>> nodeDeque = new ArrayDeque<>();
        nodeDeque.add(node);
        while (!nodeDeque.isEmpty()) {
            node = nodeDeque.removeFirst();
            nodeVisitor.accept(node);
            if (node.getLeft() != null) {
                nodeDeque.add(node.getLeft());
            }
            if (node.getRight() != null) {
                nodeDeque.add(node.getRight());
            }
        }
    }

    // -------------------- Constructors --------------------

    private BSTTreeVisitor() {}
}
