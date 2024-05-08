package components.linear;

import java.util.Iterator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;

/**
 * Linear system represented as an double.
 *
 * @author Wesley Kamau
 */
public final class LinearVariable implements Linear<LinearVariable> {

    /*
     * Private members
     */

    /**
     * Value of this as a Map. The string represents the name of the variable.
     * The nested map Integer represents the exponent The nested map
     * LinearDouble represents the value for that variable at that exponent
     *
     * Each Map.Pair : value represents: <String, Map<Integer,LinearDouble> =
     * LinearDouble(String)^Integer
     */
    public Map<String, Map<Integer, LinearDouble>> value;

    /**
     * Nonvariable value as a LinearDouble.
     */
    public LinearDouble num;

    /*
     * Constructors ------------------------------------------------------------
     */

    /**
     * Basic constructor.
     */
    public LinearVariable() {
        this.createNewRep();
    }

    /**
     * Constructor from a Map of variables and values and a value.
     *
     * @param newMap
     *            the Map representing the variables and corresponding values.
     * @param num
     *            the numeric value
     */
    private LinearVariable(Map<String, Map<Integer, LinearDouble>> newMap,
            LinearDouble num) {
        this.value = newMap;
        this.num = num;
    }

    /*
     * Helper methods ----------------------------------------------------------
     */

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
                tempMap.add(pairElement.key(), pairElement.value());
            }
            temp.add(element.key(), tempMap);
        }
        return temp;
    }

    /**
     * Sets this to initial value.
     */
    private void createNewRep() {
        this.value = new Map1L<String, Map<Integer, LinearDouble>>();
        this.num = new LinearDouble();
    }

    /*
     * Standard methods --------------------------------------------------------
     */

    @Override
    public LinearVariable add(LinearVariable other) {
        /*
         * Copy over the contents of this.value to a new Map
         */
        LinearVariable result = new LinearVariable();

        for (Map.Pair<String, Map<Integer, LinearDouble>> element : this.value) {
            if (other.value.hasKey(element.key())) {
                // Handles all of the variables in both LinearVariables
                for (Map.Pair<Integer, LinearDouble> variable : element
                        .value()) {
                    if (other.value.value(element.key())
                            .hasKey(variable.key())) {
                        result = result.add(element.key(),
                                variable.value()
                                        .add(other.value.value(element.key())
                                                .value(variable.key())),
                                variable.key());
                    } else {
                        result = result.add(element.key(), variable.value(),
                                variable.key());
                    }
                }
                for (Map.Pair<Integer, LinearDouble> variable : other.value
                        .value(element.key())) {
                    if (!element.value().hasKey(variable.key())) {
                        /*
                         * Case for when the exponent value is only in other
                         */
                        result = result.add(element.key(), variable.value(),
                                variable.key());
                    }
                }
            } else {
                // Handles all of the variables only in this
                for (Map.Pair<Integer, LinearDouble> variable : element
                        .value()) {
                    result = result.add(element.key(), variable.value(),
                            variable.key());
                }
            }
        }
        for (Map.Pair<String, Map<Integer, LinearDouble>> element : other.value) {
            if (!this.value.hasKey(element.key())) {
                // Handles all of the variables only in other
                for (Map.Pair<Integer, LinearDouble> variable : element
                        .value()) {
                    result = result.add(element.key(), variable.value(),
                            variable.key());
                }
            }
        }
        result = result.add(this.num.add(other.num));

        return result;
    }

    @Override
    public LinearVariable constant(int c) {
        /*
         * Copy over the contents of this.value to a new Map
         */
        Map<String, Map<Integer, LinearDouble>> newMap = new Map1L<String, Map<Integer, LinearDouble>>();
        ;

        for (Map.Pair<String, Map<Integer, LinearDouble>> variable : this.value) {
            Map<Integer, LinearDouble> variableMap = new Map1L<Integer, LinearDouble>();
            for (Map.Pair<Integer, LinearDouble> exponent : variable.value()) {
                variableMap.add(exponent.key(), exponent.value().constant(c));
            }
            newMap.add(variable.key(), variableMap);
        }

        return new LinearVariable(newMap, this.num.constant(c));
    }

    @Override
    public LinearVariable constant(double c) {
        Map<String, Map<Integer, LinearDouble>> newMap = new Map1L<String, Map<Integer, LinearDouble>>();
        ;

        for (Map.Pair<String, Map<Integer, LinearDouble>> variable : this.value) {
            Map<Integer, LinearDouble> variableMap = new Map1L<Integer, LinearDouble>();
            for (Map.Pair<Integer, LinearDouble> exponent : variable.value()) {
                variableMap.add(exponent.key(), exponent.value().constant(c));
            }
            newMap.add(variable.key(), variableMap);
        }

        return new LinearVariable(newMap, this.num.constant(c));
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
        if (!this.value.equals(q.value)) {
            return false;
        }

        if (!this.num.equals(q.num)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode() + this.num.hashCode();
    }

    private String firstVariableString(
            Iterator<Pair<Integer, LinearDouble>> variableIterator,
            String name) {

        Map.Pair<Integer, LinearDouble> exponent = variableIterator.next();
        while (exponent.value().isZero() && variableIterator.hasNext()) {
            exponent = variableIterator.next();
        }

        String result = "";
        if (!exponent.value().isZero()) {
            if (!(exponent.value().isOne()
                    || exponent.value().isNegativeOne())) {
                result += exponent.value() + "*";
            }
            if (exponent.value().isNegativeOne()) {
                result += "-";
            }
            result += name;
            if (exponent.key() != 1) {
                result += "^" + exponent.key();
            }
        }
        return result;
    }

    private String variableString(
            Iterator<Pair<Integer, LinearDouble>> variableIterator,
            String name) {

        Map.Pair<Integer, LinearDouble> exponent = variableIterator.next();
        while (exponent.value().isZero() && variableIterator.hasNext()) {
            exponent = variableIterator.next();
        }

        String result = "";
        if (!exponent.value().isZero()) {
            int coefficient = 1;
            if (exponent.value().isPositive()) {
                result += " + ";
            } else {
                result += " - ";
                coefficient = -1;
            }

            if (!(exponent.value().isOne()
                    || exponent.value().isNegativeOne())) {
                result += exponent.value().constant(coefficient) + "*";
            }
            result += name;
            if (exponent.key() != 1) {
                result += "^" + exponent.key();
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Iterator<Pair<String, Map<Integer, LinearDouble>>> iterator = this.value
                .iterator();

        if (iterator.hasNext()) {
            Map.Pair<String, Map<Integer, LinearDouble>> temp = iterator.next();
            Iterator<Pair<Integer, LinearDouble>> variableIterator = temp
                    .value().iterator();
            if (variableIterator.hasNext()) {
                sb.append(
                        this.firstVariableString(variableIterator, temp.key()));
            }

            while (variableIterator.hasNext()) {
                sb.append(this.variableString(variableIterator, temp.key()));

            }
        }

        while (iterator.hasNext()) {
            Map.Pair<String, Map<Integer, LinearDouble>> temp = iterator.next();
            Iterator<Pair<Integer, LinearDouble>> variableIterator = temp
                    .value().iterator();

            while (variableIterator.hasNext()) {
                sb.append(this.variableString(variableIterator, temp.key()));
            }

        }

        if (!this.num.isZero()) {
            if (this.num.isPositive()) {
                sb.append(" + " + this.num);
            } else {
                sb.append(" - " + this.num.constant(-1));
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
        Map<String, Map<Integer, LinearDouble>> newMap = new Map1L<String, Map<Integer, LinearDouble>>();
        ;

        for (Map.Pair<String, Map<Integer, LinearDouble>> thisVariable : this.value) {
            for (Map.Pair<String, Map<Integer, LinearDouble>> otherVariable : other.value) {

                Map<Integer, LinearDouble> variableMap = new Map1L<Integer, LinearDouble>();

                if (thisVariable.key().equals(otherVariable.key())) {
                    // They are the same variable

                    for (Map.Pair<Integer, LinearDouble> thisExponent : thisVariable
                            .value()) {
                        for (Map.Pair<Integer, LinearDouble> otherExponent : otherVariable
                                .value()) {

                            if (variableMap.hasKey(
                                    thisExponent.key() + otherExponent.key())) {
                                variableMap.replaceValue(
                                        thisExponent.key()
                                                + otherExponent.key(),
                                        variableMap
                                                .value(thisExponent.key()
                                                        + otherExponent.key())
                                                .add(thisExponent.value()
                                                        .multiply(otherExponent
                                                                .value())));
                            } else {
                                variableMap.add(
                                        thisExponent.key()
                                                + otherExponent.key(),
                                        thisExponent.value().multiply(
                                                otherExponent.value()));
                            }

                        }
                    }

                    newMap.add(thisVariable.key(), variableMap);
                } else {
                    // They are different variables

                    for (Map.Pair<Integer, LinearDouble> thisExponent : thisVariable
                            .value()) {
                        for (Map.Pair<Integer, LinearDouble> otherExponent : otherVariable
                                .value()) {

                            String variableName = "( ";

                            if (thisExponent.key() != 1) {
                                variableName += "( " + thisVariable.key() + "^"
                                        + thisExponent.key() + " )";
                            } else {
                                variableName += thisVariable.key();
                            }
                            variableName += " * ";
                            if (otherExponent.key() != 1) {
                                variableName += "( " + otherVariable.key() + "^"
                                        + otherExponent.key() + " )";
                            } else {
                                variableName += otherVariable.key();
                            }
                            variableName += " )";

                            variableMap.add(1, thisExponent.value()
                                    .multiply(otherExponent.value()));

                            newMap.add(variableName, variableMap);
                        }
                    }

                }
            }

        }

        /*
         * Handle double constant
         */
        if (!other.num.isZero()) {
            for (Map.Pair<String, Map<Integer, LinearDouble>> thisVariable : this.value) {
                for (Map.Pair<Integer, LinearDouble> exponent : thisVariable
                        .value()) {
                    if (newMap.hasKey(thisVariable.key())) {
                        if (newMap.value(thisVariable.key())
                                .hasKey(exponent.key())) {
                            newMap.value(thisVariable.key()).replaceValue(
                                    exponent.key(),
                                    newMap.value(thisVariable.key())
                                            .value(exponent.key())
                                            .add(exponent.value()
                                                    .multiply(other.num)));
                        } else {
                            newMap.value(thisVariable.key()).add(exponent.key(),
                                    exponent.value().multiply(other.num));
                        }
                    } else {
                        Map<Integer, LinearDouble> variableMap = new Map1L<Integer, LinearDouble>();
                        variableMap.add(exponent.key(),
                                exponent.value().multiply(other.num));
                        newMap.add(thisVariable.key(), variableMap);
                    }
                }
            }
        }

        if (!this.num.isZero()) {
            for (Map.Pair<String, Map<Integer, LinearDouble>> otherVariable : other.value) {
                for (Map.Pair<Integer, LinearDouble> exponent : otherVariable
                        .value()) {
                    if (newMap.hasKey(otherVariable.key())) {
                        if (newMap.value(otherVariable.key())
                                .hasKey(exponent.key())) {
                            newMap.value(otherVariable.key()).replaceValue(
                                    exponent.key(),
                                    newMap.value(otherVariable.key())
                                            .value(exponent.key())
                                            .add(exponent.value()
                                                    .multiply(this.num)));
                        } else {
                            newMap.value(otherVariable.key()).add(
                                    exponent.key(),
                                    exponent.value().multiply(this.num));
                        }
                    } else {
                        Map<Integer, LinearDouble> variableMap = new Map1L<Integer, LinearDouble>();
                        variableMap.add(exponent.key(),
                                exponent.value().multiply(this.num));
                        newMap.add(otherVariable.key(), variableMap);
                    }
                }
            }
        }

        return new LinearVariable(newMap, this.num.multiply(other.num));
    }

    /*
     * Other methods -----------------------------------------------------------
     */

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     *
     * @ensures add = this + ( value * name )
     */
    public LinearVariable add(String name, LinearDouble value) {
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

        return new LinearVariable(newMap, this.num);
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     *
     * @ensures add = this + ( value * name )
     */
    public LinearVariable add(String name, double value) {
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

        return new LinearVariable(newMap, this.num);
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     * @param exponent
     *            the exponent value of the variable being added.
     *
     * @ensures add = this + ( value * ( name ^ exponent ) )
     */
    public LinearVariable add(String name, double value, int exponent) {
        /*
         * Copy over the contents of this.value to a new Map
         */
        Map<String, Map<Integer, LinearDouble>> newMap = this.copyValue();
        LinearDouble tempNum = new LinearDouble(this.num);

        if (exponent != 0) {
            // Case for when the variable already exists in the LinearVariable
            if (newMap.hasKey(name)) {
                Map.Pair<String, Map<Integer, LinearDouble>> index = newMap
                        .remove(name);
                if (index.value().hasKey(exponent)) {
                    index.value().replaceValue(exponent,
                            index.value().value(exponent).add(value));
                } else {
                    index.value().add(exponent, new LinearDouble(value));
                }
                newMap.add(name, index.value());
            } else {
                Map<Integer, LinearDouble> temp = new Map1L<Integer, LinearDouble>();
                temp.add(exponent, new LinearDouble(value));
                newMap.add(name, temp);
            }
        } else {
            tempNum = tempNum.add(value);
        }

        return new LinearVariable(newMap, tempNum);
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     * @param exponent
     *            the exponent value of the variable being added.
     *
     * @ensures add = this + ( value * ( name ^ exponent ) )
     */
    public LinearVariable add(String name, LinearDouble value, int exponent) {
        /*
         * Copy over the contents of this.value to a new Map
         */
        Map<String, Map<Integer, LinearDouble>> newMap = this.copyValue();
        LinearDouble tempNum = new LinearDouble(this.num);

        if (exponent != 0) {
            // Case for when the variable already exists in the LinearVariable
            if (newMap.hasKey(name)) {
                Map.Pair<String, Map<Integer, LinearDouble>> index = newMap
                        .remove(name);
                if (index.value().hasKey(exponent)) {
                    index.value().replaceValue(exponent,
                            index.value().value(exponent).add(value));
                } else {
                    index.value().add(exponent, new LinearDouble(value));
                }
                newMap.add(name, index.value());
            } else {
                Map<Integer, LinearDouble> temp = new Map1L<Integer, LinearDouble>();
                temp.add(exponent, new LinearDouble(value));
                newMap.add(name, temp);
            }
        } else {
            tempNum = tempNum.add(value);
        }

        return new LinearVariable(newMap, tempNum);
    }

    /**
     * Add a value to this.
     *
     * @param value
     *            the value being added
     * @return this + value
     */
    public LinearVariable add(double value) {
        return new LinearVariable(this.copyValue(),
                this.num.add(new LinearDouble(value)));
    }

    /**
     * Add a value to this.
     *
     * @param value
     *            the value being added
     * @return this + value
     */
    public LinearVariable add(LinearDouble value) {
        return new LinearVariable(this.copyValue(), this.num.add(value));
    }

    public LinearDouble set(double value) {
        assert this.value.size() <= 1 : "Violation of: There is one variable";

        LinearDouble result = new LinearDouble().add(this.num);
        if (this.value.size() > 0) {
            Pair<String, Map<Integer, LinearDouble>> tempValue = this
                    .copyValue().removeAny();

            for (Map.Pair<Integer, LinearDouble> power : tempValue.value()) {
                result = result.add(power.value().multiply(
                        new LinearDouble(Math.pow(value, power.key()))));
            }
        }

        return result;

    }
}
