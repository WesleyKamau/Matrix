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
public class LinearIntegerTest {

    /**
     * Test method for
     * {@link components.linear.LinearInteger#add(components.linear.LinearInteger)}.
     */
    @Test
    public void testAddLinearInteger() {
        final int value1 = 2;
        final int value2 = 3;
        LinearInteger test1 = new LinearInteger(value1);
        LinearInteger test2 = new LinearInteger(value2);

        LinearInteger expected = new LinearInteger(value1 + value2);
        assertEquals(test1.add(test2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearInteger#add(int)}.
     */
    @Test
    public void testAddInt() {
        final int value1 = 6;
        final int value2 = -5;
        LinearInteger test = new LinearInteger(value1);

        LinearInteger expected = new LinearInteger(value1 + value2);
        assertEquals(test.add(value2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearInteger#add(double)}.
     */
    @Test
    public void testAddDouble() {
        final int value1 = 12;
        final double value2 = 64.1;
        LinearInteger test = new LinearInteger(value1);

        LinearInteger expected = new LinearInteger((int) (value1 + value2));
        assertEquals(test.add(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearInteger#multiply(components.linear.LinearInteger)}.
     */
    @Test
    public void testMultiplyLinearInteger() {
        final int value1 = 1;
        final int value2 = 7;
        LinearInteger test1 = new LinearInteger(value1);
        LinearInteger test2 = new LinearInteger(value2);

        LinearInteger expected = new LinearInteger(value1 * value2);
        assertEquals(test1.multiply(test2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearInteger#multiply(int)}.
     */
    @Test
    public void testMultiplyInt() {
        final int value1 = 1;
        final int value2 = -5;
        LinearInteger test = new LinearInteger(value1);

        LinearInteger expected = new LinearInteger(value1 * value2);
        assertEquals(test.multiply(value2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearInteger#multiply(double)}.
     */
    @Test
    public void testMultiplyDouble() {
        final int value1 = 12;
        final double value2 = 64.1;
        LinearInteger test = new LinearInteger(value1);

        LinearInteger expected = new LinearInteger((int) (value1 * value2));
        assertEquals(test.multiply(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearInteger#divide(components.linear.LinearInteger)}.
     */
    @Test
    public void testDivide() {
        final int value1 = 1;
        final int value2 = 7;
        LinearInteger test1 = new LinearInteger(value1);
        LinearInteger test2 = new LinearInteger(value2);

        LinearInteger expected = new LinearInteger(value1 / value2);
        assertEquals(test1.divide(test2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearInteger#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsFalse() {
        final int value1 = 1;
        final int value2 = 7;
        LinearInteger test1 = new LinearInteger(value1);
        LinearInteger test2 = new LinearInteger(value2);

        assertFalse(test1.equals(test2));
    }

    /**
     * Test method for
     * {@link components.linear.LinearInteger#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsTrue() {
        final int value1 = -81;
        final int value2 = -81;
        LinearInteger test1 = new LinearInteger(value1);
        LinearInteger test2 = new LinearInteger(value2);

        assertTrue(test1.equals(test2));
    }

    /**
     * Test method for {@link components.linear.LinearInteger#toString()}.
     */
    @Test
    public void testToStringPositive() {
        final int value = 61;
        String expectedOutput = "61";
        LinearInteger test = new LinearInteger(value);

        assertEquals(test.toString(), expectedOutput);
    }

    /**
     * Test method for {@link components.linear.LinearInteger#toString()}.
     */
    @Test
    public void testToStringNegative() {
        final int value = -12;
        String expectedOutput = "-12";
        LinearInteger test = new LinearInteger(value);

        assertEquals(test.toString(), expectedOutput);
    }

    /**
     * Test method for {@link components.linear.LinearInteger#clear()}.
     */
    @Test
    public void testClear() {
        final int value = -11;
        LinearInteger test = new LinearInteger(value);

        /*
         * Call method under test
         */
        test.clear();

        assertEquals(test, new LinearInteger());
    }

    /**
     * Test method for {@link components.linear.LinearInteger#newInstance()}.
     */
    @Test
    public void testNewInstance() {
        final int value = -11;
        LinearInteger test = new LinearInteger(value);
        LinearInteger result = test.newInstance();

        assertEquals(result, new LinearInteger());
    }

    /**
     * Test method for
     * {@link components.linear.LinearInteger#transferFrom(components.linear.LinearInteger)}.
     */
    @Test
    public void testTransferFrom() {
        final int value1 = 1;
        LinearInteger test1 = new LinearInteger(value1);
        LinearInteger test2 = new LinearInteger();

        /*
         * Call method under test
         */

        test2.transferFrom(test1);

        assertEquals(test1, new LinearInteger());
        assertEquals(test2, new LinearInteger(value1));
    }

    /**
     * Test method for {@link components.linear.LinearInteger#isZero()}.
     */
    @Test
    public void testIsZeroTruePositive() {
        final int value = 0;
        LinearInteger test = new LinearInteger(value);

        assertTrue(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearInteger#isZero()}.
     */
    @Test
    public void testIsZeroTrueNegative() {
        final int value = -0;
        LinearInteger test = new LinearInteger(value);

        assertTrue(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearInteger#isZero()}.
     */
    @Test
    public void testIsZeroFalsePositive() {
        final int value = 12;
        LinearInteger test = new LinearInteger(value);

        assertFalse(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearInteger#isZero()}.
     */
    @Test
    public void testIsZeroFalseNegative() {
        final int value = -11;
        LinearInteger test = new LinearInteger(value);

        assertFalse(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearInteger#isOne()}.
     */
    @Test
    public void testIsOneTrue() {
        final int value = 1;
        LinearInteger test = new LinearInteger(value);

        assertTrue(test.isOne());
    }

    /**
     * Test method for {@link components.linear.LinearInteger#isOne()}.
     */
    @Test
    public void testIsOneFalsePositive() {
        final int value = 11;
        LinearInteger test = new LinearInteger(value);

        assertFalse(test.isOne());
    }

    /**
     * Test method for {@link components.linear.LinearInteger#isOne()}.
     */
    @Test
    public void testIsOneFalseNegative() {
        final int value = -11211;
        LinearInteger test = new LinearInteger(value);

        assertFalse(test.isOne());
    }

    /**
     * Test method for {@link components.linear.LinearInteger#value()}.
     */
    @Test
    public void testValue() {
        final int value = -112;
        LinearInteger test = new LinearInteger(value);

        assertTrue(Double.compare(test.value(), value) == 0);
    }

    /**
     * Test method for
     * {@link components.linear.LinearInteger#compareTo(components.linear.LinearInteger)}.
     */
    @Test
    public void testCompareTo() {
        final int value1 = 1;
        final int value2 = 7;
        LinearInteger test1 = new LinearInteger(value1);
        LinearInteger test2 = new LinearInteger(value2);

        assertTrue(test1.compareTo(test2) < 0);
    }

    /**
     * Test method for
     * {@link components.linear.LinearSecondary#subtract(components.linear.LinearSecondary)}.
     */
    @Test
    public void testSubract() {
        final int value1 = 1;
        final int value2 = 7;
        LinearInteger test1 = new LinearInteger(value1);
        LinearInteger test2 = new LinearInteger(value2);

        LinearInteger expected = new LinearInteger(value1 - value2);
        assertEquals(test1.subtract(test2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearSecondary#subtract(int)}.
     */
    @Test
    public void testSubtractInt() {
        final int value1 = 1;
        final int value2 = -5;
        LinearInteger test = new LinearInteger(value1);

        LinearInteger expected = new LinearInteger(value1 - value2);
        assertEquals(test.subtract(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearSecondary#subtract(double)}.
     */
    @Test
    public void testSubtractDouble() {
        final int value1 = 12;
        final double value2 = 64.1;
        LinearInteger test = new LinearInteger(value1);

        LinearInteger expected = new LinearInteger((int) (value1 - value2));
        assertEquals(test.subtract(value2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearSecondary#negative()}.
     */
    @Test
    public void testNegative1() {
        final int value = 12;
        final int expectedValue = -12;
        LinearInteger test = new LinearInteger(value);
        LinearInteger expected = new LinearInteger(expectedValue);

        assertEquals(test.negative(), expected);
    }

    /**
     * Test method for {@link components.linear.LinearSecondary#negative()}.
     */
    @Test
    public void testNegative2() {
        final int value = -5512;
        final int expectedValue = 5512;
        LinearInteger test = new LinearInteger(value);
        LinearInteger expected = new LinearInteger(expectedValue);

        assertEquals(test.negative(), expected);
    }

}
