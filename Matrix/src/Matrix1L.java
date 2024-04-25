import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Kernel methods for Matrix.
 *
 * @author Wesley Kamau
 * @param <T>
 *            the type being held by the matrix
 *
 */
public final class Matrix1L<T> {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */

    private Sequence<Sequence<T>> entries;

    /**
     * Private variable representing if this is an augmented matrix.
     */
    private boolean augmented;

    /**
     * The number of rows in the original column of an augment matrix.
     */
    private int originalcolumns;

//  Helper methods

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.entries = new Sequence1L<Sequence<T>>();
        this.entries.add(0, new Sequence1L<T>());
        this.augmented = false;
        this.originalcolumns = 0;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * Constructor for a sequence of sequences.
     *
     * @param entries
     *            the Sequence of Sequences.
     */
    Matrix1L(Sequence<Sequence<T>> entries) {
        this.entries = entries;
        this.augmented = false;
        this.originalcolumns = 0;
    }

    /**
     * Constructor for matrix.
     *
     * @param source
     *            the matrix.
     */
    private Matrix1L(Matrix<T> source) {
        Sequence<Sequence<T>> tempEntries = new Sequence1L<Sequence<T>>();
        for (int i = 1; i <= source.rows(); i++) {
            Sequence<T> row = new Sequence1L<T>();
            for (int j = 1; j <= source.columns(); j++) {
                row.add(j - 1, source.element(i, j));
            }
            tempEntries.add(tempEntries.length(), row);
        }
        this.entries = tempEntries;
        this.augmented = source.augmented;
        this.originalcolumns = source.originalcolumns;
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
    Matrix1L(int m, int n, T... elements) {
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
        this.originalcolumns = 0;
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final NaturalNumber newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(NaturalNumber source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof NaturalNumber2 : ""
                + "Violation of: source is of dynamic type NaturalNumber2";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        NaturalNumber2 localSource = (NaturalNumber2) source;
        this.digits = localSource.digits;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < RADIX : "Violation of: k < 10";
        if (this.digits.length() > 0 || k > 0) {
            this.digits.push(k);
        }
    }

    @Override
    public final int divideBy10() {
        int k = 0;
        if (this.digits.length() > 0) {
            k = this.digits.pop();
        }
        return k;
    }

    @Override
    public final boolean isZero() {
        return this.digits.length() == 0;
    }
}
