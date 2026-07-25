package boundedstack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*
 * CS 5704 (Spring 2023) boundedstack
 *
 * @author Shannon Smith (shae1223)
 */
public class ArrayStackTest {
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

    ///////////////// Iterator /////////////////

    @Test
    public void testABC6Iterator() {
        String result = "";
        for (String s : stack_ABC_6) {
            result += s;
        }
        assertEquals("ABC", result);
    }

    ///////////////// ToString /////////////////

    @Test
    public void testEmpty3ToString() {
        assertEquals("[]:3", stack_empty_3.toString());
    }

    @Test
    public void testABC6ToString() {
        assertEquals("[A, B, C]:6", stack_ABC_6.toString());
    }

    ///////////////// Equals /////////////////

    @Test
    public void testABC6EqualsNull() {
        Stack<String> s = null;
        assertFalse(stack_ABC_6.equals(s));
    }

    @Test
    public void testABC6EqualsSelf() {
        assertTrue(stack_ABC_6.equals(stack_ABC_6));
    }

    @Test
    public void testABC6EqualsNonStack() {
        assertFalse(stack_ABC_6.equals("[A, B, C]:6"));
    }

    @Test
    public void testABC6EqualsDifferentABC6() {
        Stack<String> stack_ABC_6_dup = new ListStack<>(6);
        stack_ABC_6_dup.push("A");
        stack_ABC_6_dup.push("B");
        stack_ABC_6_dup.push("C");

        assertTrue(stack_ABC_6.equals(stack_ABC_6_dup));
    }

    @Test
    public void testABC6EqualsABC10() {
        Stack<String> stack_ABC_10 = new ListStack<>(10);
        stack_ABC_10.push("A");
        stack_ABC_10.push("B");
        stack_ABC_10.push("C");

        assertFalse(stack_ABC_6.equals(stack_ABC_10));
    }

    @Test
    public void testABC6EqualsAB6() {
        Stack<String> stack_AB_6 = new ListStack<>(10);
        stack_AB_6.push("A");
        stack_AB_6.push("B");

        assertFalse(stack_ABC_6.equals(stack_AB_6));
    }

    @Test
    public void testEmpty3EqualsDifferentEmpty3() {
        Stack<String> stack_empty_3_dup = new ListStack<>(3);

        assertTrue(stack_empty_3.equals(stack_empty_3_dup));
    }

    @Test
    public void testEmpty3EqualsEmpty5() {
        Stack<String> stack_empty_5 = new ListStack<>(5);

        assertFalse(stack_empty_3.equals(stack_empty_5));
    }

    @Test
    public void testABC6EqualsDEF6() {
        Stack<String> stack_DEF_6 = new ListStack<>(6);
        stack_DEF_6.push("D");
        stack_DEF_6.push("E");
        stack_DEF_6.push("F");

        assertFalse(stack_ABC_6.equals(stack_DEF_6));
    }

    ///////////////// HashCode /////////////////

    @Test
    public void testHashCodeABC6AndAB6() {
        Stack<String> stack_AB_6 = new ListStack<>(10);
        stack_AB_6.push("A");
        stack_AB_6.push("B");
        System.out.println("Expected: " + stack_ABC_6.hashCode() + "    Actual: " + stack_AB_6.hashCode());
        assertFalse(stack_ABC_6.hashCode() == stack_AB_6.hashCode());
    }

    @Test
    public void testHashCodeABC6AndDifferentABC6() {
        Stack<String> stack_ABC_6_dup = new ListStack<>(6);
        stack_ABC_6_dup.push("A");
        stack_ABC_6_dup.push("B");
        stack_ABC_6_dup.push("C");
        System.out.println("Expected: " + stack_ABC_6.hashCode() + "    Actual: " + stack_ABC_6_dup.hashCode());
        assertTrue(stack_ABC_6.hashCode() == stack_ABC_6_dup.hashCode());
    }

    @Test
    public void testHashCodeABC6AndABC10() {
        Stack<String> stack_ABC_10 = new ListStack<>(10);
        stack_ABC_10.push("A");
        stack_ABC_10.push("B");
        stack_ABC_10.push("C");
        System.out.println("Expected: " + stack_ABC_6.hashCode() + "    Actual: " + stack_ABC_10.hashCode());
        assertFalse(stack_ABC_6.hashCode() == stack_ABC_10.hashCode());
    }

    ///////////////// New Instance /////////////////

    @Test
    public void testEmpty3NewInstance() {
        Stack<String> newStack = stack_empty_3.newInstance();
        assertEquals(0, newStack.depth());
        assertEquals(3, newStack.capacity());
    }

    @Test
    public void testABC6NewInstance() {
        Stack<String> newStack = stack_ABC_6.newInstance();
        assertEquals(0, newStack.depth());
        assertEquals(6, newStack.capacity());
    }

    ///////////////// Copy /////////////////

    @Test
    public void testEmpty3Copy() {
        Stack<String> copy = stack_empty_3.copy();
        assertEquals(0, copy.depth());
        assertEquals(3, copy.capacity());
        assertEquals(stack_empty_3, copy);
    }

    @Test
    public void testABC6Copy() {
        Stack<String> copy = stack_ABC_6.copy();
        assertEquals(3, copy.depth());
        assertEquals(6, copy.capacity());
        assertEquals(stack_ABC_6, copy);
        String s1 = stack_ABC_6.pop();
        String s1_copy = copy.pop();
        assertTrue(s1 == s1_copy);
    }

    ///////////////// Reverse /////////////////

    @Test
    public void testEmpty3Reverse() {
        stack_empty_3.reverse();
        assertEquals(0, stack_empty_3.depth());
        assertEquals(3, stack_empty_3.capacity());
    }

    @Test
    public void testABC6Reverse() {
        stack_ABC_6.reverse();
        assertEquals(3, stack_ABC_6.depth());
        assertEquals(6, stack_ABC_6.capacity());
        String str = stack_ABC_6.pop();
        assertEquals("A", str);
    }

    ///////////////// Clear /////////////////

    @Test
    public void testEmpty3Clear() {
        stack_empty_3.clear();
        assertEquals(0, stack_empty_3.depth());
        assertEquals(3, stack_empty_3.capacity());
    }

    @Test
    public void testABC6Clear() {
        stack_ABC_6.clear();
        assertEquals(0, stack_ABC_6.depth());
        assertEquals(6, stack_ABC_6.capacity());
    }


    //****************************************************************************

    @Test
    public void testABC6EqualsDifferentABC10() {
        Stack<String> stack_ABC_10_dup = new ListStack<>(10);
        stack_ABC_10_dup.push("A");
        stack_ABC_10_dup.push("B");
        stack_ABC_10_dup.push("C");

        assertFalse(stack_ABC_6.equals(stack_ABC_10_dup));
    }

    @Test
    public void testHashCodeABC6AndDEF6() {
        Stack<String> stack_DEF_6 = new ListStack<>(6);
        stack_DEF_6.push("D");
        stack_DEF_6.push("E");
        stack_DEF_6.push("F");
        System.out.println("Expected: " + stack_ABC_6.hashCode() + "    Actual: " + stack_DEF_6.hashCode());
        assertFalse(stack_ABC_6.hashCode() == stack_DEF_6.hashCode());  // <-- getting assertion error here

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
    public void testPushNullOntoStack() {
        stack_ABC_6.push(null); // <== this should cause IAE
        fail(); // <== if this line is reached, no IAE
    }

    ///////////////// ISE /////////////////

    @Test(expected = IllegalStateException.class)
    public void testPushOntoFullEmpty3Stack() {
        stack_empty_3.push("A");
        stack_empty_3.push("B");
        stack_empty_3.push("C");
        stack_empty_3.push("X"); // <== this should cause ISE
        fail(); // <== if this line is reached, no ISE
    }

    @Test(expected = IllegalStateException.class)
    public void testPushOntoFullABC6Stack() {
        stack_ABC_6.push("A");
        stack_ABC_6.push("B");
        stack_ABC_6.push("C");
        stack_ABC_6.push("X"); // <== this should cause ISE
        fail(); // <== if this line is reached, no ISE
    }

    @Test(expected = IllegalStateException.class)
    public void testPopFromEmpty3Stack() {
        String str = stack_empty_3.pop(); // <== this should cause ISE but was IndexOutOfBoundsException
        fail(); // <== if this line is reached, no ISE
    }


    ///////////////// Multiple ///////////////// ---> AppendAll???
/*
    @Test(expected = IllegalStateException.class)
    public void testMultipleError() {
        stack_ABC_6.pushMultiple("D", "E", "F", "X"); // <== this should cause ISE
        fail(); // <== if this line is reached, no ISE
    }

    @Test
    public void pushMultiple() {
        stack_ABC_6.pushMultiple("D", "E", "F");
        assertEquals(6, stack_ABC_6.depth());
    }
*/

    @Test
    public void testABC6ListBasedEqualsABC6ArrayBased() {
        Stack<String> stack_ABC_6_array = new ArrayStack<>(6);
        stack_ABC_6_array.push("A");
        stack_ABC_6_array.push("B");
        stack_ABC_6_array.push("C");

        assertTrue(stack_ABC_6.equals(stack_ABC_6_array));
    }

}