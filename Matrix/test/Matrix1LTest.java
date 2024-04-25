import components.linear.LinearDouble;
import components.matrix.Matrix;
import components.matrix.Matrix1L;

/**
 * Customized JUnit test fixture for {@code Set3a}.
 */
public class Matrix1LTest extends MatrixTest {

    @Override
    protected final Matrix<LinearDouble> constructorTest(LinearDouble t) {
        return new Matrix1L<LinearDouble>(t.newInstance());
    }

}
