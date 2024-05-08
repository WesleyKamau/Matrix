import components.linear.LinearDouble;
import components.matrix.Matrix;
import components.matrix.Matrix2;

/**
 * Customized JUnit test fixture for {@code Set3a}.
 */
public class Matrix2Test extends MatrixTest {

    @Override
    protected final Matrix<LinearDouble> constructorTest(LinearDouble t) {
        return new Matrix2<LinearDouble>();
    }

}
