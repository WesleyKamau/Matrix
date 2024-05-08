package components.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code Matrix} represented as a 2d array of sequences.
 *
 * @param <T>
 *            the type of entries in the matrix
 * @author Wesley Kamau
 */
public final class SimpleMatrix1L<T> extends SimpleMatrixSecondary<T> {

    /**
     * entries represented as a 2d array as a List.
     */
    private List<List<T>> entries;

    /*
     * Constructors
     */

    /**
     * Basic constructor.
     *
     * @param source
     *            the source Matrix.
     */
    public SimpleMatrix1L(SimpleMatrix<T> source) {
        List<List<T>> tempEntries = new ArrayList<List<T>>();
        for (int i = 1; i <= source.rows(); i++) {
            List<T> row = new ArrayList<T>();
            for (int j = 1; j <= source.columns(); j++) {
                row.add(j - 1, source.element(i, j));
            }
            tempEntries.add(tempEntries.size(), row);
        }
        this.entries = tempEntries;
    }

    /**
     * Basic constructor.
     *
     */
    public SimpleMatrix1L() {
        this.createNewRep();
    }

    /**
     * Constructor of a matrix.
     *
     * @param m
     *            the number of rows
     * @param n
     *            the number of columns
     * @param elements
     *            the elements of the matrix
     */
    @SafeVarargs
    public SimpleMatrix1L(int m, int n, T... elements) {
        assert elements.length == (m
                * n) : "Violation of: elements.length == m * n";
        assert m >= 1 : "Violation of: m is at least 1";
        assert n >= 1 : "Violation of: n is at least 1";

        this.entries = new ArrayList<List<T>>();
        int index = 0;
        for (int i = 0; i < m; i++) {
            List<T> row = new ArrayList<T>();
            for (int j = 0; j < n; j++) {
                row.add(j, elements[index]);
                index++;
            }
            this.entries.add(this.entries.size(), row);
        }
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.entries = new ArrayList<List<T>>();
    }

    @Override
    public void swapRows(int row1, int row2) {
        assert row1 >= 1
                && row1 <= this.rows() : "Violation of: 1 <= row1 <= this.rows";
        assert row2 >= 1
                && row2 <= this.rows() : "Violation of: 1 <= row2 <= this.rows";

        if (row1 != row2) {
            List<T> temprow1 = this.entries.set(row1 - 1,
                    this.entries.get(row2 - 1));
            this.entries.set(row2 - 1, temprow1);
        }

    }

    @Override
    public int rows() {
        return this.entries.size();
    }

    @Override
    public int columns() {
        if (this.entries.size() == 0) {
            return 0;
        }
        return this.entries.get(0).size();
    }

    @Override
    public T element(int i, int j) {
        assert i > 0 && i <= this.rows() && j > 0
                && j <= this.columns() : "Violation of: index is in range";

        return this.entries.get(i - 1).get(j - 1);
    }

    @Override
    public void setElement(int i, int j, T element) {
        assert i >= 1 && i <= this.rows() + 1 : "Violation of: i is in range";
        assert j >= 1
                && j <= this.columns() + 1 : "Violation of: j is in range";

        if ((i == this.rows() + 1) && (j == this.columns() + 1)) {
            List<T> row = new ArrayList<T>();
            for (int count = 1; count <= this.columns(); count++) {
                row.add(count - 1, null);
            }
            row.add(j - 1, element);
            this.entries.add(i - 1, row);
        } else if (i == this.rows() + 1) {
            List<T> row = new ArrayList<T>();
            for (int count = 1; count <= this.columns(); count++) {
                if (count == j) {
                    row.add(count - 1, element);
                } else {
                    row.add(count - 1, null);
                }
            }
            this.entries.add(i - 1, row);
        } else if (j == this.columns() + 1) {
            this.entries.get(i - 1).add(j - 1, element);
        } else {
            this.entries.get(i - 1).set(j - 1, element);
        }
    }

    @Override
    public SimpleMatrix<T> newInstance() {
        return new SimpleMatrix1L<T>();
    }

    @Override
    public void transferFrom(SimpleMatrix<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof SimpleMatrix1L<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        SimpleMatrix1L<T> localSource = (SimpleMatrix1L<T>) source;
        this.entries = localSource.entries;
        localSource.createNewRep();

    }

    @Override
    public void clear() {
        this.createNewRep();
    }

}
