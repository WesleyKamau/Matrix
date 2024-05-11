import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.linear.LinearDouble;
import components.matrix.Matrix;

/**
 * JUnit test fixture for {@code Matrix<LinearDouble>}'s constructor and kernel
 * methods.
 *
 * @author Wesley Kamau
 *
 */
public abstract class MatrixTest {

    /**
     * Invokes the appropriate {@code Matrix} constructor for the reference
     * implementation and returns the result.
     *
     * @param t
     *            the new instance of T for the constructor
     *
     * @return the new Matrix
     * @ensures constructorRef = {}
     */
    protected abstract Matrix<LinearDouble> constructorTest(LinearDouble t);

    /**
     * Creates and returns a {@code Matrix<LinearDouble>} of the implementation
     * under test type with the given entries.
     *
     * @param rows
     *            the number of rows
     * @param columns
     *            the number of columns
     * @param entries
     *            the entries
     * @return the Matrix
     */
    private Matrix<LinearDouble> createFromArgsTest(int rows, int columns,
            double[] entries) {
        assert entries.length <= rows * columns : "Incorrect dimension";

        Matrix<LinearDouble> matrix = this.constructorTest(new LinearDouble());

        int count = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                if (count < entries.length) {
                    matrix.setElement(i, j, new LinearDouble(entries[count]));
                }
                count++;
            }
        }

        return matrix;

    }

    /*
     * Tests for reduce from
     * https://server.math.umanitoba.ca/~coopers5/pastcourses_um_materials/
     * courses_summer2018/RREF_Practice.pdf
     */

    /**
     * Test for practice problem 1.
     */
    @Test
    public final void practice1() {
        final int rows = 4;
        final int columns = 2;

        final double[] testEntries = { -1., 1., -1., 0., 0., -1., -1., -2. };
        final double[] expectedEntries = { 1., 0., 0., 1., 0., 0., 0., 0. };

        Matrix<LinearDouble> test = this.createFromArgsTest(rows, columns,
                testEntries);
        Matrix<LinearDouble> expected = this.createFromArgsTest(rows, columns,
                expectedEntries);
        assertEquals(test.reduce(), expected);

    }

    /**
     * Test for practice problem 2.
     */
    @Test
    public final void practice2() {
        final int rows = 3;
        final int columns = 2;

        final double[] testEntries = { -1., 1., -1., 2., -3., 2. };
        final double[] expectedEntries = { 1., 0., 0., 1., 0., 0., };

        Matrix<LinearDouble> test = this.createFromArgsTest(rows, columns,
                testEntries);
        Matrix<LinearDouble> expected = this.createFromArgsTest(rows, columns,
                expectedEntries);
        assertEquals(test.reduce(), expected);

    }

    /**
     * Test for practice problem 3.
     */
    @Test
    public final void practice3() {
        final int rows = 2;
        final int columns = 4;

        final double[] testEntries = { 1., 3., 2., 1., 2., -3., 0., -2. };
        final double[] expectedEntries = { 1., 0., 2.0 / 3.0, -1.0 / 3.0, 0.,
                1., 4.0 / 9.0, 4.0 / 9.0 };

        Matrix<LinearDouble> test = this.createFromArgsTest(rows, columns,
                testEntries);
        Matrix<LinearDouble> expected = this.createFromArgsTest(rows, columns,
                expectedEntries);
        assertEquals(test.reduce(), expected);

    }

    /**
     * Test for practice problem 4.
     */
    @Test
    public final void practice4() {
        final int rows = 4;
        final int columns = 2;

        final double[] testEntries = { -1., -3., 3., -3., -3., -3., 2., 0. };
        final double[] expectedEntries = { 1., 0., 0., 1., 0., 0., 0., 0. };

        Matrix<LinearDouble> test = this.createFromArgsTest(rows, columns,
                testEntries);
        Matrix<LinearDouble> expected = this.createFromArgsTest(rows, columns,
                expectedEntries);
        assertEquals(test.reduce(), expected);

    }

    /**
     * Test for practice problem 5.
     */
    @Test
    public final void practice5() {
        final int rows = 4;
        final int columns = 2;

        final double[] testEntries = { 1., 3., -1., 3., 3., 0., 1., -3. };
        final double[] expectedEntries = { 1., 0., 0., 1., 0., 0., 0., 0. };

        Matrix<LinearDouble> test = this.createFromArgsTest(rows, columns,
                testEntries);
        Matrix<LinearDouble> expected = this.createFromArgsTest(rows, columns,
                expectedEntries);
        assertEquals(test.reduce(), expected);

    }

    /**
     * Test for practice problem 6.
     */
    @Test
    public final void practice6() {
        final int rows = 4;
        final int columns = 3;

        final double[] testEntries = { 1., 2., 0., 1., 3., 3., -1., 0., -1.,
                -3., 0., 0., };
        final double[] expectedEntries = { 1., 0., 0., 0., 1., 0., 0., 0., 1.,
                0., 0., 0. };

        Matrix<LinearDouble> test = this.createFromArgsTest(rows, columns,
                testEntries);
        Matrix<LinearDouble> expected = this.createFromArgsTest(rows, columns,
                expectedEntries);
        assertEquals(test.reduce(), expected);

    }

    /**
     * Test for practice problem 7.
     */
    @Test
    public final void practice7() {
        final int rows = 4;
        final int columns = 3;

        final double[] testEntries = { 3., -3., 2., -2., 2., -2., 2., 1., 1.,
                3., 1., 1. };
        final double[] expectedEntries = { 1., 0., 0., 0., 1., 0., 0., 0., 1.,
                0., 0., 0. };

        Matrix<LinearDouble> test = this.createFromArgsTest(rows, columns,
                testEntries);
        Matrix<LinearDouble> expected = this.createFromArgsTest(rows, columns,
                expectedEntries);
        assertEquals(test.reduce(), expected);

    }

    /**
     * Test for practice problem 8.
     */
    @Test
    public final void practice8() {
        final int rows = 3;
        final int columns = 4;

        final double[] testEntries = { -1., 1., 0., 1., -2., -3., -1., -2., -3.,
                -1., -2., -1. };
        final double[] expectedEntries = { 1., 0., 0., -1.0 / 3.0, 0., 1., 0.,
                2.0 / 3.0, 0., 0., 1., 2.0 / 3.0 };

        Matrix<LinearDouble> test = this.createFromArgsTest(rows, columns,
                testEntries);
        Matrix<LinearDouble> expected = this.createFromArgsTest(rows, columns,
                expectedEntries);
        assertEquals(test.reduce(), expected);

    }

    /**
     * Test for practice problem 9.
     */
    @Test
    public final void practice9() {
        final int rows = 3;
        final int columns = 3;

        final double[] testEntries = { 0., 1., 3., -1., -3., 3., 1., -3., 0 };
        final double[] expectedEntries = { 1., 0., 0., 0., 1., 0., 0., 0., 1. };

        Matrix<LinearDouble> test = this.createFromArgsTest(rows, columns,
                testEntries);
        Matrix<LinearDouble> expected = this.createFromArgsTest(rows, columns,
                expectedEntries);
        assertEquals(test.reduce(), expected);

    }

    /**
     * Test for practice problem 10.
     */
    @Test
    public final void practice10() {
        final int rows = 2;
        final int columns = 4;

        final double[] testEntries = { -3, -2., -3., 3., 2., 3., 3., 2. };
        final double[] expectedEntries = { 1., 0., -3.0 / 13.0, 1., 0., 1.,
                15.0 / 13.0, 0.0 };

        Matrix<LinearDouble> test = this.createFromArgsTest(rows, columns,
                testEntries);
        Matrix<LinearDouble> expected = this.createFromArgsTest(rows, columns,
                expectedEntries);
        assertEquals(test.reduce(), expected);

    }

}
