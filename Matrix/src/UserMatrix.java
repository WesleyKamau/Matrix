import components.linear.LinearDouble;
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
     * Prompts the user to enter the parameters for a Matrix and returns it.
     *
     * @param in
     *            the input stream
     * @param out
     *            the console
     * @return the Matrix
     */
    private static Matrix<LinearDouble> getUserMatrix(SimpleReader in,
            SimpleWriter out) {
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

        Matrix<LinearDouble> result = new Matrix2<LinearDouble>(
                new LinearDouble(), rows, columns);

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {

                out.print("Enter the element (" + i + ", " + j + "): ");
                tempVariables = in.nextLine();
                while (!FormatChecker.canParseDouble(tempVariables)) {
                    out.print("Error. Enter the element (" + i + ", " + j
                            + "): ");
                    tempVariables = in.nextLine();
                }
                result.setElement(i, j,
                        new LinearDouble(Double.parseDouble(tempVariables)));

            }
        }

        return result;

    }

    /**
     * Prints out all of the matrices in the index.
     *
     * @param out
     *            the ouptu stream
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
        out.print("Enter a name: ");
        String matrixName = in.nextLine();

        while (index.hasKey(matrixName)) {
            out.print(matrixName + " already exists. Enter a new name: ");
            matrixName = in.nextLine();
        }

        index.add(matrixName, getUserMatrix(in, out));

        out.println();
    }

    /**
     * Prompts the user for valid Matrices and then adds the augmented matrix to
     * the index.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param index
     *            the index to update
     */
    private static void augmentMatrix(SimpleWriter out, SimpleReader in,
            Map<String, Matrix<LinearDouble>> index) {
        minimumMatrix(out, in, index, 2);

        out.println("Current Matrices: ");
        printMatrices(out, index);

        out.print("Enter the name of the matrix on the left:");
        String a = in.nextLine();
        while (!index.hasKey(a)) {
            out.print(a + " doesn't exist. Enter a new name: ");
            a = in.nextLine();
        }

        Matrix<LinearDouble> matrixA = index.value(a);

        out.print("Enter the name of the matrix on the right:");
        String b = in.nextLine();
        while (!index.hasKey(b)) {
            out.print(b + " doesn't exist. Enter a new name: ");
            b = in.nextLine();
        }

        Matrix<LinearDouble> matrixB = index.value(b);

        out.print("Enter a name for your Augmented matrix: ");
        String matrixName = in.nextLine();

        while (index.hasKey(matrixName)) {
            out.print(matrixName + " already exists. Enter a new name: ");
            matrixName = in.nextLine();
        }

        index.add(matrixName, matrixA.augment(matrixB));

    }

    /**
     * Prompts the user for a valid Matrix and then adds the reduced matrix to
     * the index.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param index
     *            the index to update
     */
    private static void reduceMatrix(SimpleWriter out, SimpleReader in,
            Map<String, Matrix<LinearDouble>> index) {
        minimumMatrix(out, in, index, 1);

        out.println("Current Matrices: ");
        printMatrices(out, index);

        out.print("Enter the name of the matrix to reduce:");
        String matrixName = in.nextLine();
        while (!index.hasKey(matrixName)) {
            out.print(matrixName + " doesn't exist. Enter a new name: ");
            matrixName = in.nextLine();
        }

        Matrix<LinearDouble> matrix = index.value(matrixName);

        out.print("Enter a name for your reduced matrix: ");
        String reduced = in.nextLine();

        while (index.hasKey(reduced)) {
            out.print(reduced + " already exists. Enter a new name: ");
            reduced = in.nextLine();
        }

        index.add(reduced, matrix.reduce());
    }

    /**
     * Prompts the user for valid Matrices and then adds the multiplied matrix
     * to the index.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param index
     *            the index to update
     */
    private static void multiplyMatrix(SimpleWriter out, SimpleReader in,
            Map<String, Matrix<LinearDouble>> index) {
        minimumMatrix(out, in, index, 2);

        out.println("Current Matrices: ");
        printMatrices(out, index);

        out.print("Enter the name of the matrix on the left:");
        String a = in.nextLine();
        while (!index.hasKey(a)) {
            out.print(a + " doesn't exist. Enter a new name: ");
            a = in.nextLine();
        }

        Matrix<LinearDouble> matrixA = index.value(a);

        out.print("Enter the name of the matrix on the right:");
        String b = in.nextLine();
        while (!index.hasKey(b)) {
            out.print(b + " doesn't exist. Enter a new name: ");
            b = in.nextLine();
        }

        Matrix<LinearDouble> matrixB = index.value(b);

        out.print("Enter a name for your multiplied matrix (FIRST*SECOND): ");

        String matrixName = in.nextLine();

        while (index.hasKey(matrixName)) {
            out.print(matrixName + " already exists. Enter a new name: ");
            matrixName = in.nextLine();
        }

        index.add(matrixName, matrixA.multiply(matrixB));
    }

    /**
     * Prompts the user for a valid Matrix and then multiplies it by a constant
     * and adds it to the index.
     *
     * @param out
     *            the output stream
     * @param in
     *            user input stream
     * @param index
     *            the index to update
     */
    private static void constMatrix(SimpleWriter out, SimpleReader in,
            Map<String, Matrix<LinearDouble>> index) {
        minimumMatrix(out, in, index, 1);

        out.println("Current Matrices: ");
        printMatrices(out, index);

        out.print("Enter the name of the matrix to multiply by a constant:");
        String matrixName = in.nextLine();
        while (!index.hasKey(matrixName)) {
            out.print(matrixName + " doesn't exist. Enter a new name: ");
            matrixName = in.nextLine();
        }

        Matrix<LinearDouble> matrix = index.value(matrixName);

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

        while (index.hasKey(reduced)) {
            out.print(reduced + " already exists. Enter a new name: ");
            reduced = in.nextLine();
        }

        if (FormatChecker.canParseInt(constant)) {
            index.add(reduced, matrix.multiply(Integer.parseInt(constant)));
        } else {
            index.add(reduced, matrix.multiply(Double.parseDouble(constant)));
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
     * @param index
     *            the index to update
     */
    private static void addMatrix(SimpleWriter out, SimpleReader in,
            Map<String, Matrix<LinearDouble>> index) {
        minimumMatrix(out, in, index, 2);

        out.println("Current Matrices: ");
        printMatrices(out, index);

        out.print("Enter the name of the first matrix:");
        String a = in.nextLine();
        while (!index.hasKey(a)) {
            out.print(a + " doesn't exist. Enter a new name: ");
            a = in.nextLine();
        }

        Matrix<LinearDouble> matrixA = index.value(a);

        out.print("Enter the name of the second matrix:");
        String b = in.nextLine();
        while (!index.hasKey(b)) {
            out.print(b + " doesn't exist. Enter a new name: ");
            b = in.nextLine();
        }

        Matrix<LinearDouble> matrixB = index.value(b);

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

            while (index.hasKey(matrixName)) {
                out.print(matrixName + " already exists. Enter a new name: ");
                matrixName = in.nextLine();
            }

            index.add(matrixName, matrixA.add(matrixB));
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
     * @param index
     *            the index to update
     */
    private static void consistentMatrix(SimpleWriter out, SimpleReader in,
            Map<String, Matrix<LinearDouble>> index) {

        Map<String, Matrix<LinearDouble>> temp;
        temp = new Map1L<String, Matrix<LinearDouble>>();

        for (Map.Pair<String, Matrix<LinearDouble>> matrix : index) {
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
     * Collects matrices from the user until they have at least n matrices.
     *
     * @param out
     *            output stream
     * @param in
     *            input stream
     * @param index
     *            the collection of matrices and names
     * @param n
     *            the minimum number of matrices
     */
    private static void minimumMatrix(SimpleWriter out, SimpleReader in,
            Map<String, Matrix<LinearDouble>> index, int n) {
        while (index.size() < n) {
            if (index.size() > 0) {
                out.println("Current Matrices: ");
                printMatrices(out, index);
            }

            if ((n - index.size()) == 1) {
                out.print("You need 1 more matrix.");
            } else {
                out.print("You need " + (n - index.size()) + " more matrices.");
            }

            newMatrix(out, in, index);
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

        Map<String, Matrix<LinearDouble>> dex = new Map1L<String, Matrix<LinearDouble>>();

        String input = "";

        while (!input.toLowerCase().equals("stop")) {

            if (dex.size() > 0) {
                clearTerminal(out);
                out.println("Current Matrices: ");
                printMatrices(out, dex);
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
                printMatrices(out, dex);
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
