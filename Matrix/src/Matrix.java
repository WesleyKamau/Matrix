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
 *
 */
public final class Matrix<T> {

    /**
     * entries represented as a 2d array as a sequence.
     */
    private Sequence<Sequence<T>> entries;

    private Matrix(Sequence<Sequence<T>> entries) {
        this.entries = entries;
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
    private Matrix(int m, int n, T... elements) {
        assert elements.length != (m
                * n) : "Violation of: elements.length == m * n";
        assert m < 1 : "Violation of: m is at least 1";
        assert n < 1 : "Violation of: n is at least 1";

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
     * Prints this to the provided SimpleWriter.
     *
     * @param out
     *            the output stream
     */
    private void print(SimpleWriter out) {
        out.println(" _"
                + spaces(this.maxLength() + this.entries.entry(0).length() + 1)
                + "_");
        for (int i = 0; i < this.entries.length(); i++) {
            out.print("|  ");
            for (int j = 0; j < this.entries.entry(i).length(); j++) {
                out.print(this.entries.entry(i).entry(j) + " ");
            }
            out.println(" |");
        }
        out.println(" ‾"
                + spaces(this.maxLength() + this.entries.entry(0).length() + 1)
                + "‾");

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
    private static Matrix<Integer> add(Matrix<Integer> a, Matrix<Integer> b) {
        Sequence<Sequence<Integer>> tempEntries = new Sequence1L<Sequence<Integer>>();
        for (int i = 0; i < a.entries.length(); i++) {
            Sequence<Integer> row = new Sequence1L<Integer>();
            for (int j = 0; j < a.entries.entry(i).length(); j++) {
                row.add(j, a.entries.entry(i).entry(j)
                        + b.entries.entry(i).entry(j));
            }
            tempEntries.add(tempEntries.length(), row);
        }
        return new Matrix<Integer>(tempEntries);
    }

    /**
     * Finds the length of the row with the most characters.
     *
     * @return the number of characters of the longest row.
     */
    private int maxLength() {
        int max = 0;
        for (int i = 0; i < this.entries.length(); i++) {
            int rowMax = 0;
            for (int j = 0; j < this.entries.entry(i).length(); j++) {
                rowMax += this.entries.entry(i).entry(j).toString().length();
            }
            if (rowMax > max) {
                max = rowMax;
            }
        }
        return max;
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

        Matrix<Integer> test = new Matrix<Integer>(3, 3, 1, 2, 3, 4, 5, 6, 7, 8,
                9);
        test.print(out);

        Matrix<Integer> test2 = new Matrix<Integer>(2, 2, 1, 2, 3, 4);
        test2.print(out);

        Matrix<Integer> test3 = new Matrix<Integer>(2, 2, 100, 200, 300, 400);
        test3.print(out);

        Matrix<Integer> test4 = new Matrix<Integer>(4, 2, 100, 200, 300, 400,
                500, 600, 700, 800);
        test4.print(out);

        Matrix<Integer> testadd1 = new Matrix<Integer>(2, 2, 1, 2, 3, 4);
        testadd1.print(out);

        Matrix<Integer> testadd2 = new Matrix<Integer>(2, 2, 1, 1, 1, 1);
        testadd2.print(out);

        add(testadd1, testadd2).print(out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
