package components.matrix;

import components.linear.Linear;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code Matrix} represented as a 2d array of sequences.
 *
 * @param <T>
 *            the type of entries in the matrix
 * @author Wesley Kamau
 */
public final class Matrix2<T extends Linear<T>> extends MatrixSecondary<T> {

    /**
     * entries represented as a 2d array as a sequence.
     */
    private Sequence<Sequence<T>> entries;

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
    public Matrix2(Matrix<T> source) {
        if (source.isAugmented()) {
            this.augmented = true;
            this.leftcolumns = source.leftColumns();
        } else {
            this.augmented = false;
            this.leftcolumns = 0;
        }
        Sequence<Sequence<T>> tempEntries = new Sequence1L<Sequence<T>>();
        for (int i = 1; i <= source.rows(); i++) {
            Sequence<T> row = new Sequence1L<T>();
            for (int j = 1; j <= source.columns(); j++) {
                row.add(j - 1, source.element(i, j));
            }
            tempEntries.add(tempEntries.length(), row);
        }
        this.entries = tempEntries;
    }

    /**
     * Basic constructor.
     *
     */
    public Matrix2() {
        this.augmented = false;
        this.leftcolumns = 0;
        this.entries = new Sequence1L<Sequence<T>>();
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
    public Matrix2(int m, int n, T... elements) {
        assert elements.length == (m
                * n) : "Violation of: elements.length == m * n";
        assert m >= 1 : "Violation of: m is at least 1";
        assert n >= 1 : "Violation of: n is at least 1";

        this.entries = new Sequence1L<Sequence<T>>();
        int index = 0;
        for (int i = 0; i < m; i++) {
            Sequence<T> row = new Sequence1L<T>();
            for (int j = 0; j < n; j++) {
                row.add(j, elements[index]);
                index++;
            }
            this.entries.add(this.entries.length(), row);
        }
        this.augmented = false;
        this.leftcolumns = 0;
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.entries = new Sequence1L<Sequence<T>>();
        this.augmented = false;
        this.leftcolumns = 0;

    }

    /**
     * Generates a new instance of T.
     *
     * @return the new instance of T
     */
    private T newT() {
        return this.entries.entry(0).entry(0).newInstance();
    }

    @Override
    public void swapRows(int row1, int row2) {
        assert row1 >= 1
                && row1 <= this.rows() : "Violation of: 1 <= row1 <= this.rows";
        assert row2 >= 1
                && row2 <= this.rows() : "Violation of: 1 <= row2 <= this.rows";

        if (row1 != row2) {
            Sequence<T> temprow1 = this.entries.replaceEntry(row1 - 1,
                    this.entries.entry(row2 - 1));
            this.entries.replaceEntry(row2 - 1, temprow1);
        }

    }

    @Override
    public int rows() {
        return this.entries.length();
    }

    @Override
    public int columns() {
        if (this.rows() > 0) {
            return this.entries.entry(0).length();
        }
        return 0;
    }

    @Override
    public T element(int i, int j) {
        assert i > 0 && i <= this.rows() && j > 0
                && j <= this.columns() : "Violation of: index is in range";

        return this.entries.entry(i - 1).entry(j - 1);
    }

    @Override
    public void setElement(int i, int j, T element) {
        assert i >= 1 && i <= this.rows() + 1 : "Violation of: i is in range";
        assert j >= 1
                && j <= this.columns() + 1 : "Violation of: j is in range";

        if ((i == this.rows() + 1) && (j == this.columns() + 1)) {
            Sequence<T> row = new Sequence1L<T>();
            for (int count = 1; count <= this.columns(); count++) {
                row.add(count - 1, this.newT());
            }
            row.add(j - 1, element);
            this.entries.add(i - 1, row);
        } else if (i == this.rows() + 1) {
            Sequence<T> row = new Sequence1L<T>();
            for (int count = 1; count <= this.columns(); count++) {
                if (count == j) {
                    row.add(count - 1, element);
                } else {
                    row.add(count - 1, this.newT());
                }
            }
            this.entries.add(i - 1, row);
        } else if (j == this.columns() + 1) {
            this.entries.entry(i - 1).add(j - 1, element);
        } else {
            this.entries.entry(i - 1).replaceEntry(j - 1, element);
        }
    }

    @Override
    public Matrix<T> newInstance() {
        return new Matrix2<T>();
    }

    @Override
    public void transferFrom(Matrix<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Matrix2<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        Matrix2<T> localSource = (Matrix2<T>) source;
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
