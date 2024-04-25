package components.matrix;

import components.simplewriter.SimpleWriter;
import components.standard.Standard;

/**
 * Simpler Matrix interface without math refrences, essentially a grid.
 *
 * @param <T>
 *            the type of entries in the matrix
 *
 * @mathmodel Matrix<T> represents a matrix with entries of type T.
 *
 * @author Wesley Kamau
 */
public interface SimpleMatrix<T>
        extends MatrixKernel<T>, Standard<SimpleMatrix<T>> {

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
    void copyFrom(SimpleMatrix<T> source);

    /**
     * Prints this to the provided SimpleWriter.
     *
     * @param out
     *            the output stream
     */
    void print(SimpleWriter out);

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

}
