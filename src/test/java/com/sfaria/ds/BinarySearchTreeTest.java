package com.sfaria.ds;

import net.andreinc.mockneat.MockNeat;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Scott Faria
 */
public class BinarySearchTreeTest {

    // -------------------- Private Variables --------------------

    private MockNeat mockNeat;

    // -------------------- Setup --------------------

    @Before
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