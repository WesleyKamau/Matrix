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
public final class SimpleMatrix2<T extends Linear<T>>
        extends SimpleMatrixSecondary<T> {

    /**
     * entries represented as a 2d array as a sequence.
     */
    private Sequence<Sequence<T>> entries;

    /*
     * Constructors
     */

    /**
     * Basic constructor.
     *
     * @param source
     *            the source Matrix.
     */
    public SimpleMatrix2(Matrix<T> source) {
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
     * @param initial
     *            an instance of T to call newInstance.
     */
    public SimpleMatrix2(T initial) {
        this.entries = new Sequence1L<Sequence<T>>();
        this.entries.add(0, new Sequence1L<T>());
        this.entries.entry(0).add(0, initial.newInstance());
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
    public SimpleMatrix2(T initial, int i, int j) {
        this.entries = new Sequence1L<Sequence<T>>();
        this.entries.add(0, new Sequence1L<T>());
        this.entries.entry(0).add(0, initial.newInstance());
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
    public SimpleMatrix2(int m, int n, T... elements) {
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
    }

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        T tempT = this.newT();
        this.entries = new Sequence1L<Sequence<T>>();
        Sequence<T> row = new Sequence1L<>();
        row.add(0, tempT);
        this.entries.add(0, row);
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
    public void setRows(int rows) {

        while (this.entries.length() != rows) {
            if (this.entries.length() > rows) {
                this.entries.remove(this.entries.length() - 1);
            }

            if (this.rows() < rows) {
                Sequence<T> row = new Sequence1L<T>();
                while (row.length() < this.entries.entry(0).length()) {
                    row.add(row.length(), this.newT());
                }
                this.entries.add(this.entries.length(), row);
            }
        }

    }

    @Override
    public int rows() {
        return this.entries.length();
    }

    @Override
    public void setColumns(int columns) {

        for (int i = 0; i < this.entries.length(); i++) {
            while (this.entries.entry(i).length() != columns) {
                if (this.entries.entry(i).length() < columns) {
                    this.entries.entry(i).add(this.entries.entry(i).length(),
                            this.newT());
                }

                if (this.entries.entry(i).length() > columns) {
                    this.entries.entry(i)
                            .remove(this.entries.entry(i).length() - 1);
                }
            }
        }

    }

    @Override
    public int columns() {
        return this.entries.entry(0).length();
    }

    @Override
    public T element(int i, int j) {
        assert i > 0 && i <= this.rows() && j > 0
                && j <= this.columns() : "Violation of: index is in range";

        return this.entries.entry(i - 1).entry(j - 1);
    }

    @Override
    public void setElement(int i, int j, T element) {
        assert i >= 1 && i <= this.rows() : "Violation of: i is in range";
        assert j >= 1 && j <= this.columns() : "Violation of: j is in range";

        this.entries.entry(i - 1).replaceEntry(j - 1, element);
    }

    @Override
    public SimpleMatrix<T> newInstance() {
        return new SimpleMatrix2<T>(this.element(1, 1).newInstance());
    }

    @Override
    public void transferFrom(SimpleMatrix<T> source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof SimpleMatrix2<?> : ""
                + "Violation of: source is of dynamic type Set3<?>";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case: source must be of dynamic type Set3a<?>, and
         * the ? must be T or the call would not have compiled.
         */
        SimpleMatrix2<T> localSource = (SimpleMatrix2<T>) source;
        this.entries = localSource.entries;
        localSource.createNewRep();

    }

    @Override
    public void clear() {
        this.createNewRep();
    }

}
