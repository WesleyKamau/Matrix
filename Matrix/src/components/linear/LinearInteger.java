package components.linear;

/**
 * Linear system represented as an integer.
 */
public final class LinearInteger extends LinearSecondary<LinearInteger> {

    /**
     * Value of this as an integer.
     */
    private int value;

    /**
     * Constructs a LinearInteger object with the specified value.
     *
     * @param value
     *            The value of the LinearInteger object.
     */
    public LinearInteger(int value) {
        this.value = value;
    }

    /**
     * Basic constructor.
     */
    public LinearInteger() {
        this.createNewRep();
    }

    /**
     * Sets this to initial value.
     */
    private void createNewRep() {
        this.value = 0;
    }

    @Override
    public LinearInteger add(LinearInteger other) {
        return new LinearInteger(this.value + other.value);
    }

    @Override
    public LinearInteger add(int other) {
        return new LinearInteger(this.value + other);
    }

    @Override
    public LinearInteger add(double other) {
        return new LinearInteger((int) (this.value + other));
    }

    @Override
    public LinearInteger multiply(LinearInteger other) {
        return new LinearInteger(this.value * other.value);
    }

    @Override
    public LinearInteger multiply(int c) {
        return new LinearInteger(this.value * c);
    }

    @Override
    public LinearInteger multiply(double c) {
        return new LinearInteger((int) (this.value * c));
    }

    @Override
    public LinearInteger divide(LinearInteger denominator) {
        return new LinearInteger(this.value / denominator.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LinearInteger)) {
            return false;
        }
        LinearInteger q = (LinearInteger) obj;
        if (this.value != q.value) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.value);
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    @Override
    public boolean isZero() {
        return this.value == 0;
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public LinearInteger newInstance() {
        return new LinearInteger();
    }

    @Override
    public void transferFrom(LinearInteger source) {
        this.value = source.value;
        source.createNewRep();
    }

    @Override
    public boolean isOne() {
        return this.value == 1;
    }

    @Override
    public int compareTo(LinearInteger o) {
        return Integer.compare(this.value, o.value);
    }

    /**
     * Returns the value of this as a double.
     *
     * @return the value of this as a double.
     */
    public double value() {
        return this.value;
    }

}
