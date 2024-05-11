import components.linear.LinearDouble;
import components.linear.LinearInteger;
import components.linear.LinearNaturalNumber;
import components.linear.LinearVariable;
import components.map.Map;
import components.map.Map1L;
import components.matrix.Matrix;
import components.matrix.Matrix2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * User-friendly Matrix Interface.
 *
 * @author Wesley Kamau
 *
 */
public final class UserMatrix {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private UserMatrix() {
    }

    /**
     * Prompts the user for a valid Matrix Name and then adds it to the index.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param dex
     *            the index to update
     */
    private static void newMatrix(SimpleWriter out, SimpleReader in,
            MatrixIndex dex) {
        out.println();
        out.print("Enter a name: ");
        String matrixName = in.nextLine();

        while (dex.hasName(matrixName)) {
            out.print(matrixName + " already exists. Enter a new name: ");
            matrixName = in.nextLine();
        }
    }

    private static boolean isEnum(String s) {
        for (MatrixIndex.Kind temp : MatrixIndex.Kind.values()) {
            if (temp.toString().equals(s)) {
                return true;
            }
        }
        return false;
    }

    private static void getUserMatrix(SimpleReader in, SimpleWriter out,
            MatrixIndex dex) {

        out.print("Enter how many rows: ");
        String tempVariables = in.nextLine();
        while (!FormatChecker.canParseInt(tempVariables)
                && Integer.parseInt(tempVariables) >= 1) {
            out.print("Error. Enter how many variables: ");
            tempVariables = in.nextLine();
        }
        int rows = Integer.parseInt(tempVariables);

        out.print("Enter how many Columns: ");
        tempVariables = in.nextLine();
        while (!FormatChecker.canParseInt(tempVariables)
                && Integer.parseInt(tempVariables) >= 1) {
            out.print("Error. Enter how many variables: ");
            tempVariables = in.nextLine();
        }
        int columns = Integer.parseInt(tempVariables);

        out.print("What kind of Linear variable: ");
        tempVariables = in.nextLine();
        while (!isEnum(tempVariables)) {
            out.print("Error. Enter the kind of Linear variable: ");
            tempVariables = in.nextLine();
        }
        MatrixIndex.Kind variableKind = MatrixIndex.Kind.valueOf(tempVariables);

        out.print("Enter a name for your new matrix: ");
        String matrixName = getNewName(out, in, dex);

        switch (variableKind) {
            case Double: {
                Matrix<LinearDouble> result = new Matrix2<LinearDouble>();

                for (int i = 1; i <= rows; i++) {
                    for (int j = 1; j <= columns; j++) {

                        out.print("Enter the element (" + i + ", " + j + "): ");
                        tempVariables = in.nextLine();
                        while (!FormatChecker.canParseDouble(tempVariables)) {
                            out.print("Error. Enter the element (" + i + ", "
                                    + j + "): ");
                            tempVariables = in.nextLine();
                        }
                        result.setElement(i, j, new LinearDouble(
                                Double.parseDouble(tempVariables)));
                    }
                }

                dex.addDoubleMatrix(matrixName, result);

                break;

            }
            case Integer: {
                Matrix<LinearInteger> result = new Matrix2<LinearInteger>();

                for (int i = 1; i <= rows; i++) {
                    for (int j = 1; j <= columns; j++) {

                        out.print("Enter the element (" + i + ", " + j + "): ");
                        tempVariables = in.nextLine();
                        while (!FormatChecker.canParseInt(tempVariables)) {
                            out.print("Error. Enter the element (" + i + ", "
                                    + j + "): ");
                            tempVariables = in.nextLine();
                        }
                        result.setElement(i, j, new LinearInteger(
                                Integer.parseInt(tempVariables)));

                    }
                }

                dex.addIntegerMatrix(matrixName, result);

                break;

            }
            case NaturalNumber: {
                Matrix<LinearNaturalNumber> result = new Matrix2<LinearNaturalNumber>();

                for (int i = 1; i <= rows; i++) {
                    for (int j = 1; j <= columns; j++) {

                        out.print("Enter the element (" + i + ", " + j + "): ");
                        tempVariables = in.nextLine();
                        while (!FormatChecker.canParseInt(tempVariables)
                                || Integer.parseInt(tempVariables) <= 0) {
                            out.print("Error. Enter the element (" + i + ", "
                                    + j + "): ");
                            tempVariables = in.nextLine();
                        }
                        result.setElement(i, j, new LinearNaturalNumber(
                                Integer.parseInt(tempVariables)));

                    }
                }

                dex.addNaturalNumberMatrix(matrixName, result);

                break;
            }
            case Variable: {
                Matrix<LinearVariable> result = new Matrix2<LinearVariable>();

                for (int i = 1; i <= rows; i++) {
                    for (int j = 1; j <= columns; j++) {

                        out.print("Enter the element (" + i + ", " + j + "): ");
                        tempVariables = in.nextLine();
                        while (!VariableParser.isValidTerm(tempVariables)) {
                            out.print("Error. Enter the element (" + i + ", "
                                    + j + "): ");
                            tempVariables = in.nextLine();
                        }
                        result.setElement(i, j,
                                VariableParser.parseExpr(tempVariables));

                    }
                }

                dex.addVariableMatrix(matrixName, result);

                break;
            }
            default: {
                break;
            }
        }

    }

    /**
     * Prints out all of the matrices in the index.
     *
     * @param out
     *            the output stream
     * @param index
     *            the Map containing all the Matrices and their names.
     */
    private static void printMatrices(SimpleWriter out,
            Map<String, Matrix<LinearDouble>> index) {

        for (Map.Pair<String, Matrix<LinearDouble>> element : index) {
            out.println(element.key());
            element.value().print(out);
            out.println();
        }
    }

    /**
     * Prompts the user for a valid Matrix Name and then adds it to the index.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param index
     *            the index to update
     */
    private static void newMatrix(SimpleWriter out, SimpleReader in,
            Map<String, Matrix<LinearDouble>> index) {
        out.println();
    }

    private static String getExistingName(SimpleWriter out, SimpleReader in,
            MatrixIndex dex) {
        String a = in.nextLine();
        while (!dex.hasName(a)) {
            out.print(a + " doesn't exist. Enter a new name: ");
            a = in.nextLine();
        }
        return a;
    }

    private static String getNewName(SimpleWriter out, SimpleReader in,
            MatrixIndex dex) {
        String matrixName = in.nextLine();

        while (dex.hasName(matrixName)) {
            out.print(matrixName + " already exists. Enter a new name: ");
            matrixName = in.nextLine();
        }

        return matrixName;
    }

    private static boolean isValidPair(String a, String b, MatrixIndex dex) {
        boolean validPair = false;

        if (dex.getKind(a).equals(MatrixIndex.Kind.Double)
                || dex.getKind(a).equals(MatrixIndex.Kind.Integer)) {
            // a is a compatible number

            if (dex.getKind(a).equals(MatrixIndex.Kind.Double)
                    || dex.getKind(a).equals(MatrixIndex.Kind.Integer)) {
                validPair = true;
            }
        } else if (dex.getKind(a).equals(MatrixIndex.Kind.NaturalNumber)) {
            // a is a Naturalnumber

            if (dex.getKind(b).equals(MatrixIndex.Kind.NaturalNumber)) {
                validPair = true;
            }

        } else {
            // a is a Variable
            if (dex.getKind(b).equals(MatrixIndex.Kind.Variable)) {
                validPair = true;
            }
        }

        return validPair;
    }

    /**
     * Prompts the user for valid Matrices and then adds the augmented matrix to
     * the index.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param dex
     *            the index to update
     */
    private static void augmentMatrix(SimpleWriter out, SimpleReader in,
            MatrixIndex dex) {
        minimumCompatibleMatrix(out, in, dex, 2);

        out.println("Current Matrices: ");
        dex.printMatrices(out);

        out.print("Enter the name of the matrix on the left:");
        String a = getExistingName(out, in, dex);

        out.print("Enter the name of the matrix on the right:");
        String b = getExistingName(out, in, dex);

        if (isValidPair(a, b, dex)) {
            out.print("Enter a name for your Augmented matrix: ");
            String matrixName = getNewName(out, in, dex);

            switch (dex.getKind(a)) {
                case Double: {

                    Matrix<LinearDouble> matrixA = dex.getDoubleMatrix(a);

                    switch (dex.getKind(b)) {
                        case Double: {
                            Matrix<LinearDouble> matrixB = dex
                                    .getDoubleMatrix(b);

                            dex.addDoubleMatrix(matrixName,
                                    matrixA.augment(matrixB));
                            break;
                        }
                        case Integer: {

                            Matrix<LinearInteger> matrixB = dex
                                    .getIntegerMatrix(a);
                            dex.addDoubleMatrix(matrixName, matrixA.augment(
                                    MatrixHelper.intToDouble(matrixB)));

                            break;
                        }
                        default:
                            break; // Will never happen, isValidPair
                    }
                    break;
                }
                case Integer: {

                    Matrix<LinearInteger> matrixA = dex.getIntegerMatrix(a);

                    switch (dex.getKind(b)) {
                        case Double: {

                            Matrix<LinearDouble> matrixB = dex
                                    .getDoubleMatrix(b);
                            dex.addDoubleMatrix(matrixName, MatrixHelper
                                    .intToDouble(matrixA).augment(matrixB));

                            break;
                        }
                        case Integer: {

                            Matrix<LinearInteger> matrixB = dex
                                    .getIntegerMatrix(a);
                            dex.addIntegerMatrix(matrixName,
                                    matrixA.augment(matrixB));

                            break;
                        }
                        default:
                            break; // Will never happen, isValidPair
                    }
                    break;
                }
                case NaturalNumber: {

                    Matrix<LinearNaturalNumber> matrixA = dex
                            .getNaturalNumberMatrix(a);
                    Matrix<LinearNaturalNumber> matrixB = dex
                            .getNaturalNumberMatrix(a);
                    dex.addNaturalNumberMatrix(matrixName,
                            matrixA.augment(matrixB));

                    break;
                }
                case Variable: {

                    Matrix<LinearVariable> matrixA = dex.getVariableMatrix(a);
                    Matrix<LinearVariable> matrixB = dex.getVariableMatrix(b);
                    dex.addVariableMatrix(matrixName, matrixA.augment(matrixB));

                    break;
                }
                default:
                    break; // Will never happen
            }
        } else {

        }

    }

    /**
     * Prompts the user for a valid Matrix and then adds the reduced matrix to
     * the index.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param dex
     *            the index to update
     */
    private static void reduceMatrix(SimpleWriter out, SimpleReader in,
            MatrixIndex dex) {
        minimumNonVariableMatrix(out, in, dex, 1);

        out.println("Current Matrices: ");
        dex.printMatrices(out);

        out.print("Enter the name of the matrix to reduce:");
        String matrixName = getExistingName(out, in, dex);

        while (dex.getKind(matrixName).equals(MatrixIndex.Kind.Variable)) {
            out.print(
                    "Variables are not compatible for reduction. Enter the name of the matrix to reduce:");
            matrixName = getExistingName(out, in, dex);
        }

        out.print("Enter a name for your reduced matrix: ");
        String reduced = getNewName(out, in, dex);

        switch (dex.getKind(matrixName)) {
            case Double: {

                Matrix<LinearDouble> matrix = dex.getDoubleMatrix(matrixName);
                dex.addDoubleMatrix(reduced, matrix.reduce());

                break;
            }
            case Integer: {

                Matrix<LinearInteger> matrix = dex.getIntegerMatrix(matrixName);
                dex.addIntegerMatrix(reduced, matrix.reduce());

                break;
            }
            case NaturalNumber: {

                Matrix<LinearNaturalNumber> matrix = dex
                        .getNaturalNumberMatrix(matrixName);
                dex.addNaturalNumberMatrix(reduced, matrix.reduce());

                break;
            }
            default:
                break; // Will never happen
        }

    }

    /**
     * Prompts the user for valid Matrices and then adds the multiplied matrix
     * to the index.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param dex
     *            the index to update
     */
    private static void multiplyMatrix(SimpleWriter out, SimpleReader in,
            MatrixIndex dex) {
        minimumCompatibleMatrix(out, in, dex, 2);

        out.println("Current Matrices: ");
        printMatrices(out, dex);

        out.print("Enter the name of the matrix on the left:");
        String a = in.nextLine();
        while (!dex.hasKey(a)) {
            out.print(a + " doesn't exist. Enter a new name: ");
            a = in.nextLine();
        }

        Matrix<LinearDouble> matrixA = dex.value(a);

        out.print("Enter the name of the matrix on the right:");
        String b = in.nextLine();
        while (!dex.hasKey(b)) {
            out.print(b + " doesn't exist. Enter a new name: ");
            b = in.nextLine();
        }

        Matrix<LinearDouble> matrixB = dex.value(b);

        out.print("Enter a name for your multiplied matrix (FIRST*SECOND): ");

        String matrixName = in.nextLine();

        while (dex.hasKey(matrixName)) {
            out.print(matrixName + " already exists. Enter a new name: ");
            matrixName = in.nextLine();
        }

        dex.add(matrixName, matrixA.multiply(matrixB));
    }

    /**
     * Prompts the user for a valid Matrix and then multiplies it by a constant
     * and adds it to the index.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param dex
     *            the index to update
     */
    private static void constMatrix(SimpleWriter out, SimpleReader in,
            MatrixIndex dex) {
        minimumCompatibleMatrix(out, in, dex, 1);

        out.println("Current Matrices: ");
        printMatrices(out, dex);

        out.print("Enter the name of the matrix to multiply by a constant:");
        String matrixName = in.nextLine();
        while (!dex.hasKey(matrixName)) {
            out.print(matrixName + " doesn't exist. Enter a new name: ");
            matrixName = in.nextLine();
        }

        Matrix<LinearDouble> matrix = dex.value(matrixName);

        out.print("Enter the constant to multiply by:");
        String constant = in.nextLine();
        while (!(FormatChecker.canParseDouble(constant)
                || FormatChecker.canParseInt(constant))) {
            out.print(
                    "That is not a valid constant, enter the constant to multiply by:");
            constant = in.nextLine();
        }

        out.print("Enter a name for your constant multiplied matrix: ");
        String reduced = in.nextLine();

        while (dex.hasKey(reduced)) {
            out.print(reduced + " already exists. Enter a new name: ");
            reduced = in.nextLine();
        }

        if (FormatChecker.canParseInt(constant)) {
            dex.add(reduced, matrix.multiply(Integer.parseInt(constant)));
        } else {
            dex.add(reduced, matrix.multiply(Double.parseDouble(constant)));
        }

    }

    /**
     * Prompts the user for valid Matrices and then adds the Matrix sum to the
     * index.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param dex
     *            the index to update
     */
    private static void addMatrix(SimpleWriter out, SimpleReader in,
            MatrixIndex dex) {
        minimumCompatibleMatrix(out, in, dex, 2);

        out.println("Current Matrices: ");
        printMatrices(out, dex);

        out.print("Enter the name of the first matrix:");
        String a = in.nextLine();
        while (!dex.hasKey(a)) {
            out.print(a + " doesn't exist. Enter a new name: ");
            a = in.nextLine();
        }

        Matrix<LinearDouble> matrixA = dex.value(a);

        out.print("Enter the name of the second matrix:");
        String b = in.nextLine();
        while (!dex.hasKey(b)) {
            out.print(b + " doesn't exist. Enter a new name: ");
            b = in.nextLine();
        }

        Matrix<LinearDouble> matrixB = dex.value(b);

        boolean isValid = true;

        if (matrixA.rows() != matrixB.rows()) {
            out.println("Error, wrong number of rows.");
            isValid = false;
        }

        if (matrixA.columns() != matrixB.columns()) {
            out.println("Error, wrong number of columns.");
            isValid = false;
        }

        if (isValid) {

            out.print("Enter a name for your Matrix sum: ");
            String matrixName = in.nextLine();

            while (dex.hasKey(matrixName)) {
                out.print(matrixName + " already exists. Enter a new name: ");
                matrixName = in.nextLine();
            }

            dex.add(matrixName, matrixA.add(matrixB));
        } else {
            out.print("Enter to continue");
            in.nextLine();
        }

    }

    /**
     * Prompts the user for a reduced matrix and checks if it is consistent.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param dex
     *            the index to update
     */
    private static void consistentMatrix(SimpleWriter out, SimpleReader in,
            MatrixIndex dex) {

        Map<String, Matrix<LinearDouble>> temp;
        temp = new Map1L<String, Matrix<LinearDouble>>();

        for (Map.Pair<String, Matrix<LinearDouble>> matrix : dex) {
            if (matrix.value().isRREF()) {
                temp.add(matrix.key(), matrix.value());
            }
        }

        if (temp.size() > 0) {

            out.println("Current Reduced Matrices: ");
            printMatrices(out, temp);

            out.print("Enter the name of the matrix to check for consistency:");
            String a = in.nextLine();
            while (!temp.hasKey(a)) {
                out.print(a + " doesn't exist. Enter a new name: ");
                a = in.nextLine();
            }

            if (temp.value(a).isConsistent()) {
                out.print(a + " is consistent.");
            } else {
                out.print(a + " is NOT consistent.");
            }

            out.print(" Enter to continue:");
            in.nextLine();
        } else {
            out.print("You have no reduced matrices. Enter to continue:");
            in.nextLine();
        }

    }

    /**
     * Collects matrices from the user until they have at least n matrices that
     * are compatible.
     *
     * @param out
     *            output stream
     * @param in
     *            input stream
     * @param dex
     *            the collection of matrices and names
     * @param n
     *            the minimum number of matrices
     */
    private static void minimumCompatibleMatrix(SimpleWriter out,
            SimpleReader in, MatrixIndex dex, int n) {
        while (dex.maxCompatibleSize() < n) {
            if (dex.size() > 0) {
                out.println("Current Matrices: ");
                dex.printMatrices(out);
            }

            if ((n - dex.size()) == 1) {
                out.print("You need 1 more matrix.");
            } else {
                out.print("You need " + (n - dex.size()) + " more matrices.");
            }

            newMatrix(out, in, dex);
        }
    }

    /**
     * Collects matrices from the user until they have at least n matrices that
     * are not Variables.
     *
     * @param out
     *            output stream
     * @param in
     *            input stream
     * @param dex
     *            the collection of matrices and names
     * @param n
     *            the minimum number of matrices
     */
    private static void minimumNonVariableMatrix(SimpleWriter out,
            SimpleReader in, MatrixIndex dex, int n) {
        while (dex.kindSize(MatrixIndex.Kind.Double) < n
                && dex.kindSize(MatrixIndex.Kind.Integer) < n
                && dex.kindSize(MatrixIndex.Kind.NaturalNumber) < n) {
            if (dex.size() > 0) {
                out.println("Current Matrices: ");
                dex.printMatrices(out);
            }

            if ((n - dex.size()) == 1) {
                out.print("You need 1 more matrix.");
            } else {
                out.print("You need " + (n - dex.size()) + " more matrices.");
            }

            newMatrix(out, in, dex);
        }
    }

    /**
     * Really jank way of clearing the terminal.
     *
     * @param out
     *            output stream to "clear"
     */
    private static void clearTerminal(SimpleWriter out) {
        final int iterations = 50;
        for (int i = 0; i < iterations; i++) {
            out.println();
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        /*
         * Create matrix
         */

        MatrixIndex dex = new MatrixIndex();

        String input = "";

        while (!input.toLowerCase().equals("stop")) {

            if (dex.size() > 0) {
                clearTerminal(out);
                out.println("Current Matrices: ");
                dex.printMatrices(out);
            }
            out.println("To generate a new matrix, enter \"new\"");
            out.println("To augment, enter \"aug\"");
            out.println("To reduce to RREF, enter \"red\" or \"reduce\"");
            out.println("To multiply by a Matrix, enter \"mult\"");
            out.println("To multiply by a constant, enter \"const\"");
            out.println("To add two Matrices, enter \"add\"");
            out.println("To print your current matrices, enter \"print\"");
            out.println("To check if a reduced matrix is consistent,"
                    + " enter \"consistent\" or \"check\"");
            out.println("To stop program, enter \"stop\"");
            out.println();

            input = in.nextLine().toLowerCase();
            out.println();

            if (input.equals("new")) {
                newMatrix(out, in, dex);
            } else if (input.equals("aug")) {
                augmentMatrix(out, in, dex);
            } else if (input.equals("red") || input.equals("reduce")) {
                reduceMatrix(out, in, dex);
            } else if (input.equals("mult")) {
                multiplyMatrix(out, in, dex);
            } else if (input.equals("const")) {
                constMatrix(out, in, dex);
            } else if (input.equals("add")) {
                addMatrix(out, in, dex);
            } else if (input.equals("print")) {
                out.println("Current Matrices: ");
                dex.printMatrices(out);
            } else if (input.equals("consistent") || input.equals("check")) {
                consistentMatrix(out, in, dex);
            } else if (input.equals("stop")) {
                out.print("Are you sure? (y/n): ");
                input = in.nextLine().toLowerCase();
                if (input.equals("y")) {
                    input = "stop";
                }
            }
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
