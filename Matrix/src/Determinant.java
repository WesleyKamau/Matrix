import components.linear.LinearVariable;
import components.matrix.Matrix;
import components.matrix.Matrix2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * User-friendly Matrix Interface.
 *
 * @author Wesley Kamau
 *
 */
public final class Determinant {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private Determinant() {
    }

    private static LinearVariable determinant(Matrix<LinearVariable> dex) {
        assert dex.rows() == dex
                .columns() : "Violation of : is a square matrix";

        LinearVariable result = new LinearVariable();

        if (dex.rows() == 2) {
            result = dex.element(1, 1).multiply(dex.element(2, 2)).add(
                    dex.element(1, 2).multiply(dex.element(2, 1)).constant(-1));
        } else if (dex.rows() == 1) {
            result = dex.element(1, 1);
        } else if (dex.rows() > 2) {
            int coefficient = 1;
            for (int i = 1; i <= dex.rows(); i++) {
                Matrix<LinearVariable> temp = new Matrix2<LinearVariable>(
                        new LinearVariable());
                temp.setColumns(dex.columns() - 1);
                temp.setRows(dex.rows() - 1);

                int icount = 1;
                for (int i2 = 1; i2 <= dex.rows(); i2++) {
                    if (i2 != 1) {

                        int jcount = 1;
                        for (int j2 = 1; j2 <= dex.columns(); j2++) {
                            if (j2 != i) {

                                temp.setElement(icount, jcount,
                                        dex.element(i2, j2));
                                jcount++;
                            }
                        }
                        icount++;
                    }
                }

                result = result.add(determinant(temp).constant(coefficient)
                        .multiply(dex.element(1, i)));
                coefficient *= -1;

            }

        }

        return result;
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

        Matrix<LinearVariable> dex = new Matrix2<LinearVariable>(
                new LinearVariable());

        dex.setColumns(10);
        dex.setRows(10);

        for (int i = 1; i <= dex.rows(); i++) {
            for (int j = 1; j <= dex.columns(); j++) {
                if (i == j) {
                    dex.setElement(i, j,
                            new LinearVariable().add("λ", -1.0).add(1.0));
                } else {
                    dex.setElement(i, j, new LinearVariable().add(-1.0));
                }
            }
        }

        dex.print(out);
        out.print("The determinant is: " + determinant(dex));
        //dex.print(out);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
