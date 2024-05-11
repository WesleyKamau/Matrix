package components.linear;

/**
 * Linear system represented as an integer.
 */
public final class LinearInteger implements Linear<LinearInteger> {

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
    public LinearInteger constant(int c) {
        return new LinearInteger(this.value * c);
    }

    @Override
    public LinearInteger constant(double c) {
        return new LinearInteger((int) (this.value * c));
    }

    @Override
    public LinearInteger divide(LinearInteger denominator) {
        return new LinearInteger(this.value / denominator.value);
    }

    @Override
    public LinearInteger multiply(LinearInteger other) {
        return new LinearInteger(this.value * other.value);
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

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        return Integer.hashCode(this.value);
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
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
        source.clear();

    }

    @Override
    public boolean isOne() {
        return this.value == 1;
    }

    public double value() {
        return this.value;
    }

}
