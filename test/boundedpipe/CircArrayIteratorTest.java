package boundedpipe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * CS 5704 (Spring 2023) boundedpipe
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.02.17
 */

public class CircArrayIteratorTest {

    private Pipe<String> array_ABC6;

    @Before
    public void setUp() {
        array_ABC6 = new CircArrayPipe<>(6);
        array_ABC6.append("A");
        array_ABC6.append("B");
        array_ABC6.append("C");
    }

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

}