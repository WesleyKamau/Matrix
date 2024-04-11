package components.linear;

/**
 * Linear system represented as an integer.
 */
public final class LinearDouble implements Linear<LinearDouble> {

    /**
     * Value of this as an integer.
     */
    private double value;

    /**
     * Constructs a Linear1 object with the specified value.
     *
     * @param value
     *            The value of the Linear1 object.
     */
    public LinearDouble(double value) {
        this.value = value;
    }

    @Override
    public LinearDouble add(LinearDouble other) {
        return new LinearDouble(this.value + other.value);
    }

    @Override
    public LinearDouble constant(int c) {
        return new LinearDouble(this.value * c);
    }

    @Override
    public LinearDouble constant(double c) {
        return new LinearDouble(this.value * c);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LinearDouble)) {
            return false;
        }
        LinearDouble q = (LinearDouble) obj;
        if (!isEqual(this.value, q.value)) {
            return false;
        }

        return true;
    }

    /**
     * Epsilon vaue.
     */
    private static final double EPSILON = 1e-10;

    /**
     * Compares two values with the accuracy epsilon.
     *
     * @param a
     *            value a
     * @param b
     *            value b
     * @return true if they are close enough to each other
     */
    public static boolean isEqual(double a, double b) {
        return Math.abs(a - b) < EPSILON;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        return Double.hashCode(this.value);
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        return Double.toString(this.value);
    }
}
