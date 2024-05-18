import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import VariableParser.VariableParser;
import components.linear.LinearVariable;

/**
 * Tests for isValidTerm and parseTerm.
 */
public class VariableParserTest {

    /*
     * Valid tests
     */

    /**
     * Test for a valid variable.
     */
    @Test
    public void validTest1() {
        String test = "x^2+2*x^2-5*x";
        assertTrue(VariableParser.isValidTerm(test));
    }

    /**
     * Test for a valid variable.
     */
    @Test
    public final void parseTest1() {
        String test = "x^2+2*x^2-5*x";
        LinearVariable parsed = VariableParser.parseExpr(test);
        LinearVariable expected = new LinearVariable().add("x", 3, 2).add("x",
                -5);
        System.out.print(parsed.compareTo(expected));
        assertEquals(parsed, expected);
    }

    /**
     * Test for a valid variable.
     */
    @Test
    public void validTest2() {
        String test = "5";
        assertTrue(VariableParser.isValidTerm(test));
    }

    /**
     * Test for a valid variable.
     */
    @Test
    public void validTest3() {
        String test = "0";
        assertTrue(VariableParser.isValidTerm(test));
    }

    /**
     * Test for a valid variable.
     */
    @Test
    public void validTest4() {
        String test = "a+b+c+d+e+f+g+h+i+j+k+l+m+n+o+p+q+r+s+t+u+v+w+x+y+z";
        assertTrue(VariableParser.isValidTerm(test));
    }

    /**
     * Test for a valid variable.
     */
    @Test
    public void validTest5() {
        String test = "(x+y)^2";
        assertTrue(VariableParser.isValidTerm(test));
    }

    /**
     * Test for a valid variable.
     */
    @Test
    public void validTest6() {
        String test = "2*(x+y)";
        assertTrue(VariableParser.isValidTerm(test));
    }

    /*
     * Invalid tests
     */

    /**
     * Test for an invalid variable.
     */
    @Test
    public void invalidTest1() {
        String test = "2*";
        assertFalse(VariableParser.isValidTerm(test));
    }

    /**
     * Test for an invalid variable.
     */
    @Test
    public void invalidTest2() {
        String test = "2*x^";
        assertFalse(VariableParser.isValidTerm(test));
    }

    /**
     * Test for an invalid variable.
     */
    @Test
    public void invalidTest3() {
        String test = "22-";
        assertFalse(VariableParser.isValidTerm(test));
    }

}
