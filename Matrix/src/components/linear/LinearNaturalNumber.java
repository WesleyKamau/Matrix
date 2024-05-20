package components.linear;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * Linear system represented as an NaturalNumber.
 */
public final class LinearNaturalNumber
        extends LinearSecondary<LinearNaturalNumber> {

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
        this.createNewRep();
        this.value.copyFrom(value);
    }

    /**
     * Basic constructor.
     */
    public LinearNaturalNumber() {
        this.createNewRep();
    }

    /**
     * Constructs a LinearNaturalNumber object with the specified value.
     *
     * @param value
     *            The value of the LinearNaturalNumber object.
     */
    public LinearNaturalNumber(int value) {
        this.value = new NaturalNumber1L(value);
    }

    /**
     * Sets this to initial value.
     */
    private void createNewRep() {
        this.value = new NaturalNumber1L();
    }

    @Override
    public LinearNaturalNumber add(LinearNaturalNumber other) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.add(other.value);
        return new LinearNaturalNumber(temp);
    }

    @Override
    public LinearNaturalNumber add(int other) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.add(new NaturalNumber1L(other));
        return new LinearNaturalNumber(temp);
    }

    @Override
    public LinearNaturalNumber add(double other) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.add(new NaturalNumber1L((int) other));
        return new LinearNaturalNumber(temp);
    }

    @Override
    public LinearNaturalNumber multiply(LinearNaturalNumber other) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.multiply(other.value);
        return new LinearNaturalNumber(temp);
    }

    @Override
    public LinearNaturalNumber multiply(int c) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.multiply(new NaturalNumber1L(c));
        return new LinearNaturalNumber(temp);
    }

    @Override
    public LinearNaturalNumber multiply(double c) {
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
    public LinearNaturalNumber subtract(LinearNaturalNumber other) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.subtract(new NaturalNumber1L(other.value));
        return new LinearNaturalNumber(temp);
    }

    @Override
    public LinearNaturalNumber subtract(int other) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.subtract(new NaturalNumber1L(other));
        return new LinearNaturalNumber(temp);
    }

    @Override
    public LinearNaturalNumber subtract(double other) {
        NaturalNumber temp = new NaturalNumber1L(this.value);
        temp.subtract(new NaturalNumber1L((int) other));
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

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

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
        source.createNewRep();
    }

    @Override
    public boolean isOne() {
        return this.value.equals(new NaturalNumber1L(1));
    }

    @Override
    public int compareTo(LinearNaturalNumber o) {
        return this.value.compareTo(o.value);
    }

    public LinearNaturalNumber power(LinearNaturalNumber p) {
        NaturalNumber result = this.value.newInstance();
        result.power(p.value.toInt());
        return new LinearNaturalNumber(result);
    }

    @Override
    public LinearNaturalNumber power(int p) {
        NaturalNumber result = this.value.newInstance();
        result.power(p);
        return new LinearNaturalNumber(result);
    }

    @Override
    public LinearNaturalNumber power(double p) {
        NaturalNumber result = this.value.newInstance();
        result.power((int) p);
        return new LinearNaturalNumber(result);
    }

}
