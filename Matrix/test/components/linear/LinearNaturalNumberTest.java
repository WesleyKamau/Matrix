/**
 *
 */
package components.linear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 */
public class LinearNaturalNumberTest {

    /**
     * Test method for
     * {@link components.linear.LinearNaturalNumber#add(components.linear.LinearNaturalNumber)}.
     */
    @Test
    public void testAddLinearNaturalNumber() {
        final int value1 = 2;
        final int value2 = 3;
        LinearNaturalNumber test1 = new LinearNaturalNumber(value1);
        LinearNaturalNumber test2 = new LinearNaturalNumber(value2);

        LinearNaturalNumber expected = new LinearNaturalNumber(value1 + value2);
        assertEquals(test1.add(test2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearNaturalNumber#add(int)}.
     */
    @Test
    public void testAddInt() {
        final int value1 = 6;
        final int value2 = 5;
        LinearNaturalNumber test = new LinearNaturalNumber(value1);

        LinearNaturalNumber expected = new LinearNaturalNumber(value1 + value2);
        assertEquals(test.add(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearNaturalNumber#add(double)}.
     */
    @Test
    public void testAddDouble() {
        final int value1 = 12;
        final double value2 = 64.1;
        LinearNaturalNumber test = new LinearNaturalNumber(value1);

        LinearNaturalNumber expected = new LinearNaturalNumber(
                (int) (value1 + value2));
        assertEquals(test.add(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearNaturalNumber#multiply(components.linear.LinearNaturalNumber)}.
     */
    @Test
    public void testMultiplyLinearNaturalNumber() {
        final int value1 = 1;
        final int value2 = 7;
        LinearNaturalNumber test1 = new LinearNaturalNumber(value1);
        LinearNaturalNumber test2 = new LinearNaturalNumber(value2);

        LinearNaturalNumber expected = new LinearNaturalNumber(value1 * value2);
        assertEquals(test1.multiply(test2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearNaturalNumber#multiply(int)}.
     */
    @Test
    public void testMultiplyInt() {
        final int value1 = 1;
        final int value2 = 5;
        LinearNaturalNumber test = new LinearNaturalNumber(value1);

        LinearNaturalNumber expected = new LinearNaturalNumber(value1 * value2);
        assertEquals(test.multiply(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearNaturalNumber#multiply(double)}.
     */
    @Test
    public void testMultiplyDouble() {
        final int value1 = 12;
        final double value2 = 64.5;
        LinearNaturalNumber test = new LinearNaturalNumber(value1);

        LinearNaturalNumber expected = new LinearNaturalNumber(
                value1 * (int) value2);
        assertEquals(test.multiply(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearNaturalNumber#divide(components.linear.LinearNaturalNumber)}.
     */
    @Test
    public void testDivide() {
        final int value1 = 1;
        final int value2 = 7;
        LinearNaturalNumber test1 = new LinearNaturalNumber(value1);
        LinearNaturalNumber test2 = new LinearNaturalNumber(value2);

        LinearNaturalNumber expected = new LinearNaturalNumber(value1 / value2);
        assertEquals(test1.divide(test2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearNaturalNumber#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsFalse() {
        final int value1 = 1;
        final int value2 = 7;
        LinearNaturalNumber test1 = new LinearNaturalNumber(value1);
        LinearNaturalNumber test2 = new LinearNaturalNumber(value2);

        assertFalse(test1.equals(test2));
    }

    /**
     * Test method for
     * {@link components.linear.LinearNaturalNumber#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsTrue() {
        final int value1 = 81;
        final int value2 = 81;
        LinearNaturalNumber test1 = new LinearNaturalNumber(value1);
        LinearNaturalNumber test2 = new LinearNaturalNumber(value2);

        assertTrue(test1.equals(test2));
    }

    /**
     * Test method for {@link components.linear.LinearNaturalNumber#toString()}.
     */
    @Test
    public void testToStringPositive() {
        final int value = 61;
        String expectedOutput = "61";
        LinearNaturalNumber test = new LinearNaturalNumber(value);

        assertEquals(test.toString(), expectedOutput);
    }

    /**
     * Test method for {@link components.linear.LinearNaturalNumber#clear()}.
     */
    @Test
    public void testClear() {
        final int value = 11;
        LinearNaturalNumber test = new LinearNaturalNumber(value);

        /*
         * Call method under test
         */
        test.clear();

        assertEquals(test, new LinearNaturalNumber());
    }

    /**
     * Test method for
     * {@link components.linear.LinearNaturalNumber#newInstance()}.
     */
    @Test
    public void testNewInstance() {
        final int value = 11;
        LinearNaturalNumber test = new LinearNaturalNumber(value);
        LinearNaturalNumber result = test.newInstance();

        assertEquals(result, new LinearNaturalNumber());
    }

    /**
     * Test method for
     * {@link components.linear.LinearNaturalNumber#transferFrom(components.linear.LinearNaturalNumber)}.
     */
    @Test
    public void testTransferFrom() {
        final int value1 = 1;
        LinearNaturalNumber test1 = new LinearNaturalNumber(value1);
        LinearNaturalNumber test2 = new LinearNaturalNumber();

        /*
         * Call method under test
         */

        test2.transferFrom(test1);

        assertEquals(test1, new LinearNaturalNumber());
        assertEquals(test2, new LinearNaturalNumber(value1));
    }

    /**
     * Test method for {@link components.linear.LinearNaturalNumber#isZero()}.
     */
    @Test
    public void testIsZeroTruePositive() {
        final int value = 0;
        LinearNaturalNumber test = new LinearNaturalNumber(value);

        assertTrue(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearNaturalNumber#isZero()}.
     */
    @Test
    public void testIsZeroFalsePositive() {
        final int value = 12;
        LinearNaturalNumber test = new LinearNaturalNumber(value);

        assertFalse(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearNaturalNumber#isOne()}.
     */
    @Test
    public void testIsOneTrue() {
        final int value = 1;
        LinearNaturalNumber test = new LinearNaturalNumber(value);

        assertTrue(test.isOne());
    }

    /**
     * Test method for {@link components.linear.LinearNaturalNumber#isOne()}.
     */
    @Test
    public void testIsOneFalsePositive() {
        final int value = 11;
        LinearNaturalNumber test = new LinearNaturalNumber(value);

        assertFalse(test.isOne());
    }

    /**
     * Test method for
     * {@link components.linear.LinearNaturalNumber#compareTo(components.linear.LinearNaturalNumber)}.
     */
    @Test
    public void testCompareTo() {
        final int value1 = 1;
        final int value2 = 7;
        LinearNaturalNumber test1 = new LinearNaturalNumber(value1);
        LinearNaturalNumber test2 = new LinearNaturalNumber(value2);

        assertTrue(test1.compareTo(test2) < 0);
    }

    /**
     * Test method for
     * {@link components.linear.LinearSecondary#subtract(components.linear.LinearSecondary)}.
     */
    @Test
    public void testSubract() {
        final int value1 = 7;
        final int value2 = 2;
        LinearNaturalNumber test1 = new LinearNaturalNumber(value1);
        LinearNaturalNumber test2 = new LinearNaturalNumber(value2);

        LinearNaturalNumber expected = new LinearNaturalNumber(value1 - value2);
        assertEquals(test1.subtract(test2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearSecondary#subtract(int)}.
     */
    @Test
    public void testSubtractInt() {
        final int value1 = 5;
        final int value2 = 2;
        LinearNaturalNumber test = new LinearNaturalNumber(value1);

        LinearNaturalNumber expected = new LinearNaturalNumber(value1 - value2);
        assertEquals(test.subtract(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearSecondary#subtract(double)}.
     */
    @Test
    public void testSubtractDouble() {
        final int value1 = 64;
        final double value2 = 12.51;
        LinearNaturalNumber test = new LinearNaturalNumber(value1);

        LinearNaturalNumber expected = new LinearNaturalNumber(
                value1 - (int) value2);
        assertEquals(test.subtract(value2), expected);
    }

}
