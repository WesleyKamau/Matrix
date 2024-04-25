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

    @Override
    boolean equals(Object obj);

    @Override
    int hashCode();

    @Override
    String toString();

}
