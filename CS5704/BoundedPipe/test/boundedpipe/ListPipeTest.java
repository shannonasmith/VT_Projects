package boundedpipe;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * CS 5704 (Spring 2023) boundedpipe
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.17
 */

public class ListPipeTest {
    private Pipe<String> list_empty3;
    private Pipe<String> list_ABC6;

    @Before
    public void setUp() {
        list_empty3 = new ListPipe<>(3);
        list_ABC6 = new ListPipe<>(6);
        list_ABC6.append("A");
        list_ABC6.append("B");
        list_ABC6.append("C");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidParam() {
        ListPipe<String> list = new ListPipe<>(-1);
        fail();
    }

    //******************** ABC6 TESTS IN PIPE METHOD ORDER ********************//

    ////////////////////// prepend --> ISE, IAE //////////////////////

    @Test
    public void testABC6PrependX() {
        list_ABC6.prepend("X");
        assertEquals(4, list_ABC6.length());
    }

    @Test
    public void testABC6PrependXY() {
        list_ABC6.prepend("X");
        list_ABC6.prepend("Y");
        assertEquals(5, list_ABC6.length());
    }

    @Test
    public void testABC6PrependXYZ() {
        list_ABC6.prepend("X");
        list_ABC6.prepend("Y");
        list_ABC6.prepend("Z");
        assertEquals(6, list_ABC6.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6PrependIntoFull() {
        list_ABC6.prepend("F");
        list_ABC6.prepend("E");
        list_ABC6.prepend("D");
        list_ABC6.prepend("G");
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6PrependNull() {
        String str = null;
        list_ABC6.prepend(null);
        fail();
    }

    ////////////////////// append --> ISE, IAE //////////////////////

    @Test
    public void testABC6AppendD() {
        list_ABC6.append("D");
        assertEquals(4, list_ABC6.length());
    }

    @Test
    public void testABC6AppendDE() {
        list_ABC6.append("D");
        list_ABC6.append("E");
        assertEquals(5, list_ABC6.length());
    }

    @Test
    public void testABC6AppendDEF() {
        list_ABC6.append("D");
        list_ABC6.append("E");
        list_ABC6.append("F");
        assertEquals(6, list_ABC6.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6AppendNull() {
        list_ABC6.append(null);
        fail();
    }

    ////////////////////// removeFirst --> ISE //////////////////////

    @Test
    public void testABC6RemoveFirst() {
        String str = list_ABC6.removeFirst();
        assertEquals(2, list_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6AppendARemoveFirst() {
        list_ABC6.append("A");
        assertEquals(4, list_ABC6.length());
        String str = list_ABC6.removeFirst();
        assertEquals(3, list_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6AppendABRemoveFirst() {
        list_ABC6.append("A");
        list_ABC6.append("B");
        assertEquals(5, list_ABC6.length());
        String str = list_ABC6.removeFirst();
        assertEquals(4, list_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6AppendBPrependARemoveFirst() {
        list_ABC6.append("B");
        list_ABC6.prepend("A");
        String str = list_ABC6.removeFirst();
        assertEquals(4, list_ABC6.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6RemoveFirstFromEmptyList() {
        list_ABC6.removeLast();
        list_ABC6.removeLast();
        list_ABC6.removeLast();
        String str = list_ABC6.removeFirst(); // if (isEmpty()) { throw new IllegalStateException(); <----
        assertEquals(2, list_ABC6.length());
        assertEquals("A", str);
    }

    ////////////////////// removeLast --> ISE //////////////////////

    @Test
    public void testABC6PrependARemoveLast() {
        list_ABC6.prepend("A");
        assertEquals(4, list_ABC6.length());
        String str = list_ABC6.removeLast();
        assertEquals(3, list_ABC6.length());
        assertEquals("C", str);
    }

    @Test
    public void testABC6AppendABRemoveLast() {
        list_ABC6.append("A");
        list_ABC6.append("B");
        String str = list_ABC6.removeLast();
        assertEquals(4, list_ABC6.length());
        assertEquals("B", str);
    }

    @Test
    public void testABC6PrependABRemoveLast() {
        list_ABC6.prepend("A");
        list_ABC6.prepend("B");
        assertEquals(5, list_ABC6.length());
        String str = list_ABC6.removeLast();
        assertEquals(4, list_ABC6.length());
        assertEquals("C", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6RemoveLastFromEmptyList() {
        list_ABC6.removeFirst();
        list_ABC6.removeFirst();
        list_ABC6.removeFirst();
        String str = list_ABC6.removeLast(); // if (isEmpty()) { throw new IllegalStateException(); <----
        assertEquals(2, list_ABC6.length());
        assertEquals("A", str);
    }

    ////////////////////// removeFirstLast combo //////////////////////

    @Test
    public void testABC6PrependXYZRemoveFirstAndLast() {
        list_ABC6.prepend("X");
        list_ABC6.prepend("Y");
        list_ABC6.prepend("Z");
        list_ABC6.removeFirst();
        list_ABC6.removeLast();
        assertEquals(4, list_ABC6.length());
    }

    @Test
    public void testABC6RemoveFirstLastFirst() {
        list_ABC6.append("A");
        list_ABC6.append("B");
        list_ABC6.append("C");
        list_ABC6.removeFirst();
        list_ABC6.removeLast();
        list_ABC6.removeFirst();
        assertEquals(3, list_ABC6.length());
    }

    ////////////////////// length //////////////////////

    @Test
    public void testABC6Length() {
        assertEquals(3, list_ABC6.length());
        assertFalse(list_ABC6.isFull());
    }

    ////////////////////// capacity //////////////////////

    @Test
    public void testABC6Capacity() {
        assertEquals(6, list_ABC6.capacity());
    }

    ////////////////////// newInstance //////////////////////

    @Test
    public void testABC6NewInstance() {
        Pipe<String> list_ABC6_new = list_ABC6.newInstance();
        assertEquals(0, list_ABC6_new.length());
        assertEquals(6, list_ABC6_new.capacity());
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

    ////////////////////// isEmpty //////////////////////

    @Test
    public void testABC6IsEmpty() {
        list_ABC6.clear();
        assertTrue(list_ABC6.isEmpty());
    }

    ////////////////////// isFull //////////////////////

    @Test
    public void testABC6IsFull() {
        list_ABC6.prepend("X");
        list_ABC6.prepend("Y");
        list_ABC6.prepend("Z");
        assertTrue(list_ABC6.isFull());
    }

    ////////////////////// appendAll --> IAE, ISE //////////////////////

    @Test
    public void testABC6AppendAllList() {
        list_ABC6.clear();
        assertEquals(0, list_ABC6.length());
        assertEquals(6, list_ABC6.capacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6AppendAllListWhenEmpty() {
        testABC6AppendAllList();
        ListPipe<String> list_ABC6_aa1 = new ListPipe<>(0);
        list_ABC6_aa1.append("X");
        list_ABC6.appendAll(list_ABC6_aa1);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6AppendAllListWhenFull() {
        testABC6AppendAllList();
        ListPipe<String> list_ABC6_aa2 = new ListPipe<>(1);
        list_ABC6_aa2.append("X");
        list_ABC6_aa2.append("Y");
        list_ABC6.appendAll(list_ABC6_aa2);
        fail();
    }

    ////////////////////// copy //////////////////////

    @Test
    public void testABC6Copy() {
        Pipe<String> list_ABC6_copy = list_ABC6.copy();
        assertEquals(3, list_ABC6_copy.length());
        assertEquals(6, list_ABC6_copy.capacity());
        assertEquals(list_ABC6, list_ABC6_copy);
        String s1 = list_ABC6.removeFirst();
        String s1_copy = list_ABC6_copy.removeFirst();
        assertTrue(Objects.equals(s1, s1_copy));
    }

    ////////////////////// first //////////////////////

    @Test
    public void testABC6First() {
        assertEquals("A", list_ABC6.first());
    }

    ////////////////////// last //////////////////////

    @Test
    public void testABC6Last() {
        assertEquals("C", list_ABC6.last());
    }

    ////////////////////// equals //////////////////////

    @Test
    public void testABC6EqualsNull() {
        Pipe<String> s = null;
        assertFalse(list_ABC6.equals(null));
    }

    @Test
    public void testABC6EqualsSelf() {
        assertTrue(list_ABC6.equals(list_ABC6));
    }

    @Test
    public void testABC6EqualsNonPipe() {
        assertFalse(list_ABC6.equals(("[A, B, C]:6")));
    }

    @Test
    public void testABC6EqualsDifferentABC6() {
        Pipe<String> list_ABC6_dup = new ListPipe<>(6);
        list_ABC6_dup.append("A");
        list_ABC6_dup.append("B");
        list_ABC6_dup.append("C");
        assertEquals(list_ABC6, list_ABC6_dup);
    }

    @Test
    public void testABC6EqualsABC10() {
        Pipe<String> list_ABC10_dup = new ListPipe<>(10);
        list_ABC10_dup.append("A");
        list_ABC10_dup.append("B");
        list_ABC10_dup.append("C");
        assertNotEquals(list_ABC6, list_ABC10_dup);
    }

    @Test
    public void testABC6EqualsAB6() {
        Pipe<String> list_AB6 = new ListPipe<>(6);
        list_AB6.append("A");
        list_AB6.append("B");
        assertFalse(list_ABC6.equals(list_AB6));
    }

    @Test
    public void testABC6EqualsDEF6() {
        Pipe<String> list_DEF6 = new ListPipe<>(6);
        list_DEF6.append("D");
        list_DEF6.append("E");
        list_DEF6.append("F");
        assertFalse(list_ABC6.equals(list_DEF6));
    }

    ////////////////////// hashCode //////////////////////

    @Test
    public void testHashCodeABC6AndAB6() {
        Pipe<String> list_AB6 = new ListPipe<>(10);
        list_AB6.append("A");
        list_AB6.append("B");
        assertFalse(list_ABC6.hashCode() == list_AB6.hashCode());
    }

    @Test
    public void testHashCodeABC6AndDifferentABC6() {
        Pipe<String> list_ABC6_dup = new ListPipe<>(6);
        list_ABC6_dup.append("A");
        list_ABC6_dup.append("B");
        list_ABC6_dup.append("C");
        assertTrue(list_ABC6.hashCode() == list_ABC6_dup.hashCode());
    }

    @Test
    public void testHashCodeABC6AndABC10() {
        Pipe<String> list_ABC10 = new ListPipe<>(10);
        list_ABC10.append("A");
        list_ABC10.append("B");
        list_ABC10.append("C");
        assertFalse(list_ABC6.hashCode() == list_ABC10.hashCode());
    }

    @Test
    public void testHashCodeABC6ArrayAndABC6List() {
        Pipe<String> array_ABC6 = new CircArrayPipe<>(6);
        array_ABC6.append("A");
        array_ABC6.append("B");
        array_ABC6.append("C");
        assertTrue(list_ABC6.hashCode() == array_ABC6.hashCode());
    }

    @Test
    public void testHashCodeABC6LinkedAndABC6List() {
        Pipe<String> linked_ABC6 = new LinkedPipe<>(6);
        linked_ABC6.append("A");
        linked_ABC6.append("B");
        linked_ABC6.append("C");
        assertFalse(list_ABC6.hashCode() == linked_ABC6.hashCode());
    }

    ////////////////////// toString //////////////////////

    @Test
    public void testABC6ToString() {
        assertEquals("[A, B, C]:6", list_ABC6.toString());
    }

    //******************** EMPTY3 TESTS IN PIPE METHOD ORDER ********************//

    ////////////////////// prepend //////////////////////

    @Test
    public void testEmpty3PrependX() {
        list_empty3.prepend("X");
        assertEquals(1, list_empty3.length());
    }

    @Test
    public void testEmpty3PrependXY() {
        list_empty3.prepend("X");
        list_empty3.prepend("Y");
        assertEquals(2, list_empty3.length());
    }

    @Test
    public void testEmpty3PrependXYZ() {
        list_empty3.prepend("X");
        list_empty3.prepend("Y");
        list_empty3.prepend("Z");
        assertEquals(3, list_empty3.length());
    }

    ////////////////////// append --> ISE, IAE //////////////////////

    @Test
    public void testEmpty3AppendA() {
        list_empty3.append("A");
        assertEquals(1, list_empty3.length());
        assertFalse(list_empty3.isEmpty());
    }

    @Test
    public void testEmpty3AppendAB() {
        list_empty3.append("A");
        list_empty3.append("B");
        assertEquals(2, list_empty3.length());
    }

    @Test
    public void testEmpty3AppendABC() {
        list_empty3.append("A");
        list_empty3.append("B");
        list_empty3.append("C");
        assertEquals(3, list_empty3.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testAppendIntoFullPipe() {
        list_empty3.append("D");
        list_empty3.append("E");
        list_empty3.append("F");
        list_empty3.append("G");
        fail();
    }

    ////////////////////// removeFirst --> ISE //////////////////////

    @Test
    public void testEmpty3AppendARemoveFirst() {
        list_empty3.append("A");
        assertEquals(1, list_empty3.length());
        String str = list_empty3.removeFirst();
        assertEquals(0, list_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3AppendABRemoveFirst() {
        list_empty3.append("A");
        list_empty3.append("B");
        assertEquals(2, list_empty3.length());
        String str = list_empty3.removeFirst();
        assertEquals(1, list_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3AppendBPrependARemoveFirst() {
        list_empty3.append("B");
        list_empty3.prepend("A");
        String str = list_empty3.removeFirst();
        assertEquals(1, list_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveFirstEmpty() {
        list_empty3.removeFirst();
        fail();
    }

    ////////////////////// removeLast --> ISE //////////////////////

    @Test
    public void testEmpty3PrependARemoveLast() {
        list_empty3.prepend("A");
        assertEquals(1, list_empty3.length());
        String str = list_empty3.removeLast();
        assertEquals(0, list_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3AppendABRemoveLast() {
        list_empty3.append("A");
        list_empty3.append("B");
        String str = list_empty3.removeLast();
        assertEquals(1, list_empty3.length());
        assertEquals("B", str);
    }

    @Test
    public void testEmpty3PrependABRemoveLast() {
        list_empty3.prepend("A");
        list_empty3.prepend("B");
        assertEquals(2, list_empty3.length());
        String str = list_empty3.removeLast();
        assertEquals(1, list_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveLastEmpty() {
        list_empty3.removeLast();
        fail();
    }

    ////////////////////// removeFirstLast combo //////////////////////

    @Test
    public void testEmpty3RemoveFirstAndLast() {
        list_empty3.prepend("X");
        list_empty3.prepend("Y");
        list_empty3.prepend("Z");
        list_empty3.removeFirst();
        list_empty3.removeLast();
        assertEquals(1, list_empty3.length());
    }

    @Test
    public void testEmpty3RemoveFirstLastFirst() {
        list_empty3.append("A");
        list_empty3.append("B");
        list_empty3.append("C");
        list_empty3.removeFirst();
        list_empty3.removeLast();
        list_empty3.removeFirst();
        assertEquals(0, list_empty3.length());
    }

    ////////////////////// length //////////////////////

    @Test
    public void testEmpty3Length() {
        assertEquals(0, list_empty3.length());
        assertTrue(list_empty3.isEmpty());
    }

    ////////////////////// capacity //////////////////////

    @Test
    public void testEmpty3Capacity() {
        assertEquals(3, list_empty3.capacity());
    }

    ////////////////////// newInstance //////////////////////

    @Test
    public void testEmpty3NewInstance() {
        Pipe<String> list_empty3_new = list_empty3.newInstance();
        assertEquals(0, list_empty3_new.length());
        assertEquals(3, list_empty3_new.capacity());
    }

    ////////////////////// clear //////////////////////

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
    public void testEmpty3IsEmpty() {
        assertTrue(list_empty3.isEmpty());
    }

    ////////////////////// isFull //////////////////////

    @Test
    public void testEmpty3IsFull() {
        list_empty3.append("A");
        list_empty3.append("B");
        list_empty3.append("C");
        assertTrue(list_empty3.isFull());
    }

    ////////////////////// appendAll --> IAE, ISE //////////////////////

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
        ListPipe<String> list_empty3_aa1 = new ListPipe<>(0);
        list_empty3_aa1.append("X");
        list_empty3.appendAll(list_empty3_aa1);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3AppendAllListWhenFull() {
        testEmpty3AppendAllList();
        ListPipe<String> list_empty3_aa2 = new ListPipe<>(2);
        list_empty3_aa2.append("X");
        list_empty3_aa2.append("Y");
        list_empty3.appendAll(list_empty3_aa2);
        fail();
    }

    ////////////////////// copy //////////////////////

    @Test
    public void testEmpty3Copy() {
        Pipe<String> list_empty3_copy = list_empty3.copy();
        assertEquals(0, list_empty3_copy.length());
        assertEquals(3, list_empty3_copy.capacity());
        assertEquals(list_empty3, list_empty3_copy);
    }

    ////////////////////// first //////////////////////

    @Test
    public void testEmpty3First() {
        assertEquals(null, list_empty3.first());
    }

    ////////////////////// last //////////////////////

    @Test
    public void testEmpty3Last() {
        assertEquals(null, list_empty3.last());
    }

    ////////////////////// equals //////////////////////

    @Test
    public void testEmpty3EqualsNull() {
        Pipe<String> s = null;
        assertFalse(list_empty3.equals(null));
    }

    @Test
    public void testEmpty3EqualsSelf() {
        assertTrue(list_empty3.equals(list_empty3));
    }

    @Test
    public void testEmpty3EqualsNonPipe() {
        assertFalse(list_empty3.equals(("[]:3")));
    }

    @Test
    public void testEmpty3EqualsDifferentEmpty3() {
        Pipe<String> list_empty3_dup = new ListPipe<>(3);
        assertEquals(list_empty3, list_empty3_dup);
    }

    @Test
    public void testEmpty3EqualsEmpty5() {
        Pipe<String> list_empty5 = new ListPipe<>(5);
        assertFalse(list_empty3.equals(list_empty5));
    }

    ////////////////////// hashCode //////////////////////

    // no hash code tests for empty list //

    ////////////////////// toString //////////////////////

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

