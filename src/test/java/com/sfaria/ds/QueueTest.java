package com.sfaria.ds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    // -------------------- Test Methods --------------------

    @Test
    void enqueue() {
        Queue<String> q = new Queue<>();
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        assertEquals("A", q.dequeue());
        assertEquals("B", q.dequeue());
        assertEquals("C", q.dequeue());
    }

    @Test
    void dequeue() {
        Queue<String> q = new Queue<>();
        assertNull(q.dequeue());
        q.enqueue("A");
        q.enqueue("B");
        q.enqueue("C");
        assertEquals("A", q.dequeue());
        assertEquals("B", q.dequeue());
        assertEquals("C", q.dequeue());
        assertNull(q.dequeue());
    }
}