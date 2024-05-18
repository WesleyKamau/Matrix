package components.linear;

import java.util.Iterator;

import VariableParser.VariableParser;
import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;

/**
 * Linear system represented as an double.
 *
 * @author Wesley Kamau
 */
public final class LinearVariable extends LinearSecondary<LinearVariable> {

    private class Variable {

        /**
         * Each Map.Pair : value represents: <Variable, LinearDouble> =
         * LinearDouble(Variable)
         */
        Map<String, LinearDouble> elements;

        Variable(String variableName, int power) {
            this.elements = new Map1L<String, LinearDouble>();
            this.elements.add(variableName, new LinearDouble(power));
        }

        Variable(String variableName, double power) {
            this.elements = new Map1L<String, LinearDouble>();
            this.elements.add(variableName, new LinearDouble(power));
        }

        /**
         * Adds the given variable and exponent to this.
         *
         * @param variableName
         *            the name of the product to add
         * @param power
         *            the power to add
         */
        public void addProduct(String variableName, int power) {
            this.elements.add(variableName, new LinearDouble(power));
        }

        /**
         * Adds the given variable and exponent to this.
         *
         * @param variableName
         *            the name of the product to add
         * @param power
         *            the power to add
         */
        public void addProduct(String variableName, double power) {
            this.elements.add(variableName, new LinearDouble(power));
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (this.elements.size() > 1) {
                sb.append("( ");
            }

            Iterator<Map.Pair<String, LinearDouble>> elementIterator = this.elements
                    .iterator();

            while (elementIterator.hasNext()) {
                Map.Pair<String, LinearDouble> element = elementIterator.next();
                sb.append(element.key());
                if (!element.value().isOne()) {
                    sb.append("^" + element.value());
                }
                if (elementIterator.hasNext()) {
                    sb.append(" * ");
                }
            }

            if (this.elements.size() > 1) {
                sb.append(" )");
            }
            return sb.toString();
        }
    }

    /*
     * Private members
     */

    /**
     * Each Map.Pair : value represents: <Variable, LinearDouble> =
     * LinearDouble(Variable)
     */
    private Map<Variable, LinearDouble> rep;

    /**
     * Nonvariable value as a LinearDouble.
     */
    private LinearDouble num;

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
     * @param s
     *            the string representation of the LinearVariable
     */
    LinearVariable(String s) {
        LinearVariable temp = VariableParser.parseExpr(s);
        this.rep = temp.rep;
        this.num = temp.num;
    }

    /*
     * Helper methods ----------------------------------------------------------
     */

    /**
     * Sets this to initial value.
     */
    private void createNewRep() {
        this.rep = new Map1L<Variable, LinearDouble>();
        this.num = new LinearDouble();
    }

    /*
     * Standard methods --------------------------------------------------------
     */

    @Override
    public LinearVariable add(LinearVariable other) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        for (Map.Pair<Variable, LinearDouble> element : other.rep) {
            result.add(element);
        }

        return result.add(this.num.add(other.num));
    }

    @Override
    public LinearVariable multiply(int c) {
        LinearVariable result = this.newInstance();
        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element.key(), element.value().multiply(c));
        }
        return result.add(this.num.multiply(c));
    }

    @Override
    public LinearVariable multiply(double c) {
        LinearVariable result = this.newInstance();
        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element.key(), element.value().multiply(c));
        }
        return result.add(this.num.multiply(c));
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
        if (!this.rep.equals(q.rep)) {
            return false;
        }

        if (!this.num.equals(q.num)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return this.rep.hashCode() + this.num.hashCode();
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
                result += exponent.value().multiply(coefficient) + "*";
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

        Iterator<Pair<String, Map<Integer, LinearDouble>>> iterator = this.rep
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
                sb.append(" - " + this.num.multiply(-1));
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
        this.rep = source.rep;
        this.num = source.num;
        source.createNewRep();
    }

    @Override
    public void clear() {
        this.createNewRep();
    }

    @Override
    public boolean isZero() {
        if (!this.num.isZero()) {
            return false;
        }
        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            if (!element.value().isZero()) {
                return false;
            }
        }
        return true;
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

        for (Map.Pair<String, Map<Integer, LinearDouble>> thisVariable : this.rep) {
            for (Map.Pair<String, Map<Integer, LinearDouble>> otherVariable : other.rep) {

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

                    if (newMap.hasKey(thisVariable.key())) {
                        for (Map.Pair<Integer, LinearDouble> element : newMap
                                .value(thisVariable.key())) {
                            if (variableMap.hasKey(element.key())) {
                                variableMap.replaceValue(element.key(),
                                        variableMap.value(element.key())
                                                .add(element.value()));
                            } else {
                                variableMap.add(element.key(), element.value());
                            }
                        }

                        newMap.replaceValue(thisVariable.key(), variableMap);
                    } else {
                        newMap.add(thisVariable.key(), variableMap);
                    }

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

                            if (variableMap.hasKey(1)) {
                                variableMap.replaceValue(1, variableMap.value(1)
                                        .add(thisExponent.value().multiply(
                                                otherExponent.value())));
                            } else {
                                variableMap.add(1, thisExponent.value()
                                        .multiply(otherExponent.value()));
                            }

                            if (newMap.hasKey(variableName)) {

                                for (Map.Pair<Integer, LinearDouble> element : newMap
                                        .value(variableName)) {
                                    if (variableMap.hasKey(element.key())) {
                                        variableMap.replaceValue(element.key(),
                                                variableMap.value(element.key())
                                                        .add(element.value()));
                                    } else {
                                        variableMap.add(element.key(),
                                                element.value());
                                    }
                                }

                                newMap.replaceValue(variableName, variableMap);
                            } else {
                                newMap.add(variableName, variableMap);
                            }

                        }
                    }

                }
            }

        }

        /*
         * Handle double constant
         */
        if (!other.num.isZero()) {
            for (Map.Pair<String, Map<Integer, LinearDouble>> thisVariable : this.rep) {
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
            for (Map.Pair<String, Map<Integer, LinearDouble>> otherVariable : other.rep) {
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
    @Override
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
        assert this.rep.size() <= 1 : "Violation of: There is one variable";

        LinearDouble result = new LinearDouble().add(this.num);
        if (this.rep.size() > 0) {
            Pair<String, Map<Integer, LinearDouble>> tempValue = this
                    .copyValue().removeAny();

            for (Map.Pair<Integer, LinearDouble> power : tempValue.value()) {
                result = result.add(power.value().multiply(
                        new LinearDouble(Math.pow(value, power.key()))));
            }
        }

        return result;

    }

    private void add(Map.Pair<Variable, LinearDouble> element) {
        if (this.rep.hasKey(element.key())) {
            this.rep.replaceValue(element.key(),
                    this.rep.value(element.key()).add(element.value()));
        } else {
            this.rep.add(element.key(), element.value());
        }
    }

    private void add(Variable product, LinearDouble coefficient) {
        if (this.rep.hasKey(product)) {
            this.rep.replaceValue(product,
                    this.rep.value(product).add(coefficient));
        } else {
            this.rep.add(product, coefficient);
        }
    }

    private void add(Variable product, double coefficient) {
        if (this.rep.hasKey(product)) {
            this.rep.replaceValue(product,
                    this.rep.value(product).add(coefficient));
        } else {
            this.rep.add(product, new LinearDouble(coefficient));
        }
    }

    @Override
    public LinearVariable add(int other) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int compareTo(LinearVariable o) {
        // TODO Auto-generated method stub
        return 0;
    }
}
