import components.linear.LinearDouble;
import components.linear.LinearVariable;
import components.map.Map;
import components.matrix.Matrix;
import components.matrix.Matrix1L;
import components.matrix.Matrix2;
import components.queue.Queue;
import components.queue.Queue1L;
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
                Matrix<LinearVariable> temp = new Matrix2<LinearVariable>();

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

    private static Queue<Double> eigenvalues(LinearVariable equation) {
        final int maxPower = 2;
        assert equation.value
                .size() == 1 : "Violaton of: there is one variable";
        assert highestPower(
                equation) <= maxPower : "Violation of: highest power is 3";

        int highest = highestPower(equation);
        Map<Integer, LinearDouble> powers = equation.value
                .value(getVariable(equation));

        Queue<Double> result = new Queue1L<Double>();

        if (highest == 2) {
            double a = getCoefficient(2, powers);
            double b = getCoefficient(1, powers);
            double c = equation.num.value();

            result.enqueue(
                    (-b + Math.sqrt(Math.pow(b, 2.0) - 4 * a * c)) / (2 * a));
            result.enqueue(
                    (-b - Math.sqrt(Math.pow(b, 2.0) - 4 * a * c)) / (2 * a));
        } else if (highest == 1) {
            double a = getCoefficient(1, powers);
            double b = equation.num.value();

            if (Double.compare(a, 0.0) > 0) {
                result.enqueue(-b);
            } else {
                result.enqueue(b);
            }
        } else {
            double a = equation.num.value();

            result.enqueue(a);
        }

        return result;
    }

    private static Matrix<LinearDouble> eigenvector(
            Matrix<LinearVariable> equation, double eigenvalue) {
        Queue<Double> eigenvalues = eigenvalues(determinant(equation));
        boolean hasEigenvalue = false;
        for (Double value : eigenvalues) {
            if (Double.compare(eigenvalue, value) == 0) {
                hasEigenvalue = true;
            }
        }
        assert hasEigenvalue : "Violation of: eigenvalue is an eigenvalue of the matrix";

        Matrix<LinearDouble> result = new Matrix1L<LinearDouble>(
                new LinearDouble());
        Matrix<LinearDouble> tempMatrix = set(equation, eigenvalue);

        System.out.print(tempMatrix.reduce());

        return result;
    }

    private static Matrix<LinearDouble> set(Matrix<LinearVariable> matrix,
            double value) {
        Matrix<LinearDouble> result = new Matrix2<LinearDouble>();
        for (int i = 1; i <= matrix.rows(); i++) {
            for (int j = 1; j <= matrix.columns(); j++) {
                result.setElement(i, j, matrix.element(i, j).set(value));
            }
        }
        return result;
    }

    private static double getCoefficient(int power,
            Map<Integer, LinearDouble> index) {
        double result = 0.0;
        if (index.hasKey(power)) {
            result = index.value(power).value();
        }
        return result;
    }

    private static int highestPower(LinearVariable equation) {
        int result = 0;
        for (Map.Pair<Integer, LinearDouble> power : equation.value
                .value(getVariable(equation))) {
            if (power.key() > result) {
                result = power.key();
            }
        }
        return result;
    }

    private static String getVariable(LinearVariable equation) {
        String result = "";
        for (Map.Pair<String, Map<Integer, LinearDouble>> variable : equation.value) {
            result = variable.key();
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

        Matrix<LinearVariable> dex = new Matrix2<LinearVariable>();

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                if (i == j) {
                    dex.setElement(i, j,
                            new LinearVariable().add("λ", -1.0).add(1.0));
                } else {
                    dex.setElement(i, j, new LinearVariable().add(-1.0));
                }
            }
        }

        dex.print(out);
        out.println("The determinant is: " + determinant(dex));
        //dex.print(out);

        Matrix<LinearVariable> dex2 = new Matrix2<LinearVariable>();

        dex2.setElement(1, 1, new LinearVariable().add(16).add("λ", -1));
        dex2.setElement(1, 2, new LinearVariable().add(6));
        dex2.setElement(2, 1, new LinearVariable().add(-18));
        dex2.setElement(2, 2, new LinearVariable().add(-5).add("λ", -1));

        dex2.print(out);
        out.println(eigenvector(dex2, 4.0));

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
