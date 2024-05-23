package components.linear;

import java.util.Iterator;

import VariableParser.VariableParser;
import components.map.Map;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.standard.Standard;

/**
 * Linear system represented as an double.
 *
 * @author Wesley Kamau
 */
public final class LinearVariable extends LinearSecondary<LinearVariable> {

    private class Variable implements Standard<Variable> {

        /**
         * Each element in the set represents the product of Variables that make
         * up this.
         */
        Set<Variable> elements;

        boolean isSingleVariable;
        String singleVariableName;
        LinearDouble singleVariableExponent;

        private void createNewRep() {
            this.elements = new Set1L<Variable>();
            this.isSingleVariable = false;
            this.singleVariableName = "";
            this.singleVariableExponent = new LinearDouble();
        }

        Variable() {
            this.createNewRep();
        }

        Variable(String variableName, int power) {
            this.elements = new Set1L<Variable>();
            this.isSingleVariable = true;
            this.singleVariableName = variableName;
            this.singleVariableExponent = new LinearDouble(power);
        }

        Variable(String variableName, double power) {
            this.elements = new Set1L<Variable>();
            this.isSingleVariable = true;
            this.singleVariableName = variableName;
            this.singleVariableExponent = new LinearDouble(power);
        }

        Variable(String variableName, LinearDouble power) {
            this.elements = new Set1L<Variable>();
            this.isSingleVariable = true;
            this.singleVariableName = variableName;
            this.singleVariableExponent = new LinearDouble(power);
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
            if (this.isSingleVariable) {
                this.isSingleVariable = false;
                this.elements.add(new Variable(this.singleVariableName,
                        this.singleVariableExponent));
            }
            if (this.elements.size() == 0) {
                this.isSingleVariable = true;
                this.singleVariableName = variableName;
                this.singleVariableExponent = new LinearDouble(power);
            } else {
                this.elements.add(new Variable(variableName, power));
            }
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
            if (this.isSingleVariable) {
                this.isSingleVariable = false;
                this.elements.add(new Variable(this.singleVariableName,
                        this.singleVariableExponent));
            }
            if (this.elements.size() == 0) {
                this.isSingleVariable = true;
                this.singleVariableName = variableName;
                this.singleVariableExponent = new LinearDouble(power);
            } else {
                this.elements.add(new Variable(variableName, power));
            }
        }

        /**
         * Adds the given variable and exponent to this.
         *
         * @param variableName
         *            the name of the product to add
         * @param power
         *            the power to add
         */
        public void addProduct(String variableName, LinearDouble power) {
            if (this.isSingleVariable) {
                this.isSingleVariable = false;
                this.elements.add(new Variable(this.singleVariableName,
                        this.singleVariableExponent));
            }
            if (this.elements.size() == 0) {
                this.isSingleVariable = true;
                this.singleVariableName = variableName;
                this.singleVariableExponent = new LinearDouble(power);
            } else {
                this.elements.add(new Variable(variableName, power));
            }
        }

        /**
         * Adds the given variable and exponent to this.
         *
         * @param variableName
         *            the name of the product to add
         * @param power
         *            the power to add
         */
        public void addProduct(Variable newVariable) {
            if (this.isSingleVariable) {
                this.isSingleVariable = false;
                this.elements.add(new Variable(this.singleVariableName,
                        this.singleVariableExponent));
            }
            if (this.elements.size() == 0 && newVariable.isSingleVariable) {
                this.isSingleVariable = true;
                this.singleVariableName = newVariable.singleVariableName;
                this.singleVariableExponent = new LinearDouble(
                        newVariable.singleVariableExponent);
            } else {
                if (this.elements.contains(newVariable)) {
                    this.elements.add(this.multiply(newVariable));
                } else {
                    this.elements.add(newVariable);
                }
            }
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (!this.isSingleVariable) {
                // There are variables in the Set, call recursively.
                sb.append("( ");
                for (Variable element : this.elements) {
                    sb.append(element.toString() + " * ");
                }
                sb.delete(sb.length() - 2, sb.length());
                sb.append(")");
            } else {
                // Single Variable.
                sb.append(this.singleVariableName);
                if (!this.singleVariableExponent.isOne()) {
                    sb.append("^" + this.singleVariableExponent);
                }
            }

            return sb.toString();
        }

        public Variable multiply(Variable other) {
            Variable result = new Variable();
            if (this.isSingleVariable) {
                if (other.isSingleVariable) {
                    if (this.singleVariableName
                            .equals(other.singleVariableName)) {
                        // They are the same single variable
                        result.addProduct(this.singleVariableName,
                                this.singleVariableExponent
                                        .add(other.singleVariableExponent));
                    } else {
                        // They are different single variables
                        result.addProduct(this.singleVariableName,
                                this.singleVariableExponent);
                        result.addProduct(other.singleVariableName,
                                other.singleVariableExponent);
                    }
                } else {
                    // this is a single variable, while other is not
                    for (Variable element : other.elements) {
                        result.addProduct(this.multiply(element));
                    }
                }
            } else {
                if (other.isSingleVariable) {
                    // other is a single variable, while this is not
                    for (Variable element : this.elements) {
                        result.addProduct(other.multiply(element));
                    }
                } else {
                    // Neither are single variables
                    for (Variable thisElement : this.elements) {
                        for (Variable otherElement : other.elements) {
                            result.addProduct(
                                    thisElement.multiply(otherElement));
                        }
                    }
                }
            }
            return result;
        }

        public void copyfrom(Variable source) {
            this.elements = source.elements.newInstance();
            for (Variable element : source.elements) {
                if (element.isSingleVariable) {
                    this.elements.add(new Variable(source.singleVariableName,
                            source.singleVariableExponent));
                } else {
                    Variable temp = new Variable();
                    temp.copyfrom(element);
                    this.elements.add(temp);
                }
            }
            this.isSingleVariable = source.isSingleVariable;
            this.singleVariableName = source.singleVariableName;
            this.singleVariableExponent = new LinearDouble(
                    source.singleVariableExponent);
        }

        @Override
        public void clear() {
            this.createNewRep();
        }

        @Override
        public Variable newInstance() {
            return new Variable();
        }

        @Override
        public void transferFrom(Variable source) {
            this.elements = source.elements;
            this.isSingleVariable = source.isSingleVariable;
            this.singleVariableName = source.singleVariableName;
            this.singleVariableExponent = source.singleVariableExponent;
            source.createNewRep();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof Variable)) {
                return false;
            }

            Variable q = (Variable) obj;

            if (this.elements.size() != q.elements.size()) {
                return false;
            }

            if (this.elements.size() > 0) {
                boolean foundEveryElement = true;
                for (Variable element : this.elements) {
                    boolean foundElement = false;
                    for (Variable other : q.elements) {
                        if (element.equals(other)) {
                            foundElement = true;
                        }
                    }
                    if (!foundElement) {
                        foundEveryElement = false;
                    }
                }
                if (!foundEveryElement) {
                    return false;
                }
            } else {
                if (this.isSingleVariable != q.isSingleVariable) {
                    return false;
                }

                if (!this.singleVariableName.equals(q.singleVariableName)) {
                    return false;
                }

                if (!this.singleVariableExponent
                        .equals(q.singleVariableExponent)) {
                    return false;
                }
            }

            return true;
        }

        public boolean isEmpty() {
            return this.equals(new Variable());
        }

        public boolean hasVariableName(String variableName) {
            if (this.isSingleVariable) {
                return this.singleVariableName.equals(variableName);
            } else {
                for (Variable element : this.elements) {
                    if (element.hasVariableName(variableName)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean hasVariable(String variableName, LinearDouble exponent) {
            if (this.isSingleVariable) {
                return this.singleVariableName.equals(variableName)
                        && this.singleVariableExponent.equals(exponent);
            } else {
                for (Variable element : this.elements) {
                    if (element.hasVariableName(variableName)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean hasVariable(String variableName, int exponent) {
            if (this.isSingleVariable) {
                return this.singleVariableName.equals(variableName)
                        && this.singleVariableExponent
                                .equals(new LinearDouble(exponent));
            } else {
                for (Variable element : this.elements) {
                    if (element.hasVariableName(variableName)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean hasVariable(String variableName, double exponent) {
            if (this.isSingleVariable) {
                return this.singleVariableName.equals(variableName)
                        && this.singleVariableExponent
                                .equals(new LinearDouble(exponent));
            } else {
                for (Variable element : this.elements) {
                    if (element.hasVariableName(variableName)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public Variable remove(String variableName) {
            Variable result = this.newInstance();
            if (this.isSingleVariable
                    && this.singleVariableName.equals(variableName)) {
                result.copyfrom(this);
                this.createNewRep();
            } else {
                Set<Variable> tempElements = this.elements.newInstance();
                while (this.elements.size() > 0) {
                    Variable element = this.elements.removeAny();
                    Variable removed = element.remove(variableName);
                    tempElements.add(element);
                    if (!removed.isEmpty()) {
                        result.addProduct(removed);
                    }
                }
                this.elements.transferFrom(tempElements);
            }
            return result;
        }

        public LinearDouble set(String variableName, LinearDouble value) {
            LinearDouble result = new LinearDouble(1.0);
            Variable removed = this.remove(variableName);
            if (!removed.isEmpty()) {
                if (removed.isSingleVariable) {
                    result = result.multiply(
                            value.power(removed.singleVariableExponent));
                } else {
                    result = result.multiply(removed.set(variableName, value));
                }
            }
            return result;
        }

        public Variable power(double p) {
            Variable result = this.newInstance();

            if (this.isSingleVariable) {
                result.addProduct(new Variable(this.singleVariableName,
                        this.singleVariableExponent.multiply(p)));
            } else {
                for (Variable element : this.elements) {
                    result.addProduct(element.power(p));
                }
            }

            return result;
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
     * Private helper methods
     * ----------------------------------------------------------
     */

    /**
     * Sets this to initial value.
     */
    private void createNewRep() {
        this.rep = new Map1L<Variable, LinearDouble>();
        this.num = new LinearDouble();
    }

    private boolean hasVariable(Variable key) {
        for (Map.Pair<Variable, LinearDouble> thisElement : this.rep) {
            if (thisElement.key().equals(key)) {
                return true;
            }
        }
        return false;
    }

    private Map.Pair<Variable, LinearDouble> getElement(Variable key) {
        assert this.hasVariable(key) : "Violation of: Variable is in this";
        for (Map.Pair<Variable, LinearDouble> thisElement : this.rep) {
            if (thisElement.key().equals(key)) {
                return thisElement;
            }
        }
        return null;
    }

    private void add(Map.Pair<Variable, LinearDouble> element) {
        if (this.hasVariable(element.key())) {
            this.rep.replaceValue(this.getElement(element.key()).key(), this
                    .getElement(element.key()).value().add(element.value()));
        } else {
            this.rep.add(element.key(), new LinearDouble(element.value()));
        }
    }

    private void add(Variable product, LinearDouble coefficient) {
        if (this.hasVariable(product)) {
            this.rep.replaceValue(this.getElement(product).key(),
                    this.getElement(product).value().add(coefficient));
        } else {
            this.rep.add(product, new LinearDouble(coefficient));
        }
    }

    private void add(Variable product, double coefficient) {
        if (this.hasVariable(product)) {
            this.rep.replaceValue(this.getElement(product).key(),
                    this.getElement(product).value().add(coefficient));
        } else {
            this.rep.add(product, new LinearDouble(coefficient));
        }
    }

    private static Set<Variable> variableToSingles(Variable parent) {
        Set<Variable> singleVariables = new Set1L<Variable>();
        if (parent.isSingleVariable) {
            singleVariables.add(parent);
        } else {
            for (Variable element : parent.elements) {
                singleVariables.add(variableToSingles(element));
            }
        }
        return singleVariables;
    }

    private static Set<Variable> simplify(Set<Variable> input) {
        Set<Variable> result = input.newInstance();
        while (input.size() > 0) {
            Variable temp = input.removeAny();
        }
        return result;
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
        if (this.rep.size() != q.rep.size()) {
            return false;
        }

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            if (!q.hasVariable(element.key())) {
                return false;
            }
            if (!q.getElement(element.key()).value().equals(element.value())) {
                return false;
            }
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (this.rep.size() == 0) {
            return this.num.toString();
        }

        Iterator<Map.Pair<Variable, LinearDouble>> iterator = this.rep
                .iterator();

        if (iterator.hasNext()) {
            Map.Pair<Variable, LinearDouble> temp = iterator.next();

            if (temp.value().isOne()) {
                sb.append(temp.key());
            } else if (temp.value().isNegativeOne()) {
                sb.append("-" + temp.key());
            } else {
                sb.append(temp.value() + "*" + temp.key());
            }
        }

        while (iterator.hasNext()) {
            Map.Pair<Variable, LinearDouble> temp = iterator.next();
            if (temp.value().isOne()) {
                sb.append(" + " + temp.key());
            } else if (temp.value().isNegativeOne()) {
                sb.append(" - " + temp.key());
            } else if (temp.value().isPositive()) {
                sb.append(" + " + temp.value() + "*" + temp.key());
            } else {
                sb.append(" - " + temp.value().negative() + "*" + temp.key());
            }
        }

        if (!this.num.isZero()) {
            if (this.num.isPositive()) {
                sb.append(" + " + this.num);
            } else {
                sb.append(" - " + this.num.negative());
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
        if (!this.num.isOne()) {
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
    public LinearVariable divide(LinearVariable denominator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LinearVariable multiply(LinearVariable other) {
        LinearVariable result = new LinearVariable();

        for (Map.Pair<Variable, LinearDouble> thisElement : this.rep) {
            for (Map.Pair<Variable, LinearDouble> otherElement : other.rep) {
                result.add(thisElement.key().multiply(otherElement.key()),
                        thisElement.value().multiply(otherElement.value()));
            }
        }

        if (!this.num.isZero()) {
            for (Map.Pair<Variable, LinearDouble> otherElement : other.rep) {
                result.add(otherElement.key(),
                        otherElement.value().multiply(this.num));
            }
        }

        if (!other.num.isZero()) {
            for (Map.Pair<Variable, LinearDouble> otherElement : this.rep) {
                result.add(otherElement.key(),
                        otherElement.value().multiply(other.num));
            }
        }

        return result.add(this.num.multiply(other.num));

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
     * @ensures add = this + value
     */
    public LinearVariable add(String name) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, 1), 1);

        return result;
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
    public LinearVariable add(LinearDouble value, String name) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, 1), value);

        return result;
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
    public LinearVariable add(double value, String name) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, 1), value);

        return result;
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
    public LinearVariable add(int value, String name) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, 1), value);

        return result;
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     * @param d
     *            the exponent value of the variable being added.
     *
     * @ensures add = this + ( value * ( name ^ exponent ) )
     */
    public LinearVariable add(double value, String name, double d) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, d), value);

        return result;
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     * @param d
     *            the exponent value of the variable being added.
     *
     * @ensures add = this + ( value * ( name ^ exponent ) )
     */
    public LinearVariable add(double value, String name, int d) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, d), value);

        return result;
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     * @param d
     *            the exponent value of the variable being added.
     *
     * @ensures add = this + ( value * ( name ^ exponent ) )
     */
    public LinearVariable add(int value, String name, double d) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, d), value);

        return result;
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     * @param d
     *            the exponent value of the variable being added.
     *
     * @ensures add = this + ( value * ( name ^ exponent ) )
     */
    public LinearVariable add(int value, String name, int d) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, d), value);

        return result;
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     * @param d
     *            the exponent value of the variable being added.
     *
     * @ensures add = this + ( value * ( name ^ exponent ) )
     */
    public LinearVariable add(LinearDouble value, String name, LinearDouble d) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, d), value);

        return result;
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     * @param d
     *            the exponent value of the variable being added.
     *
     * @ensures add = this + ( value * ( name ^ exponent ) )
     */
    public LinearVariable add(LinearDouble value, String name, int d) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, d), value);

        return result;
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     * @param d
     *            the exponent value of the variable being added.
     *
     * @ensures add = this + ( value * ( name ^ exponent ) )
     */
    public LinearVariable add(int value, String name, LinearDouble d) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, d), value);

        return result;
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     *
     * @ensures add = this + ( name ^ value )
     */
    public LinearVariable add(String name, int value) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, value), 1);

        return result;
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     *
     * @ensures add = this + ( name ^ value )
     */
    public LinearVariable add(String name, double value) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, value), 1);

        return result;
    }

    /**
     * Add a variable and value to this.
     *
     * @param name
     *            the Variable to be added
     * @param value
     *            the value of the variable being added
     *
     * @ensures add = this + ( name ^ value )
     */
    public LinearVariable add(String name, LinearDouble value) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.add(new Variable(name, value), 1);

        return result;
    }

    @Override
    public LinearVariable add(double value) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.num = this.num.add(value);

        return result;
    }

    @Override
    public LinearVariable add(int value) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.num = this.num.add(value);

        return result;
    }

    /**
     * Add a value to this.
     *
     * @param value
     *            the value being added
     * @return this + value
     */
    public LinearVariable add(LinearDouble value) {
        LinearVariable result = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            result.add(element);
        }

        result.num = result.num.add(value);

        return result;
    }

    private boolean hasVariableName(String variableName) {
        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            if (element.key().hasVariableName(variableName)) {
                return true;
            }
        }
        return false;
    }

    public LinearDouble set(String vairableName, double value) {
        assert this.hasVariableName(
                vairableName) : "Violation of : This has a variable named"
                        + vairableName;

        // TODO - Method to set the value of one of the variables
        return null;
    }

    public LinearVariable power(int p) {
        LinearVariable result = this.newInstance();
        LinearVariable temp = this.newInstance();

        for (Map.Pair<Variable, LinearDouble> element : this.rep) {
            temp.add(element);
            result.add(element);
        }

        for (int i = 1; i < p; i++) {
            result = result.multiply(temp);
        }

        result.num = result.num.power(p);

        return result;
    }


    @Override
    public int compareTo(LinearVariable o) {
        // TODO Auto-generated method stub
        return 0;
    }

}
