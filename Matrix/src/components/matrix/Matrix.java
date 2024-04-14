package components.matrix;

import components.linear.Linear;
import components.simplewriter.SimpleWriter;

/**
 * {@code Matrix<T>} enhanced with secondary methods.
 *
 * @param <T>
 *            the type of entries in the matrix
 *
 * @mathmodel Matrix<T> represents a matrix with entries of type T.
 *
 * @author Wesley Kamau
 */
public interface Matrix<T extends Linear<T>> extends MatrixKernel<T> {

    /**
     * Swaps row1 and row2.
     *
     * @param row1
     *            the first row to be swapped
     * @param row2
     *            the second row to be swapped
     */
    void swapRows(int row1, int row2);

    /**
     * Copies the value of source into this.
     *
     * @param source
     *            the Matrix to copy from
     */
    void copyFrom(Matrix<T> source);

    /**
     * Returns if {@code this} is an augmented matrix.
     *
     * @return true if this is an augment matrix.
     */
    boolean isAugmented();

    /**
     * Sets the value of the AUGMENTED flag to true.
     *
     * @param leftColumns
     *            the number of columns on the left side of the Matrix
     *
     */
    void setAugmented(int leftColumns);

    /**
     * Returns the number of left columns in an augmented matrix.
     *
     * @return the number of left columns.
     */
    int leftColumns();

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();

    @Override
    String toString();

    /**
     * Prints this to the provided SimpleWriter.
     *
     * @param out
     *            the output stream
     */
    void print(SimpleWriter out);

    /**
     * Attempts to reduce this into RREF.
     *
     * @return {@code this} in RREF.
     */
    Matrix<T> reduce();

    /**
     * Multiplies the matrix by a constant and returns the result matrix.
     *
     * @param c
     *            the constant
     * @return A matrix that is the original matrix multiplied by constant c
     */
    Matrix<T> multiply(int c);

    /**
     * Multiplies the matrix by a constant and returns the result matrix.
     *
     * @param c
     *            the constant
     * @return A matrix that is the original matrix multiplied by constant c
     */
    Matrix<T> multiply(double c);

    /**
     * Returns matrix multiplication thisA.
     *
     * @param a
     *            matrix A
     * @return thisA
     */
    Matrix<T> multiply(Matrix<T> a);

    /**
     * Swaps the given entry in this with the given entry, and returns what was
     * in its place.
     *
     * @param i
     *            the i index
     * @param j
     *            the j index
     * @param entry
     *            the new value at that position
     * @return the old value at that position
     */
    T swapEntry(int i, int j, T entry);

    /**
     * Creates and returns augmented matrix [this | b].
     *
     * @param b
     *            the right side of the matrix
     * @return a matrix representing [this | b]
     */
    Matrix<T> augment(Matrix<T> b);

    /**
     * Adds two matrices and returns the result.
     *
     * @param a
     *            the first matrix being added.
     * @return A matrix representing this + a
     */
    Matrix<T> add(Matrix<T> a);

    /**
     * Determines if this is in Reduced Row Echelon Form.
     *
     * @return true if this is in RREF
     */
    boolean isRREF();

}
