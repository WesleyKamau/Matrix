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
     * Constructs a Linear1 object with the specified value.
     *
     * @param value
     *            The value of the Linear1 object.
     */
    public LinearInteger(int value) {
        this.value = value;
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

}
