package components.linear;

/**
 * Work in progress.
 *
 * @param <T>
 *            The type of this
 */
public interface Linear<T extends Linear<T>> {

    /**
     * Adds two linear variables.
     *
     * @param other
     *            variable to add
     *
     * @return the result of this + other
     */
    T add(T other);

    /**
     * Multiplies this by a constant.
     *
     * @param c
     *            the constant
     * @return the result of c * this
     */
    T constant(int c);

    /**
     * Multiplies this by a constant.
     *
     * @param c
     *            the constant
     * @return the result of c * this
     */
    T constant(double c);

    /**
     * Reports if this is zero.
     *
     * @return true if this is zero.
     */
    boolean isZero();
}
