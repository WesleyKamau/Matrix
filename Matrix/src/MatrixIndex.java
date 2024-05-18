import VariableParser.VariableParser;
import components.linear.LinearDouble;
import components.linear.LinearInteger;
import components.linear.LinearNaturalNumber;
import components.linear.LinearVariable;
import components.map.Map;
import components.map.Map1L;
import components.matrix.Matrix;
import components.matrix.Matrix2;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Helper class that holds several different Maps of matrices of different
 * kinds.
 *
 * @author Wesley Kamau
 *
 */
public final class MatrixIndex {

    enum Kind {
        Double, Integer, NaturalNumber, Variable
    }

    /**
     * Maps holding the double matrices.
     */
    private Map<String, Matrix<LinearDouble>> doubleMap;

    /**
     * Maps holding the integer matrices.
     */
    private Map<String, Matrix<LinearInteger>> integerMap;

    /**
     * Maps holding the NaturalNumber matrices.
     */
    private Map<String, Matrix<LinearNaturalNumber>> nnMap;

    /**
     * Maps holding the Variable matrices.
     */
    private Map<String, Matrix<LinearVariable>> variableMap;

    /**
     * Map that holds the matrix names and kinds.
     */
    private Map<String, Kind> names;

    /**
     * Number of matrices in this.
     */
    private int size;

    /**
     * No argument constructor--private to prevent instantiation.
     */
    MatrixIndex() {
        this.createNewRep();
    }

    private void createNewRep() {
        this.doubleMap = new Map1L<String, Matrix<LinearDouble>>();
        this.integerMap = new Map1L<String, Matrix<LinearInteger>>();
        this.nnMap = new Map1L<String, Matrix<LinearNaturalNumber>>();
        this.variableMap = new Map1L<String, Matrix<LinearVariable>>();
        this.names = new Map1L<String, Kind>();
    }

    /**
     * Adds s and matrix into this.
     *
     * @param s
     *            the name of the matrix to be added
     * @param matrix
     *            a matrix of LinearDoubles
     */
    public void addDoubleMatrix(String s, Matrix<LinearDouble> matrix) {
        assert !this.names.hasKey(s) : "Violation of: " + s + " is in this";

        this.names.add(s, Kind.Double);
        this.doubleMap.add(s, matrix);
        this.size++;
    }

    /**
     * Adds s and matrix into this.
     *
     * @param s
     *            the name of the matrix to be added
     * @param matrix
     *            a matrix of LinearIntegers
     */
    public void addIntegerMatrix(String s, Matrix<LinearInteger> matrix) {
        assert !this.names.hasKey(s) : "Violation of: " + s + " is in this";

        this.names.add(s, Kind.Integer);
        this.integerMap.add(s, matrix);
        this.size++;
    }

    /**
     * Adds s and matrix into this.
     *
     * @param s
     *            the name of the matrix to be added
     * @param matrix
     *            a matrix of LinearNaturalNumbers
     */
    public void addNaturalNumberMatrix(String s,
            Matrix<LinearNaturalNumber> matrix) {
        assert !this.names.hasKey(s) : "Violation of: " + s + " is in this";

        this.names.add(s, Kind.NaturalNumber);
        this.nnMap.add(s, matrix);
        this.size++;
    }

    /**
     * Adds s and matrix into this.
     *
     * @param s
     *            the name of the matrix to be added
     * @param matrix
     *            a matrix of LinearVariables
     */
    public void addVariableMatrix(String s, Matrix<LinearVariable> matrix) {
        assert !this.names.hasKey(s) : "Violation of: " + s + " is in this";

        this.names.add(s, Kind.Variable);
        this.variableMap.add(s, matrix);
        this.size++;
    }

    /**
     * Finds and returns the matrix of LinearDoubles with the name s.
     *
     * @param s
     *            the name of the matrix
     * @return the Matrix s
     */
    public Matrix<LinearDouble> getDoubleMatrix(String s) {
        assert this.names.hasKey(
                s) : "Violation of: there is a Matrix with the name" + s;
        assert this.doubleMap.hasKey(s) : "Violation of: " + s
                + " is a Matrix of LinearDoubles";

        return this.doubleMap.value(s);
    }

    /**
     * Finds and returns the matrix of LinearIntegers with the name s.
     *
     * @param s
     *            the name of the matrix
     * @return the Matrix s
     */
    public Matrix<LinearInteger> getIntegerMatrix(String s) {
        assert this.names.hasKey(
                s) : "Violation of: there is a Matrix with the name" + s;
        assert this.integerMap.hasKey(s) : "Violation of: " + s
                + " is a Matrix of LinearDoubles";

        return this.integerMap.value(s);
    }

    /**
     * Finds and returns the matrix of LinearNaturalNumbers with the name s.
     *
     * @param s
     *            the name of the matrix
     * @return the Matrix s
     */
    public Matrix<LinearNaturalNumber> getNaturalNumberMatrix(String s) {
        assert this.names.hasKey(
                s) : "Violation of: there is a Matrix with the name" + s;
        assert this.nnMap.hasKey(s) : "Violation of: " + s
                + " is a Matrix of LinearDoubles";

        return this.nnMap.value(s);
    }

    /**
     * Finds and returns the matrix of LinearVariables with the name s.
     *
     * @param s
     *            the name of the matrix
     * @return the Matrix s
     */
    public Matrix<LinearVariable> getVariableMatrix(String s) {
        assert this.names.hasKey(
                s) : "Violation of: there is a Matrix with the name" + s;
        assert this.variableMap.hasKey(s) : "Violation of: " + s
                + " is a Matrix of LinearDoubles";

        return this.variableMap.value(s);
    }

    /**
     * Reports if there is a matrix with the name s in this.
     *
     * @param s
     *            name of a matrix
     * @return true if it already exists in this.
     */
    public boolean hasName(String s) {
        return this.names.hasKey(s);
    }

    /**
     * Determines the number of matrices in this.
     *
     * @return the number of matrices in this
     */
    public int size() {
        return this.size;
    }

    /**
     * Finds and returns the kind of the matrix s.
     *
     * @param s
     *            the name of the matrix.
     * @return the kind of the matrix s
     */
    public Kind getKind(String s) {
        assert this.names.hasKey(s) : "Violation of: there is a matrix s";
        return this.names.value(s);
    }

    /**
     * Returns the maximum size of Matrices of compatible types (Double,Integer,
     * and NaturalNumber).
     *
     * @return the maximum size of Matrices of compatible types
     */
    public int maxCompatibleSize() {
        return Integer
                .max(Integer.max(this.doubleMap.size() + this.integerMap.size(),
                        this.nnMap.size()), this.variableMap.size());
    }

    /**
     * Returns the count of matrices of a specific Kind.
     *
     * @param k
     *            the kind of matrices
     * @return the count of matrices of that Kind
     */
    public int kindSize(Kind k) {
        switch (k) {
            case Double:
                return this.doubleMap.size();
            case Integer:
                return this.integerMap.size();
            case NaturalNumber:
                return this.nnMap.size();
            case Variable:
                return this.variableMap.size();
            default:
                return 0;
        }
    }

    /**
     * Prints out all of the matrices in the index.
     *
     * @param out
     *            the output stream
     */
    public void printMatrices(SimpleWriter out) {

        if (this.doubleMap.size() > 0) {
            out.println("Matrices of Doubles:");
            out.println();
        }
        for (Map.Pair<String, Matrix<LinearDouble>> element : this.doubleMap) {
            out.println(element.key());
            out.println();
            element.value().print(out);
            out.println();
        }

        if (this.integerMap.size() > 0) {
            out.println("Matrices of Integers:");
            out.println();
        }
        for (Map.Pair<String, Matrix<LinearInteger>> element : this.integerMap) {
            out.println(element.key());
            out.println();
            element.value().print(out);
            out.println();
        }

        if (this.nnMap.size() > 0) {
            out.println("Matrices of NaturalNumbers:");
            out.println();
        }
        for (Map.Pair<String, Matrix<LinearNaturalNumber>> element : this.nnMap) {
            out.println(element.key());
            out.println();
            element.value().print(out);
            out.println();
        }

        if (this.variableMap.size() > 0) {
            out.println("Matrices of Variables:");
            out.println();
        }
        for (Map.Pair<String, Matrix<LinearVariable>> element : this.variableMap) {
            out.println(element.key());
            out.println();
            element.value().print(out);
            out.println();
        }
    }

    /**
     * Gets the rows of a given matrix s.
     *
     * @param s
     *            the name of a matrix in this.
     * @return the number of rows of the matrix.
     */
    public int getMatrixRows(String s) {
        assert this.names.hasKey(s) : "Violation of: s is in this";
        switch (this.names.value(s)) {
            case Double:
                return this.doubleMap.value(s).rows();
            case Integer:
                return this.integerMap.value(s).rows();
            case NaturalNumber:
                return this.nnMap.value(s).rows();
            case Variable:
                return this.variableMap.value(s).rows();
            default:
                return 0; // Will never happen
        }
    }

    /**
     * Gets the columns of a given matrix s.
     *
     * @param s
     *            the name of a matrix in this.
     * @return the number of columns of the matrix.
     */
    public int getMatrixColumns(String s) {
        assert this.names.hasKey(s) : "Violation of: s is in this";
        switch (this.names.value(s)) {
            case Double:
                return this.doubleMap.value(s).columns();
            case Integer:
                return this.integerMap.value(s).columns();
            case NaturalNumber:
                return this.nnMap.value(s).columns();
            case Variable:
                return this.variableMap.value(s).columns();
            default:
                return 0; // Will never happen
        }
    }

    public MatrixIndex reducedMatrices() {

        MatrixIndex result = new MatrixIndex();

        for (Map.Pair<String, Matrix<LinearDouble>> element : this.doubleMap) {
            if (element.value().isRREF()) {
                result.addDoubleMatrix(element.key(), element.value());
            }
        }

        for (Map.Pair<String, Matrix<LinearInteger>> element : this.integerMap) {
            if (element.value().isRREF()) {
                result.addIntegerMatrix(element.key(), element.value());
            }
        }

        for (Map.Pair<String, Matrix<LinearNaturalNumber>> element : this.nnMap) {
            if (element.value().isRREF()) {
                result.addNaturalNumberMatrix(element.key(), element.value());
            }
        }

        return result;
    }

    public void save(String filename) {
        SimpleWriter file = new SimpleWriter1L(filename);

        for (Map.Pair<String, Matrix<LinearDouble>> element : this.doubleMap) {
            file.println(this.names.value(element.key()));
            file.println(element.key());
            file.println(element.value().rows());
            file.println(element.value().columns());
            for (LinearDouble value : element.value()) {
                file.println(value);
            }
        }

        for (Map.Pair<String, Matrix<LinearInteger>> element : this.integerMap) {
            file.println(this.names.value(element.key()));
            file.println(element.key());
            file.println(element.value().rows());
            file.println(element.value().columns());
            for (LinearInteger value : element.value()) {
                file.println(value);
            }
        }

        for (Map.Pair<String, Matrix<LinearNaturalNumber>> element : this.nnMap) {
            file.println(this.names.value(element.key()));
            file.println(element.key());
            file.println(element.value().rows());
            file.println(element.value().columns());
            for (LinearNaturalNumber value : element.value()) {
                file.println(value);
            }
        }

        for (Map.Pair<String, Matrix<LinearVariable>> element : this.variableMap) {
            file.println(this.names.value(element.key()));
            file.println(element.key());
            file.println(element.value().rows());
            file.println(element.value().columns());
            for (LinearVariable value : element.value()) {
                file.println(value);
            }
        }

        file.close();

    }

    public void open(String filename) {
        this.createNewRep();
        SimpleReader file = new SimpleReader1L(filename);

        while (!file.atEOS()) {

            Kind matrixKind = Kind.valueOf(file.nextLine());
            String name = file.nextLine();
            int rows = file.nextInteger();
            int columns = file.nextInteger();

            switch (matrixKind) {
                case Double: {
                    Matrix<LinearDouble> temp = new Matrix2<LinearDouble>();
                    for (int i = 1; i <= rows; i++) {
                        for (int j = 1; j <= columns; j++) {
                            temp.setElement(i, j,
                                    new LinearDouble(file.nextDouble()));
                        }
                    }
                    this.addDoubleMatrix(name, temp);
                    break;
                }
                case Integer: {
                    Matrix<LinearInteger> temp = new Matrix2<LinearInteger>();
                    for (int i = 1; i <= rows; i++) {
                        for (int j = 1; j <= columns; j++) {
                            temp.setElement(i, j,
                                    new LinearInteger(file.nextInteger()));
                        }
                    }
                    this.addIntegerMatrix(name, temp);
                    break;
                }
                case NaturalNumber: {
                    Matrix<LinearNaturalNumber> temp = new Matrix2<LinearNaturalNumber>();
                    for (int i = 1; i <= rows; i++) {
                        for (int j = 1; j <= columns; j++) {
                            temp.setElement(i, j, new LinearNaturalNumber(
                                    new NaturalNumber1L(file.nextLine())));
                        }
                    }
                    this.addNaturalNumberMatrix(name, temp);
                    break;
                }
                case Variable: {
                    Matrix<LinearVariable> temp = new Matrix2<LinearVariable>();
                    for (int i = 1; i <= rows; i++) {
                        for (int j = 1; j <= columns; j++) {
                            temp.setElement(i, j,
                                    VariableParser.parseExpr(file.nextLine()));
                        }
                    }
                    this.addVariableMatrix(name, temp);
                    break;
                }
                default:
                    break; // will never happen
            }

        }

        file.close();
    }

}
