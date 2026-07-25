package boundedqueue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * CS 5704 (Fall 2023) BoundedQueue
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.09.06
 */
public class CircArrayQueueTest {
    private Queue<String> array_empty3;
    private Queue<String> array_ABC6;

    @Before
    public void setUp() {
        array_empty3 = new CircArrayQueue<>(3); // []:3
        // System.out.println(array_empty3);
        array_ABC6 = new CircArrayQueue<>(6); // []:6
        // System.out.println(array_ABC6);
        array_ABC6.enqueue("A"); // [A]:6
        array_ABC6.enqueue("B"); // [A, B]:6
        array_ABC6.enqueue("C"); // [A, B, C]:6
    }

    ///////////////////////////////////////////////

    @Test
    public void testABC6EnqueueD() {
        array_ABC6.enqueue("D");
        assertEquals(4, array_ABC6.length());
    }

    @Test
    public void testABC6EnqueueDE() {
        array_ABC6.enqueue("D");
        array_ABC6.enqueue("E");
        assertEquals(5, array_ABC6.length());
    }

    @Test
    public void testABC6EnqueueDEF() {
        array_ABC6.enqueue("D");
        array_ABC6.enqueue("E");
        array_ABC6.enqueue("F");
        assertEquals(6, array_ABC6.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6EnqueueNull() {
        array_ABC6.enqueue(null);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6EnqueueIntoFullQueue() {
        array_ABC6.enqueue("D");
        array_ABC6.enqueue("E");
        array_ABC6.enqueue("F");
        array_ABC6.enqueue("G");
        fail();
    }

    @Test
    public void testABC6DequeueX() {
        array_ABC6.dequeue();
        assertEquals(2, array_ABC6.length());
    }

    @Test
    public void testABC6DequeueXY() {
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        assertEquals(1, array_ABC6.length());
    }

    @Test
    public void testABC6DequeueXYZ() {
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        assertEquals(0, array_ABC6.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6DequeueIntoFull() {
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        fail();
    }

    @Test
    public void testABC6DequeueNull() {
        array_ABC6.dequeue();
    }

    @Test
    public void testABC6Dequeue() {
        String str = array_ABC6.dequeue();
        assertEquals(2, array_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6EnqueueADequeue() {
        array_ABC6.enqueue("A");
        assertEquals(4, array_ABC6.length());
        String str = array_ABC6.dequeue();
        assertEquals(3, array_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6EnqueueABDequeue() {
        array_ABC6.enqueue("A");
        array_ABC6.enqueue("B");
        assertEquals(5, array_ABC6.length());
        String str = array_ABC6.dequeue();
        assertEquals(4, array_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6EnqueueBDequeueADequeue() {
        array_ABC6.enqueue("B");
        array_ABC6.dequeue();
        String str = array_ABC6.dequeue();
        assertEquals(2, array_ABC6.length());
        assertEquals("B", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6DequeueFromEmptyList() {
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        String str = array_ABC6.dequeue();
        assertEquals(2, array_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6DequeueARemoveLast() {
        array_ABC6.dequeue();
        assertEquals(2, array_ABC6.length());
        String str = array_ABC6.dequeue();
        assertEquals(1, array_ABC6.length());
        assertEquals("B", str);
    }

    @Test
    public void testABC6EnqueueABRemoveLast() {
        array_ABC6.enqueue("A");
        array_ABC6.enqueue("B");
        String str = array_ABC6.dequeue();
        assertEquals(4, array_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6DequeueABRemoveLast() {
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        assertEquals(1, array_ABC6.length());
        String str = array_ABC6.dequeue();
        assertEquals(0, array_ABC6.length());
        assertEquals("C", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6RemoveLastFromEmptyList() {
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        String str = array_ABC6.dequeue();
        assertEquals(2, array_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6DequeueXYZDequeueAndLast() {
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        assertEquals(0, array_ABC6.length());
    }

    @Test
    public void testABC6DequeueLastFirst() {
        array_ABC6.enqueue("A");
        array_ABC6.enqueue("B");
        array_ABC6.enqueue("C");
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        array_ABC6.dequeue();
        assertEquals(3, array_ABC6.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3DequeueX() {
        array_empty3.dequeue();
        assertEquals(1, array_empty3.length());
    }


    @Test(expected = IllegalStateException.class)
    public void testEmpty3DequeueXY() {
        array_empty3.dequeue();
        array_empty3.dequeue();
        assertEquals(2, array_empty3.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3DequeueXYZ() {
        array_empty3.dequeue();
        array_empty3.dequeue();
        array_empty3.dequeue();
        assertEquals(3, array_empty3.length());
    }

    @Test
    public void testEmpty3EnqueueA() {
        array_empty3.enqueue("A");
        assertEquals(1, array_empty3.length());
        assertFalse(array_empty3.isEmpty());
    }

    @Test
    public void testEmpty3EnqueueAB() {
        array_empty3.enqueue("A");
        array_empty3.enqueue("B");
        assertEquals(2, array_empty3.length());
    }

    @Test
    public void testEmpty3EnqueueABC() {
        array_empty3.enqueue("A");
        array_empty3.enqueue("B");
        array_empty3.enqueue("C");
        assertEquals(3, array_empty3.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3EnqueueIntoFullQueue() {
        array_empty3.enqueue("D");
        array_empty3.enqueue("E");
        array_empty3.enqueue("F");
        array_empty3.enqueue("G");
        fail();
    }

    @Test
    public void testEmpty3EnqueueADequeue() {
        array_empty3.enqueue("A");
        assertEquals(1, array_empty3.length());
        String str = array_empty3.dequeue();
        assertEquals(0, array_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3EnqueueABDequeue() {
        array_empty3.enqueue("A");
        array_empty3.enqueue("B");
        assertEquals(2, array_empty3.length());
        String str = array_empty3.dequeue();
        assertEquals(1, array_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3Dequeue() {
        array_empty3.dequeue();
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3DequeueARemoveLast() {
        array_empty3.dequeue();
        assertEquals(1, array_empty3.length());
        String str = array_empty3.dequeue();
        assertEquals(0, array_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3EnqueueABRemoveLast() {
        array_empty3.enqueue("A");
        array_empty3.enqueue("B");
        String str = array_empty3.dequeue();
        assertEquals(1, array_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3DequeueABRemoveLast() {
        array_empty3.dequeue();
        array_empty3.dequeue();
        assertEquals(2, array_empty3.length());
        String str = array_empty3.dequeue();
        assertEquals(1, array_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3RemoveLast() {
        array_empty3.dequeue();
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3DequeueAndLast() {
        array_empty3.dequeue();
        array_empty3.dequeue();
        array_empty3.dequeue();
        array_empty3.dequeue();
        array_empty3.dequeue();
        assertEquals(1, array_empty3.length());
    }

    @Test
    public void testEmpty3DequeueLastFirst() {
        array_empty3.enqueue("A");
        array_empty3.enqueue("B");
        array_empty3.enqueue("C");
        array_empty3.dequeue();
        array_empty3.dequeue();
        array_empty3.dequeue();
        assertEquals(0, array_empty3.length());
    }

    ////////////////////// length //////////////////////

    @Test
    public void testABC6Length() {
        assertEquals(3, array_ABC6.length());
    }

    @Test
    public void testEmpty3Length() {
        assertEquals(0, array_empty3.length());
        assertTrue(array_empty3.isEmpty());
    }

    ////////////////////// capacity //////////////////////

    @Test
    public void testABC6Capacity() {
        assertEquals(6, array_ABC6.capacity());
    }

    @Test
    public void testEmpty3Capacity() {
        assertEquals(3, array_empty3.capacity());
    }

    ////////////////////// newInstance //////////////////////

    @Test
    public void testABC6NewInstance() {
        Queue<String> new_array_ABC6 = array_ABC6.newInstance();
        assertEquals(0, new_array_ABC6.length());
        assertEquals(6, new_array_ABC6.capacity());
    }

    @Test
    public void testEmpty3NewInstance() {
        Queue<String> new_empty3 = array_empty3.newInstance();
        assertEquals(0, new_empty3.length());
        assertEquals(3, new_empty3.capacity());
    }

    ////////////////////// clear //////////////////////

    @Test
    public void testABC6Clear() {
        array_ABC6.clear();
        StringBuilder result = new StringBuilder();
        for (String s : array_ABC6) {
            if (s != null) {
                result.append(s);
            }
        }
        assertEquals(0, array_ABC6.length());
        assertEquals(6, array_ABC6.capacity());
        assertEquals("", result.toString());
    }

    @Test
    public void testEmpty3Clear() {
        array_empty3.clear();
        StringBuilder result = new StringBuilder();
        for (String s : array_empty3) {
            if (s != null) {
                result.append(s);
            }
        }
        assertEquals(0, array_empty3.length());
        assertEquals(3, array_empty3.capacity());
        assertEquals("", result.toString());
    }

    ////////////////////// isEmpty //////////////////////

    @Test
    public void testABC6IsEmpty() {
        array_ABC6.clear();
        assertTrue(array_ABC6.isEmpty());
    }

    @Test
    public void testEmpty3IsEmpty() {
        assertTrue(array_empty3.isEmpty());
    }

    ////////////////////// isFull //////////////////////

    @Test
    public void testABC6IsFull() {
        array_ABC6.enqueue("D");
        array_ABC6.enqueue("E");
        array_ABC6.enqueue("F");
        assertTrue(array_ABC6.isFull());
    }

    @Test
    public void testEmpty3IsFull() {
        array_empty3.enqueue("A");
        array_empty3.enqueue("B");
        array_empty3.enqueue("C");
        assertTrue(array_empty3.isFull());
    }

    ////////////////////// appendAll //////////////////////

    @Test
    public void testABC6AppendAllArray() {
        array_ABC6.clear();
        assertEquals(0, array_ABC6.length());
        assertEquals(6, array_ABC6.capacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6AppendAllArrayWhenEmpty() {
        testABC6AppendAllArray();
        CircArrayQueue<String> array_ABC6_aa1 = new CircArrayQueue<>(0);
        array_ABC6_aa1.enqueue("X");
        array_ABC6.appendAll(array_ABC6_aa1);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6AppendAllArrayWhenFull() {
        testABC6AppendAllArray();
        CircArrayQueue<String> array_ABC6_aa2 = new CircArrayQueue<>(1);
        array_ABC6_aa2.enqueue("X");
        array_ABC6_aa2.enqueue("Y");
        array_ABC6.appendAll(array_ABC6_aa2);
        fail();
    }

    @Test
    public void testEmpty3AppendAllArray() {
        array_empty3.appendAll(array_ABC6);
        StringBuilder empty3_result = new StringBuilder();
        for (String s : array_empty3) {
            if (s != null) {
                empty3_result.append(s);
            }
        }
        StringBuilder abc6_result = new StringBuilder();
        for (String s : array_ABC6) {
            if (s != null) {
                abc6_result.append(s);
            }
        }
        assertEquals(3, array_empty3.length());
        assertEquals(3, array_empty3.capacity());
        assertEquals("ABC", empty3_result.toString());
        assertEquals(3, array_empty3.length());
        assertEquals(3, array_empty3.capacity());
        assertEquals("", abc6_result.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty3AppendAllArrayWhenEmpty() {
        testEmpty3AppendAllArray();
        ListQueue<String> array_empty3_aa1 = new ListQueue<>(0);
        array_empty3_aa1.enqueue("X");
        array_empty3.appendAll(array_empty3_aa1);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3AppendAllArrayWhenFull() {
        testEmpty3AppendAllArray();
        CircArrayQueue<String> array_empty3_aa2 = new CircArrayQueue<>(2);
        array_empty3_aa2.enqueue("X");
        array_empty3_aa2.enqueue("Y");
        array_empty3.appendAll(array_empty3_aa2);
        fail();
    }

    ////////////////////// copy //////////////////////

    @Test
    public void testABC6Copy() {
        Queue<String> array_ABC6_copy = array_ABC6.copy();
        assertEquals(3, array_ABC6_copy.length());
        assertEquals(6, array_ABC6_copy.capacity());
        assertEquals(array_ABC6, array_ABC6_copy);
        String s1 = array_ABC6.dequeue();
        String copy_s1 = array_ABC6_copy.dequeue();
        assertEquals(s1, copy_s1);
    }

    @Test
    public void testEmpty3Copy() {
        Queue<String> array_empty3_copy = array_empty3.copy();
        assertEquals(0, array_empty3_copy.length());
        assertEquals(3, array_empty3_copy.capacity());
        assertEquals(array_empty3, array_empty3_copy);
    }

    ////////////////////// first //////////////////////

    @Test
    public void testABC6First() {
        assertEquals("A", array_ABC6.first());
    }

    @Test
    public void testEmpty3First() {
        assertNull(array_empty3.first());
    }

    ////////////////////// last //////////////////////

    @Test
    public void testABC6Last() {
        assertEquals("C", array_ABC6.last());
        System.out.println("Actual: " + array_ABC6.toString());
    }

    @Test
    public void testEmpty3Last() {
        assertNull(array_empty3.last());
    }

    ////////////////////// equals //////////////////////

    @Test
    public void testABC6EqualsSelf() {
        assertEquals(array_ABC6, array_ABC6);
    }

    @Test
    public void testABC6EqualsNonQueue() {
        assertNotEquals(("[A, B, C]:6"), array_ABC6);
    }

    @Test
    public void testABC6EqualsABC10() {
        Queue<String> array_ABC10_dup = new CircArrayQueue<>(10);
        array_ABC10_dup.enqueue("A");
        array_ABC10_dup.enqueue("B");
        array_ABC10_dup.enqueue("C");
        assertNotEquals(array_ABC6, array_ABC10_dup);
    }

    @Test
    public void testABC6EqualsAB6() {
        Queue<String> array_AB6 = new CircArrayQueue<>(6);
        array_AB6.enqueue("A");
        array_AB6.enqueue("B");
        assertNotEquals(array_ABC6, array_AB6);
    }

    @Test
    public void testABC6EqualsDEF6() {
        Queue<String> array_DEF6 = new CircArrayQueue<>(6);
        array_DEF6.enqueue("D");
        array_DEF6.enqueue("E");
        array_DEF6.enqueue("F");
        assertNotEquals(array_ABC6, array_DEF6);
    }

    @Test
    public void testABCD6EqualsDifferentABCD6() {
        array_ABC6.enqueue("D");
        Queue<String> array_ABC6_dup = new CircArrayQueue<>(6);
        array_ABC6_dup.enqueue("A");
        array_ABC6_dup.enqueue("B");
        array_ABC6_dup.enqueue("C");
        array_ABC6_dup.enqueue(" ");
        array_ABC6_dup.enqueue("D");
        assertNotEquals(array_ABC6, array_ABC6_dup);
    }

    @Test
    public void testEmpty3EqualsSelf() {
        assertEquals(array_empty3, array_empty3);
    }

    @Test
    public void testEmpty3EqualsNonQueue() {
        assertNotEquals(("[]:3"), array_empty3);
    }

    @Test
    public void testEmpty3EqualsDifferentEmpty3() {
        Queue<String> array_empty3_dup = new CircArrayQueue<>(3);
        assertEquals(array_empty3, array_empty3_dup);
    }

    @Test
    public void testEmpty3EqualsEmpty5() {
        Queue<String> array_empty5 = new CircArrayQueue<>(5);
        assertNotEquals(array_empty3, array_empty5);
    }

    ////////////////////// hashCode //////////////////////

    @Test
    public void testHashCodeABC6AndAB6() {
        Queue<String> array_AB6 = new CircArrayQueue<>(10);
        array_AB6.enqueue("A");
        array_AB6.enqueue("B");
        assertNotEquals(array_ABC6.hashCode(), array_AB6.hashCode());
    }

    @Test
    public void testHashCodeABC6AndABC10() {
        Queue<String> array_ABC10 = new CircArrayQueue<>(10);
        array_ABC10.enqueue("A");
        array_ABC10.enqueue("B");
        array_ABC10.enqueue("C");
        assertNotEquals(array_ABC6.hashCode(), array_ABC10.hashCode());
    }

    @Test
    public void testHashCodeABC6LinkedAndABC6Array() {
        Queue<String> linked_ABC6 = new LinkedQueue<>(6);
        linked_ABC6.enqueue("A");
        linked_ABC6.enqueue("B");
        linked_ABC6.enqueue("C");
        assertEquals(array_ABC6.hashCode(), linked_ABC6.hashCode());
    }

    // no hash code tests for empty list //

    ////////////////////// toString //////////////////////

    @Test
    public void testABC6ToString() {
        assertEquals("[A, B, C]:6", array_ABC6.toString());
    }

    @Test
    public void testEmpty3ToString() {
        assertEquals("[]:3", array_empty3.toString());
    }

    ////////////////////// iterator //////////////////////

    @Test
    public void testABC6Iterator() {
        StringBuilder result = new StringBuilder();
        for (String s : array_ABC6) {
            if (s != null) {
                result.append(s);
            }
        }
        assertEquals("ABC", result.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void testEmpty3IteratorNext() {
        array_empty3.iterator().next();
        fail();
    }

}
