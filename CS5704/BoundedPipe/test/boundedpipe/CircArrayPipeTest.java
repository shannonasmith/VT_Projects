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

public class CircArrayPipeTest {
    private Pipe<String> array_empty3;
    private Pipe<String> array_ABC6;

    @Before
    public void setUp() {
        array_empty3 = new CircArrayPipe<>(3);
        array_ABC6 = new CircArrayPipe<>(6);
        array_ABC6.prepend("A");
        array_ABC6.prepend("B");
        array_ABC6.prepend("C");
       }

    ///////////////// Constructor /////////////////

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidParam() {
        Pipe<String> array = new CircArrayPipe<>(-1);
        fail();
    }

    //******************** ABC6 TESTS IN PIPE METHOD ORDER ********************//

    ////////////////////// prepend //////////////////////

    @Test
    public void testABC6PrependX() {
        array_ABC6.prepend("X");
        assertEquals(4, array_ABC6.length());
    }

    @Test
    public void testABC6PrependXY() {
        array_ABC6.prepend("X");
        array_ABC6.prepend("Y");
        assertEquals(5, array_ABC6.length());
    }

    @Test
    public void testABC6PrependXYZ() {
        array_ABC6.prepend("X");
        array_ABC6.prepend("Y");
        array_ABC6.prepend("Z");
        assertEquals(6, array_ABC6.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6PrependIntoFull() {
        array_ABC6.prepend("F");
        array_ABC6.prepend("E");
        array_ABC6.prepend("D");
        array_ABC6.prepend("G");
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6PrependNull() {
        array_ABC6.prepend(null);
        fail();
    }

    ////////////////////// append --> ISE, IAE //////////////////////

    @Test
    public void testABC6AppendD() {
        array_ABC6.append("D");
        assertEquals(4, array_ABC6.length());
    }

    @Test
    public void testABC6AppendDE() {
        array_ABC6.append("D");
        array_ABC6.append("E");
        assertEquals(5, array_ABC6.length());
    }

    @Test
    public void testABC6AppendDEF() {
        array_ABC6.append("D");
        array_ABC6.append("E");
        array_ABC6.append("F");
        assertEquals(6, array_ABC6.length());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6AppendNull() {
        array_ABC6.append(null);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6AppendIntoFullPipe() {
        array_ABC6.append("D");
        array_ABC6.append("E");
        array_ABC6.append("F");
        array_ABC6.append("G");
        fail();
    }

    ////////////////////// removeFirst --> ISE //////////////////////

    @Test
    public void testABC6RemoveFirst() {
        String str = array_ABC6.removeFirst();
        assertEquals(2, array_ABC6.length());
        assertEquals("C", str);
    }

    @Test
    public void testABC6AppendARemoveFirst() {
        array_ABC6.append("A");
        assertEquals(4, array_ABC6.length());
        String str = array_ABC6.removeFirst();
        assertEquals(3, array_ABC6.length());
        assertEquals("C", str);
    }

    @Test
    public void testABC6AppendABRemoveFirst() {
        array_ABC6.append("A");
        array_ABC6.append("B");
        assertEquals(5, array_ABC6.length());
        String str = array_ABC6.removeFirst();
        assertEquals(4, array_ABC6.length());
        assertEquals("C", str);
    }

    @Test
    public void testABC6AppendBPrependARemoveFirst() {
        array_ABC6.append("B");
        array_ABC6.prepend("A");
        String str = array_ABC6.removeFirst();
        assertEquals(4, array_ABC6.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6RemoveFirstFromEmptyList() {
        array_ABC6.removeFirst();
        array_ABC6.removeLast();
        array_ABC6.removeFirst();
        String str = array_ABC6.removeFirst();
        assertEquals(2, array_ABC6.length());
        assertEquals("A", str);
    }

    ////////////////////// removeLast --> ISE //////////////////////

    @Test
    public void testABC6PrependARemoveLast() {
        array_ABC6.prepend("A");
        assertEquals(4, array_ABC6.length());
        String str = array_ABC6.removeLast();
        assertEquals(3, array_ABC6.length());
        assertEquals("A", str);
    }

    @Test
    public void testABC6AppendABRemoveLast() {
        array_ABC6.append("A");
        array_ABC6.append("B");
        String str = array_ABC6.removeLast();
        assertEquals(4, array_ABC6.length());
        assertEquals("B", str);
    }

    @Test
    public void testABC6PrependABRemoveLast() {
        array_ABC6.prepend("A");
        array_ABC6.prepend("B");
        assertEquals(5, array_ABC6.length());
        String str = array_ABC6.removeLast();
        assertEquals(4, array_ABC6.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6RemoveLastFromEmptyList() {
        array_ABC6.removeFirst();
        array_ABC6.removeFirst();
        array_ABC6.removeFirst();
        String str = array_ABC6.removeLast();
        assertEquals(2, array_ABC6.length());
        assertEquals("A", str);
    }

    ////////////////////// removeFirstLast combo //////////////////////

    @Test
    public void testABC6PrependXYZRemoveFirstAndLast() {
        array_ABC6.prepend("X");
        array_ABC6.prepend("Y");
        array_ABC6.prepend("Z");
        array_ABC6.removeFirst();
        array_ABC6.removeLast();
        assertEquals(4, array_ABC6.length());
    }

    @Test
    public void testABC6RemoveFirstLastFirst() {
        array_ABC6.append("A");
        array_ABC6.append("B");
        array_ABC6.append("C");
        array_ABC6.removeFirst();
        array_ABC6.removeLast();
        array_ABC6.removeFirst();
        assertEquals(3, array_ABC6.length());
    }

    ////////////////////// length //////////////////////

    @Test
    public void testABC6Length() {
        assertEquals(3, array_ABC6.length());
    }

    ////////////////////// capacity //////////////////////

    @Test
    public void testABC6Capacity() {
        assertEquals(6, array_ABC6.capacity());
    }

    ////////////////////// newInstance //////////////////////

    @Test
    public void testABC6NewInstance() {
        Pipe<String> new_array_ABC6 = array_ABC6.newInstance();
        assertEquals(0, new_array_ABC6.length());
        assertEquals(6, new_array_ABC6.capacity());
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

    ////////////////////// isEmpty //////////////////////

    @Test
    public void testABC6IsEmpty() {
        array_ABC6.clear();
        assertTrue(array_ABC6.isEmpty());
    }

    ////////////////////// isFull //////////////////////

    @Test
    public void testABC6IsFull() {
        array_ABC6.prepend("X");
        array_ABC6.prepend("Y");
        array_ABC6.prepend("Z");
        assertTrue(array_ABC6.isFull());
    }

    ////////////////////// appendAll --> IAE, ISE //////////////////////

    @Test
    public void testABC6AppendAllArray() {
        array_ABC6.clear();
        assertEquals(0, array_ABC6.length());
        assertEquals(6, array_ABC6.capacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testABC6AppendAllArrayWhenEmpty() {
        testABC6AppendAllArray();
        CircArrayPipe<String> array_ABC6_aa1 = new CircArrayPipe<>(0);
        array_ABC6_aa1.append("X");
        array_ABC6.appendAll(array_ABC6_aa1);
        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void testABC6AppendAllArrayWhenFull() {
        testABC6AppendAllArray();
        CircArrayPipe<String> array_ABC6_aa2 = new CircArrayPipe<>(1);
        array_ABC6_aa2.append("X");
        array_ABC6_aa2.append("Y");
        array_ABC6.appendAll(array_ABC6_aa2);
        fail();
    }

    ////////////////////// copy //////////////////////

    @Test
    public void testABC6Copy() {
        Pipe<String> array_ABC6_copy = array_ABC6.copy();
        assertEquals(3, array_ABC6_copy.length());
        assertEquals(6, array_ABC6_copy.capacity());
        assertEquals(array_ABC6, array_ABC6_copy);
        String s1 = array_ABC6.removeFirst();
        String copy_s1 = array_ABC6_copy.removeFirst();
        assertTrue(Objects.equals(s1, copy_s1));
    }

    ////////////////////// first //////////////////////

    @Test
    public void testABC6First() {
        assertEquals("C", array_ABC6.first());
    }

    ////////////////////// last //////////////////////

    @Test
    public void testABC6Last() {
        assertEquals("A", array_ABC6.last());
        System.out.println("Actual: " + array_ABC6.toString());
    }

    ////////////////////// equals //////////////////////

    @Test
    public void testABC6EqualsNull() {
        Pipe<String> s = null;
        assertFalse(array_ABC6.equals(null));
    }

    @Test
    public void testABC6EqualsSelf() {
        assertTrue(array_ABC6.equals(array_ABC6));
    }

    @Test
    public void testABC6EqualsNonPipe() {
        assertFalse(array_ABC6.equals(("[A, B, C]:6")));
    }

    @Test
    public void testABC6EqualsABC10() {
        Pipe<String> array_ABC10_dup = new CircArrayPipe<>(10);
        array_ABC10_dup.append("A");
        array_ABC10_dup.append("B");
        array_ABC10_dup.append("C");
        assertNotEquals(array_ABC6, array_ABC10_dup);
    }

    @Test
    public void testABC6EqualsAB6() {
        Pipe<String> array_AB6 = new CircArrayPipe<>(6);
        array_AB6.append("A");
        array_AB6.append("B");
        assertFalse(array_ABC6.equals(array_AB6));
    }

    @Test
    public void testABC6EqualsDEF6() {
        Pipe<String> array_DEF6 = new CircArrayPipe<>(6);
        array_DEF6.append("D");
        array_DEF6.append("E");
        array_DEF6.append("F");
        assertFalse(array_ABC6.equals(array_DEF6));
    }

    @Test
    public void testABCD6EqualsDifferentABCD6() {
        array_ABC6.append("D");
        Pipe<String> array_ABC6_dup = new CircArrayPipe<>(6);
        array_ABC6_dup.append("A");
        array_ABC6_dup.append("B");
        array_ABC6_dup.append("C");
        array_ABC6_dup.append(" ");
        array_ABC6_dup.append("D");
        assertFalse(array_ABC6.equals(array_ABC6_dup));
    }

    ////////////////////// hashCode //////////////////////

    @Test
    public void testHashCodeABC6AndAB6() {
        Pipe<String> array_AB6 = new CircArrayPipe<>(10);
        array_AB6.append("A");
        array_AB6.append("B");
        assertFalse(array_ABC6.hashCode() == array_AB6.hashCode());
    }

    @Test
    public void testHashCodeABC6AndDifferentABC6() {
        Pipe<String> array_ABC6_dup = new CircArrayPipe<>(6);
        array_ABC6_dup.append("A");
        array_ABC6_dup.append("B");
        array_ABC6_dup.append("C");
        assertFalse(array_ABC6.hashCode() == array_ABC6_dup.hashCode());
    }

    @Test
    public void testHashCodeABC6AndABC10() {
        Pipe<String> array_ABC10 = new CircArrayPipe<>(10);
        array_ABC10.append("A");
        array_ABC10.append("B");
        array_ABC10.append("C");
        assertFalse(array_ABC6.hashCode() == array_ABC10.hashCode());
    }

    @Test
    public void testHashCodeABC6ListAndABC6Array() {
        Pipe<String> list_ABC6 = new ListPipe<>(6);
        list_ABC6.append("A");
        list_ABC6.append("B");
        list_ABC6.append("C");
        assertFalse(array_ABC6.hashCode() == list_ABC6.hashCode());
    }

    @Test
    public void testHashCodeABC6LinkedAndABC6Array() {
        Pipe<String> linked_ABC6 = new LinkedPipe<>(6);
        linked_ABC6.append("A");
        linked_ABC6.append("B");
        linked_ABC6.append("C");
        assertFalse(array_ABC6.hashCode() == linked_ABC6.hashCode());
    }

    ////////////////////// toString //////////////////////

    @Test
    public void testABC6ToString() {
        assertEquals("[C, B, A]:6", array_ABC6.toString());
    }

    //******************** EMPTY3 TESTS IN PIPE METHOD ORDER ********************//

    ////////////////////// prepend //////////////////////

    @Test
    public void testEmpty3PrependX() {
        array_empty3.prepend("X");
        assertEquals(1, array_empty3.length());
    }

    @Test
    public void testEmpty3PrependXY() {
        array_empty3.prepend("X");
        array_empty3.prepend("Y");
        assertEquals(2, array_empty3.length());
    }

    @Test
    public void testEmpty3PrependXYZ() {
        array_empty3.prepend("X");
        array_empty3.prepend("Y");
        array_empty3.prepend("Z");
        assertEquals(3, array_empty3.length());
    }

    ////////////////////// append --> ISE, IAE //////////////////////

    @Test
    public void testEmpty3AppendA() {
        array_empty3.append("A");
        assertEquals(1, array_empty3.length());
        assertFalse(array_empty3.isEmpty());
    }

    @Test
    public void testEmpty3AppendAB() {
        array_empty3.append("A");
        array_empty3.append("B");
        assertEquals(2, array_empty3.length());
    }

    @Test
    public void testEmpty3AppendABC() {
        array_empty3.append("A");
        array_empty3.append("B");
        array_empty3.append("C");
        assertEquals(3, array_empty3.length());
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3AppendIntoFullPipe() {
        array_empty3.append("D");
        array_empty3.append("E");
        array_empty3.append("F");
        array_empty3.append("G");
        fail();
    }

    ////////////////////// removeFirst --> ISE //////////////////////

    @Test
    public void testEmpty3AppendARemoveFirst() {
        array_empty3.append("A");
        assertEquals(1, array_empty3.length());
        String str = array_empty3.removeFirst();
        assertEquals(0, array_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3AppendABRemoveFirst() {
        array_empty3.append("A");
        array_empty3.append("B");
        assertEquals(2, array_empty3.length());
        String str = array_empty3.removeFirst();
        assertEquals(1, array_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3RemoveFirst() {
        array_empty3.removeFirst();
        fail();
    }

    ////////////////////// removeLast --> ISE //////////////////////

    @Test
    public void testEmpty3PrependARemoveLast() {
        array_empty3.prepend("A");
        assertEquals(1, array_empty3.length());
        String str = array_empty3.removeLast();
        assertEquals(0, array_empty3.length());
        assertEquals("A", str);
    }

    @Test
    public void testEmpty3AppendABRemoveLast() {
        array_empty3.append("A");
        array_empty3.append("B");
        String str = array_empty3.removeLast();
        assertEquals(1, array_empty3.length());
        assertEquals("B", str);
    }

    @Test
    public void testEmpty3PrependABRemoveLast() {
        array_empty3.prepend("A");
        array_empty3.prepend("B");
        assertEquals(2, array_empty3.length());
        String str = array_empty3.removeLast();
        assertEquals(1, array_empty3.length());
        assertEquals("A", str);
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3RemoveLast() {
        array_empty3.removeLast();
        fail();
    }

    ////////////////////// removeFirstLast combo //////////////////////

    @Test
    public void testEmpty3RemoveFirstAndLast() {
        array_empty3.prepend("X");
        array_empty3.prepend("Y");
        array_empty3.prepend("Z");
        array_empty3.removeFirst();
        array_empty3.removeLast();
        assertEquals(1, array_empty3.length());
    }

    @Test
    public void testEmpty3RemoveFirstLastFirst() {
        array_empty3.append("A");
        array_empty3.append("B");
        array_empty3.append("C");
        array_empty3.removeFirst();
        array_empty3.removeLast();
        array_empty3.removeFirst();
        assertEquals(0, array_empty3.length());
    }

    ////////////////////// length //////////////////////

    @Test
    public void testEmpty3Length() {
        assertEquals(0, array_empty3.length());
        assertTrue(array_empty3.isEmpty());
    }

    ////////////////////// capacity //////////////////////

    @Test
    public void testEmpty3Capacity() {
        assertEquals(3, array_empty3.capacity());
    }

    ////////////////////// newInstance //////////////////////

    @Test
    public void testEmpty3NewInstance() {
        Pipe<String> new_empty3 = array_empty3.newInstance();
        assertEquals(0, new_empty3.length());
        assertEquals(3, new_empty3.capacity());
    }

    ////////////////////// clear //////////////////////

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
    public void testEmpty3IsEmpty() {
        assertTrue(array_empty3.isEmpty());
    }

    ////////////////////// isFull //////////////////////

    @Test
    public void testEmpty3IsFull() {
        array_empty3.append("A");
        array_empty3.append("B");
        array_empty3.append("C");
        assertTrue(array_empty3.isFull());
    }

    ////////////////////// appendAll --> IAE, ISE //////////////////////

    @Test
    public void testEmpty3AppendAllArray() {
        array_empty3.appendAll(array_ABC6);
        String empty3_result = "";
        for (String s : array_empty3) {
            if (s != null) {
                empty3_result += s;
            }
        }
        String abc6_result = "";
        for (String s : array_ABC6) {
            if (s != null) {
                abc6_result += s;
            }
        }
        assertEquals(3, array_empty3.length());
        assertEquals(3, array_empty3.capacity());
        assertEquals("CBA", empty3_result);
        assertEquals(3, array_empty3.length());
        assertEquals(3, array_empty3.capacity());
        assertEquals("", abc6_result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty3AppendAllArrayWhenEmpty() {
        testEmpty3AppendAllArray();
        ListPipe<String> array_empty3_aa1 = new ListPipe<>(0);
        array_empty3_aa1.append("X");
        array_empty3.appendAll(array_empty3_aa1); // <== this should cause IAE
        fail(); // <== if this line is reached, no IAE
    }

    @Test(expected = IllegalStateException.class)
    public void testEmpty3AppendAllArrayWhenFull() {
        testEmpty3AppendAllArray();
        CircArrayPipe<String> array_empty3_aa2 = new CircArrayPipe<>(2);
        array_empty3_aa2.append("X");
        array_empty3_aa2.append("Y");
        array_empty3.appendAll(array_empty3_aa2); // <== this should cause ISE
        fail(); // <== if this line is reached, no ISE
    }

    ////////////////////// copy //////////////////////

    @Test
    public void testEmpty3Copy() {
        Pipe<String> array_empty3_copy = array_empty3.copy();
        assertEquals(0, array_empty3_copy.length());
        assertEquals(3, array_empty3_copy.capacity());
        assertEquals(array_empty3, array_empty3_copy);
    }

    ////////////////////// first //////////////////////

    @Test
    public void testEmpty3First() {
        assertEquals(null, array_empty3.first());
    }

    ////////////////////// last //////////////////////

    @Test
    public void testEmpty3Last() {
        assertEquals(null, array_empty3.last());
    }

    ////////////////////// equals //////////////////////

    @Test
    public void testEmpty3EqualsNull() {
        Pipe<String> s = null;
        assertFalse(array_empty3.equals(null));
    }

    @Test
    public void testEmpty3EqualsSelf() {
        assertTrue(array_empty3.equals(array_empty3));
    }

    @Test
    public void testEmpty3EqualsNonPipe() {
        assertFalse(array_empty3.equals(("[]:3")));
    }

    @Test
    public void testEmpty3EqualsDifferentEmpty3() {
        Pipe<String> array_empty3_dup = new CircArrayPipe<>(3);
        assertEquals(array_empty3, array_empty3_dup);
    }

    @Test
    public void testEmpty3EqualsEmpty5() {
        Pipe<String> array_empty5 = new CircArrayPipe<>(5);
        assertFalse(array_empty3.equals(array_empty5));
    }

    ////////////////////// hashCode //////////////////////

    // no hash code tests for empty list //

    ////////////////////// toString //////////////////////

    @Test
    public void testEmpty3ToString() {
        assertEquals("[]:3", array_empty3.toString());
    }

    ////////////////////// iterator //////////////////////

    @Test(expected = NoSuchElementException.class)
    public void testEmpty3IteratorNext() {
        array_empty3.iterator().next(); // <== this should cause NSEE
        fail(); // <== if this line is reached, no NSEE
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testTwoStrings() {
        CircArrayPipe<String> array = new CircArrayPipe<>(4);

        array.append("W");
        System.out.println("array:  Expected: [W]:4      Actual: " + array);

        array.append("X");
        System.out.println("array:  Expected: [W, X]:4   Actual: " + array);

        String s1 = array.removeFirst();
        System.out.println("   s1:  Expected: W          Actual: " + s1);
        System.out.println("array:  Expected: [X]:4      Actual: " + array);

        array.prepend("Y");
        System.out.println("array:  Expected: [Y, X]:4   Actual: " + array);

        String s2 = array.removeLast();
        System.out.println("   s2:  Expected: X          Actual: " + s2);
        System.out.println("array:  Expected: [Y]:4      Actual: " + array);

        array.append("Z");
        System.out.println("array:  Expected: [Y, Z]:4   Actual: " + array);

        array.prepend(s2);

        StringBuilder result = new StringBuilder();
        for (String s : array) {
            if (s != null) {
                result.append(s);
            }
        }
        assertEquals("XZY", result.toString());
        assertEquals("X", array.first());
        assertEquals("Y", array.last());
        assertEquals(3, array.length());
        assertEquals("[X, Z, Y]:4", array.toString());
        fail();
    }
}
