package components.linear;

/**
 * Secondary class for linear computations.
 *
 * @param <T>
 *            The type of this
 */
public abstract class LinearSecondary<T extends LinearSecondary<T>>
        implements Linear<T>, Comparable<T> {

    /**
     * Subtracts two linear variables.
     *
     * @param other
     *            variable to add
     *
     * @return the result of this - other
     */
    T subtract(T other) {
        return this.add(other.negative());
    }

    /**
     * Subtracts other from this.
     *
     * @param other
     *            variable to add
     *
     * @return the result of this - other
     */
    T subtract(int other) {
        return this.add(-other);
    }

    /**
     * Subtracts other from this.
     *
     * @param other
     *            variable to add
     *
     * @return the result of this - other
     */
    T subtract(double other) {
        return this.add(-other);
    }

    /**
     * Returns the opposite of this.
     *
     * @return -this
     */
    T negative() {
        return this.multiply(-1);
    }
}
