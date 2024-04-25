import components.linear.LinearDouble;
import components.matrix.Matrix;
import components.matrix.Matrix2;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * System of Equations solver using Matrix.
 *
 * @author Wesley Kamau
 *
 */
public final class SystemOfEquationSolver {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private SystemOfEquationSolver() {
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
         * Put your main program code here
         */
        Sequence<String> variableNames = new Sequence1L<String>();

        out.print("Enter how many variables: ");
        int variables = 0;
        String tempVariables = in.nextLine();
        while (!FormatChecker.canParseInt(tempVariables)) {
            out.print("Error. Enter how many variables: ");
            tempVariables = in.nextLine();
        }
        variables = Integer.parseInt(tempVariables);

        out.print("Enter how many equations: ");
        int equations = 0;
        String tempequations = in.nextLine();
        while (!FormatChecker.canParseInt(tempequations)) {
            out.print("Error. Enter how many equations: ");
            tempequations = in.nextLine();
        }
        equations = Integer.parseInt(tempequations);

        out.println();

        for (int i = 0; i < variables; i++) {
            out.print("Enter the name of variable " + (i + 1) + ": ");
            variableNames.add(i, in.nextLine());
        }

        out.println();

        Matrix<LinearDouble> index = new Matrix2<LinearDouble>(
                new LinearDouble(), equations, variables);

        Matrix<LinearDouble> rightSide = new Matrix2<LinearDouble>(
                new LinearDouble(), equations, 1);

        for (int i = 1; i <= equations; i++) {
            for (int j = 0; j < variables; j++) {
                out.print("Enter the value of " + variableNames.entry(j)
                        + " in equation " + i + ": ");
                double value = 0;
                String tempValue = in.nextLine();
                while (!FormatChecker.canParseDouble(tempValue)) {
                    out.print("Error. Enter the value of "
                            + variableNames.entry(j) + " in equation " + i
                            + ": ");
                    tempValue = in.nextLine();
                }
                value = Double.parseDouble(tempValue);
                index.setElement(i, j + 1, new LinearDouble(value));
            }

            out.print("Enter the value of equation " + i + ": ");
            double value = 0;
            String tempValue = in.nextLine();
            while (!FormatChecker.canParseDouble(tempValue)) {
                out.print("Error. Enter the value of equation " + i + ": ");
                tempValue = in.nextLine();
            }
            value = Double.parseDouble(tempValue);
            rightSide.setElement(i, 1, new LinearDouble(value));

            out.println();
        }

        /* Augment and Reduce */
        Matrix<LinearDouble> reduced = index.augment(rightSide).reduce();

        out.println();

        if (reduced.isConsistent()) {
            for (int i = 1; i <= variables; i++) {
                out.println(variableNames.entry(i - 1) + " = "
                        + reduced.element(i, reduced.columns()));
            }
        } else {
            out.println("The system has no solution.");
            reduced.print(out);
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
