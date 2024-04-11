package components.matrix;

import components.linear.Linear;
import components.standard.Standard;

/**
 * First-in-first-out (FIFO) queue kernel component with primary methods. (Note:
 * by package-wide convention, all references are non-null.)
 *
 * @param <T>
 *            type of {@code QueueKernel} entries
 * @mathmodel type QueueKernel is modeled by string of T
 * @initially {@code
 * ():
 *  ensures
 *   this = <>
 * }
 * @iterator ~this.seen * ~this.unseen = this
 */
public interface MatrixKernel<T extends Linear<T>>
        extends Standard<Matrix<T>>, Iterable<T> {

    /**
     * Sets m, the number of rows.
     *
     * @param rows
     *            the number of rows
     *
     */
    void setRows(int rows);

    /**
     * Returns m, the number of rows.
     *
     * @return the number of rows
     */
    int rows();

    /**
     * Sets n, the number of columns.
     *
     * @param columns
     *            the number of columns
     *
     */
    void setColumns(int columns);

    /**
     * Returns n, the number of columns.
     *
     * @return the number of rows
     */
    int columns();

    /**
     * Finds the element at i,j.
     *
     * @param i
     *            the row
     * @param j
     *            the column
     * @return the element
     */
    T element(int i, int j);

    /**
     * Sets the element at i,j.
     *
     * @param i
     *            the row
     * @param j
     *            the column
     * @param element
     *            the element
     */
    void setElement(int i, int j, T element);

}
