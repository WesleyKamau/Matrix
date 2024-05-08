package components.matrix;

import java.util.Iterator;
import java.util.NoSuchElementException;

import components.simplewriter.SimpleWriter;

/**
 * Layered implementations of secondary methods for {@code Matrix}.
 *
 * @param <T>
 *            type of {@code Queue} entries
 *
 * @author Wesley Kamau
 */
public abstract class SimpleMatrixSecondary<T> implements SimpleMatrix<T> {

    /*
     * Common methods (from Object) -------------------------------------------
     */

    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof SimpleMatrix)) {
            return false;
        }

        SimpleMatrix<?> c = (SimpleMatrix<?>) obj;

        if (this.rows() != c.rows() || this.columns() != c.columns()) {
            return false;
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

        result.append(" _" + this.spaces(this.maxLength() + this.columns() + 1)
                + "_\n");
        for (int i = 1; i <= this.rows(); i++) {
            result.append("|  ");
            for (int j = 1; j <= this.columns(); j++) {
                result.append(this.element(i, j)
                        + this.spaces(this.maxColumnEntry(j)
                                - this.element(i, j).toString().length())
                        + " ");
            }
            result.append(" |\n");
        }
        result.append(" ‾" + this.spaces(this.maxLength() + this.columns() + 1)
                + "‾\n");

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

        out.println(" _" + this.spaces(this.maxLength() + this.columns() + 1)
                + "_");
        for (int i = 1; i <= this.rows(); i++) {
            out.print("|  ");
            for (int j = 1; j <= this.columns(); j++) {
                out.print(this.element(i, j).toString()
                        + this.spaces(this.maxColumnEntry(j)
                                - this.element(i, j).toString().length())
                        + " ");
            }
            out.println(" |");
        }
        out.println(" ‾" + this.spaces(this.maxLength() + this.columns() + 1)
                + "‾");

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

    @Override
    public final void copyFrom(SimpleMatrix<T> source) {
        this.clear();

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
            return this.i < SimpleMatrixSecondary.this.rows()
                    || this.j < SimpleMatrixSecondary.this.columns();
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
            if (this.j < SimpleMatrixSecondary.this.columns()) {
                this.j++;

            } else {
                this.j = 1;
                this.i++;

            }
            return SimpleMatrixSecondary.this.element(this.i, this.j);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                    "remove operation not supported");
        }

    }

}
