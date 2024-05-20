package components.linear;

import components.standard.Standard;

/**
 * Class for linear computations.
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
     * Adds other to this.
     *
     * @param other
     *            variable to add
     *
     * @return the result of this + other
     */
    T add(int other);

    /**
     * Adds other to this.
     *
     * @param other
     *            variable to add
     *
     * @return the result of this + other
     */
    T add(double other);

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
     * Multiplies this by a constant.
     *
     * @param c
     *            the constant
     * @return the result of c * this
     */
    T multiply(int c);

    /**
     * Multiplies this by a constant.
     *
     * @param c
     *            the constant
     * @return the result of c * this
     */
    T multiply(double c);

    T power(int p);

    T power(double p);

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
