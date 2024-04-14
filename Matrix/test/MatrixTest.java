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
        OldMatrix<Integer> test1 = new OldMatrix<Integer>(2, 3, 1, 2, 3, 4, 5, 6);
        OldMatrix<Integer> test2 = new OldMatrix<Integer>(3, 2, 7, 8, 9, 10, 11, 12);

        OldMatrix<Integer> expected = new OldMatrix<Integer>(2, 2, 58, 64, 139, 154);

        assertEquals(expected, OldMatrix.multiply(test1, test2));
    }

    @Test
    public void reduceTest1() {
        final OldMatrix<Double> test = new OldMatrix<Double>(4, 2, -1., 1., -1., 0.,
                0., -1., -1., -2.);
        final OldMatrix<Double> expected = new OldMatrix<Double>(4, 2, 1., 0., 0., 1.,
                0., 0., 0., 0.);

        assertEquals(expected, OldMatrix.reduce(test));
    }

    @Test
    public void reduceTest2() {
        final OldMatrix<Double> test = new OldMatrix<Double>(3, 2, -1., 1., -1., 2.,
                -3., 2.);
        final OldMatrix<Double> expected = new OldMatrix<Double>(3, 2, 1., 0., 0., 1.,
                0., 0.);

        assertEquals(expected, OldMatrix.reduce(test));
    }

    @Test
    public void reduceTest3() {
        final OldMatrix<Double> test = new OldMatrix<Double>(2, 4, 1., 3., 2., 1., 2.,
                -3., 0., -2.);
        final OldMatrix<Double> expected = new OldMatrix<Double>(2, 4, 1., 0.,
                2. / 3., -1. / 3., 0., 1., 4. / 9., 4. / 9.);

        assertEquals(expected, OldMatrix.reduce(test));
    }

    @Test
    public void reduceTest4() {
        final OldMatrix<Double> test = new OldMatrix<Double>(4, 2, -1., -3., 3., -3.,
                -3., -3., 2., 0.);
        final OldMatrix<Double> expected = new OldMatrix<Double>(4, 2, 1., 0., 0., 1.,
                0., 0., 0., 0.);

        assertEquals(expected, OldMatrix.reduce(test));
    }

    @Test
    public void reduceTest7() {
        final OldMatrix<Double> test = new OldMatrix<Double>(4, 3, 3., -3., 2., -2.,
                2., -2., 2., 1., 1., 3., 1., 1.);
        final OldMatrix<Double> expected = new OldMatrix<Double>(4, 3, 1., 0., 0., 0.,
                1., 0., 0., 0., 1., 0., 0., 0.);

        assertEquals(expected, OldMatrix.reduce(test));
    }

    // Test cases for Matrix Constructors
    // Test case 1: Construct a matrix with positive integer elements
    // Test case 2: Construct a matrix with zero elements
    // Test case 3: Construct a matrix with negative integer elements
    // Test case 4: Construct a matrix with double elements
    // Test case 5: Construct an empty matrix
    // Test case 6: Construct a matrix with only one row/column
    // Test case 7: Construct a large matrix with many rows and columns

    // Test cases for isAugmented()
    // Test case 1: Verify for a non-augmented matrix
    // Test case 2: Verify for an augmented matrix

    // Test cases for print(SimpleWriter out)
    // Test case 1: Print a small integer matrix
    // Test case 2: Print a small double matrix
    // Test case 3: Print a small string matrix
    // Test case 4: Print an empty matrix
    // Test case 5: Print a matrix with negative elements

    // Test cases for transpose()
    // Test case 1: Transpose a square matrix
    // Test case 2: Transpose a non-square matrix
    // Test case 3: Transpose an empty matrix

    // Test cases for rows() and columns()
    // Test case 1: Get the number of rows of a non-empty matrix
    // Test case 2: Get the number of columns of a non-empty matrix
    // Test case 3: Get the number of rows of an empty matrix
    // Test case 4: Get the number of columns of an empty matrix

    // Test cases for isEqual(double a, double b)
    // Test case 1: Compare two equal double values
    // Test case 2: Compare two different double values

    // Test cases for equals(Object o)
    // Test case 1: Compare two equal matrices
    // Test case 2: Compare two different matrices

    // Test cases for hashCode()
    // Test case 1: Verify hash code generation for a non-empty matrix
    // Test case 2: Verify hash code generation for an empty matrix

    // Test cases for toString()
    // Test case 1: Convert a non-empty matrix to string
    // Test case 2: Convert an empty matrix to string

    // Test cases for element(int i, int j)
    // Test case 1: Get an element from a non-empty matrix
    // Test case 2: Get an element from an empty matrix
    // Test case 3: Get an element from a matrix out of bounds

    // Test cases for isRREF(Matrix<Double> a)
    // Test case 1: Verify if a matrix is in RREF
    // Test case 2: Verify if a matrix is not in RREF

    // Test cases for copyFrom(Matrix<T> source)
    // Test case 1: Copy a non-empty matrix from another matrix
    // Test case 2: Copy an empty matrix from another matrix

    // Test cases for reduce(Matrix<Double> a)
    // Test case 1: Reduce a matrix to RREF
    // Test case 2: Reduce an already reduced matrix
    // Test case 3: Reduce an empty matrix

    // Test cases for swapEntry(int i, int j, T entry)
    // Test case 1: Swap an entry in a non-empty matrix
    // Test case 2: Swap an entry in an empty matrix
    // Test case 3: Swap entries in a matrix out of bounds

    // Test cases for augment(Matrix<T> b)
    // Test case 1: Augment two matrices with the same number of rows
    // Test case 2: Augment two matrices with a different number of rows

    // Test cases for add(Matrix<Integer> a, Matrix<Integer> b)
    // Test case 1: Add two matrices with positive integer elements
    // Test case 2: Add two matrices with zero elements
    // Test case 3: Add two matrices with negative integer elements

    // Test cases for doubleadd(Matrix<Double> a, Matrix<Double> b)
    // Test case 1: Add two matrices with positive double elements
    // Test case 2: Add two matrices with zero elements
    // Test case 3: Add two matrices with negative double elements

    // Test cases for stringadd(Matrix<String> a, Matrix<String> b)
    // Test case 1: Concatenate two matrices with non-empty string elements
    // Test case 2: Concatenate two matrices with empty string elements

    // Test cases for multiply(int c, Matrix<Integer> a)
    // Test case 1: Multiply a matrix by a positive integer constant
    // Test case 2: Multiply a matrix by a negative integer constant

    // Test cases for multiply(Matrix<Integer> a, Matrix<Integer> b)
    // Test case 1: Multiply two matrices with positive integer elements
    // Test case 2: Multiply two matrices with zero elements
    // Test case 3: Multiply two matrices with negative integer elements

    // Test cases for maxLength() and maxColumnEntry(int column)
    // Test case 1: Get the maximum length of a column in a non-empty matrix
    // Test case 2: Get the maximum length of a column in an empty matrix

    // Test cases for intToDouble(Matrix<Integer> a)
    // Test case 1: Convert a matrix with integer elements to double elements
    // Test case 2: Convert an empty matrix to double elements

    // Test cases for spaces(int i)
    // Test case 1: Generate spaces string with a positive integer
    // Test case 2: Generate spaces string with zero
    // Test case 3: Generate spaces string with a negative integer

}
