package components.matrix;

/**
 * Matrix kernel component with primary methods.
 *
 * @param <T>
 *            the type of entries in the matrix
 *
 * @mathmodel type {@code MatrixKernel<T>} is modeled by a matrix of type T.
 *
 * @iterator ~this.seen * ~this.unseen = this
 *
 * @author Wesley Kamau
 */
public interface MatrixKernel<T> extends Iterable<T> {

    /**
     * Returns m, the number of rows.
     *
     * @return the number of rows
     */
    int rows();

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
     * @requires 1 <= i <= this.rows
     * @requires 1 <= j <= this.columns
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
     * @requires 1 <= i <= this.rows + 1
     * @requires 1 <= j <= this.columns + 1
     */
    void setElement(int i, int j, T element);

}
