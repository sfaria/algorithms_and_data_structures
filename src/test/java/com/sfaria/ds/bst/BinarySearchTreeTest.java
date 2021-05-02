package com.sfaria.ds.bst;

import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Scott Faria
 */
public class BinarySearchTreeTest {

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
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Set<Integer> values = mockNeat.ints().range(0, 10000).set(1000).val();
        values.forEach(tree::insert);
        values.forEach(val -> assertTrue(tree.contains(val)));

        Set<Integer> valuesNotIn = mockNeat.ints().range(10001, 20000).set(1000).val();
        valuesNotIn.forEach(val -> assertFalse(tree.contains(val)));

        assertFalse(tree.contains(null));
    }
}