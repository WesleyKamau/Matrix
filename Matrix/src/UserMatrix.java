import VariableParser.VariableParser;
import components.linear.LinearDouble;
import components.linear.LinearInteger;
import components.linear.LinearNaturalNumber;
import components.linear.LinearVariable;
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
     * Checks if the string input is a valid Enum identifier.
     *
     * @param s
     *            the String to be checked
     * @return true if it is an Enum
     */
    private static boolean isEnum(String s) {
        for (MatrixIndex.Kind temp : MatrixIndex.Kind.values()) {
            if (temp.toString().equals(s)) {
                return true;
            }
        }
        return false;
    }

    private static void getUserMatrix(SimpleWriter out, SimpleReader in,
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

        out.print("Enter a name for your new Matrix: ");
        String MatrixName = getNewName(out, in, dex);

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

                dex.addDoubleMatrix(MatrixName, result);

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

                dex.addIntegerMatrix(MatrixName, result);

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

                dex.addNaturalNumberMatrix(MatrixName, result);

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

                dex.addVariableMatrix(MatrixName, result);

                break;
            }
            default: {
                break;
            }
        }

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
        String MatrixName = in.nextLine();

        while (dex.hasName(MatrixName)) {
            out.print(MatrixName + " already exists. Enter a new name: ");
            MatrixName = in.nextLine();
        }

        return MatrixName;
    }

    private static boolean isValidAugmentPair(String a, String b,
            MatrixIndex dex) {
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

        if (dex.getMatrixRows(a) != dex.getMatrixRows(b)) {
            validPair = false;
        }

        return validPair;
    }

    private static boolean isValidSumPair(String a, String b, MatrixIndex dex) {
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

        if (dex.getMatrixRows(a) != dex.getMatrixRows(b)) {
            validPair = false;
        }

        if (dex.getMatrixColumns(a) != dex.getMatrixColumns(b)) {
            validPair = false;
        }

        return validPair;
    }

    private static boolean isValidMultiplyPair(String a, String b,
            MatrixIndex dex) {
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

        if (dex.getMatrixRows(a) != dex.getMatrixColumns(b)) {
            validPair = false;
        }

        if (dex.getMatrixColumns(a) != dex.getMatrixRows(b)) {
            validPair = false;
        }

        return validPair;
    }

    /**
     * Prompts the user for valid Matrices and then adds the augmented Matrix to
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

        if (dex.size() > 0) {
            out.println("Current Matrices: ");
            dex.printMatrices(out);
            out.println();
        }

        if (dex.maxCompatibleSize() >= 2) {

            out.print("Enter the name of the Matrix on the left:");
            String a = getExistingName(out, in, dex);

            out.print("Enter the name of the Matrix on the right:");
            String b = getExistingName(out, in, dex);

            if (isValidAugmentPair(a, b, dex)) {
                out.print("Enter a name for your Augmented Matrix: ");
                String MatrixName = getNewName(out, in, dex);

                switch (dex.getKind(a)) {
                    case Double: {

                        Matrix<LinearDouble> MatrixA = dex.getDoubleMatrix(a);

                        switch (dex.getKind(b)) {
                            case Double: {
                                Matrix<LinearDouble> MatrixB = dex
                                        .getDoubleMatrix(b);

                                dex.addDoubleMatrix(MatrixName,
                                        MatrixA.augment(MatrixB));
                                break;
                            }
                            case Integer: {

                                Matrix<LinearInteger> MatrixB = dex
                                        .getIntegerMatrix(a);
                                dex.addDoubleMatrix(MatrixName, MatrixA.augment(
                                        MatrixHelper.intToDouble(MatrixB)));

                                break;
                            }
                            default:
                                break; // Will never happen, isValidPair
                        }
                        break;
                    }
                    case Integer: {

                        Matrix<LinearInteger> MatrixA = dex.getIntegerMatrix(a);

                        switch (dex.getKind(b)) {
                            case Double: {

                                Matrix<LinearDouble> MatrixB = dex
                                        .getDoubleMatrix(b);
                                dex.addDoubleMatrix(MatrixName, MatrixHelper
                                        .intToDouble(MatrixA).augment(MatrixB));

                                break;
                            }
                            case Integer: {

                                Matrix<LinearInteger> MatrixB = dex
                                        .getIntegerMatrix(b);
                                dex.addIntegerMatrix(MatrixName,
                                        MatrixA.augment(MatrixB));

                                break;
                            }
                            default:
                                break; // Will never happen, isValidPair
                        }
                        break;
                    }
                    case NaturalNumber: {

                        Matrix<LinearNaturalNumber> MatrixA = dex
                                .getNaturalNumberMatrix(a);
                        Matrix<LinearNaturalNumber> MatrixB = dex
                                .getNaturalNumberMatrix(b);
                        dex.addNaturalNumberMatrix(MatrixName,
                                MatrixA.augment(MatrixB));

                        break;
                    }
                    case Variable: {

                        Matrix<LinearVariable> MatrixA = dex
                                .getVariableMatrix(a);
                        Matrix<LinearVariable> MatrixB = dex
                                .getVariableMatrix(b);
                        dex.addVariableMatrix(MatrixName,
                                MatrixA.augment(MatrixB));

                        break;
                    }
                    default:
                        break; // Will never happen
                }
            } else {
                suspend("The matrices you selected were not of compatible types.",
                        out, in);
            }
        } else {
            suspend("You need at least 2 matrices of compatible types. (Double/Integer), NaturalNumber, and Variable.",
                    out, in);
        }

    }

    /**
     * Prompts the user for a valid Matrix and then adds the reduced Matrix to
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

        if (dex.size() > 0) {
            out.println("Current Matrices: ");
            dex.printMatrices(out);
            out.println();
        }

        if (dex.kindSize(MatrixIndex.Kind.Double) >= 1
                || dex.kindSize(MatrixIndex.Kind.Integer) >= 1
                || dex.kindSize(MatrixIndex.Kind.NaturalNumber) >= 1) {

            out.print("Enter the name of the Matrix to reduce:");
            String MatrixName = getExistingName(out, in, dex);

            out.print("Enter a name for your reduced Matrix: ");
            String reduced = getNewName(out, in, dex);

            switch (dex.getKind(MatrixName)) {
                case Double: {

                    Matrix<LinearDouble> Matrix = dex
                            .getDoubleMatrix(MatrixName);
                    dex.addDoubleMatrix(reduced, Matrix.reduce());

                    break;
                }
                case Integer: {

                    Matrix<LinearInteger> Matrix = dex
                            .getIntegerMatrix(MatrixName);
                    dex.addIntegerMatrix(reduced, Matrix.reduce());

                    break;
                }
                case NaturalNumber: {

                    Matrix<LinearNaturalNumber> Matrix = dex
                            .getNaturalNumberMatrix(MatrixName);
                    dex.addNaturalNumberMatrix(reduced, Matrix.reduce());

                    break;
                }
                case Variable: {
                    suspend("Variables are not compatible for reduction.", out,
                            in);
                    break;
                }
                default:
                    break; // Will never happen
            }
        } else {
            suspend("You need at least 1 NonVariable Matrix", out, in);
        }

    }

    /**
     * Prompts the user for valid Matrices and then adds the multiplied Matrix
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

        if (dex.size() > 0) {
            out.println("Current Matrices: ");
            dex.printMatrices(out);
            out.println();
        }

        if (dex.maxCompatibleSize() >= 2) {

            out.print("Enter the name of the Matrix on the left:");
            String a = getExistingName(out, in, dex);

            out.print("Enter the name of the Matrix on the right:");
            String b = getExistingName(out, in, dex);

            if (isValidMultiplyPair(a, b, dex)) {
                out.print(
                        "Enter a name for your multiplied Matrix (FIRST*SECOND): ");
                String MatrixName = getNewName(out, in, dex);

                switch (dex.getKind(a)) {
                    case Double: {

                        Matrix<LinearDouble> MatrixA = dex.getDoubleMatrix(a);

                        switch (dex.getKind(b)) {
                            case Double: {
                                Matrix<LinearDouble> MatrixB = dex
                                        .getDoubleMatrix(b);

                                dex.addDoubleMatrix(MatrixName,
                                        MatrixA.multiply(MatrixB));
                                break;
                            }
                            case Integer: {

                                Matrix<LinearInteger> MatrixB = dex
                                        .getIntegerMatrix(b);
                                dex.addDoubleMatrix(MatrixName,
                                        MatrixA.multiply(MatrixHelper
                                                .intToDouble(MatrixB)));

                                break;
                            }
                            default:
                                break; // Will never happen, isValidPair
                        }
                        break;
                    }
                    case Integer: {

                        Matrix<LinearInteger> MatrixA = dex.getIntegerMatrix(a);

                        switch (dex.getKind(b)) {
                            case Double: {

                                Matrix<LinearDouble> MatrixB = dex
                                        .getDoubleMatrix(b);
                                dex.addDoubleMatrix(MatrixName,
                                        MatrixHelper.intToDouble(MatrixA)
                                                .multiply(MatrixB));

                                break;
                            }
                            case Integer: {

                                Matrix<LinearInteger> MatrixB = dex
                                        .getIntegerMatrix(b);
                                dex.addIntegerMatrix(MatrixName,
                                        MatrixA.multiply(MatrixB));

                                break;
                            }
                            default:
                                break; // Will never happen, isValidPair
                        }
                        break;
                    }
                    case NaturalNumber: {

                        Matrix<LinearNaturalNumber> MatrixA = dex
                                .getNaturalNumberMatrix(a);
                        Matrix<LinearNaturalNumber> MatrixB = dex
                                .getNaturalNumberMatrix(b);
                        dex.addNaturalNumberMatrix(MatrixName,
                                MatrixA.multiply(MatrixB));

                        break;
                    }
                    case Variable: {

                        Matrix<LinearVariable> MatrixA = dex
                                .getVariableMatrix(a);
                        Matrix<LinearVariable> MatrixB = dex
                                .getVariableMatrix(b);
                        dex.addVariableMatrix(MatrixName,
                                MatrixA.multiply(MatrixB));

                        break;
                    }
                    default:
                        break; // Will never happen
                }
            } else {
                suspend("The matrices you selected were not of compatible types.",
                        out, in);
            }
        } else {
            suspend("You need at least 2 matrices of compatible types. (Double/Integer), NaturalNumber, and Variable.",
                    out, in);
        }

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

        if (dex.size() > 0) {
            out.println("Current Matrices: ");
            dex.printMatrices(out);
            out.println();

            out.print(
                    "Enter the name of the Matrix to multiply by a constant:");
            String MatrixName = getExistingName(out, in, dex);

            out.print("Enter the constant to multiply by:");
            String constant = in.nextLine();
            while (!(FormatChecker.canParseDouble(constant)
                    || FormatChecker.canParseInt(constant))) {
                out.print(
                        "That is not a valid constant, enter the constant to multiply by:");
                constant = in.nextLine();
            }

            out.print("Enter a name for your constant multiplied Matrix: ");
            String constantMatrix = getNewName(out, in, dex);

            switch (dex.getKind(MatrixName)) {
                case Double: {
                    if (FormatChecker.canParseInt(constant)) {
                        dex.addDoubleMatrix(constantMatrix,
                                dex.getDoubleMatrix(MatrixName)
                                        .multiply(Integer.parseInt(constant)));
                    } else {
                        dex.addDoubleMatrix(constantMatrix,
                                dex.getDoubleMatrix(MatrixName).multiply(
                                        Double.parseDouble(constant)));
                    }
                    break;
                }
                case Integer: {
                    if (FormatChecker.canParseInt(constant)) {
                        dex.addIntegerMatrix(constantMatrix,
                                dex.getIntegerMatrix(MatrixName)
                                        .multiply(Integer.parseInt(constant)));
                    } else {
                        dex.addIntegerMatrix(constantMatrix,
                                dex.getIntegerMatrix(MatrixName).multiply(
                                        Double.parseDouble(constant)));
                    }
                    break;
                }
                case NaturalNumber: {
                    if (FormatChecker.canParseInt(constant)) {
                        dex.addNaturalNumberMatrix(constantMatrix,
                                dex.getNaturalNumberMatrix(MatrixName)
                                        .multiply(Integer.parseInt(constant)));
                    } else {
                        dex.addNaturalNumberMatrix(constantMatrix,
                                dex.getNaturalNumberMatrix(MatrixName).multiply(
                                        Double.parseDouble(constant)));
                    }
                    break;
                }
                case Variable: {
                    if (FormatChecker.canParseInt(constant)) {
                        dex.addVariableMatrix(constantMatrix,
                                dex.getVariableMatrix(MatrixName)
                                        .multiply(Integer.parseInt(constant)));
                    } else {
                        dex.addVariableMatrix(constantMatrix,
                                dex.getVariableMatrix(MatrixName).multiply(
                                        Double.parseDouble(constant)));
                    }
                    break;
                }
                default:
                    break;

            }

        } else {
            suspend("You need at least one Matrix.", out, in);
        }

    }

    /**
     * Prompts the user for a valid Matrix and then prints out the determinant.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param dex
     *            the index to read from
     */
    private static void determinantMatrix(SimpleWriter out, SimpleReader in,
            MatrixIndex dex) {

        if (dex.size() > 0) {
            out.println("Current Matrices: ");
            dex.printMatrices(out);
            out.println();

            out.print(
                    "Enter the name of the Matrix to calculate the determinant of: ");
            String MatrixName = getExistingName(out, in, dex);

            if (dex.getMatrixRows(MatrixName) == dex
                    .getMatrixColumns(MatrixName)) {

                String output = "";

                switch (dex.getKind(MatrixName)) {
                    case Double: {
                        LinearDouble result = new LinearDouble();
                        dex.getDoubleMatrix(MatrixName).determinant(result);
                        output = result.toString();
                        break;
                    }
                    case Integer: {
                        LinearInteger result = new LinearInteger();
                        dex.getIntegerMatrix(MatrixName).determinant(result);
                        output = result.toString();
                        break;
                    }
                    case NaturalNumber: {
                        LinearNaturalNumber result = new LinearNaturalNumber();
                        dex.getNaturalNumberMatrix(MatrixName)
                                .determinant(result);
                        output = result.toString();
                        break;
                    }
                    case Variable: {
                        LinearVariable result = new LinearVariable();
                        dex.getVariableMatrix(MatrixName).determinant(result);
                        output = result.toString();
                        break;
                    }
                    default:
                        break;
                }

                suspend("The determinant of " + MatrixName + " is: " + output,
                        out, in);
            } else {
                suspend(MatrixName + " is not a square Matrix.", out, in);
            }

        } else {
            suspend("You need at least one Matrix.", out, in);
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

        if (dex.size() > 0) {
            out.println("Current Matrices: ");
            dex.printMatrices(out);
            out.println();
        }

        if (dex.maxCompatibleSize() >= 2) {

            out.print("Enter the name of Matrix a:");
            String a = getExistingName(out, in, dex);

            out.print("Enter the name of Matrix b:");
            String b = getExistingName(out, in, dex);

            if (isValidSumPair(a, b, dex)) {
                out.print("Enter a name for your Matrix sum a+b: ");
                String MatrixName = getNewName(out, in, dex);

                switch (dex.getKind(a)) {
                    case Double: {

                        Matrix<LinearDouble> MatrixA = dex.getDoubleMatrix(a);

                        switch (dex.getKind(b)) {
                            case Double: {
                                Matrix<LinearDouble> MatrixB = dex
                                        .getDoubleMatrix(b);

                                dex.addDoubleMatrix(MatrixName,
                                        MatrixA.add(MatrixB));
                                break;
                            }
                            case Integer: {

                                Matrix<LinearInteger> MatrixB = dex
                                        .getIntegerMatrix(a);
                                dex.addDoubleMatrix(MatrixName, MatrixA.add(
                                        MatrixHelper.intToDouble(MatrixB)));

                                break;
                            }
                            default:
                                break; // Will never happen, isValidPair
                        }
                        break;
                    }
                    case Integer: {

                        Matrix<LinearInteger> MatrixA = dex.getIntegerMatrix(a);

                        switch (dex.getKind(b)) {
                            case Double: {

                                Matrix<LinearDouble> MatrixB = dex
                                        .getDoubleMatrix(b);
                                dex.addDoubleMatrix(MatrixName, MatrixHelper
                                        .intToDouble(MatrixA).add(MatrixB));

                                break;
                            }
                            case Integer: {

                                Matrix<LinearInteger> MatrixB = dex
                                        .getIntegerMatrix(a);
                                dex.addIntegerMatrix(MatrixName,
                                        MatrixA.add(MatrixB));

                                break;
                            }
                            default:
                                break; // Will never happen, isValidPair
                        }
                        break;
                    }
                    case NaturalNumber: {

                        Matrix<LinearNaturalNumber> MatrixA = dex
                                .getNaturalNumberMatrix(a);
                        Matrix<LinearNaturalNumber> MatrixB = dex
                                .getNaturalNumberMatrix(a);
                        dex.addNaturalNumberMatrix(MatrixName,
                                MatrixA.add(MatrixB));

                        break;
                    }
                    case Variable: {

                        Matrix<LinearVariable> MatrixA = dex
                                .getVariableMatrix(a);
                        Matrix<LinearVariable> MatrixB = dex
                                .getVariableMatrix(b);
                        dex.addVariableMatrix(MatrixName, MatrixA.add(MatrixB));

                        break;
                    }
                    default:
                        break; // Will never happen
                }
            } else {
                suspend("The matrices you selected were not of compatible types.",
                        out, in);
            }
        } else {
            suspend("You need at least 2 matrices of compatible types. "
                    + "(Double/Integer), NaturalNumber, and Variable.", out,
                    in);
        }

    }

    /**
     * Prompts the user for a reduced Matrix and checks if it is consistent.
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

        MatrixIndex reduced = dex.reducedMatrices();

        if (reduced.size() > 0) {

            out.println("Current Reduced Matrices: ");
            reduced.printMatrices(out);
            out.println();

            out.print("Enter the name of the Matrix to check for consistency:");
            String a = getExistingName(out, in, reduced);
            boolean consistent = false;

            switch (reduced.getKind(a)) {
                case Double: {
                    if (reduced.getDoubleMatrix(a).isConsistent()) {
                        consistent = true;
                    }
                    break;
                }
                case Integer: {
                    if (reduced.getIntegerMatrix(a).isConsistent()) {
                        consistent = true;
                    }
                    break;
                }
                case NaturalNumber: {
                    if (reduced.getNaturalNumberMatrix(a).isConsistent()) {
                        consistent = true;
                    }
                    break;
                }
                default:
                    break;
            }

            if (consistent) {
                suspend(a + " is consistent.", out, in);
            } else {
                suspend(a + " is NOT consistent.", out, in);
            }
        } else {
            suspend("You have no reduced matrices.", out, in);
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
     * Prints the message and then suspends the output stream until the user
     * presses enter.
     *
     * @param msg
     *            the message to be printed
     * @param out
     *            the output stream
     * @param in
     *            the input stream
     */
    private static void suspend(String msg, SimpleWriter out, SimpleReader in) {
        out.println(msg);
        out.print("Enter to continue");
        in.nextLine();
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
         * Create Matrix
         */

        MatrixIndex dex = new MatrixIndex();
        String input = "";
        boolean firstRun = true;

        while (!input.toLowerCase().equals("stop")) {

            if (!firstRun) {
                clearTerminal(out);
            }

            if (dex.size() > 0) {
                out.println("Current Matrices: ");
                dex.printMatrices(out);
            }
            out.println("To generate a new Matrix, enter \"new\"");
            out.println("To augment, enter \"aug\"");
            out.println("To reduce to RREF, enter \"red\" or \"reduce\"");
            out.println("To multiply by a Matrix, enter \"mult\"");
            out.println("To multiply by a constant, enter \"const\"");
            out.println("To add two Matrices, enter \"add\"");
            out.println("To print your current matrices, enter \"print\"");
            out.println("To check if a reduced Matrix is consistent,"
                    + " enter \"consistent\" or \"check\"");
            out.println("To calculate the determinant of a square Matrix,"
                    + " enter \"determinant\" or \"det\"");
            out.println("To save to a file, enter \"save\"");
            out.println("To open a file, enter \"open\"");
            out.println("To stop program, enter \"stop\"");
            out.println();

            input = in.nextLine().toLowerCase();
            out.println();

            if (input.equals("new")) {
                getUserMatrix(out, in, dex);
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
            } else if (input.equals("determinant") || input.equals("det")) {
                determinantMatrix(out, in, dex);
            } else if (input.equals("save")) {
                out.print("Enter the filename to save to: ");
                dex.save(in.nextLine());
            } else if (input.equals("open")) {
                out.print("Enter the filename to open: ");
                dex.open(in.nextLine());
            } else if (input.equals("stop")) {
                out.print("Are you sure? (y/n): ");
                input = in.nextLine().toLowerCase();
                if (input.equals("y")) {
                    input = "stop";
                }
            }

            firstRun = false;
        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
