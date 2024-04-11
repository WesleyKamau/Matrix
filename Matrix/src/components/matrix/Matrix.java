package components.matrix;

import components.linear.Linear;

/**
 * {@code QueueKernel} enhanced with secondary methods.
 *
 * @param <T>
 *            type of {@code Queue} entries
 * @mathdefinitions {@code
 * IS_TOTAL_PREORDER (
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y, z: T
 *   ((r(x, y) or r(y, x))  and
 *    (if (r(x, y) and r(y, z)) then r(x, z)))
 *
 * IS_SORTED (
 *   s: string of T,
 *   r: binary relation on T
 *  ) : boolean is
 *  for all x, y: T where (<x, y> is substring of s) (r(x, y))
 * }
 */
public interface Matrix<T extends Linear<T>> extends MatrixKernel<T> {
    /**
     * Swaps the given entry with the given entry, and returns what was in its
     * place.
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
     * Adds two matrices and returns the result of integer addition.
     *
     * @param a
     *            the first matrix being added.
     * @param b
     *            The second matrix being added.
     * @return A matrix representing the matrix sum.
     */
    Matrix<T> add(Matrix<T> a, Matrix<T> b);

    /**
     * Adds up the length of the longest elements in each column.
     *
     * @return the number of characters of the longest row.
     */
    int maxLength();

    /**
     * Finds the length of the longest entry in the column.
     *
     * @param column
     *            the column
     * @return the length of the longest entry in the column
     */
    int maxColumnEntry(int column);

    /**
     * Returns if {@code this} is an augmented matrix.
     *
     * @return true if this is an augment matrix.
     */
    boolean isAugmented();

    /**
     * Returns the number of rows in the original column of an augment matrix.
     *
     * @return The number of rows in the original column of an augment matrix.
     */
    int originalColumns();

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
}
