/**
 *
 */
package components.linear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for LinearDouble
 */
public class LinearDoubleTest {

    /**
     * Test method for
     * {@link components.linear.LinearDouble#add(components.linear.LinearDouble)}.
     */
    @Test
    public void testAddLinearDouble() {
        final double value1 = 1.65;
        final double value2 = 7.2;
        LinearDouble test1 = new LinearDouble(value1);
        LinearDouble test2 = new LinearDouble(value2);

        LinearDouble expected = new LinearDouble(value1 + value2);
        assertEquals(test1.add(test2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearDouble#add(int)}.
     */
    @Test
    public void testAddInt() {
        final double value1 = 1.65;
        final int value2 = -5;
        LinearDouble test = new LinearDouble(value1);

        LinearDouble expected = new LinearDouble(value1 + value2);
        assertEquals(test.add(value2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearDouble#add(double)}.
     */
    @Test
    public void testAddDouble() {
        final double value1 = 12.314;
        final double value2 = 64.1;
        LinearDouble test = new LinearDouble(value1);

        LinearDouble expected = new LinearDouble(value1 + value2);
        assertEquals(test.add(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearDouble#multiply(components.linear.LinearDouble)}.
     */
    @Test
    public void testMultiplyLinearDouble() {
        final double value1 = 1.65;
        final double value2 = 7.2;
        LinearDouble test1 = new LinearDouble(value1);
        LinearDouble test2 = new LinearDouble(value2);

        LinearDouble expected = new LinearDouble(value1 * value2);
        assertEquals(test1.multiply(test2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearDouble#multiply(int)}.
     */
    @Test
    public void testMultiplyInt() {
        final double value1 = 1.65;
        final int value2 = -5;
        LinearDouble test = new LinearDouble(value1);

        LinearDouble expected = new LinearDouble(value1 * value2);
        assertEquals(test.multiply(value2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearDouble#multiply(double)}.
     */
    @Test
    public void testMultiplyDouble() {
        final double value1 = 12.314;
        final double value2 = 64.1;
        LinearDouble test = new LinearDouble(value1);

        LinearDouble expected = new LinearDouble(value1 * value2);
        assertEquals(test.multiply(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearDouble#divide(components.linear.LinearDouble)}.
     */
    @Test
    public void testDivide() {
        final double value1 = 1.65;
        final double value2 = 7.2;
        LinearDouble test1 = new LinearDouble(value1);
        LinearDouble test2 = new LinearDouble(value2);

        LinearDouble expected = new LinearDouble(value1 / value2);
        assertEquals(test1.divide(test2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearDouble#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsFalse() {
        final double value1 = 1.65;
        final double value2 = 7.2;
        LinearDouble test1 = new LinearDouble(value1);
        LinearDouble test2 = new LinearDouble(value2);

        assertFalse(test1.equals(test2));
    }

    /**
     * Test method for
     * {@link components.linear.LinearDouble#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsTrue() {
        final double value1 = -81.5;
        final double value2 = -81.5;
        LinearDouble test1 = new LinearDouble(value1);
        LinearDouble test2 = new LinearDouble(value2);

        assertTrue(test1.equals(test2));
    }

    /**
     * Test method for {@link components.linear.LinearDouble#toString()}.
     */
    @Test
    public void testToStringNoDecimalsPositive() {
        final double value = 61.0;
        String expectedOutput = "61";
        LinearDouble test = new LinearDouble(value);

        assertEquals(test.toString(), expectedOutput);
    }

    /**
     * Test method for {@link components.linear.LinearDouble#toString()}.
     */
    @Test
    public void testToStringDecimalsPositive() {
        final double value = 61.012;
        String expectedOutput = "61.012";
        LinearDouble test = new LinearDouble(value);

        assertEquals(test.toString(), expectedOutput);
    }

    /**
     * Test method for {@link components.linear.LinearDouble#toString()}.
     */
    @Test
    public void testToStringNoDecimalsNegative() {
        final double value = -12.0;
        String expectedOutput = "-12";
        LinearDouble test = new LinearDouble(value);

        assertEquals(test.toString(), expectedOutput);
    }

    /**
     * Test method for {@link components.linear.LinearDouble#toString()}.
     */
    @Test
    public void testToStringDecimalsNegative() {
        final double value = -11.0423;
        String expectedOutput = "-11.0423";
        LinearDouble test = new LinearDouble(value);

        assertEquals(test.toString(), expectedOutput);
    }

    /**
     * Test method for {@link components.linear.LinearDouble#clear()}.
     */
    @Test
    public void testClear() {
        final double value = -11.0423;
        LinearDouble test = new LinearDouble(value);

        /*
         * Call method under test
         */
        test.clear();

        assertEquals(test, new LinearDouble());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#newInstance()}.
     */
    @Test
    public void testNewInstance() {
        final double value = -11.0423;
        LinearDouble test = new LinearDouble(value);
        LinearDouble result = test.newInstance();

        assertEquals(result, new LinearDouble());
    }

    /**
     * Test method for
     * {@link components.linear.LinearDouble#transferFrom(components.linear.LinearDouble)}.
     */
    @Test
    public void testTransferFrom() {
        final double value1 = 1.65;
        LinearDouble test1 = new LinearDouble(value1);
        LinearDouble test2 = new LinearDouble();

        /*
         * Call method under test
         */

        test2.transferFrom(test1);

        assertEquals(test1, new LinearDouble());
        assertEquals(test2, new LinearDouble(value1));
    }

    /**
     * Test method for {@link components.linear.LinearDouble#isZero()}.
     */
    @Test
    public void testIsZeroTruePositive() {
        final double value = 0.0;
        LinearDouble test = new LinearDouble(value);

        assertTrue(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#isZero()}.
     */
    @Test
    public void testIsZeroTrueNegative() {
        final double value = -0.0;
        LinearDouble test = new LinearDouble(value);

        assertTrue(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#isZero()}.
     */
    @Test
    public void testIsZeroFalsePositive() {
        final double value = 12.0;
        LinearDouble test = new LinearDouble(value);

        assertFalse(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#isZero()}.
     */
    @Test
    public void testIsZeroFalseNegative() {
        final double value = -11.0423;
        LinearDouble test = new LinearDouble(value);

        assertFalse(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#isOne()}.
     */
    @Test
    public void testIsOneTrue() {
        final double value = 1.0;
        LinearDouble test = new LinearDouble(value);

        assertTrue(test.isOne());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#isOne()}.
     */
    @Test
    public void testIsOneFalsePositive() {
        final double value = 11.0;
        LinearDouble test = new LinearDouble(value);

        assertFalse(test.isOne());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#isOne()}.
     */
    @Test
    public void testIsOneFalseNegative() {
        final double value = -11211.0;
        LinearDouble test = new LinearDouble(value);

        assertFalse(test.isOne());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#isPositive()}.
     */
    @Test
    public void testIsPositiveTrue() {
        final double value = 11211.0;
        LinearDouble test = new LinearDouble(value);

        assertTrue(test.isPositive());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#isPositive()}.
     */
    @Test
    public void testIsPositiveFalse() {
        final double value = -411211.01;
        LinearDouble test = new LinearDouble(value);

        assertFalse(test.isPositive());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#isNegativeOne()}.
     */
    @Test
    public void testIsNegativeOneTrue() {
        final double value = -1.0;
        LinearDouble test = new LinearDouble(value);

        assertTrue(test.isNegativeOne());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#isNegativeOne()}.
     */
    @Test
    public void testIsNegativeOneFalsePositive() {
        final double value = 12.31;
        LinearDouble test = new LinearDouble(value);

        assertFalse(test.isNegativeOne());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#isNegativeOne()}.
     */
    @Test
    public void testIsNegativeOneFalseNegative() {
        final double value = -112.1;
        LinearDouble test = new LinearDouble(value);

        assertFalse(test.isNegativeOne());
    }

    /**
     * Test method for {@link components.linear.LinearDouble#value()}.
     */
    @Test
    public void testValue() {
        final double value = -112.1;
        LinearDouble test = new LinearDouble(value);

        assertTrue(Double.compare(test.value(), value) == 0);
    }

    /**
     * Test method for
     * {@link components.linear.LinearDouble#compareTo(components.linear.LinearDouble)}.
     */
    @Test
    public void testCompareTo() {
        final double value1 = 1.65;
        final double value2 = 7.2;
        LinearDouble test1 = new LinearDouble(value1);
        LinearDouble test2 = new LinearDouble(value2);

        assertTrue(test1.compareTo(test2) < 0);
    }

    /**
     * Test method for
     * {@link components.linear.LinearSecondary#subtract(components.linear.LinearSecondary)}.
     */
    @Test
    public void testSubract() {
        final double value1 = 1.65;
        final double value2 = 7.2;
        LinearDouble test1 = new LinearDouble(value1);
        LinearDouble test2 = new LinearDouble(value2);

        LinearDouble expected = new LinearDouble(value1 - value2);
        assertEquals(test1.subtract(test2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearSecondary#subtract(int)}.
     */
    @Test
    public void testSubtractInt() {
        final double value1 = 1.65;
        final int value2 = -5;
        LinearDouble test = new LinearDouble(value1);

        LinearDouble expected = new LinearDouble(value1 - value2);
        assertEquals(test.subtract(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearSecondary#subtract(double)}.
     */
    @Test
    public void testSubtractDouble() {
        final double value1 = 12.314;
        final double value2 = 64.1;
        LinearDouble test = new LinearDouble(value1);

        LinearDouble expected = new LinearDouble(value1 - value2);
        assertEquals(test.subtract(value2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearSecondary#negative()}.
     */
    @Test
    public void testNegative1() {
        final double value = 12.314;
        final double expectedValue = -12.314;
        LinearDouble test = new LinearDouble(value);
        LinearDouble expected = new LinearDouble(expectedValue);

        assertEquals(test.negative(), expected);
    }

    /**
     * Test method for {@link components.linear.LinearSecondary#negative()}.
     */
    @Test
    public void testNegative2() {
        final double value = -5512.7;
        final double expectedValue = 5512.7;
        LinearDouble test = new LinearDouble(value);
        LinearDouble expected = new LinearDouble(expectedValue);

        assertEquals(test.negative(), expected);
    }

}
