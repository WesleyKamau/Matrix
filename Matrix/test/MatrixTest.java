import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for Matrix
 *
 * @author Wesley Kamau
 */
public class MatrixTest {

    @Test
    public void multiplyTest() {
        Matrix<Integer> test1 = new Matrix<Integer>(2, 3, 1, 2, 3, 4, 5, 6);
        Matrix<Integer> test2 = new Matrix<Integer>(3, 2, 7, 8, 9, 10, 11, 12);

        Matrix<Integer> expected = new Matrix<Integer>(2, 2, 58, 64, 139, 154);

        assertEquals(expected, Matrix.multiply(test1, test2));
    }

    @Test
    public void reduceTest1() {
        final Matrix<Double> test = new Matrix<Double>(4, 2, -1., 1., -1., 0.,
                0., -1., -1., -2.);
        final Matrix<Double> expected = new Matrix<Double>(4, 2, 1., 0., 0., 1.,
                0., 0., 0., 0.);

        assertEquals(expected, Matrix.reduce(test));
    }

    @Test
    public void reduceTest2() {
        final Matrix<Double> test = new Matrix<Double>(3, 2, -1., 1., -1., 2.,
                -3., 2.);
        final Matrix<Double> expected = new Matrix<Double>(3, 2, 1., 0., 0., 1.,
                0., 0.);

        assertEquals(expected, Matrix.reduce(test));
    }

    @Test
    public void reduceTest3() {
        final Matrix<Double> test = new Matrix<Double>(2, 4, 1., 3., 2., 1., 2.,
                -3., 0., -2.);
        final Matrix<Double> expected = new Matrix<Double>(2, 4, 1., 0.,
                2. / 3., -1. / 3., 0., 1., 4. / 9., 4. / 9.);

        assertEquals(expected, Matrix.reduce(test));
    }

    @Test
    public void reduceTest4() {
        final Matrix<Double> test = new Matrix<Double>(4, 2, -1., -3., 3., -3.,
                -3., -3., 2., 0.);
        final Matrix<Double> expected = new Matrix<Double>(4, 2, 1., 0., 0., 1.,
                0., 0., 0., 0.);

        assertEquals(expected, Matrix.reduce(test));
    }

    @Test
    public void reduceTest7() {
        final Matrix<Double> test = new Matrix<Double>(4, 3, 3., -3., 2., -2.,
                2., -2., 2., 1., 1., 3., 1., 1.);
        final Matrix<Double> expected = new Matrix<Double>(4, 3, 1., 0., 0., 0.,
                1., 0., 0., 0., 1., 0., 0., 0.);

        assertEquals(expected, Matrix.reduce(test));
    }

}
