import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Wesley Kamau
 * @param <T>
 *            the type being held by the matrix
 *
 */
public final class Matrix<T> {

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
    private int originalcolumns;

    /**
     * Constructor for a sequence of sequences.
     *
     * @param entries
     *            the Sequence of Sequences.
     */
    Matrix(Sequence<Sequence<T>> entries) {
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
    private Matrix(Matrix<T> source) {
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
    Matrix(int m, int n, T... elements) {
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

    /**
     * Returns if {@code this} is an augmented matrix.
     *
     * @return true if this is an augment matrix.
     */
    public boolean isAugmented() {
        return this.augmented;
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
        if (this.augmented) {
            augmentedRow = "| ";
        }

        out.println(" _" + spaces(this.maxLength() + 1 * this.columns()
                + augmentedRow.length() + 1) + "_");
        for (int i = 1; i <= this.rows(); i++) {
            out.print("|  ");
            for (int j = 1; j <= this.columns(); j++) {
                if (this.augmented && j == this.originalcolumns + 1) {
                    out.print(augmentedRow);
                }
                out.print(this.element(i, j)
                        + spaces(this.maxColumnEntry(j)
                                - this.element(i, j).toString().length())
                        + " ");
            }
            out.println(" |");
        }
        out.println(" ‾" + spaces(this.maxLength() + 1 * this.columns()
                + augmentedRow.length() + 1) + "‾");

    }

    /**
     * Transposes the matrix.
     *
     * @return a transposed version of {@code this}.
     */
    public Matrix<T> transpose() {
        Sequence<Sequence<T>> tempEntries = new Sequence1L<Sequence<T>>();

        for (int i = 1; i <= this.columns(); i++) {
            Sequence<T> row = new Sequence1L<T>();
            for (int j = 1; j <= this.rows(); j++) {
                row.add(row.length(), this.element(j, i));
            }
            tempEntries.add(tempEntries.length(), row);
        }
        return new Matrix<T>(tempEntries);
    }

    /**
     * Returns m, the number of rows.
     *
     * @return the number of rows
     */
    public int rows() {
        return this.entries.length();
    }

    /**
     * Returns n, the number of columns.
     *
     * @return the number of rows
     */
    public int columns() {
        return this.entries.entry(0).length();
    }

    /**
     * Epsilon vaue.
     */
    private static final double EPSILON = 1e-10;

    /**
     * Compares two values with the accuracy epsilon.
     *
     * @param a
     *            value a
     * @param b
     *            value b
     * @return true if they are close enough to each other
     */
    public static boolean isEqual(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Matrix)) {
            return false;
        }

        Matrix<?> c = (Matrix<?>) o;

        if (this.rows() != c.rows() || this.columns() != c.columns()) {
            return false;
        }

        for (int i = 1; i <= c.rows(); i++) {
            for (int j = 1; j <= c.columns(); j++) {
                T thisElement = this.element(i, j);
                Object otherElement = c.element(i, j);

                // If both elements are instances of Double, perform specific comparison
                if (thisElement instanceof Double
                        && otherElement instanceof Double) {
                    double thisDouble = ((Double) thisElement).doubleValue();
                    double otherDouble = ((Double) otherElement).doubleValue();

                    if (!(isEqual(thisDouble, otherDouble))) {
                        System.out.println(
                                Double.compare(thisDouble, otherDouble));

                        return false;
                    }
                } else {
                    // For other types, use standard equals comparison
                    if (!thisElement.equals(otherElement)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int output = 0;
        for (int i = 1; i < this.rows(); i++) {
            for (int j = 1; j < this.columns(); j++) {
                if (j % 2 == 0) {
                    output += this.element(i, j).hashCode();
                } else {
                    output += this.element(i, j).toString().length();
                }
            }
        }
        return output;
    }

    @Override
    public String toString() {
        String result = "";

        result = result.concat(" _"
                + spaces(this.maxLength() + 2 * this.columns() + 2) + "_\n");
        for (int i = 1; i <= this.rows(); i++) {
            result = result.concat("|   ");
            for (int j = 1; j <= this.columns(); j++) {
                result = result.concat(this.element(i, j)
                        + spaces(this.maxColumnEntry(j)
                                - this.element(i, j).toString().length())
                        + "  ");
            }
            result = result.concat(" |\n");
        }
        result = result.concat(" ‾"
                + spaces(this.maxLength() + 2 * this.columns() + 2) + "‾\n");

        return result;
    }

    /**
     * Finds the element at i,j.
     *
     * @param i
     *            the row
     * @param j
     *            the column
     * @return the element
     */
    public T element(int i, int j) {
        assert i > 0 && i <= this.rows() && j > 0
                && j <= this.columns() : "Violation of: index is in range";

        return this.entries.entry(i - 1).entry(j - 1);
    }

    /**
     * Determines if matrix A is in Reduced Row Echelon Form.
     *
     * @param a
     *            the matrix
     * @return true if A is in RREF
     */
    public static boolean isRREF(Matrix<Double> a) {
        boolean result = true;
        for (int i = 1; i <= a.rows() && result; i++) {
            boolean foundLeadingDigit = false;
            for (int j = 1; j <= a.columns() && !foundLeadingDigit
                    && result; j++) {
                if (!(a.element(i, j) == 0.0)) {
                    foundLeadingDigit = true;
                    if (a.element(i, j) != 1.0) {
                        result = false;
                    } else {
                        for (int k = 1; k < a.rows(); k++) {
                            if (k != j) {
                                if (!(a.element(k, j) == 0.0)) {
                                    result = false;
                                }
                            }
                        }
                    }
                }
            }

        }
        return result;
    }

    /**
     * Copies the content from the target Matrix into {@code this}.
     *
     * @param source
     *            The source matrix.
     */
    public void copyFrom(Matrix<T> source) {
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
     * Attempts to reduce the given matrix into RREF.
     *
     * @param a
     *            The matrix to be reduced.
     *
     * @return {@code this} in RREF.
     */
    public static Matrix<Double> reduce(Matrix<Double> a) {
        Matrix<Double> result = new Matrix<Double>(a);
        int lead = 0;
        int rowCount = result.rows();
        int columnCount = result.columns();

        for (int r = 0; r < rowCount; r++) {
            if (columnCount <= lead) {
                break;
            }
            int i = r;
            while (i < rowCount
                    && isEqual(result.element(i + 1, lead + 1), 0.0)) {
                i++;
                if (i == rowCount) {
                    i = r;
                    lead++;
                    if (columnCount == lead) {
                        // Replace -0.0 with 0.0
                        for (i = 1; i <= result.rows(); i++) {
                            for (int j = 1; j <= result.columns(); j++) {

                                if (isEqual(result.element(i, j), 0.0)) {
                                    result.swapEntry(i, j, 0.0);
                                }
                            }
                        }
                        return result;
                    }
                }
            }

            // Swap rows manually
            Sequence<Double> temp = result.entries.entry(r);
            result.entries.replaceEntry(r, result.entries.entry(i));
            result.entries.replaceEntry(i, temp);

            double lv = result.element(r + 1, lead + 1);
            for (int j = 0; j < columnCount; j++) {
                result.swapEntry(r + 1, j + 1,
                        result.element(r + 1, j + 1) / lv);
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

    /**
     * Swaps the given entry with the given entry, and returns what was in its
     * place.
     *
     * @param i
     *            the i index
     * @param j
     *            the j index
     * @param entry
     *            the new value at that position
     * @return the old value at that position
     */
    public T swapEntry(int i, int j, T entry) {
        return this.entries.entry(i - 1).replaceEntry(j - 1, entry);
    }

    /**
     * Creates and returns augmented matrix [this | b].
     *
     * @param b
     *            the right side of the matrix
     * @return a matrix representing [this | b]
     */
    public Matrix<T> augment(Matrix<T> b) {
        assert this.rows() == b
                .rows() : "Violation of: this.rows() == b.rows()";

        Sequence<Sequence<T>> tempEntries = new Sequence1L<Sequence<T>>();
        for (int i = 1; i <= this.rows(); i++) {
            Sequence<T> row = new Sequence1L<T>();
            for (int j = 1; j <= this.columns(); j++) {
                row.add(row.length(), this.element(i, j));
            }
            for (int j = 1; j <= b.columns(); j++) {
                row.add(row.length(), b.element(i, j));
            }
            tempEntries.add(tempEntries.length(), row);
        }
        Matrix<T> result = new Matrix<T>(tempEntries);
        result.augmented = true;
        result.originalcolumns = this.columns();
        return result;
    }

    /**
     * Adds two matrices and returns the result of integer addition.
     *
     * @param a
     *            the first matrix being added.
     * @param b
     *            The second matrix being added.
     * @return A matrix representing the matrix sum.
     */
    public static Matrix<Integer> add(Matrix<Integer> a, Matrix<Integer> b) {
        assert a.rows() == b.rows() && a.columns() == b
                .columns() : "Violation of: a and b are the same size";

        Sequence<Sequence<Integer>> tempEntries = new Sequence1L<Sequence<Integer>>();
        for (int i = 1; i <= a.rows(); i++) {
            Sequence<Integer> row = new Sequence1L<Integer>();
            for (int j = 1; j <= a.columns(); j++) {
                row.add(j - 1, a.element(i, j) + b.element(i, j));
            }
            tempEntries.add(tempEntries.length(), row);
        }
        return new Matrix<Integer>(tempEntries);
    }

    /**
     * Adds two matrices and returns the result of double addition.
     *
     * @param a
     *            the first matrix being added.
     * @param b
     *            The second matrix being added.
     * @return A matrix representing the matrix sum.
     */
    public static Matrix<Double> doubleadd(Matrix<Double> a, Matrix<Double> b) {
        assert a.rows() == b.rows() && a.columns() == b
                .columns() : "Violation of: a and b are the same size";

        Sequence<Sequence<Double>> tempEntries = new Sequence1L<Sequence<Double>>();
        for (int i = 1; i <= a.rows(); i++) {
            Sequence<Double> row = new Sequence1L<Double>();
            for (int j = 1; j <= a.columns(); j++) {
                row.add(j, a.element(i, j) + b.element(i, j));
            }
            tempEntries.add(tempEntries.length(), row);
        }
        return new Matrix<Double>(tempEntries);
    }

    /**
     * Adds two matrices and returns the result of integer addition.
     *
     * @param a
     *            the first matrix being added.
     * @param b
     *            The second matrix being added.
     * @return A matrix representing the matrix sum.
     */
    public static Matrix<String> stringadd(Matrix<String> a, Matrix<String> b) {
        assert a.rows() == b.rows() && a.columns() == b
                .columns() : "Violation of: a and b are the same size";

        Sequence<Sequence<String>> tempEntries = new Sequence1L<Sequence<String>>();
        for (int i = 1; i <= a.rows(); i++) {
            Sequence<String> row = new Sequence1L<String>();
            for (int j = 1; j <= a.columns(); j++) {
                row.add(j - 1, a.element(i, j) + b.element(i, j));
            }
            tempEntries.add(tempEntries.length(), row);
        }
        return new Matrix<String>(tempEntries);
    }

    /**
     * Multiplies the matrix by a constant and returns the result matrix.
     *
     * @param c
     *            the constant
     * @param a
     *            the matrix to be multiplied
     * @return A matrix that is the original matrix multiplied by constant c
     */
    public static Matrix<Integer> multiply(int c, Matrix<Integer> a) {
        Sequence<Sequence<Integer>> tempEntries = new Sequence1L<Sequence<Integer>>();
        for (int i = 1; i <= a.rows(); i++) {
            Sequence<Integer> row = new Sequence1L<Integer>();
            for (int j = 1; j <= a.columns(); j++) {
                row.add(j - 1, c * a.element(i, j));
            }
            tempEntries.add(tempEntries.length(), row);
        }
        return new Matrix<Integer>(tempEntries);
    }

    /**
     * Returns matrix multiplication AB.
     *
     * @param a
     *            matrix A
     * @param b
     *            matrix B
     * @return AB
     */
    public static Matrix<Integer> multiply(Matrix<Integer> a,
            Matrix<Integer> b) {
        assert a.rows() == b.columns() : "Violation of: rows(a) == cols(b)";
        assert a.columns() == b.rows() : "Violation of: cols(a) == rows(b)";

        Sequence<Sequence<Integer>> tempEntries = new Sequence1L<Sequence<Integer>>();
        for (int i = 1; i <= a.rows(); i++) {
            Sequence<Integer> row = new Sequence1L<Integer>();
            for (int j = 1; j <= b.columns(); j++) {
                int rowProduct = 0;
                for (int k = 1; k <= a.columns(); k++) {
                    rowProduct += a.element(i, k) * b.element(k, j);
                }
                row.add(row.length(), rowProduct);
            }
            tempEntries.add(tempEntries.length(), row);
        }
        return new Matrix<Integer>(tempEntries);
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

    public static Matrix<Double> intToDouble(Matrix<Integer> a) {
        Sequence<Sequence<Double>> tempEntries = new Sequence1L<Sequence<Double>>();
        for (int i = 1; i <= a.rows(); i++) {
            Sequence<Double> row = new Sequence1L<Double>();
            for (int j = 1; j <= a.columns(); j++) {
                row.add(row.length(), (double) a.element(i, j));
            }
            tempEntries.add(tempEntries.length(), row);
        }
        Matrix<Double> result = new Matrix<Double>(tempEntries);
        result.augmented = a.augmented;
        result.originalcolumns = a.originalcolumns;
        return result;
    }

    /**
     * Generates a string of spaces.
     *
     * @param i
     *            the length of the string
     * @return the string of spaces
     */

    private static String spaces(int i) {
        String s = "";
        for (int j = 0; j < i; j++) {
            s = s.concat(" ");
        }
        return s;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        // final Matrix<Integer> test = new Matrix<Integer>(3, 3, 1, 2, 3, 4, 5, 6,
        //ssssssssssssss  7, 8, 9);
        //test.print(out);

        final Matrix<Double> test2 = new Matrix<Double>(3, 3, 0., 1., 3., -1.,
                -3., 3., 1., -3., 0.);
        //Matrix<Double> RREF = new Matrix<Double>(2, 2, 1.0, 0.0, 0.0, 1.0);
        // test2.print(out);

        //reduce(test2).print(out);
        //out.println(isRREF(test2));
        //out.println(isRREF(RREF));

        // final Double testt = (-0.0);

        /*
         * Matrix<Integer> test3 = new Matrix<Integer>(2, 2, 100, 200, 300,
         * 400); test3.print(out);
         *
         * Matrix<Integer> test4 = new Matrix<Integer>(4, 2, 100, 200, 300, 400,
         * 500, 600, 700, 800); test4.print(out);
         *
         * Matrix<Integer> testadd1 = new Matrix<Integer>(2, 2, 1, 2, 3, 4);
         * testadd1.print(out);
         *
         * Matrix<Integer> testadd2 = new Matrix<Integer>(2, 2, 1, 1, 1, 1);
         * testadd2.print(out);
         *
         * add(testadd1, testadd2).print(out);
         *
         * Matrix<Integer> RREF = new Matrix<Integer>(2, 2, 1, 0, 0, 1);
         *
         * Matrix<String> stringtest = new Matrix<String>(2, 3, "Hi", "heyy",
         * "yo", "wassuppppp", "lol", "hi cutie");
         *
         * stringadd(stringtest, stringtest).transpose().print(out);
         * stringadd(stringtest, stringtest).print(out);
         *
         * Matrix<Double> augmentTest = new Matrix<Double>(2, 2, 1., 2., 3.,
         * 4.); Matrix<Double> b = new Matrix<Double>(2, 1, 1., 1.);
         * Matrix<Double> augmented = augmentTest.augment(b); Matrix<Double>
         * reducedAugmented = Matrix.reduce(augmented);
         * reducedAugmented.print(out);
         */

        Matrix<Double> gauss = reduce(intToDouble(
                new Matrix<Integer>(3, 4, 1, 2, 3, -1, 3, 5, 8, -2, 1, 1, 2, 0)
                        .augment(new Matrix<Integer>(3, 1, 0, 0, 0))));
        gauss.print(out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
