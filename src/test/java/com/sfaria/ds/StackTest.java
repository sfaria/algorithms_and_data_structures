package com.sfaria.ds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    // -------------------- Tests --------------------

    @Test
    void peek() {
        Stack<String> stack = new Stack<>();
        assertNull(stack.peek());
        stack.push("A");
        assertEquals("A", stack.peek());
        stack.push("B");
        assertEquals("B", stack.peek());
        stack.push("C");
        assertEquals("C", stack.peek());
        stack.pop();
        assertEquals("B", stack.peek());
        stack.pop();
        assertEquals("A", stack.peek());
        stack.pop();
        assertNull(stack.peek());
    }

    @Test
    void pushPop() {
        Stack<String> stack = new Stack<>();
        assertNull(stack.pop());
        stack.push("A");
        stack.push("B");
        stack.push("C");
        assertEquals("C", stack.pop());
        assertEquals("B", stack.pop());
        assertEquals("A", stack.pop());
        assertNull(stack.pop());
    }

}