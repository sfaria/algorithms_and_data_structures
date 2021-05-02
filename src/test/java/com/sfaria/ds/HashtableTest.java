package com.sfaria.ds;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Scott Faria (scott.faria@gmail.com)
 */
public class HashtableTest {

    // -------------------- Test Methods --------------------

    @Test
    public void test_get_put_no_collisions() {
        Hashtable<String, String> table = new Hashtable<>(10, 0.7f);
        assertNull(table.put("A", "B"));
        assertNull(table.put("B", "C"));
        assertNull(table.put("C", "D"));
        assertNull(table.put("E", "F"));

        assertEquals("B", table.get("A"));
        assertEquals("C", table.get("B"));
        assertEquals("D", table.get("C"));
        assertEquals("F", table.get("E"));
        assertNull(table.get("F"));

        assertEquals("B", table.put("A", "G"));
        assertEquals("G", table.get("A"));
    }

    @Test
    public void test_put_rehash() {
        Hashtable<String, String> table = new Hashtable<>(1, 0.7f);
        assertNull(table.put("A", "B"));
        assertNull(table.put("B", "C"));
        assertNull(table.put("C", "D"));
        assertNull(table.put("E", "F"));

        assertEquals("B", table.get("A"));
        assertEquals("C", table.get("B"));
        assertEquals("D", table.get("C"));
        assertEquals("F", table.get("E"));
        assertNull(table.get("F"));

        assertEquals("B", table.put("A", "G"));
        assertEquals("G", table.get("A"));
    }

    @Test
    public void test_get_put_with_guaranteed_collisions() {
        Hashtable<CollidingKey, String> table = new Hashtable<>(1, 0.7f);
        assertNull(table.put(new CollidingKey("A"), "B"));
        assertNull(table.put(new CollidingKey("B"), "C"));
        assertNull(table.put(new CollidingKey("C"), "D"));
        assertNull(table.put(new CollidingKey("E"), "F"));

        assertEquals("B", table.get(new CollidingKey("A")));
        assertEquals("C", table.get(new CollidingKey("B")));
        assertEquals("D", table.get(new CollidingKey("C")));
        assertEquals("F", table.get(new CollidingKey("E")));
        assertNull(table.get(new CollidingKey("F")));
    }

    @Test
    public void test_remove_no_collisions() {
        Hashtable<String, String> table = new Hashtable<>(10, 0.7f);
        assertNull(table.put("A", "B"));
        assertNull(table.put("B", "C"));
        assertNull(table.put("C", "D"));
        assertNull(table.put("E", "F"));

        assertEquals("B", table.remove("A"));
        assertEquals("C", table.remove("B"));
        assertEquals("D", table.remove("C"));
        assertEquals("F", table.remove("E"));
        assertNull(table.remove("A"));
        assertNull(table.remove("X"));
    }

    @Test
    public void test_remove_with_collisions() {
        Hashtable<CollidingKey, String> table = new Hashtable<>(1, 0.7f);
        assertNull(table.put(new CollidingKey("A"), "B"));
        assertNull(table.put(new CollidingKey("B"), "C"));
        assertNull(table.put(new CollidingKey("C"), "D"));
        assertNull(table.put(new CollidingKey("E"), "F"));

        assertEquals("B", table.remove(new CollidingKey("A")));
        assertEquals("C", table.remove(new CollidingKey("B")));
        assertEquals("D", table.remove(new CollidingKey("C")));
        assertEquals("F", table.remove(new CollidingKey("E")));
    }

    @Test
    public void test_size() {
        Hashtable<Object, String> table = new Hashtable<>(5, 0.7f);
        assertEquals(0, table.size());
        assertNull(table.put("A", "B"));
        assertEquals(1, table.size());
        assertNull(table.put("B", "C"));
        assertEquals(2, table.size());
        assertNull(table.put("C", "D"));
        assertEquals(3, table.size());
        assertNull(table.put("E", "F"));
        assertEquals(4, table.size());

        assertNull(table.put(new CollidingKey("G"), "H"));
        assertEquals(5, table.size());
        assertNull(table.put(new CollidingKey("I"), "J"));
        assertEquals(6, table.size());
        assertNull(table.put(new CollidingKey("K"), "L"));
        assertEquals(7, table.size());
        assertNull(table.put(new CollidingKey("M"), "N"));
        assertEquals(8, table.size());

        table.remove("A");
        assertEquals(7, table.size());
        table.remove("B");
        assertEquals(6, table.size());
        table.remove("C");
        assertEquals(5, table.size());
        table.remove("E");
        assertEquals(4, table.size());

        table.remove(new CollidingKey("G"));
        assertEquals(3, table.size());
        table.remove(new CollidingKey("I"));
        assertEquals(2, table.size());
        table.remove(new CollidingKey("K"));
        assertEquals(1, table.size());
        table.remove(new CollidingKey("M"));
        assertEquals(0, table.size());
    }

    // -------------------- Private Classes --------------------

    private static final class CollidingKey {
        private String value;

        CollidingKey(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CollidingKey that = (CollidingKey) o;
            return Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }
}