package components.linear;

import components.standard.Standard;

/**
 * Work in progress.
 *
 * @param <T>
 *            The type of this
 */
public interface Linear<T extends Linear<T>> extends Standard<T> {

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
     * Divides this by a linear variable.
     *
     * @param denominator
     *            the denominator
     * @return this / denominator
     */
    T divide(T denominator);

    /**
     * Multiplies this by a linear variable.
     *
     * @param other
     *            the number to multiply
     * @return this * other
     */
    T multiply(T other);

    /**
     * Reports if this is zero.
     *
     * @return true if this is zero.
     */
    boolean isZero();

    /**
     * Reports if this is one.
     *
     * @return true if this is one.
     */
    boolean isOne();
}
