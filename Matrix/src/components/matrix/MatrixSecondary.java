package components.matrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

import components.linear.Linear;
import components.simplewriter.SimpleWriter;

/**
 * Layered implementations of secondary methods for {@code Matrix}.
 *
 * @param <T>
 *            type of {@code Queue} entries
 *
 * @author Wesley Kamau
 */
public abstract class MatrixSecondary<T extends Linear<T>>
        implements Matrix<T> {

    /*
     * Common methods (from Object) -------------------------------------------
     */

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Matrix)) {
            return false;
        }

        Matrix<?> c = (Matrix<?>) obj;

        if (this.rows() != c.rows() || this.columns() != c.columns()) {
            return false;
        }

        if (this.isAugmented() != c.isAugmented()) {
            return false;
        } else {
            if (this.leftColumns() != c.leftColumns()) {
                return false;
            }
        }

        // We know that the matrices are of the same dimension, check for empty
        if (this.rows() == 0 || this.columns() == 0) {
            return true;
        }

        // We know that the matrices are not empty
        // Check if both matrices are of the same type (T)
        if (!this.element(1, 1).getClass().equals(c.element(1, 1).getClass())) {
            return false;
        }

        for (int i = 1; i <= c.rows(); i++) {
            for (int j = 1; j <= c.columns(); j++) {
                T thisElement = this.element(i, j);
                @SuppressWarnings("unchecked")
                T otherElement = (T) c.element(i, j);

                // For other types, use standard equals comparison
                if (!thisElement.equals(otherElement)) {
                    return false;

                }
            }
        }

        return true;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        final int samples = 2;
        final int a = 37;
        final int b = 17;
        int result = 0;
        /*
         * This code makes hashCode run in O(1) time. It works because of the
         * iterator order string specification, which guarantees that the (at
         * most) samples entries returned by the it.next() calls are the same
         * when the two Queues are equal.
         */
        int n = 0;
        Iterator<T> it = this.iterator();
        while (n < samples && it.hasNext()) {
            n++;
            T x = it.next();
            result = a * result + b * x.hashCode();
        }
        return result;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        String augmentedRow = "";
        if (this.isAugmented()) {
            augmentedRow = "| ";
        }

        result.append(" _" + this.spaces(this.maxLength() + 1 * this.columns()
                + augmentedRow.length() + 1) + "_\n");
        for (int i = 1; i <= this.rows(); i++) {
            result.append("|  ");
            for (int j = 1; j <= this.columns(); j++) {
                if (this.isAugmented() && j == this.leftColumns() + 1) {
                    result.append(augmentedRow);
                }
                result.append(this.element(i, j)
                        + this.spaces(this.maxColumnEntry(j)
                                - this.element(i, j).toString().length())
                        + " ");
            }
            result.append(" |\n");
        }
        result.append(" ‾" + this.spaces(this.maxLength() + 1 * this.columns()
                + augmentedRow.length() + 1) + "‾\n");

        return result.toString();
    }

    /*
     * Other non-kernel methods -----------------------------------------------
     */

    /**
     * Generates a string of spaces.
     *
     * @param i
     *            the length of the string
     * @return the string of spaces
     */
    private String spaces(int i) {
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < i; j++) {
            s.append(" ");
        }
        return s.toString();
    }

    /**
     * Prints this to the provided SimpleWriter.
     *
     * @param out
     *            the output stream
     */
    @Override
    public void print(SimpleWriter out) {
        assert out.isOpen() : "Violation of: out is open";

        String augmentedRow = "";
        if (this.isAugmented()) {
            augmentedRow = "| ";
        }

        out.println(" _" + this.spaces(this.maxLength() + 1 * this.columns()
                + augmentedRow.length() + 1) + "_");
        for (int i = 1; i <= this.rows(); i++) {
            out.print("|  ");
            for (int j = 1; j <= this.columns(); j++) {
                if (this.isAugmented() && j == this.leftColumns() + 1) {
                    out.print(augmentedRow);
                }
                out.print(this.element(i, j).toString()
                        + this.spaces(this.maxColumnEntry(j)
                                - this.element(i, j).toString().length())
                        + " ");
            }
            out.println(" |");
        }
        out.println(" ‾" + this.spaces(this.maxLength() + 1 * this.columns()
                + augmentedRow.length() + 1) + "‾");

    }

    /**
     * Adds up the length of the longest elements in each column.
     *
     * @return the number of characters of the longest row.
     */
    private int maxLength() {
        int total = 0;
        for (int j = 1; j <= this.columns(); j++) {
            total += this.maxColumnEntry(j);
        }
        return total;
    }

    /**
     * Finds the length of the longest entry in the column.
     *
     * @param column
     *            the column
     * @return the length of the longest entry in the column
     */
    private int maxColumnEntry(int column) {
        int max = 0;
        for (int i = 1; i <= this.rows(); i++) {
            int currentEntry = this.element(i, column).toString().length();
            if (currentEntry > max) {
                max = currentEntry;
            }
        }
        return max;
    }

    /**
     * Attempts to reduce this into RREF.
     *
     * @return {@code this} in RREF.
     */
    @Override
    public Matrix<T> reduce() {
        Matrix<T> result = this.newInstance();
        result.copyFrom(this);
        int lead = 0;

        for (int r = 0; r < this.rows(); r++) {
            if (this.columns() <= lead) {
                break;
            }
            int i = r;
            while (i < this.rows()
                    && result.element(i + 1, lead + 1).isZero()) {
                i++;
                if (i == this.rows()) {
                    i = r;
                    lead++;
                    if (this.columns() == lead) {
                        return result;
                    }
                }
            }

            // Swap rows manually
            result.swapRows(r + 1, i + 1);

            T lv = result.element(r + 1, lead + 1);
            for (int j = 0; j < this.columns(); j++) {
                result.setElement(r + 1, j + 1,
                        result.element(r + 1, j + 1).divide(lv));
            }

            for (int i1 = 0; i1 < this.rows(); i1++) {
                if (i1 != r) {
                    T l = result.element(i1 + 1, lead + 1);
                    for (int j = 0; j < this.columns(); j++) {
                        // Subtract the multiple of the leading row from the current row
                        T multiple = l.constant(-1)
                                .multiply(result.element(r + 1, j + 1));
                        T currentElement = result.element(i1 + 1, j + 1);
                        result.setElement(i1 + 1, j + 1,
                                currentElement.add(multiple));
                    }
                }
            }

            lead++;
        }

        return result;
    }

    /**
     * Multiplies the matrix by a constant and returns the result matrix.
     *
     * @param c
     *            the constant
     * @return A matrix that is the original matrix multiplied by constant c
     */
    @Override
    public Matrix<T> multiply(int c) {
        Matrix<T> result = this.newInstance();
        for (int i = 1; i <= this.rows(); i++) {
            for (int j = 1; j <= this.columns(); j++) {
                result.setElement(i, j, this.element(i, j).constant(c));
            }
        }
        return result;
    }

    /**
     * Multiplies the matrix by a constant and returns the result matrix.
     *
     * @param c
     *            the constant
     * @return A matrix that is the original matrix multiplied by constant c
     */
    @Override
    public Matrix<T> multiply(double c) {
        Matrix<T> result = this.newInstance();
        for (int i = 1; i <= this.rows(); i++) {
            for (int j = 1; j <= this.columns(); j++) {
                result.setElement(i, j, this.element(i, j).constant(c));
            }
        }
        return result;
    }

    /**
     * Returns matrix multiplication thisA.
     *
     * @param a
     *            matrix A
     * @return thisA
     */
    @Override
    public Matrix<T> multiply(Matrix<T> a) {
        assert this.rows() == a
                .columns() : "Violation of: rows(this) == cols(a)";
        assert this.columns() == a
                .rows() : "Violation of: cols(this) == rows(a)";

        Matrix<T> result = this.newInstance();
        for (int i = 1; i <= this.rows(); i++) {
            for (int j = 1; j <= a.columns(); j++) {
                for (int k = 1; k <= this.columns(); k++) {
                    result.setElement(i, j, result.element(i, j)
                            .add(this.element(i, k).multiply(a.element(k, j))));
                }
            }
        }
        return result;
    }

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
    @Override
    public T swapEntry(int i, int j, T entry) {
        T temp = this.element(i, j);
        this.setElement(i, j, entry);
        return temp;
    }

    /**
     * Creates and returns augmented matrix [this | b].
     *
     * @param b
     *            the right side of the matrix
     * @return a matrix representing [this | b]
     */
    @Override
    public Matrix<T> augment(Matrix<T> b) {
        assert this.rows() == b
                .rows() : "Violation of: this.rows() == b.rows()";

        Matrix<T> result = this.newInstance();

        for (int i = 1; i <= this.rows(); i++) {
            int count = 1;
            for (int j = 1; j <= this.columns(); j++) {
                result.setElement(i, count, this.element(i, j));
                count++;

            }
            for (int j = 1; j <= b.columns(); j++) {
                result.setElement(i, count, b.element(i, j));
                count++;
            }
        }
        result.setAugmented(this.columns());
        return result;
    }

    /**
     * Adds two matrices and returns the result.
     *
     * @param a
     *            the first matrix being added.
     * @return A matrix representing this + a
     */
    @Override
    public Matrix<T> add(Matrix<T> a) {
        assert a.rows() == this.rows() && a.columns() == this
                .columns() : "Violation of: a and this are the same size";

        Matrix<T> result = this.newInstance();

        for (int i = 1; i <= this.rows(); i++) {
            for (int j = 1; j <= this.columns(); j++) {
                result.setElement(i, j,
                        this.element(i, j).add(a.element(i, j)));
            }
        }

        return result;

    }

    /**
     * Determines if this is in Reduced Row Echelon Form.
     *
     * @return true if this is in RREF
     */
    @Override
    public boolean isRREF() {
        return this.equals(this.reduce());
    }

    @Override
    public final boolean isConsistent() {
        assert this.isRREF() : "Violation of: this.isRREF";

        boolean result = true;
        int leadingColumn = 0;

        for (int i = 1; i <= this.rows() && result; i++) {
            /*
             * Check all of the columns in this row up to the last leading row
             * to make sure it is zero
             */
            for (int j = 1; j <= leadingColumn; j++) {
                if (!this.element(i, j).isZero()) {
                    result = false;
                }
            }

            /*
             * Starting from the last leading column, find the first nonzero
             * term in that row.
             */
            boolean foundNonZero = false;
            for (int j = leadingColumn + 1; j <= this.columns()
                    && !foundNonZero; j++) {
                if (!this.element(i, j).isZero()) {
                    foundNonZero = true;
                    leadingColumn = j;
                }
            }
        }

        return result;
    }

    @Override
    public final void copyFrom(Matrix<T> source) {
        this.clear();
        if (source.isAugmented()) {
            this.setAugmented(source.leftColumns());
        }

        for (int i = 1; i <= source.rows(); i++) {
            for (int j = 1; j <= source.columns(); j++) {
                this.setElement(i, j, source.element(i, j));
            }
        }
    }

    @Override
    public final Iterator<T> iterator() {
        return new MatrixIterator();
    }

    @Override
    public final void determinant(T result) {
        assert this.rows() == this
                .columns() : "Violation of : is a square matrix";

        result.clear();

        if (this.rows() == 2) {
            result.transferFrom(this.element(1, 1).multiply(this.element(2, 2))
                    .add(this.element(1, 2).multiply(this.element(2, 1))
                            .constant(-1)));
        } else if (this.rows() == 1) {
            result.transferFrom(this.element(1, 1));
        } else if (this.rows() > 2) {
            int coefficient = 1;
            for (int i = 1; i <= this.rows(); i++) {
                Matrix<T> temp = new Matrix2<T>();

                int icount = 1;
                for (int i2 = 1; i2 <= this.rows(); i2++) {
                    if (i2 != 1) {

                        int jcount = 1;
                        for (int j2 = 1; j2 <= this.columns(); j2++) {
                            if (j2 != i) {

                                temp.setElement(icount, jcount,
                                        this.element(i2, j2));
                                jcount++;
                            }
                        }
                        icount++;
                    }
                }

                T subMatrixDeterminant = result.newInstance();
                temp.determinant(subMatrixDeterminant);
                result.transferFrom(result.add(subMatrixDeterminant
                        .constant(coefficient).multiply(this.element(1, i))));
                coefficient *= -1;

            }

        }

    }

    /**
     * Implementation of {@code Iterator} interface for {@code Matrix2}.
     */
    final class MatrixIterator implements Iterator<T> {

        /**
         * Private dimension variables.
         */
        private int i, j;

        /**
         * No-argument constructor.
         */
        MatrixIterator() {
            this.i = 1;
            this.j = 0;
        }

        @Override
        public boolean hasNext() {
            return this.i < MatrixSecondary.this.rows()
                    || this.j < MatrixSecondary.this.columns();
        }

        @Override
        public T next() {
            assert this.hasNext() : "Violation of: ~this.unseen /= <>";
            if (!this.hasNext()) {
                /*
                 * Exception is supposed to be thrown in this case, but with
                 * assertion-checking enabled it cannot happen because of assert
                 * above.
                 */
                throw new NoSuchElementException();
            }
            if (this.j < MatrixSecondary.this.columns()) {
                this.j++;

            } else {
                this.j = 1;
                this.i++;

            }
            return MatrixSecondary.this.element(this.i, this.j);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "remove operation not supported");
        }

    }

}
