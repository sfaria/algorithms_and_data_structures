package com.sfaria.ds;

import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.unit.text.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Scott Faria
 */
class ArrayListTest {

    // -------------------- Private Variables --------------------

    private MockNeat mockNeat;

    // -------------------- Setup --------------------

    @BeforeEach
    public void setUp() {
        mockNeat = MockNeat.secure();
    }

    // -------------------- Test Methods --------------------

    @Test
    void size() {
        ArrayList<String> ll = new ArrayList<>(5);
        assertEquals(0, ll.size());
        mockNeat.strings().consume(100, (index, s) -> {
            ll.add(s);
            assertEquals(index + 1, ll.size());
        });
    }

    @Test
    void get() {
        ArrayList<String> ll = new ArrayList<>();
        String[] expectedValues = mockNeat.strings().array(100).val();
        for (int i = 0; i < expectedValues.length; i++) {
            String expectedValue = expectedValues[i];
            ll.add(expectedValue);
            assertEquals(expectedValue, ll.get(i));
        }

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> ll.get(-1));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> ll.get(101));
    }

    @Test
    void addTail() {
        ArrayList<String> ll = new ArrayList<>();
        mockNeat.strings().consume(100, (idx, s) -> {
            ll.add(s);
            int actualIndex = ll.indexOf(s);
            assertEquals(ll.size() - 1, actualIndex);
            assertEquals(s, ll.get(actualIndex));
        });
    }

    @Test
    void add() {
        ArrayList<String> ll = new ArrayList<>(3);
        ll.add("A", 0);
        assertEquals("A", ll.get(0));
        ll.add("B", 0);
        assertEquals("B", ll.get(0));
        assertEquals("A", ll.get(1));
        ll.add("C", 1);
        assertEquals("B", ll.get(0));
        assertEquals("C", ll.get(1));
        assertEquals("A", ll.get(2));
        ll.add("D", 0); // will also trigger resize
        assertEquals("D", ll.get(0));
        assertEquals("B", ll.get(1));
        assertEquals("C", ll.get(2));
        assertEquals("A", ll.get(3));
    }

    @Test
    void indexOf() {
        ArrayList<String> ll = new ArrayList<>();
        ll.add("A");
        ll.add("B");
        ll.add("C");
        ll.add("D");
        ll.add("B");
        ll.add("E");

        assertEquals(-1, ll.indexOf("X"));
        assertEquals(3, ll.indexOf("D"));
        assertEquals(2, ll.indexOf("C"));
        assertEquals(1, ll.indexOf("B"));
        assertEquals(0, ll.indexOf("A"));
        assertEquals(5, ll.indexOf("E"));
    }

    @Test
    void remove() {
        ArrayList<String> ll = new ArrayList<>();
        ll.add("A");
        ll.add("B");
        ll.add("C");
        ll.add("D");
        ll.add("A");

        // testing left shift of elements when removing
        assertEquals("B", ll.remove(1));
        assertEquals("C", ll.remove(1));
        assertEquals("D", ll.remove(1));
        assertEquals("A", ll.remove(0));
        assertEquals("A", ll.remove(0));
        assertTrue(ll.size() == 0);
    }


    @Test
    void set() {
        ArrayList<String> ll = new ArrayList<>();
        ll.add("A");
        ll.add("B");
        ll.add("C");
        String old = ll.set("X", 0);
        assertEquals(old, "A");
        old = ll.set("Y", 1);
        assertEquals(old, "B");
        old = ll.set("Z", 2);
        assertEquals(old, "C");
    }

    @Test
    void contains() {
        ArrayList<String> ll = new ArrayList<>();
        ll.add("A");
        ll.add("B");
        ll.add("C");

        assertFalse(ll.contains("X"));
        assertTrue(ll.contains("C"));
        assertTrue(ll.contains("B"));
        assertTrue(ll.contains("C"));
    }
}