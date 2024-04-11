package components.matrix;

import java.util.Iterator;

import components.linear.Linear;
import components.simplewriter.SimpleWriter;

/**
 * Layered implementations of secondary methods for {@code Queue}.
 *
 * <p>
 * Assuming execution-time performance of O(1) for method {@code iterator} and
 * its return value's method {@code next}, execution-time performance of
 * {@code front} as implemented in this class is O(1). Execution-time
 * performance of {@code replaceFront} and {@code flip} as implemented in this
 * class is O(|{@code this}|). Execution-time performance of {@code append} as
 * implemented in this class is O(|{@code q}|). Execution-time performance of
 * {@code sort} as implemented in this class is O(|{@code this}| log
 * |{@code this}|) expected, O(|{@code this}|^2) worst case. Execution-time
 * performance of {@code rotate} as implemented in this class is
 * O({@code distance} mod |{@code this}|).
 *
 * @param <T>
 *            type of {@code Queue} entries
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
        StringBuilder result = new StringBuilder(
                " _" + this.spaces(this.maxLength() + 2 * this.columns() + 2)
                        + "_\n");

        for (int i = 1; i <= this.rows(); i++) {
            result.append("|   ");
            for (int j = 1; j <= this.columns(); j++) {
                result.append(this.element(i, j)
                        + this.spaces(this.maxColumnEntry(j)
                                - this.element(i, j).toString().length())
                        + "  ");
            }
            result.append(" |\n");
        }
        result.append(
                " ‾" + this.spaces(this.maxLength() + 2 * this.columns() + 2)
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
                if (this.isAugmented() && j == this.originalColumns() + 1) {
                    out.print(augmentedRow);
                }
                out.print(this.element(i, j)
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
     * Attempts to reduce the given matrix into RREF.
     *
     * @param a
     *            The matrix to be reduced.
     *
     * @return {@code this} in RREF.
     */
    public Matrix<T> reduce(Matrix<T> a) {
        Matrix<T> result = a.newInstance();
        result.copyFrom(a);
        int lead = 0;
        int rowCount = result.rows();
        int columnCount = result.columns();

        for (int r = 0; r < rowCount; r++) {
            if (columnCount <= lead) {
                break;
            }
            int i = r;
            while (i < rowCount && result.element(i + 1, lead + 1).isZero()) {
                i++;
                if (i == rowCount) {
                    i = r;
                    lead++;
                    if (columnCount == lead) {
                        return result;
                    }
                }
            }

            // Swap rows manually
            result.swapRows(r, i);

            double lv = result.element(r + 1, lead + 1);
            for (int j = 0; j < columnCount; j++) {
                result.swapEntry(r + 1, j + 1,
                        result.element(r + 1, j + 1).constant() / lv);
            }

            for (int i1 = 0; i1 < rowCount; i1++) {
                if (i1 != r) {
                    double l = result.element(i1 + 1, lead + 1);
                    for (int j = 0; j < columnCount; j++) {
                        double newValue = result.element(i1 + 1, j + 1)
                                - l * result.element(r + 1, j + 1);
                        if (isEqual(newValue, 0.0)) {
                            newValue = 0.0;
                        }
                        result.swapEntry(i1 + 1, j + 1, newValue);
                    }
                }
            }
            lead++;
        }
        // Replace -0.0 with 0.0
        for (int i = 1; i <= result.rows(); i++) {
            for (int j = 1; j <= result.columns(); j++) {

                if (isEqual(result.element(i, j), 0.0)) {
                    result.swapEntry(i, j, 0.0);
                }
            }
        }

        return result;
    }

}
