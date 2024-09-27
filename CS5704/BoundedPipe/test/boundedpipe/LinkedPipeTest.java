package boundedpipe;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * CS 5704 (Spring 2023) boundedpipe
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.17
 */

@SuppressWarnings("EqualsBetweenInconvertibleTypes")
public class LinkedPipeTest {

    private Pipe<String> linked_empty3;
    private Pipe<String> linked_ABC6;

    @Before
    public void setUp() {
        linked_empty3 = new LinkedPipe<>(3);
        linked_ABC6 = new LinkedPipe<>(6);
        linked_ABC6.append("A");
        linked_ABC6.append("B");
        linked_ABC6.append("C");
    }

    ///////////////// Constructor /////////////////

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidParam() {
        Pipe<String> linked = new LinkedPipe<>(-1); // <== this should cause IAE
        fail(); // <== if this line is reached, no IAE
    }
    //******************** ABC6 TESTS IN PIPE METHOD ORDER ********************//

    ////////////////////// prepend --> ISE, IAE //////////////////////

    @Test
    public void testABC6PrependX() {
        linked_ABC6.prepend("X");
        assertEquals(4, linked_ABC6.length());
    }

    @Test
    public void testABC6PrependXY() {
        linked_ABC6.prepend("X");
        linked_ABC6.prepend("Y");
        assertEquals(5, linked_ABC6.length());
    }

    @Test
    public void testABC6PrependXYZ() {
        linked_ABC6.prepend("X");
        linked_ABC6.prepend("Y");
        linked_ABC6.prepend("Z");
        assertEquals(6, linked_ABC6.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6PrependIntoFull() {
        linked_ABC6.prepend("F");
        linked_ABC6.prepend("E");
        linked_ABC6.prepend("D");
        linked_ABC6.prepend("G"); // <== this should cause ISE
        fail(); // <== if this line is reached, no ISE
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6PrependNull() {
        linked_ABC6.prepend(null); // <== this should cause IAE
        fail(); // <== if this line is reached, no IAE
    }

    ////////////////////// append --> ISE, IAE //////////////////////

    @Test
    public void testABC6AppendD() {
        linked_ABC6.append("D");
        assertEquals(4, linked_ABC6.length());
    }

    @Test
    public void testABC6AppendDE() {
        linked_ABC6.append("D");
        linked_ABC6.append("E");
        assertEquals(5, linked_ABC6.length());
    }

    @Test
    public void testABC6AppendDEF() {
        linked_ABC6.append("D");
        linked_ABC6.append("E");
        linked_ABC6.append("F");
        assertEquals(6, linked_ABC6.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6AppendNull() {
        linked_ABC6.append(null); // <== this should cause IAE
        fail(); // <== if this line is reached, no IAE
    }

    ////////////////////// removeFirst --> ISE //////////////////////

    @Test
    public void testABC6RemoveFirst() {
        String str = linked_ABC6.removeFirst();
        assertEquals(2, linked_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6AppendARemoveFirst() {
        linked_ABC6.append("A");
        assertEquals(4, linked_ABC6.length());
        String str = linked_ABC6.removeFirst();
        assertEquals(3, linked_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6AppendABRemoveFirst() {
        linked_ABC6.append("A");
        linked_ABC6.append("B");
        assertEquals(5, linked_ABC6.length());
        String str = linked_ABC6.removeFirst();
        assertEquals(4, linked_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6AppendBPrependARemoveFirst() {
        linked_ABC6.append("B");
        linked_ABC6.prepend("A");
        String str = linked_ABC6.removeFirst();
        assertEquals(4, linked_ABC6.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6RemoveFirstFromEmptyList() {
        linked_ABC6.removeLast();
        linked_ABC6.removeLast();
        linked_ABC6.removeLast();
        String str = linked_ABC6.removeFirst(); // <== this should cause ISE
        assertEquals(2, linked_ABC6.length());
        assertEquals("A", str);
        fail(); // <== if this line is reached, no ISE
    }

    ////////////////////// removeLast --> ISE //////////////////////

    @Test
    public void testABC6PrependARemoveLast() {
        linked_ABC6.prepend("A");
        assertEquals(4, linked_ABC6.length());
        String str = linked_ABC6.removeLast();
        assertEquals(3, linked_ABC6.length());
        assertEquals("C", str);
    }

    @Test
    public void testABC6AppendABRemoveLast() {
        linked_ABC6.append("A");
        linked_ABC6.append("B");
        String str = linked_ABC6.removeLast();
        assertEquals(4, linked_ABC6.length());
        assertEquals("B", str);
    }

    @Test
    public void testABC6PrependABRemoveLast() {
        linked_ABC6.prepend("A");
        linked_ABC6.prepend("B");
        assertEquals(5, linked_ABC6.length());
        String str = linked_ABC6.removeLast();
        assertEquals(4, linked_ABC6.length());
        assertEquals("C", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6RemoveLastFromEmptyList() {
        linked_ABC6.removeFirst();
        linked_ABC6.removeFirst();
        linked_ABC6.removeFirst();
        String str = linked_ABC6.removeLast();  // <== this should cause ISE
        assertEquals(2, linked_ABC6.length());
        assertEquals("A", str);
        fail(); // <== if this line is reached, no ISE
    }

    ////////////////////// removeFirstLast combo //////////////////////

    @Test
    public void testABC6PrependXYZRemoveFirstAndLast() {
        linked_ABC6.prepend("X");
        linked_ABC6.prepend("Y");
        linked_ABC6.prepend("Z");
        linked_ABC6.removeFirst();
        linked_ABC6.removeLast();
        assertEquals(4, linked_ABC6.length());
    }

    @Test
    public void testABC6RemoveFirstLastFirst() {
        linked_ABC6.append("A");
        linked_ABC6.append("B");
        linked_ABC6.append("C");
        linked_ABC6.removeFirst();
        linked_ABC6.removeLast();
        linked_ABC6.removeFirst();
        assertEquals(3, linked_ABC6.length());
    }

    ////////////////////// length //////////////////////

    @Test
    public void testABC6Length() {
        assertEquals(3, linked_ABC6.length());
    }

    ////////////////////// capacity //////////////////////

    @Test
    public void testABC6Capacity() {
        assertEquals(6, linked_ABC6.capacity());
    }

    ////////////////////// newInstance //////////////////////

    @Test
    public void testABC6NewInstance() {
        Pipe<String> linked_ABC6_new = linked_ABC6.newInstance();
        assertEquals(0, linked_ABC6_new.length());
        assertEquals(6, linked_ABC6_new.capacity());
    }

    ////////////////////// clear //////////////////////

    @Test
    public void testABC6Clear() {
        linked_ABC6.clear();
        assertEquals(0, linked_ABC6.length());
        assertEquals(6, linked_ABC6.capacity());
    }

    ////////////////////// isEmpty //////////////////////

    @Test
    public void testABC6IsEmpty() {
        linked_ABC6.clear();
        assertTrue(linked_ABC6.isEmpty());
    }

    ////////////////////// isFull //////////////////////

    @Test
    public void testABC6IsFull() {
        linked_ABC6.prepend("X");
        linked_ABC6.prepend("Y");
        linked_ABC6.prepend("Z");
        assertTrue(linked_ABC6.isFull());
    }

    ////////////////////// appendAll --> IAE, ISE //////////////////////

    @Test
    public void testABC6AppendAllLinked() {
        linked_ABC6.clear();
        assertEquals(0, linked_ABC6.length());
        assertEquals(6, linked_ABC6.capacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6AppendAllArrayWhenEmpty() {
        testABC6AppendAllLinked();
        Pipe<String> linked_ABC6_aa1 = new LinkedPipe<>(0);
        linked_ABC6_aa1.append("X");
        linked_ABC6.appendAll(linked_ABC6_aa1); // <== this should cause IAE
        fail(); // <== if this line is reached, no IAE
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6AppendAllArrayWhenFull() {
        testABC6AppendAllLinked();
        Pipe<String> linked_ABC6_aa2 = new LinkedPipe<>(1);
        linked_ABC6_aa2.append("X");
        linked_ABC6_aa2.append("Y");
        linked_ABC6.appendAll(linked_ABC6_aa2); // <== this should cause ISE
        fail(); // <== if this line is reached, no ISE
    }

    ////////////////////// copy //////////////////////

    @Test
    public void testABC6Copy() {
        Pipe<String> linked_ABC6_copy = linked_ABC6.copy();
        assertEquals(3, linked_ABC6_copy.length());
        assertEquals(6, linked_ABC6_copy.capacity());
        assertEquals(linked_ABC6, linked_ABC6_copy);
    }

    ////////////////////// first //////////////////////

    @Test
    public void testABC6First() {
        assertEquals("A", linked_ABC6.first());
    }

    ////////////////////// last //////////////////////

    @Test
    public void testABC6Last() {
        assertEquals("C", linked_ABC6.last());
    }

    ////////////////////// equals //////////////////////

    @Test
    public void testABC6EqualsNull() {
        Pipe<String> s = null;
        assertFalse(linked_ABC6.equals(null));
    }

    @Test
    public void testABC6EqualsSelf() {
        assertTrue(linked_ABC6.equals(linked_ABC6));
    }

    @Test
    public void testABC6EqualsNonPipe() {
        assertFalse(linked_ABC6.equals(("[A, B, C]:6")));
    }

    @Test
    public void testABC6EqualsDifferentABC6() {
        Pipe<String> linked_ABC6_dup = new LinkedPipe<>(6);
        linked_ABC6_dup.append("A");
        linked_ABC6_dup.append("B");
        linked_ABC6_dup.append("C");
        assertEquals(linked_ABC6, linked_ABC6_dup);
    }

    @Test
    public void testABC6EqualsABC10() {
        Pipe<String> linked_ABC10_dup = new LinkedPipe<>(10);
        linked_ABC10_dup.append("A");
        linked_ABC10_dup.append("B");
        linked_ABC10_dup.append("C");
        assertNotEquals(linked_ABC6, linked_ABC10_dup);
    }

    @Test
    public void testABC6EqualsAB6() {
        Pipe<String> linked_AB6 = new LinkedPipe<>(6);
        linked_AB6.append("A");
        linked_AB6.append("B");
        assertFalse(linked_ABC6.equals(linked_AB6));
    }

    @Test
    public void testABC6EqualsDEF6() {
        Pipe<String> linked_DEF6 = new LinkedPipe<>(6);
        linked_DEF6.append("D");
        linked_DEF6.append("E");
        linked_DEF6.append("F");
        assertFalse(linked_ABC6.equals(linked_DEF6));
    }

    @Test
    public void testABCD6EqualsDifferentABCD6() {
        linked_ABC6.append("D");
        Pipe<String> linked_ABC6_dup = new LinkedPipe<>(6);
        linked_ABC6_dup.append("A");
        linked_ABC6_dup.append("B");
        linked_ABC6_dup.append("C");
        linked_ABC6_dup.append(" ");
        linked_ABC6_dup.append("D");
        assertFalse(linked_ABC6.equals(linked_ABC6_dup));
    }

    ////////////////////// hashCode //////////////////////

    @Test
    public void testHashCodeABC6AndAB6() {
        Pipe<String> linked_AB6 = new LinkedPipe<>(10);
        linked_AB6.append("A");
        linked_AB6.append("B");
        assertNotEquals(linked_ABC6.hashCode(), linked_AB6.hashCode());
    }

    @Test
    public void testHashCodeABC6AndDifferentABC6() {
        Pipe<String> linked_ABC6_dup = new LinkedPipe<>(6);
        linked_ABC6_dup.append("A");
        linked_ABC6_dup.append("B");
        linked_ABC6_dup.append("C");
        assertTrue(linked_ABC6.hashCode() == linked_ABC6_dup.hashCode());
    }

    @Test
    public void testHashCodeABC6AndABC10() {
        Pipe<String> linked_ABC10 = new LinkedPipe<>(10);
        linked_ABC10.append("A");
        linked_ABC10.append("B");
        linked_ABC10.append("C");
        assertNotEquals(linked_ABC6.hashCode(), linked_ABC10.hashCode());
    }

    @Test
    public void testHashCodeABC6ArrayAndABC6Linked() {
        Pipe<String> array_ABC6 = new CircArrayPipe<>(6);
        array_ABC6.append("A");
        array_ABC6.append("B");
        array_ABC6.append("C");
        assertFalse(linked_ABC6.hashCode() == array_ABC6.hashCode());
    }

    @Test
    public void testHashCodeABC6ListAndABC6Linked() {
        Pipe<String> list_ABC6 = new ListPipe<>(6);
        list_ABC6.append("A");
        list_ABC6.append("B");
        list_ABC6.append("C");
        assertFalse(linked_ABC6.hashCode() == list_ABC6.hashCode());
    }

    ////////////////////// toString //////////////////////

    @Test
    public void testABC6ToString() {
        assertEquals("[A, A, A]:6", linked_ABC6.toString());
    }

    //******************** EMPTY3 TESTS IN PIPE METHOD ORDER ********************//

    ////////////////////// prepend //////////////////////

    @Test
    public void testEmpty3PrependX() {
        linked_empty3.prepend("X");
        assertEquals(1, linked_empty3.length());
    }

    @Test
    public void testEmpty3PrependXY() {
        linked_empty3.prepend("X");
        linked_empty3.prepend("Y");
        assertEquals(2, linked_empty3.length());
    }

    @Test
    public void testEmpty3PrependXYZ() {
        linked_empty3.prepend("X");
        linked_empty3.prepend("Y");
        linked_empty3.prepend("Z");
        assertEquals(3, linked_empty3.length());
    }

    ////////////////////// append --> ISE, IAE //////////////////////

    @Test
    public void testEmpty3AppendA() {
        linked_empty3.append("A");
        assertEquals(1, linked_empty3.length());
    }

    @Test
    public void testEmpty3AppendAB() {
        linked_empty3.append("A");
        linked_empty3.append("B");
        assertEquals(2, linked_empty3.length());
    }

    @Test
    public void testEmpty3AppendABC() {
        linked_empty3.append("A");
        linked_empty3.append("B");
        linked_empty3.append("C");
        assertEquals(3, linked_empty3.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testAppendIntoFullPipe() {
        linked_empty3.append("D");
        linked_empty3.append("E");
        linked_empty3.append("F");
        linked_empty3.append("G"); // <== this should cause ISE
        fail(); // <== if this line is reached, no ISE
    }

    ////////////////////// removeFirst --> ISE //////////////////////

    @Test
    public void testEmpty3AppendARemoveFirst() {
        linked_empty3.append("A");
        assertEquals(1, linked_empty3.length());
        String str = linked_empty3.removeFirst();
        assertEquals(0, linked_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3AppendABRemoveFirst() {
        linked_empty3.append("A");
        linked_empty3.append("B");
        assertEquals(2, linked_empty3.length());
        String str = linked_empty3.removeFirst();
        assertEquals(1, linked_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3AppendBPrependARemoveFirst() {
        linked_empty3.append("B");
        linked_empty3.prepend("A");
        String str = linked_empty3.removeFirst();
        assertEquals(1, linked_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveFirstEmpty() {
        linked_empty3.removeFirst(); // <== this should cause ISE
        fail(); // <== if this line is reached, no ISE
    }

    ////////////////////// removeLast --> ISE //////////////////////

    @Test
    public void testEmpty3PrependARemoveLast() {
        linked_empty3.prepend("A");
        assertEquals(1, linked_empty3.length());
        String str = linked_empty3.removeLast();
        assertEquals(0, linked_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3AppendABRemoveLast() {
        linked_empty3.append("A");
        linked_empty3.append("B");
        String str = linked_empty3.removeLast();
        assertEquals(1, linked_empty3.length());
        assertEquals("B", str);
    }

    @Test
    public void testEmpty3PrependABRemoveLast() {
        linked_empty3.prepend("A");
        linked_empty3.prepend("B");
        assertEquals(2, linked_empty3.length());
        String str = linked_empty3.removeLast();
        assertEquals(1, linked_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveLastEmpty() {
        linked_empty3.removeLast(); // <== this should cause ISE
        fail(); // <== if this line is reached, no ISE
    }

    ////////////////////// removeFirstLast combo //////////////////////

    @Test
    public void testEmpty3RemoveFirstAndLast() {
        linked_empty3.prepend("X");
        linked_empty3.prepend("Y");
        linked_empty3.prepend("Z");
        linked_empty3.removeFirst();
        linked_empty3.removeLast();
        assertEquals(1, linked_empty3.length());
    }

    @Test
    public void testEmpty3RemoveFirstLastFirst() {
        linked_empty3.append("A");
        linked_empty3.append("B");
        linked_empty3.append("C");
        linked_empty3.removeFirst();
        linked_empty3.removeLast();
        linked_empty3.removeFirst();
        assertEquals(0, linked_empty3.length());
    }

    ////////////////////// length //////////////////////

    @Test
    public void testEmpty3Length() {
        assertEquals(0, linked_empty3.length());
    }

    ////////////////////// capacity //////////////////////

    @Test
    public void testEmpty3Capacity() {
        assertEquals(3, linked_empty3.capacity());
    }

    ////////////////////// newInstance //////////////////////

    @Test
    public void testEmpty3NewInstance() {
        Pipe<String> linked_empty3_new = linked_empty3.newInstance();
        assertEquals(0, linked_empty3_new.length());
        assertEquals(3, linked_empty3_new.capacity());
    }

    ////////////////////// clear //////////////////////

    @Test
    public void testEmpty3Clear() {
        linked_empty3.clear();
        assertEquals(0, linked_empty3.length());
        assertEquals(3, linked_empty3.capacity());
    }

    @Test
    public void testEmpty3Clear2() {
        linked_empty3.clear();
        String result = "";
        for (String s : linked_empty3) {
            if (s != null) {
                result += s;
            }
        }
        assertEquals(0, linked_empty3.length());
        assertEquals(3, linked_empty3.capacity());
        assertEquals("", result);
    }

    ////////////////////// isEmpty //////////////////////

    @Test
    public void testEmpty3IsEmpty() {
        assertTrue(linked_empty3.isEmpty());
    }

    ////////////////////// isFull //////////////////////

    @Test
    public void testEmpty3IsFull() {
        linked_empty3.append("A");
        linked_empty3.append("B");
        linked_empty3.append("C");
        assertTrue(linked_empty3.isFull());
    }

    ////////////////////// appendAll --> IAE, ISE //////////////////////

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
        assertEquals("AAA", empty3_result.toString());

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
        Pipe<String> linked_empty3_aa1 = new LinkedPipe<>(0);
        linked_empty3_aa1.append("X");
        linked_empty3.appendAll(linked_empty3_aa1); // <== this should cause IAE
        fail(); // <== if this line is reached, no ISE
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3AppendAllLinkedWhenFull() {
        testEmpty3AppendAllLinked();
        Pipe<String> linked_empty3_aa2 = new LinkedPipe<>(2);
        linked_empty3_aa2.append("X");
        linked_empty3_aa2.append("Y");
        linked_empty3.appendAll(linked_empty3_aa2); // <== this should cause ISE
        fail(); // <== if this line is reached, no ISE
    }

    ////////////////////// copy //////////////////////

    @Test
    public void testEmpty3Copy() {
        Pipe<String> linked_empty3_copy = linked_empty3.copy();
        assertEquals(0, linked_empty3_copy.length());
        assertEquals(3, linked_empty3_copy.capacity());
        assertEquals(linked_empty3, linked_empty3_copy);
    }

    ////////////////////// first //////////////////////

    @Test
    public void testEmpty3First() {
        assertEquals(null, linked_empty3.first());
    }

    ////////////////////// last //////////////////////

    @Test
    public void testEmpty3Last() {
        assertEquals(null, linked_empty3.last());
    }

    ////////////////////// equals //////////////////////

    @Test
    public void testEmpty3EqualsNull() {
        Pipe<String> s = null;
        assertFalse(linked_empty3.equals(null));
    }

    @SuppressWarnings("EqualsWithItself")
    @Test
    public void testEmpty3EqualsSelf() {
        assertTrue(linked_empty3.equals(linked_empty3));
    }

    @Test
    public void testEmpty3EqualsNonPipe() {
        assertFalse(linked_empty3.equals(("[]:3")));
    }

    @Test
    public void testEmpty3EqualsDifferentEmpty3() {
        Pipe<String> linked_empty3_dup = new LinkedPipe<>(3);
        assertEquals(linked_empty3, linked_empty3_dup);
    }

    @Test
    public void testEmpty3EqualsEmpty5() {
        Pipe<String> linked_empty5 = new LinkedPipe<>(5);
        assertNotEquals(linked_empty3, linked_empty5);
    }

    ////////////////////// hashCode //////////////////////

    // no hash code tests for empty list //

    ////////////////////// toString //////////////////////

    @Test
    public void testEmpty3ToString() {
        assertEquals("[]:3", linked_empty3.toString());
    }

    ////////////////////// iterator //////////////////////

    @Test(expected = NoSuchElementException.class)
    public void testEmpty3IteratorNext() {
        linked_empty3.iterator().next(); // <== this should cause NSEE
        fail(); // <== if this line is reached, no NSEE
    }

}
