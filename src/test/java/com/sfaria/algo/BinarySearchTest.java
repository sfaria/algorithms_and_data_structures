package com.sfaria.algo;

import net.andreinc.mockneat.MockNeat;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Scott Faria
 */
public final class BinarySearchTest {

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
        Integer[] actual = mockNeat.ints()
                .range(10, Integer.MAX_VALUE)
                .array(1000)
                .val();

        Integer[] sorted = actual.clone();
        Arrays.sort(sorted);
        for (Integer val : actual) {
            assertTrue(BinarySearch.search(sorted, val));
        }
        assertFalse(BinarySearch.search(sorted, null));

        Integer notInArrayPositive = mockNeat.ints().range(0, 9).val();
        assertFalse(BinarySearch.search(sorted, notInArrayPositive));

        assertFalse(BinarySearch.search(sorted, -1));
    }
}