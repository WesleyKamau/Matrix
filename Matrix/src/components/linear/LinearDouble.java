package components.linear;

/**
 * Linear system represented as an double.
 */
public final class LinearDouble implements Linear<LinearDouble> {

    /**
     * Value of this as an double.
     */
    private double value;

    /**
     * Constructs a LinearDouble object with the specified value.
     *
     * @param value
     *            The value of the LinearDouble object.
     */
    public LinearDouble(double value) {
        this.value = value;
    }

    /**
     * Basic constructor.
     */
    public LinearDouble() {
        this.createNewRep();
    }

    /**
     * Sets this to initial value.
     */
    private void createNewRep() {
        this.value = 0;
    }

    @Override
    public LinearDouble add(LinearDouble other) {
        LinearDouble result = new LinearDouble(this.value + other.value);
        if (result.isZero()) {
            result = new LinearDouble(0.0);
        }
        return result;
    }

    @Override
    public LinearDouble constant(int c) {
        LinearDouble result = new LinearDouble(this.value * c);
        if (result.isZero()) {
            result = new LinearDouble(0.0);
        }
        return result;
    }

    @Override
    public LinearDouble constant(double c) {
        LinearDouble result = new LinearDouble(this.value * c);
        if (result.isZero()) {
            result = new LinearDouble(0.0);
        }
        return result;
    }

    @Override
    public LinearDouble divide(LinearDouble denominator) {
        LinearDouble result = new LinearDouble(this.value / denominator.value);
        if (result.isZero()) {
            result = new LinearDouble(0.0);
        }
        return result;
    }

    @Override
    public LinearDouble multiply(LinearDouble other) {
        LinearDouble result = new LinearDouble(this.value * other.value);
        if (result.isZero()) {
            result = new LinearDouble(0.0);
        }
        return result;
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

    @Override
    public int hashCode() {
        return Double.hashCode(this.value);
    }

    @Override
    public String toString() {
        double temp = this.value;

        if (temp > 0.0) {
            while (temp >= 1.0) {
                temp -= 1.0;
            }
        } else {
            while (temp <= -1.0) {
                temp += 1.0;
            }
        }

        if (Math.abs(temp) < EPSILON) {
            return Double.toString(this.value).substring(0,
                    Double.toString(this.value).indexOf('.'));
        }
        return Double.toString(this.value);
    }

    @Override
    public boolean isZero() {
        return Math.abs(this.value) < EPSILON;
    }

    @Override
    public void clear() {
        this.value = 0;

    }

    @Override
    public LinearDouble newInstance() {
        return new LinearDouble();
    }

    @Override
    public void transferFrom(LinearDouble source) {
        this.value = source.value;
        source.clear();

    }

    @Override
    public boolean isOne() {
        return Math.abs(this.value - 1) < EPSILON;
    }

    /**
     * Determines if the value of this is positive.
     *
     * @return true if this is positive.
     */
    public boolean isPositive() {
        return this.value >= 0;
    }

    /**
     * Determines if the value of this is -1.
     *
     * @return true if this is -1.
     */
    public boolean isNegativeOne() {
        return Double.compare(this.value, -1.0) == 0;
    }

}
