package com.sfaria.ds.rb;

import java.util.function.Consumer;

/**
 * @author Scott Faria
 */
public final class RBTreeVisitor {

    // -------------------- Public Statics --------------------

    public static <T> void visitDepthFirstInOrder(Node<T> node, Consumer<Node<T>> nodeVisitor) {
        if (node.getLeft() != null) {
            visitDepthFirstInOrder(node.getLeft(), nodeVisitor);
        }
        nodeVisitor.accept(node);
        if (node.getRight() != null) {
            visitDepthFirstInOrder(node.getRight(), nodeVisitor);
        }
    }

}
