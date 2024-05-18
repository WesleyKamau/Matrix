/**
 *
 */
package components.linear;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for LinearVariable
 */
public class LinearVariableTest {

    /**
     * Test method for
     * {@link components.linear.LinearVariable#add(components.linear.LinearVariable)}.
     */
    @Test
    public void testAddLinearVariable() {
        String testValue1 = "2*x + 3*y";
        String testValue2 = "x + y^2";
        String expectedValue = "3*x + 3*y + y^2";
        LinearVariable test1 = new LinearVariable(testValue1);
        LinearVariable test2 = new LinearVariable(testValue2);
        LinearVariable expected = new LinearVariable(expectedValue);
        assertEquals(test1.add(test2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearVariable#add(int)}.
     */
    @Test
    public void testAddInt() {
        String testValue1 = "2*x + 3*y";
        int testValue2 = 5;
        String expectedValue = "2*x + 3*y + 5";
        LinearVariable test1 = new LinearVariable(testValue1);
        LinearVariable expected = new LinearVariable(expectedValue);
        assertEquals(test1.add(testValue2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearVariable#add(double)}.
     */
    @Test
    public void testAddDouble() {
        String testValue1 = "2*x + 3*y";
        double testValue2 = 5.5;
        String expectedValue = "2*x + 3*y + 5.5";
        LinearVariable test1 = new LinearVariable(testValue1);
        LinearVariable expected = new LinearVariable(expectedValue);
        assertEquals(test1.add(testValue2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearVariable#multiply(components.linear.LinearVariable)}.
     */
    @Test
    public void testMultiplyLinearVariable() {
        String testValue1 = "2*x + 3*y";
        String testValue2 = "x";
        String expectedValue = "3*x + 3*y + y^2";
        LinearVariable test1 = new LinearVariable(testValue1);
        LinearVariable test2 = new LinearVariable(testValue2);
        LinearVariable expected = new LinearVariable(expectedValue);
        assertEquals(test1.add(test2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearVariable#multiply(int)}.
     */
    @Test
    public void testMultiplyInt() {
        final double value1 = 1.65;
        final int value2 = -5;
        LinearVariable test = new LinearVariable(value1);

        LinearVariable expected = new LinearVariable(value1 * value2);
        assertEquals(test.multiply(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearVariable#multiply(double)}.
     */
    @Test
    public void testMultiplyDouble() {
        final double value1 = 12.314;
        final double value2 = 64.1;
        LinearVariable test = new LinearVariable(value1);

        LinearVariable expected = new LinearVariable(value1 * value2);
        assertEquals(test.multiply(value2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearVariable#divide(components.linear.LinearVariable)}.
     */
    @Test
    public void testDivide() {
        final double value1 = 1.65;
        final double value2 = 7.2;
        LinearVariable test1 = new LinearVariable(value1);
        LinearVariable test2 = new LinearVariable(value2);

        LinearVariable expected = new LinearVariable(value1 / value2);
        assertEquals(test1.divide(test2), expected);
    }

    /**
     * Test method for
     * {@link components.linear.LinearVariable#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsFalse() {
        final double value1 = 1.65;
        final double value2 = 7.2;
        LinearVariable test1 = new LinearVariable(value1);
        LinearVariable test2 = new LinearVariable(value2);

        assertFalse(test1.equals(test2));
    }

    /**
     * Test method for
     * {@link components.linear.LinearVariable#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsTrue() {
        final double value1 = -81.5;
        final double value2 = -81.5;
        LinearVariable test1 = new LinearVariable(value1);
        LinearVariable test2 = new LinearVariable(value2);

        assertTrue(test1.equals(test2));
    }

    /**
     * Test method for {@link components.linear.LinearVariable#toString()}.
     */
    @Test
    public void testToStringNoDecimalsPositive() {
        final double value = 61.0;
        String expectedOutput = "61";
        LinearVariable test = new LinearVariable(value);

        assertEquals(test.toString(), expectedOutput);
    }

    /**
     * Test method for {@link components.linear.LinearVariable#toString()}.
     */
    @Test
    public void testToStringDecimalsPositive() {
        final double value = 61.012;
        String expectedOutput = "61.012";
        LinearVariable test = new LinearVariable(value);

        assertEquals(test.toString(), expectedOutput);
    }

    /**
     * Test method for {@link components.linear.LinearVariable#toString()}.
     */
    @Test
    public void testToStringNoDecimalsNegative() {
        final double value = -12.0;
        String expectedOutput = "-12";
        LinearVariable test = new LinearVariable(value);

        assertEquals(test.toString(), expectedOutput);
    }

    /**
     * Test method for {@link components.linear.LinearVariable#toString()}.
     */
    @Test
    public void testToStringDecimalsNegative() {
        final double value = -11.0423;
        String expectedOutput = "-11.0423";
        LinearVariable test = new LinearVariable(value);

        assertEquals(test.toString(), expectedOutput);
    }

    /**
     * Test method for {@link components.linear.LinearVariable#clear()}.
     */
    @Test
    public void testClear() {
        final double value = -11.0423;
        LinearVariable test = new LinearVariable(value);

        /*
         * Call method under test
         */
        test.clear();

        assertEquals(test, new LinearVariable());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#newInstance()}.
     */
    @Test
    public void testNewInstance() {
        final double value = -11.0423;
        LinearVariable test = new LinearVariable(value);
        LinearVariable result = test.newInstance();

        assertEquals(result, new LinearVariable());
    }

    /**
     * Test method for
     * {@link components.linear.LinearVariable#transferFrom(components.linear.LinearVariable)}.
     */
    @Test
    public void testTransferFrom() {
        final double value1 = 1.65;
        LinearVariable test1 = new LinearVariable(value1);
        LinearVariable test2 = new LinearVariable();

        /*
         * Call method under test
         */

        test2.transferFrom(test1);

        assertEquals(test1, new LinearVariable());
        assertEquals(test2, new LinearVariable(value1));
    }

    /**
     * Test method for {@link components.linear.LinearVariable#isZero()}.
     */
    @Test
    public void testIsZeroTruePositive() {
        final double value = 0.0;
        LinearVariable test = new LinearVariable(value);

        assertTrue(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#isZero()}.
     */
    @Test
    public void testIsZeroTrueNegative() {
        final double value = -0.0;
        LinearVariable test = new LinearVariable(value);

        assertTrue(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#isZero()}.
     */
    @Test
    public void testIsZeroFalsePositive() {
        final double value = 12.0;
        LinearVariable test = new LinearVariable(value);

        assertFalse(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#isZero()}.
     */
    @Test
    public void testIsZeroFalseNegative() {
        final double value = -11.0423;
        LinearVariable test = new LinearVariable(value);

        assertFalse(test.isZero());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#isOne()}.
     */
    @Test
    public void testIsOneTrue() {
        final double value = 1.0;
        LinearVariable test = new LinearVariable(value);

        assertTrue(test.isOne());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#isOne()}.
     */
    @Test
    public void testIsOneFalsePositive() {
        final double value = 11.0;
        LinearVariable test = new LinearVariable(value);

        assertFalse(test.isOne());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#isOne()}.
     */
    @Test
    public void testIsOneFalseNegative() {
        final double value = -11211.0;
        LinearVariable test = new LinearVariable(value);

        assertFalse(test.isOne());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#isPositive()}.
     */
    @Test
    public void testIsPositiveTrue() {
        final double value = 11211.0;
        LinearVariable test = new LinearVariable(value);

        assertTrue(test.isPositive());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#isPositive()}.
     */
    @Test
    public void testIsPositiveFalse() {
        final double value = -411211.01;
        LinearVariable test = new LinearVariable(value);

        assertFalse(test.isPositive());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#isNegativeOne()}.
     */
    @Test
    public void testIsNegativeOneTrue() {
        final double value = -1.0;
        LinearVariable test = new LinearVariable(value);

        assertTrue(test.isNegativeOne());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#isNegativeOne()}.
     */
    @Test
    public void testIsNegativeOneFalsePositive() {
        final double value = 12.31;
        LinearVariable test = new LinearVariable(value);

        assertFalse(test.isNegativeOne());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#isNegativeOne()}.
     */
    @Test
    public void testIsNegativeOneFalseNegative() {
        final double value = -112.1;
        LinearVariable test = new LinearVariable(value);

        assertFalse(test.isNegativeOne());
    }

    /**
     * Test method for {@link components.linear.LinearVariable#value()}.
     */
    @Test
    public void testValue() {
        final double value = -112.1;
        LinearVariable test = new LinearVariable(value);

        assertTrue(Double.compare(test.value(), value) == 0);
    }

    /**
     * Test method for
     * {@link components.linear.LinearVariable#compareTo(components.linear.LinearVariable)}.
     */
    @Test
    public void testCompareTo() {
        final double value1 = 1.65;
        final double value2 = 7.2;
        LinearVariable test1 = new LinearVariable(value1);
        LinearVariable test2 = new LinearVariable(value2);

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
        LinearVariable test1 = new LinearVariable(value1);
        LinearVariable test2 = new LinearVariable(value2);

        LinearVariable expected = new LinearVariable(value1 - value2);
        assertEquals(test1.subtract(test2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearSecondary#subtract(int)}.
     */
    @Test
    public void testSubtractInt() {
        final double value1 = 1.65;
        final int value2 = -5;
        LinearVariable test = new LinearVariable(value1);

        LinearVariable expected = new LinearVariable(value1 - value2);
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
        LinearVariable test = new LinearVariable(value1);

        LinearVariable expected = new LinearVariable(value1 - value2);
        assertEquals(test.subtract(value2), expected);
    }

    /**
     * Test method for {@link components.linear.LinearSecondary#negative()}.
     */
    @Test
    public void testNegative1() {
        final double value = 12.314;
        final double expectedValue = -12.314;
        LinearVariable test = new LinearVariable(value);
        LinearVariable expected = new LinearVariable(expectedValue);

        assertEquals(test.negative(), expected);
    }

    /**
     * Test method for {@link components.linear.LinearSecondary#negative()}.
     */
    @Test
    public void testNegative2() {
        final double value = -5512.7;
        final double expectedValue = 5512.7;
        LinearVariable test = new LinearVariable(value);
        LinearVariable expected = new LinearVariable(expectedValue);

        assertEquals(test.negative(), expected);
    }

}
