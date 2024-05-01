package components.linear;

import java.util.Iterator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;

/**
 * Linear system represented as an double.
 */
public final class LinearVariable implements Linear<LinearVariable> {

    /**
     * Value of this as a Map. The string represents the name of the variable.
     * The nested map Integer represents the exponent The nested map
     * LinearDouble represents the value for that variable at that exponent
     *
     * Each Map.Pair : value represents: <String, Map<Integer,LinearDouble> =
     * LinearDouble(String)^Integer
     */
    private Map<String, Map<Integer, LinearDouble>> value;

    /**
     * Nonvariable value as a LinearDouble.
     */
    private LinearDouble num;

    /**
     * Creates a copy of this.value and returns it.
     *
     * @return the Map value
     */
    private Map<String, Map<Integer, LinearDouble>> copyValue() {
        Map<String, Map<Integer, LinearDouble>> temp = this.value.newInstance();
        for (Map.Pair<String, Map<Integer, LinearDouble>> element : this.value) {
            Map<Integer, LinearDouble> tempMap = element.value().newInstance();
            for (Map.Pair<Integer, LinearDouble> pairElement : element
                    .value()) {
                tempMap.add(pairElement.key(),
                        new LinearDouble(pairElement.value()));
            }
            temp.add(element.key(), tempMap);
        }
        return temp;
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     */
    public LinearVariable add(String name, Double value) {
        /*
         * Copy over the contents of this.value to a new Map
         */
        Map<String, Map<Integer, LinearDouble>> newMap = this.copyValue();

        // Case for when the variable already exists in the LinearVariable
        if (newMap.hasKey(name)) {
            Map.Pair<String, Map<Integer, LinearDouble>> index = newMap
                    .remove(name);
            if (index.value().hasKey(1)) {
                index.value().replaceValue(1,
                        index.value().value(1).add(value));
            } else {
                index.value().add(1, new LinearDouble(value));
            }
            newMap.add(name, index.value());
        } else {
            Map<Integer, LinearDouble> temp = new Map1L<Integer, LinearDouble>();
            temp.add(1, new LinearDouble(value));
            newMap.add(name, temp);
        }

        return new LinearVariable(newMap, new LinearDouble(this.num));
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     */
    public void add(String name, Double value, int exponent) {
        if (this.value.hasKey(name)) {
            if (this.value.value(name).hasKey(exponent)) {
                this.value.value(name).replaceValue(exponent,
                        this.value.value(name).value(exponent)
                                .add(new LinearDouble(value)));
            } else {
                this.value.value(name).add(exponent, new LinearDouble(value));
            }
        } else {
            Map<Integer, LinearDouble> temp = new Map1L<Integer, LinearDouble>();
            temp.add(exponent, new LinearDouble(value));
            this.value.add(name, temp);
        }
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     */
    public void add(String name, LinearDouble value) {
        if (this.value.hasKey(name)) {
            if (this.value.value(name).hasKey(1)) {
                this.value.value(name).replaceValue(1,
                        this.value.value(name).value(1).add(value));
            } else {
                this.value.value(name).add(1, value);
            }
        } else {
            Map<Integer, LinearDouble> temp = new Map1L<Integer, LinearDouble>();
            temp.add(1, value);
            this.value.add(name, temp);
        }
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     */
    public void add(String name, LinearDouble value, int exponent) {
        if (this.value.hasKey(name)) {
            if (this.value.value(name).hasKey(exponent)) {
                this.value.value(name).replaceValue(exponent,
                        this.value.value(name).value(exponent).add(value));
            } else {
                this.value.value(name).add(exponent, value);
            }
        } else {
            Map<Integer, LinearDouble> temp = new Map1L<Integer, LinearDouble>();
            temp.add(exponent, value);
            this.value.add(name, temp);
        }
    }

    /**
     * Add a value to this.
     *
     * @param value
     *            the value being added
     */
    public void add(Double value) {
        this.num = this.num.add(new LinearDouble(value));
    }

    /**
     * Add a value to this.
     *
     * @param value
     *            the value being added
     */
    public void add(LinearDouble value) {
        this.num = this.num.add(value);
    }

    /**
     * Basic constructor.
     */
    public LinearVariable() {
        this.createNewRep();
    }

    /**
     * Constructor from a Map of variables and values and a value.
     *
     * @param value
     *            the Map representing the variables and corresponding values.
     * @param num
     *            the numeric value
     */
    public LinearVariable(Map<String, Map<Integer, LinearDouble>> value,
            LinearDouble num) {
        this.value = value;
        this.num = num;
    }

    /**
     * Sets this to initial value.
     */
    private void createNewRep() {
        this.value = new Map1L<String, Map<Integer, LinearDouble>>();
        this.num = new LinearDouble(0);
    }

    @Override
    public LinearVariable add(LinearVariable other) {
        Map<String, Map<Integer, LinearDouble>> temp = new Map1L<String, Map<Integer, LinearDouble>>();

//        for (Map.Pair<String, Map<Integer, LinearDouble>> variable : this.value) {
//            LinearDouble tempCount = variable.value();
//            if (other.value.hasKey(variable.key())) {
//                tempCount = tempCount.add(other.value.value(variable.key()));
//            }
//            temp.add(variable.key(), tempCount);
//        }
//
//        for (Map.Pair<String, LinearDouble> variable : other.value) {
//            if (!this.value.hasKey(variable.key())) {
//                temp.add(variable.key(), variable.value());
//            }
//        }

        return new LinearVariable(temp, this.num.add(other.num));
    }

    @Override
    public LinearVariable constant(int c) {
        Map<String, LinearDouble> temp = new Map1L<String, LinearDouble>();

        for (Map.Pair<String, LinearDouble> variable : this.value) {
            temp.add(variable.key(), variable.value().constant(c));
        }

        return new LinearVariable(temp, this.num.constant(c));
    }

    @Override
    public LinearVariable constant(double c) {
        Map<String, LinearDouble> temp = new Map1L<String, LinearDouble>();

        for (Map.Pair<String, LinearDouble> variable : this.value) {
            temp.add(variable.key(), variable.value().constant(c));
        }

        return new LinearVariable(temp, this.num.constant(c));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof LinearVariable)) {
            return false;
        }

        LinearVariable q = (LinearVariable) obj;
        if (!this.value.equals(q.value) || !this.num.equals(q.num)) {
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
        StringBuilder sb = new StringBuilder();

        Iterator<Pair<String, LinearDouble>> iterator = this.value.iterator();

        if (iterator.hasNext()) {
            Map.Pair<String, LinearDouble> temp = iterator.next();
            if (temp.value().isNegativeOne()) {
                sb.append("-" + temp.key());
            } else if (temp.value().isOne()) {
                sb.append("+" + temp.key());
            } else {
                if (!temp.value().isPositive()) {
                    sb.append("-");
                }
                sb.append(temp.value() + temp.key());
            }
        } else {
            if (!this.num.isZero()) {
                sb.append(this.num);
            }
            return sb.toString();
        }

        while (iterator.hasNext()) {
            Map.Pair<String, LinearDouble> temp = iterator.next();
            if (temp.value().isNegativeOne()) {
                sb.append("-" + temp.key());
            } else if (temp.value().isOne()) {
                sb.append("+" + temp.key());
            } else {
                if (temp.value().isPositive()) {
                    sb.append("+");
                } else {
                    sb.append("-");
                }
                sb.append(temp.value() + temp.key());
            }

        }

        if (!this.num.isZero()) {
            if (this.num.isPositive()) {
                sb.append("+" + this.num);
            } else {
                sb.append(this.num);
            }
        }

        return sb.toString();
    }

    @Override
    public LinearVariable newInstance() {
        return new LinearVariable();
    }

    @Override
    public void transferFrom(LinearVariable source) {
        this.value = source.value;
        this.num = source.num;
        source.createNewRep();
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public boolean isZero() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isOne() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LinearVariable divide(LinearVariable denominator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LinearVariable multiply(LinearVariable other) {
        LinearVariable temp = new LinearVariable();
        for (Map.Pair<String, LinearDouble> variable : this.value) {
            for (Map.Pair<String, LinearDouble> variable2 : other.value) {
                if (variable.key().equals(variable2.key())) {
                    if (variable.key().indexOf('^') != -1) {
                        int power = Integer.parseInt(variable.key()
                                .substring(variable.key().indexOf('^') + 1));
                        temp.add(variable.key() + "^" + power + 1,
                                variable.value().multiply(variable2.value()));
                    } else {
                        temp.add(variable.key() + "^2",
                                variable.value().multiply(variable2.value()));
                    }
                } else {
                    temp.add("(" + variable.key() + "*" + variable2.key() + ")",
                            variable.value().multiply(variable2.value()));
                }
            }
        }

        for (Map.Pair<String, LinearDouble> variable : this.value) {
            temp.add(variable.key(), variable.value().multiply(other.num));
        }

        for (Map.Pair<String, LinearDouble> variable : other.value) {
            temp.add(variable.key(), variable.value().multiply(this.num));
        }

        temp.add(this.num.multiply(other.num));

        return temp;
    }

}
