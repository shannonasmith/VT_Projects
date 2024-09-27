package boundedstack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
 * CS 5704 (Spring 2023) Assignment 0
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.01.25
 */

@SuppressWarnings("EqualsWithItself")
public class ListStackTest {
    private Stack<String> stack_empty_3; // empty stack with capacity of 3
    private Stack<String> stack_ABC_6; // stack with elements:
    // A (bottom of stack), B (middle of stack), and C (top of stack), w/capacity of 6

    @Before
    public void setUp() {
        stack_empty_3 = new ListStack<>(3);
        stack_ABC_6 = new ListStack<>(6);
        stack_ABC_6.push("A"); // this = [A]:6 --> A at top of stack
        stack_ABC_6.push("B"); // this = [A, B]:6 --> B at top of stack, A at bottom
        stack_ABC_6.push("C"); // this = [A, B, C]:6 --> C at top of stack, B in middle, A at bottom

    }
    // Note: The push (non-trivial) method is tested by the setup test above.
    // Note: depth and capacity are simple getters that are used to test other methods.

    @Test
    public void testInitialStacks() {
        assertEquals(3, stack_empty_3.capacity()); // accessor method
        assertEquals(0, stack_empty_3.depth()); // accessor method
        assertEquals(6, stack_ABC_6.capacity()); // testing push method
        assertEquals(3, stack_ABC_6.depth()); // testing push method
    }

    ///////////////// Empty3 /////////////////
    @Test
    public void testEmpty3Depth() {
        assertEquals(0, stack_empty_3.depth());
    }

    @Test
    public void testEmpty3Capacity() {
        assertEquals(3, stack_empty_3.capacity());
    }

    @Test
    public void testEmpty3PushA() {
        stack_empty_3.push("A");
        assertEquals(1, stack_empty_3.depth());
    }

    @Test
    public void testEmpty3PushAPop() {
        stack_empty_3.push("A");
        assertEquals(1, stack_empty_3.depth());
        String str = stack_empty_3.pop();
        assertEquals(0, stack_empty_3.depth());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3PushAPushBPop() {
        stack_empty_3.push("A");
        stack_empty_3.push("B");
        assertEquals(2, stack_empty_3.depth());
        String str = stack_empty_3.pop();
        assertEquals(1, stack_empty_3.depth());
        assertEquals("B", str);
    }

    ///////////////// ABC6 /////////////////
    @Test
    public void testABC6Depth() {
        assertEquals(3, stack_ABC_6.depth());
    }

    @Test
    public void testABC6Capacity() {
        assertEquals(6, stack_ABC_6.capacity());
    }

    @Test
    public void testABC6PushA() {
        stack_ABC_6.push("A");
        assertEquals(4, stack_ABC_6.depth());
    }

    @Test
    public void testABC6Pop() {
        String str = stack_ABC_6.pop();
        assertEquals(2, stack_ABC_6.depth());
        assertEquals("C", str);
    }

    ///////////////// Equals /////////////////

    @Test
    public void testABC6EqualsNull() {
        Stack<String> s = null;
        assertNotEquals(stack_ABC_6, s);
    }

    @Test
    public void testABC6EqualsSelf() {
        assertEquals(stack_ABC_6, stack_ABC_6);
    }

    @Test
    public void testABC6EqualsNonStack() {
        assertNotEquals("[A, B, C]:6", stack_ABC_6);
    }

    @Test
    public void testABC6EqualsDifferentABC6() {
        Stack<String> stack_ABC_6_dup = new ListStack<>(6);
        stack_ABC_6_dup.push("A");
        stack_ABC_6_dup.push("B");
        stack_ABC_6_dup.push("C");

        assertNotEquals(stack_ABC_6, stack_ABC_6_dup);
    }

    @Test
    public void testABC6EqualsABC10() {
        Stack<String> stack_ABC_10 = new ListStack<>(10);
        stack_ABC_10.push("A");
        stack_ABC_10.push("B");
        stack_ABC_10.push("C");

        assertNotEquals(stack_ABC_6, stack_ABC_10);
    }

    @Test
    public void testABC6EqualsAB6() {
        Stack<String> stack_AB_6 = new ListStack<>(10);
        stack_AB_6.push("A");
        stack_AB_6.push("B");

        assertNotEquals(stack_ABC_6.hashCode(), stack_AB_6.hashCode());
    }

    @Test
    public void testEmpty3EqualsDifferentEmpty3() {
        Stack<String> stack_empty_3_dup = new ListStack<>(3);

        assertNotEquals(stack_empty_3, stack_empty_3_dup);
    }

    @Test
    public void testEmpty3EqualsEmpty5() {
        Stack<String> stack_empty_5 = new ListStack<>(5);

        assertNotEquals(stack_empty_3, stack_empty_5);
    }

    @Test
    public void testABC6EqualsDEF6() {
        Stack<String> stack_DEF_6 = new ListStack<>(6);
        stack_DEF_6.push("D");
        stack_DEF_6.push("E");
        stack_DEF_6.push("F");

        assertNotEquals(stack_ABC_6, stack_DEF_6);
    }
    //****************************************************************************

    @Test
    public void testABC6EqualsDifferentABC10() {
        Stack<String> stack_ABC_10_dup = new ListStack<>(10);
        stack_ABC_10_dup.push("A");
        stack_ABC_10_dup.push("B");
        stack_ABC_10_dup.push("C");

        assertNotEquals(stack_ABC_6, stack_ABC_10_dup);
    }

    @Test
    public void testEmpty3PushAB() {
        stack_empty_3.push("A");
        stack_empty_3.push("B");
        assertEquals(2, stack_empty_3.depth());
    }

    @Test
    public void testABC6PushAB() {
        stack_ABC_6.push("A");
        stack_ABC_6.push("B");
        assertEquals(5, stack_ABC_6.depth());
    }

    @Test
    public void testEmpty3PushABC() {
        stack_empty_3.push("A");
        stack_empty_3.push("B");
        stack_empty_3.push("C");
        assertEquals(3, stack_empty_3.depth());
    }

    @Test
    public void testABC6PushABC() {
        stack_ABC_6.push("A");
        stack_ABC_6.push("B");
        stack_ABC_6.push("C");
        assertEquals(6, stack_ABC_6.depth());
    }

    ///////////////// IAE /////////////////

    @Test(expected = IllegalArgumentException.class)
    public void testPushNullOntoABC6Stack() {
        stack_ABC_6.push(null); // <== this should cause IAE
        fail(); // <== if this line is reached, no IAE
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPushNullOntoEmpty3Stack() {
        stack_empty_3.push(null); // <== this should cause IAE
        fail(); // <== if this line is reached, no IAE
    }

    @Test
    public void testABC6MaxCapacityLessThanOne() { // not sure how to test this
    // if (max cap < 1) throw new IllegalArgumentException();
        assertEquals(6, stack_ABC_6.capacity());
    }

    ///////////////// ISE /////////////////

    @Test(expected = IllegalStateException.class)
    public void testEmpty3PushException() {
        stack_empty_3.push("A");
        stack_empty_3.push("B");
        stack_empty_3.push("C");
        stack_empty_3.push("X"); // <== this should cause IAE but was<java.lang.IllegalStateException>
        fail(); // <== if this line is reached, no IAE   <-- getting assertion error here
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6PushException() {
        stack_ABC_6.push("A");
        stack_ABC_6.push("B");
        stack_ABC_6.push("C");
        stack_ABC_6.push("X"); // <== this should cause IAE but was<java.lang.IllegalStateException>
        fail(); // <== if this line is reached, no IAE  <-- getting assertion error here
    }

    @Test(expected = IllegalStateException.class)
    public void testPopFromEmpty3Stack() {
        String str = stack_empty_3.pop(); // <== this should cause ISE but was IndexOutOfBoundsException
        fail(); // <== if this line is reached, no ISE
    }

}
