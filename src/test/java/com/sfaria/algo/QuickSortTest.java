package com.sfaria.algo;

import net.andreinc.mockneat.MockNeat;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Scott Faria
 */
public class QuickSortTest {

    // -------------------- Private Variables --------------------

    private MockNeat mockNeat;

    // -------------------- Setup --------------------

    @Before
    public void setUp() {
        mockNeat = MockNeat.secure();
    }

    // -------------------- Test Methods --------------------

    @Test
    public void test_sort() {
        Integer[] actual = mockNeat.ints()
                .array(1000000)
                .val();
        QuickSort.sort(actual);

        Integer[] expected = actual.clone();
        Arrays.sort(expected);
        assertArrayEquals(expected, actual);
    }
}