package com.sfaria.ds.rb;

import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.sfaria.ds.rb.Node.Color.BLACK;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Scott Faria
 */
public class RedBlackTreeTest {

    // -------------------- Private Variables --------------------

    private MockNeat mockNeat;

    // -------------------- Setup --------------------

    @BeforeEach
    public void setUp() {
        mockNeat = MockNeat.secure();
    }

    // -------------------- Test Methods --------------------

    @Test
    public void test() {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        Set<Integer> values = mockNeat.ints().range(0, 100).set(10).val();
        values.forEach(tree::insert);
        values.forEach(val -> assertTrue(tree.contains(val)));

        Set<Integer> valuesNotIn = mockNeat.ints().range(10001, 20000).set(1000).val();
        valuesNotIn.forEach(val -> assertFalse(tree.contains(val)));

        assertFalse(tree.contains(null));

        Node<Integer> root = tree.getRoot();
        assertEquals(root.getColor(), BLACK);

        // check color property
        RBTreeVisitor.visitDepthFirstInOrder(root, node -> {
            Node.Color parentColor = node.getColor();
            if (parentColor == Node.Color.RED) {
                Node<Integer> leftChild = node.getLeft();
                Node<Integer> rightChild = node.getRight();
                if (leftChild != null && rightChild != null) {
                    assertEquals(BLACK, leftChild.getColor());
                    assertEquals(BLACK, rightChild.getColor());
                }
            }
        });
    }
}