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
public class ListQueueTest {
    private Queue<String> list_empty3;
    private Queue<String> list_ABC6;

    @Before
    public void setUp() {
        list_empty3 = new ListQueue<>(3);
        list_ABC6 = new ListQueue<>(6);
        list_ABC6.enqueue("A"); // A at position 0
        list_ABC6.enqueue("B"); // B at position 1
        list_ABC6.enqueue("C"); // C at position 2...
    }

    @Test
    public void testABC6EnqueueD() {
        list_ABC6.enqueue("D");
        assertEquals(4, list_ABC6.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6EnqueueNull() {
        list_ABC6.enqueue(null);
        fail();
    }

    @Test
    public void testABC6Dequeue() {
        String str = list_ABC6.dequeue();
        assertEquals(2, list_ABC6.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6DequeueFromEmptyList() {
        list_ABC6.dequeue();
        list_ABC6.dequeue();
        list_ABC6.dequeue();
        String str = list_ABC6.dequeue();
        assertEquals(2, list_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3EnqueueA() {
        list_empty3.enqueue("A");
        assertEquals(1, list_empty3.length());
        assertFalse(list_empty3.isEmpty());
    }

    @Test
    public void testEmpty3EnqueueAB() {
        list_empty3.enqueue("A");
        list_empty3.enqueue("B");
        assertEquals(2, list_empty3.length());
    }

    @Test
    public void testEmpty3EnqueueABC() {
        list_empty3.enqueue("A");
        list_empty3.enqueue("B");
        list_empty3.enqueue("C");
        assertEquals(3, list_empty3.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testEnqueueIntoFullQueue() {
        list_empty3.enqueue("D");
        list_empty3.enqueue("E");
        list_empty3.enqueue("F");
        list_empty3.enqueue("G");
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3DequeueX() {
        list_empty3.dequeue();
        assertEquals(1, list_empty3.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3DequeueXY() {
        list_empty3.dequeue();
        list_empty3.dequeue();
        assertEquals(2, list_empty3.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3DequeueXYZ() {
        list_empty3.dequeue();
        list_empty3.dequeue();
        list_empty3.dequeue();
        assertEquals(3, list_empty3.length());
    }

    @Test
    public void testEmpty3EnqueueADequeue() {
        list_empty3.enqueue("A");
        assertEquals(1, list_empty3.length());
        String str = list_empty3.dequeue();
        assertEquals(0, list_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3EnqueueABDequeue() {
        list_empty3.enqueue("A");
        list_empty3.enqueue("B");
        assertEquals(2, list_empty3.length());
        String str = list_empty3.dequeue();
        assertEquals(1, list_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3EnqueueBDequeueA() {
        list_empty3.enqueue("B");
        list_empty3.dequeue();
        String str = list_empty3.dequeue();
        assertEquals(1, list_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testDequeueEmpty() {
        list_empty3.dequeue();
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3DequeueARemoveLast() {
        list_empty3.dequeue();
        assertEquals(1, list_empty3.length());
        String str = list_empty3.dequeue();
        assertEquals(0, list_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3EnqueueABRemoveLast() {
        list_empty3.enqueue("A");
        list_empty3.enqueue("B");
        String str = list_empty3.dequeue();
        assertEquals(1, list_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3DequeueABRemoveLast() {
        list_empty3.dequeue();
        list_empty3.dequeue();
        assertEquals(2, list_empty3.length());
        String str = list_empty3.dequeue();
        assertEquals(1, list_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveLastEmpty() {
        list_empty3.dequeue();
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3DequeueX3() {
        list_empty3.dequeue();
        list_empty3.dequeue();
        list_empty3.dequeue();
        assertEquals(1, list_empty3.length());
    }

    @Test
    public void testEmpty3DequeueX2() {
        list_empty3.enqueue("A");
        list_empty3.enqueue("B");
        list_empty3.enqueue("C");
        list_empty3.dequeue();
        list_empty3.dequeue();
        assertEquals(1, list_empty3.length());
    }

    ////////////////////// length //////////////////////

    @Test
    public void testABC6Length() {
        assertEquals(3, list_ABC6.length());
        assertFalse(list_ABC6.isFull());
    }

    @Test
    public void testEmpty3Length() {
        assertEquals(0, list_empty3.length());
        assertTrue(list_empty3.isEmpty());
    }

    ////////////////////// capacity //////////////////////

    @Test
    public void testABC6Capacity() {
        assertEquals(6, list_ABC6.capacity());
    }

    @Test
    public void testEmpty3Capacity() {
        assertEquals(3, list_empty3.capacity());
    }

    ////////////////////// newInstance //////////////////////

    @Test
    public void testABC6NewInstance() {
        Queue<String> list_ABC6_new = list_ABC6.newInstance();
        assertEquals(0, list_ABC6_new.length());
        assertEquals(6, list_ABC6_new.capacity());
    }

    @Test
    public void testEmpty3NewInstance() {
        Queue<String> list_empty3_new = list_empty3.newInstance();
        assertEquals(0, list_empty3_new.length());
        assertEquals(3, list_empty3_new.capacity());
    }

    ////////////////////// clear //////////////////////

    @Test
    public void testABC6Clear() {
        list_ABC6.clear();
        StringBuilder result = new StringBuilder();
        for (String s : list_ABC6) {
            if (s != null) {
                result.append(s);
            }
        }
        assertEquals(0, list_ABC6.length());
        assertEquals(6, list_ABC6.capacity());
        assertEquals("", result.toString());
    }

    @Test
    public void testEmpty3Clear() {
        list_empty3.clear();
        StringBuilder result = new StringBuilder();
        for (String s : list_empty3) {
            if (s != null) {
                result.append(s);
            }
        }
        assertEquals(0, list_empty3.length());
        assertEquals(3, list_empty3.capacity());
        assertEquals("", result.toString());
    }

    ////////////////////// isEmpty //////////////////////

    @Test
    public void testABC6isEmpty() { // [A, B, C]:6
        list_ABC6.dequeue();
        list_ABC6.dequeue();
        list_ABC6.dequeue(); // []:6
        assertTrue(list_ABC6.isEmpty());
    }

    @Test
    public void testEmpty3IsEmpty() {
        assertTrue(list_empty3.isEmpty());
    }

    ////////////////////// isFull //////////////////////

    @Test
    public void testABC6IsFull() {
        assertFalse(list_ABC6.isFull());
        list_ABC6.enqueue("D");
        list_ABC6.enqueue("E");
        list_ABC6.enqueue("F");
        assertTrue(list_ABC6.isFull());
    }

    @Test
    public void testEmpty3IsFull() {
        list_empty3.enqueue("A");
        list_empty3.enqueue("B");
        list_empty3.enqueue("C");
        assertTrue(list_empty3.isFull());
    }

    ////////////////////// appendAll //////////////////////

    @Test
    public void testABC6AppendAllList() {
        list_ABC6.clear();
        assertEquals(0, list_ABC6.length());
        assertEquals(6, list_ABC6.capacity());
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6AppendAllListWhenFull() {
        testABC6AppendAllList();
        ListQueue<String> list_ABC6_aa2 = new ListQueue<>(1);
        list_ABC6_aa2.enqueue("X");
        list_ABC6_aa2.enqueue("Y");
        list_ABC6.appendAll(list_ABC6_aa2);
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6AppendAllListWhenEmpty() {
        testABC6AppendAllList();
        ListQueue<String> list_ABC6_aa1 = new ListQueue<>(0);
        list_ABC6_aa1.enqueue("X");
        list_ABC6.appendAll(list_ABC6_aa1);
        fail();
    }

    @Test
    public void testEmpty3AppendAllList() {
        list_empty3.appendAll(list_ABC6);
        StringBuilder empty3_result = new StringBuilder();
        for (String s : list_empty3) {
            if (s != null) {
                empty3_result.append(s);
            }
        }
        StringBuilder abc6_result = new StringBuilder();
        for (String s : list_ABC6) {
            if (s != null) {
                abc6_result.append(s);
            }
        }
        assertEquals(3, list_empty3.length());
        assertEquals(3, list_empty3.capacity());
        assertEquals("ABC", empty3_result.toString());
        assertEquals(0, list_ABC6.length());
        assertEquals(6, list_ABC6.capacity());
        assertEquals("", abc6_result.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty3AppendAllListWhenEmpty() {
        testEmpty3AppendAllList();
        ListQueue<String> list_empty3_aa1 = new ListQueue<>(0);
        list_empty3_aa1.enqueue("X");
        list_empty3.appendAll(list_empty3_aa1);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3AppendAllListWhenFull() {
        testEmpty3AppendAllList();
        ListQueue<String> list_empty3_aa2 = new ListQueue<>(2);
        list_empty3_aa2.enqueue("X");
        list_empty3_aa2.enqueue("Y");
        list_empty3.appendAll(list_empty3_aa2);
        fail();
    }

    ////////////////////// copy //////////////////////

    @Test
    public void testABC6Copy() {
        Queue<String> list_ABC6_copy = list_ABC6.copy();
        assertEquals(3, list_ABC6_copy.length());
        assertEquals(6, list_ABC6_copy.capacity());
        assertEquals(list_ABC6, list_ABC6_copy);
        String s1 = list_ABC6.dequeue();
        String s1_copy = list_ABC6_copy.dequeue();
        assertEquals(s1, s1_copy);
    }

    @Test
    public void testEmpty3Copy() {
        Queue<String> list_empty3_copy = list_empty3.copy();
        assertEquals(0, list_empty3_copy.length());
        assertEquals(3, list_empty3_copy.capacity());
        assertEquals(list_empty3, list_empty3_copy);
    }

    ////////////////////// first //////////////////////

    @Test
    public void testABC6First() {
        assertEquals("A", list_ABC6.first());
    }

    @Test
    public void testEmpty3First() {
        assertNull(list_empty3.first());
    }

    ////////////////////// last //////////////////////

    @Test
    public void testABC6Last() {
        assertEquals("C", list_ABC6.last());
    }

    @Test
    public void testEmpty3Last() {
        assertNull(list_empty3.last());
    }

    ////////////////////// equals //////////////////////

    @Test
    public void testEmpty3EqualsSelf() {
        assertEquals(list_empty3, list_empty3);
    }

    @Test
    public void testEmpty3EqualsNonQueue() {
        assertNotEquals(("[]:3"), list_empty3);
    }

    @Test
    public void testEmpty3EqualsDifferentEmpty3() {
        Queue<String> list_empty3_dup = new ListQueue<>(3);
        assertEquals(list_empty3, list_empty3_dup);
    }

    @Test
    public void testEmpty3EqualsEmpty5() {
        Queue<String> list_empty5 = new ListQueue<>(5);
        assertNotEquals(list_empty3, list_empty5);
    }

    ////////////////////// hashCode //////////////////////

    @Test
    public void testHashCodeABC6AndAB6() {
        Queue<String> list_AB6 = new ListQueue<>(10);
        list_AB6.enqueue("A");
        list_AB6.enqueue("B");
        assertNotEquals(list_ABC6.hashCode(), list_AB6.hashCode());
    }

    // no hash code tests for empty list //

    ////////////////////// toString //////////////////////

    @Test
    public void testABC6ToString() {
        assertEquals("[A, B, C]:6", list_ABC6.toString());
    }


    @Test
    public void testEmpty3ToString() {
        assertEquals("[]:3", list_empty3.toString());
    }

    ////////////////////// iterator //////////////////////

    @Test(expected = NoSuchElementException.class)
    public void testEmpty3IteratorNext() {
        list_empty3.iterator().next();
        fail();
    }

}
