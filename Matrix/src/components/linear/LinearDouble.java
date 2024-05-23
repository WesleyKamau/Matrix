package components.linear;

/**
 * Linear number represented as an double.
 */
public final class LinearDouble extends LinearSecondary<LinearDouble> {

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
     * Constructs a LinearDouble object with the specified value of another
     * LinearDouble.
     *
     * @param value
     *            The value of the LinearDouble object.
     */
    public LinearDouble(LinearDouble value) {
        this.value = value.value;
    }

    /**
     * Basic constructor with a default value of 0.
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
        return new LinearDouble(this.value + other.value);
    }

    @Override
    public LinearDouble add(int other) {
        return new LinearDouble(this.value + other);
    }

    @Override
    public LinearDouble add(double other) {
        return new LinearDouble(this.value + other);
    }

    @Override
    public LinearDouble multiply(LinearDouble other) {
        return new LinearDouble(this.value * other.value);
    }

    @Override
    public LinearDouble multiply(int c) {
        return new LinearDouble(this.value * c);
    }

    @Override
    public LinearDouble multiply(double c) {
        return new LinearDouble(this.value * c);
    }

    @Override
    public LinearDouble divide(LinearDouble denominator) {
        assert !denominator.isZero() : "Violation of: no division by zero";
        return new LinearDouble(this.value / denominator.value);
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
        if (Double.compare(this.value, q.value) != 0) {
            return false;
        }

        return true;
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

        if (Double.compare(Math.abs(temp), 0) == 0) {
            return Double.toString(this.value).substring(0,
                    Double.toString(this.value).indexOf('.'));
        }
        return Double.toString(this.value);
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public LinearDouble newInstance() {
        return new LinearDouble();
    }

    @Override
    public void transferFrom(LinearDouble source) {
        this.value = source.value;
        source.createNewRep();
    }

    @Override
    public boolean isZero() {
        return this.compareTo(new LinearDouble()) == 0
                || this.compareTo(new LinearDouble(-0.0)) == 0;
    }

    @Override
    public boolean isOne() {
        return this.compareTo(new LinearDouble(1.0)) == 0;
    }

    /**
     * Determines if the value of this is positive.
     *
     * @return true if this is positive.
     */
    public boolean isPositive() {
        return this.compareTo(new LinearDouble()) >= 0;
    }

    /**
     * Determines if the value of this is -1.
     *
     * @return true if this is -1.
     */
    public boolean isNegativeOne() {
        return this.compareTo(new LinearDouble(-1.0)) == 0;
    }

    /**
     * Returns the value of this as a double.
     *
     * @return the value of this as a double.
     */
    public double value() {
        return this.value;
    }

    @Override
    public int compareTo(LinearDouble o) {
        return Double.compare(this.value, o.value);
    }

    public LinearDouble power(LinearDouble p) {
        return new LinearDouble(Math.pow(this.value, p.value));
    }

    public LinearDouble power(int p) {
        return new LinearDouble(Math.pow(this.value, p));
    }

    public LinearDouble power(double p) {
        return new LinearDouble(Math.pow(this.value, p));
    }

}
