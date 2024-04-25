package components.linear;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * Linear system represented as an NaturalNumber.
 */
public final class LinearNaturalNumber implements Linear<LinearNaturalNumber> {

    /**
     * Value of this as an NaturalNumber.
     */
    private NaturalNumber value;

    /**
     * Constructs a LinearNaturalNumber object with the specified value.
     *
     * @param value
     *            The value of the LinearNaturalNumber object.
     */
    public LinearNaturalNumber(NaturalNumber value) {
        this.value = value;
    }

    /**
     * Basic constructor.
     */
    public LinearNaturalNumber() {
        this.createNewRep();
    }

    /**
     * Sets this to initial value.
     */
    private void createNewRep() {
        this.value = new NaturalNumber1L(0);
    }

    @Override
    public LinearNaturalNumber add(LinearNaturalNumber other) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.add(other.value);
        return new LinearNaturalNumber(temp);
    }

    @Override
    public LinearNaturalNumber constant(int c) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.multiply(new NaturalNumber1L(c));
        return new LinearNaturalNumber(temp);
    }

    @Override
    public LinearNaturalNumber constant(double c) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.multiply(new NaturalNumber1L((int) c));
        return new LinearNaturalNumber(temp);
    }

    @Override
    public LinearNaturalNumber divide(LinearNaturalNumber denominator) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.divide(new NaturalNumber1L(denominator.value));
        return new LinearNaturalNumber(temp);
    }

    @Override
    public LinearNaturalNumber multiply(LinearNaturalNumber other) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.multiply(other.value);
        return new LinearNaturalNumber(temp);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LinearNaturalNumber)) {
            return false;
        }
        LinearNaturalNumber q = (LinearNaturalNumber) obj;
        if (!this.value.equals(q.value)) {
            return false;
        }

        return true;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public boolean isZero() {
        return this.value.isZero();
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public LinearNaturalNumber newInstance() {
        return new LinearNaturalNumber();
    }

    @Override
    public void transferFrom(LinearNaturalNumber source) {
        this.value = source.value;
        source.clear();
    }

    @Override
    public boolean isOne() {
        return this.value.equals(new NaturalNumber1L(1));
    }

}
