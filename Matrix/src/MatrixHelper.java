import components.linear.LinearDouble;
import components.linear.LinearInteger;
import components.matrix.Matrix;
import components.matrix.Matrix2;

/**
 * Matrix Helper Methods.
 *
 * @author Wesley Kamau
 *
 */
public final class MatrixHelper {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private MatrixHelper() {
    }

    /**
     * Creates a Matrix of Integers and returns an equivalent Matrix of Doubles.
     *
     * @param matrix
     *            the Matrix of Integer
     * @return the Matrix of Doubles
     */
    public static Matrix<LinearDouble> intToDouble(
            Matrix<LinearInteger> matrix) {

        Matrix<LinearDouble> output = new Matrix2<LinearDouble>();

        for (int i = 1; i <= matrix.rows(); i++) {
            for (int j = 1; j <= matrix.rows(); j++) {
                output.setElement(i, j,
                        new LinearDouble(matrix.element(i, j).value()));
            }
        }

        return output;
    }

}
