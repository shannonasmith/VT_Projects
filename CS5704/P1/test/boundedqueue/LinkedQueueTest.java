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
public class LinkedQueueTest {
    private Queue<String> linked_empty3;
    private Queue<String> linked_ABC6;

    @Before
    public void setUp() {
        linked_empty3 = new LinkedQueue<>(3);
        linked_ABC6 = new LinkedQueue<>(6);
        linked_ABC6.enqueue("A");
        linked_ABC6.enqueue("B");
        linked_ABC6.enqueue("C");
    }

    ///////////////////////////////////////////////////////

    @Test
    public void testABC6EnqueueD() {
        linked_ABC6.enqueue("D");
        assertEquals(4, linked_ABC6.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6EnqueueNull() {
        linked_ABC6.enqueue(null);
        fail();
    }

    @Test
    public void testABC6Dequeue() {
        String str = linked_ABC6.dequeue();
        assertEquals(2, linked_ABC6.length());
        assertEquals("A", str);
    }

    @Test(expected = NoSuchElementException.class)
    public void testABC6DequeueFromEmptyList() {
        linked_ABC6.dequeue();
        linked_ABC6.dequeue();
        linked_ABC6.dequeue();
        String str = linked_ABC6.dequeue();
        assertEquals(2, linked_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3EnqueueA() {
        linked_empty3.enqueue("A");
        assertEquals(1, linked_empty3.length());
    }

    @Test
    public void testEmpty3EnqueueAB() {
        linked_empty3.enqueue("A");
        linked_empty3.enqueue("B");
        assertEquals(2, linked_empty3.length());
    }

    @Test
    public void testEmpty3EnqueueABC() {
        linked_empty3.enqueue("A");
        linked_empty3.enqueue("B");
        linked_empty3.enqueue("C");
        assertEquals(3, linked_empty3.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testEnqueueIntoFullQueue() {
        linked_empty3.enqueue("D");
        linked_empty3.enqueue("E");
        linked_empty3.enqueue("F");
        linked_empty3.enqueue("G");
        fail();
    }

    @Test
    public void testEmpty3EnqueueADequeue() {
        linked_empty3.enqueue("A");
        assertEquals(1, linked_empty3.length());
        String str = linked_empty3.dequeue();
        assertEquals(0, linked_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3EnqueueABDequeue() {
        linked_empty3.enqueue("A");
        linked_empty3.enqueue("B");
        assertEquals(2, linked_empty3.length());
        String str = linked_empty3.dequeue();
        assertEquals(1, linked_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueEmpty() {
        linked_empty3.dequeue();
        fail();
    }

    @Test
    public void testEmpty3DequeueLastFirst() {
        linked_empty3.enqueue("A");
        linked_empty3.enqueue("B");
        linked_empty3.enqueue("C");
        linked_empty3.dequeue();
        linked_empty3.dequeue();
        assertEquals(1, linked_empty3.length());
    }

    ////////////////////// length //////////////////////

    @Test
    public void testABC6Length() {
        assertEquals(3, linked_ABC6.length());
        assertFalse(linked_ABC6.isFull());
    }

    @Test
    public void testEmpty3Length() {
        assertEquals(0, linked_empty3.length());
    }

    ////////////////////// capacity //////////////////////

    @Test
    public void testABC6Capacity() {
        assertEquals(6, linked_ABC6.capacity());
    }

    @Test
    public void testEmpty3Capacity() {
        assertEquals(3, linked_empty3.capacity());
    }

    ////////////////////// newInstance //////////////////////

    @Test
    public void testABC6NewInstance() {
        Queue<String> linked_ABC6_new = linked_ABC6.newInstance();
        assertEquals(0, linked_ABC6_new.length());
        assertEquals(6, linked_ABC6_new.capacity());
    }

    @Test
    public void testEmpty3NewInstance() {
        Queue<String> linked_empty3_new = linked_empty3.newInstance();
        assertEquals(0, linked_empty3_new.length());
        assertEquals(3, linked_empty3_new.capacity());
    }

    ////////////////////// clear //////////////////////

    @Test
    public void testABC6Clear() {
        linked_ABC6.clear();
        StringBuilder result = new StringBuilder();
        for (String s : linked_ABC6) {
            if (s != null) {
                result.append(s);
            }
        }
        assertEquals(0, linked_ABC6.length());
        assertEquals(6, linked_ABC6.capacity());
        assertEquals("", result.toString());
    }

    @Test
    public void testEmpty3Clear() {
        linked_empty3.clear();
        assertEquals(0, linked_empty3.length());
        assertEquals(3, linked_empty3.capacity());
    }

    @Test
    public void testEmpty3Clear2() {
        linked_empty3.clear();
        StringBuilder result = new StringBuilder();
        for (String s : linked_empty3) {
            if (s != null) {
                result.append(s);
            }
        }
        assertEquals(0, linked_empty3.length());
        assertEquals(3, linked_empty3.capacity());
        assertEquals("", result.toString());
    }

    ////////////////////// isEmpty //////////////////////

    @Test
    public void testABC6IsEmpty() {
        linked_ABC6.dequeue();
        linked_ABC6.dequeue();
        linked_ABC6.dequeue();
        assertTrue(linked_ABC6.isEmpty());
    }

    @Test
    public void testEmpty3IsEmpty() {
        assertTrue(linked_empty3.isEmpty());
    }

    ////////////////////// isFull //////////////////////

    @Test
    public void testABC6IsFull() {
        assertFalse(linked_ABC6.isFull());
        linked_ABC6.enqueue("D");
        linked_ABC6.enqueue("E");
        linked_ABC6.enqueue("F");
        assertTrue(linked_ABC6.isFull());
    }

    @Test
    public void testEmpty3IsFull() {
        linked_empty3.enqueue("A");
        linked_empty3.enqueue("B");
        linked_empty3.enqueue("C");
        assertTrue(linked_empty3.isFull());
    }

    ////////////////////// appendAll //////////////////////

    @Test
    public void testABC6AppendAllList() {
        linked_ABC6.clear();
        assertEquals(0, linked_ABC6.length());
        assertEquals(6, linked_ABC6.capacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6AppendAllListWhenEmpty() {
        testABC6AppendAllList();
        ListQueue<String> linked_ABC6_aa1 = new ListQueue<>(0);
        linked_ABC6_aa1.enqueue("X");
        linked_ABC6.appendAll(linked_ABC6_aa1);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6AppendAllListWhenFull() {
        testABC6AppendAllList();
        ListQueue<String> linked_ABC6_aa2 = new ListQueue<>(1);
        linked_ABC6_aa2.enqueue("X");
        linked_ABC6_aa2.enqueue("Y");
        linked_ABC6.appendAll(linked_ABC6_aa2);
        fail();
    }

    @Test
    public void testEmpty3AppendAllLinked() {
        linked_empty3.appendAll(linked_ABC6);
        StringBuilder empty3_result = new StringBuilder();
        for (String s : linked_empty3) {
            if (s != null) {
                empty3_result.append(s);
            }
        }
        assertEquals(3, linked_empty3.length());
        assertEquals(3, linked_empty3.capacity());
        assertEquals("ABC", empty3_result.toString());

        StringBuilder abc6_result = new StringBuilder();
        for (String s : linked_ABC6) {
            if (s != null) {
                abc6_result.append(s);
            }
        }
        assertEquals(3, linked_empty3.length());
        assertEquals(3, linked_empty3.capacity());
        assertEquals("", abc6_result.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty3AppendAllLinkedWhenEmpty() {
        testEmpty3AppendAllLinked();
        Queue<String> linked_empty3_aa1 = new LinkedQueue<>(0);
        linked_empty3_aa1.enqueue("X");
        linked_empty3.appendAll(linked_empty3_aa1);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3AppendAllLinkedWhenFull() {
        testEmpty3AppendAllLinked();
        Queue<String> linked_empty3_aa2 = new LinkedQueue<>(2);
        linked_empty3_aa2.enqueue("X");
        linked_empty3_aa2.enqueue("Y");
        linked_empty3.appendAll(linked_empty3_aa2);
        fail();
    }

    ////////////////////// copy //////////////////////

    @Test
    public void testABC6Copy() {
        Queue<String> linked_ABC6_copy = linked_ABC6.copy();
        assertEquals(3, linked_ABC6_copy.length());
        assertEquals(6, linked_ABC6_copy.capacity());
        assertEquals(linked_ABC6, linked_ABC6_copy);
        String s1 = linked_ABC6.dequeue();
        String s1_copy = linked_ABC6_copy.dequeue();
        assertEquals(s1, s1_copy);
    }

    @Test
    public void testEmpty3Copy() {
        Queue<String> linked_empty3_copy = linked_empty3.copy();
        assertEquals(0, linked_empty3_copy.length());
        assertEquals(3, linked_empty3_copy.capacity());
        assertEquals(linked_empty3, linked_empty3_copy);
    }

    ////////////////////// first //////////////////////

    @Test
    public void testABC6First() {
        assertEquals("A", linked_ABC6.first());
    }

    @Test
    public void testEmpty3First() {
        assertNull(linked_empty3.first());
    }

    ////////////////////// last //////////////////////

    @Test
    public void testABC6Last() {
        assertEquals("C", linked_ABC6.last());
    }

    @Test
    public void testEmpty3Last() {
        assertNull(linked_empty3.last());
    }

    ////////////////////// equals //////////////////////

    @Test
    public void testEmpty3EqualsSelf() {
        assertEquals(linked_empty3, linked_empty3);
    }

    @Test
    public void testEmpty3EqualsNonQueue() {
        assertNotEquals(("[]:3"), linked_empty3);
    }

    @Test
    public void testEmpty3EqualsDifferentEmpty3() {
        Queue<String> linked_empty3_dup = new LinkedQueue<>(3);
        assertEquals(linked_empty3, linked_empty3_dup);
    }

    @Test
    public void testEmpty3EqualsEmpty5() {
        Queue<String> linked_empty5 = new LinkedQueue<>(5);
        assertNotEquals(linked_empty3, linked_empty5);
    }

    ////////////////////// hashCode //////////////////////

    @Test
    public void testHashCodeABC6AndAB6() {
        Queue<String> list_AB6 = new ListQueue<>(10);
        list_AB6.enqueue("A");
        list_AB6.enqueue("B");
        assertNotEquals(linked_ABC6.hashCode(), list_AB6.hashCode());
    }

    // no hash code tests for empty list //

    ////////////////////// toString //////////////////////

    @Test
    public void testABC6ToString() {
        assertEquals("[A, B, C]:6", linked_ABC6.toString());
    }

    @Test
    public void testEmpty3ToString() {
        assertEquals("[]:3", linked_empty3.toString());
    }

    ////////////////////// iterator //////////////////////

    @Test(expected = NoSuchElementException.class)
    public void testEmpty3IteratorNext() {
        linked_empty3.iterator().next();
        fail();
    }

}
