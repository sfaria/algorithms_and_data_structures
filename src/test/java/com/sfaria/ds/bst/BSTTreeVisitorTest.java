package com.sfaria.ds.bst;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Scott Faria
 */
public class BSTTreeVisitorTest {

    // -------------------- Private Variables --------------------

    /**
     *           4
     *         /   \
     *        2     5
     *      /  \
     *     1    3
     */
    private BinarySearchTree<Integer> tree;

    // -------------------- Setup Method --------------------

    @BeforeEach
    public void setUp() {
        tree = new BinarySearchTree<>();
        tree.insert(4);
        tree.insert(5);
        tree.insert(2);
        tree.insert(1);
        tree.insert(3);
    }

    // -------------------- Test Methods --------------------

    @Test
    public void visitDepthFirstInOrder() {
        Iterator<Integer> it = List.of(1, 2, 3, 4, 5).iterator();
        BSTTreeVisitor.visitDepthFirstInOrder(tree.getRoot(), node -> {
            assertNotNull(node);
            assertTrue(it.hasNext());
            Integer expected = it.next();
            assertEquals(expected, node.getValue());
        });
        assertFalse(it.hasNext());
    }

    @Test
    public void visitDepthFirstPreOrder() {
        Iterator<Integer> it = List.of(4, 2, 1, 3, 5).iterator();
        BSTTreeVisitor.visitDepthFirstPreOrder(tree.getRoot(), node -> {
            assertNotNull(node);
            assertTrue(it.hasNext());
            Integer expected = it.next();
            assertEquals(expected, node.getValue());
        });
        assertFalse(it.hasNext());
    }

    @Test
    public void visitDepthFirstPostOrder() {
        Iterator<Integer> it = List.of(1, 3, 2, 5, 4).iterator();
        BSTTreeVisitor.visitDepthFirstPostOrder(tree.getRoot(), node -> {
            assertNotNull(node);
            assertTrue(it.hasNext());
            Integer expected = it.next();
            assertEquals(expected, node.getValue());
        });
        assertFalse(it.hasNext());
    }

    @Test
    public void visitBreadthFirst() {
        Iterator<Integer> it = List.of(4, 2, 5, 1, 3).iterator();
        BSTTreeVisitor.visitBreadthFirst(tree.getRoot(), node -> {
            assertNotNull(node);
            assertTrue(it.hasNext());
            Integer expected = it.next();
            assertEquals(expected, node.getValue());
        });
        assertFalse(it.hasNext());
    }
}