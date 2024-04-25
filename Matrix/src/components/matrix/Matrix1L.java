package components.matrix;

import java.util.ArrayList;
import java.util.List;

import components.linear.Linear;

/**
 * {@code Matrix} represented as a 2d array of sequences.
 *
 * @param <T>
 *            the type of entries in the matrix
 * @author Wesley Kamau
 */
public final class Matrix1L<T extends Linear<T>> extends MatrixSecondary<T> {

    /**
     * entries represented as a 2d array as a sequence.
     */
    private List<List<T>> entries;

    /**
     * Private variable representing if this is an augmented matrix.
     */
    private boolean augmented;

    /**
     * The number of rows in the original column of an augment matrix.
     */
    private int leftcolumns;

    /*
     * Constructors
     */

    /**
     * Basic constructor.
     *
     * @param source
     *            the source Matrix.
     */
    public Matrix1L(Matrix<T> source) {
        if (source.isAugmented()) {
            this.augmented = true;
            this.leftcolumns = source.leftColumns();
        } else {
            this.augmented = false;
            this.leftcolumns = 0;
        }
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
     * @param initial
     *            an instance of T to call newInstance.
     */
    public Matrix1L(T initial) {
        this.augmented = false;
        this.leftcolumns = 0;
        this.entries = new ArrayList<List<T>>();
        this.entries.add(0, new ArrayList<T>());
        this.entries.get(0).add(0, initial.newInstance());
    }

    /**
     * Basic constructor with predetermined dimensions.
     *
     * @param initial
     *            an instance of T to call newInstance.
     * @param i
     *            the rows
     * @param j
     *            the columns
     */
    public Matrix1L(T initial, int i, int j) {
        this.augmented = false;
        this.leftcolumns = 0;
        this.entries = new ArrayList<List<T>>();
        this.entries.add(0, new ArrayList<T>());
        this.entries.get(0).add(0, initial.newInstance());
        this.setRows(i);
        this.setColumns(j);
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
    public Matrix1L(int m, int n, T... elements) {
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
        this.augmented = false;
        this.leftcolumns = 0;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        T tempT = this.newT();
        this.entries = new ArrayList<List<T>>();
        ArrayList<T> row = new ArrayList<>();
        row.add(0, tempT);
        this.entries.add(0, row);
        this.augmented = false;
        this.leftcolumns = 0;

    }

    /**
     * Generates a new instance of T.
     *
     * @return the new instance of T
     */
    private T newT() {
        return this.entries.get(0).get(0).newInstance();
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
    public void setRows(int rows) {

        while (this.entries.size() != rows) {
            if (this.entries.size() > rows) {
                this.entries.remove(this.entries.size() - 1);
            }

            if (this.rows() < rows) {
                List<T> row = new ArrayList<T>();
                while (row.size() < this.entries.get(0).size()) {
                    row.add(row.size(), this.newT());
                }
                this.entries.add(this.entries.size(), row);
            }
        }

    }

    @Override
    public int rows() {
        return this.entries.size();
    }

    @Override
    public void setColumns(int columns) {

        for (int i = 0; i < this.entries.size(); i++) {
            while (this.entries.get(i).size() != columns) {
                if (this.entries.get(i).size() < columns) {
                    this.entries.get(i).add(this.entries.get(i).size(),
                            this.newT());
                }

                if (this.entries.get(i).size() > columns) {
                    this.entries.get(i).remove(this.entries.get(i).size() - 1);
                }
            }
        }

    }

    @Override
    public int columns() {
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
        assert i >= 1 && i <= this.rows() : "Violation of: i is in range";
        assert j >= 1 && j <= this.columns() : "Violation of: j is in range";

        this.entries.get(i - 1).set(j - 1, element);
    }

    @Override
    public Matrix<T> newInstance() {
        return new Matrix1L<T>(this.element(1, 1).newInstance());
    }

    @Override
    public void transferFrom(Matrix<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Matrix1L<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Matrix1L<T> localSource = (Matrix1L<T>) source;
        this.entries = localSource.entries;
        localSource.createNewRep();

    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public boolean isAugmented() {
        return this.augmented;
    }

    @Override
    public void setAugmented(int leftColumns) {
        this.augmented = true;
        this.leftcolumns = leftColumns;
    }

    @Override
    public int leftColumns() {
        return this.leftcolumns;
    }

}
