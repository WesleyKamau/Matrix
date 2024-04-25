import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
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

        Sequence<Sequence<Double>> tempEntries = new Sequence1L<Sequence<Double>>();
        Sequence<Sequence<Double>> tempRightSide = new Sequence1L<Sequence<Double>>();
        for (int i = 1; i <= equations; i++) {
            Sequence<Double> row = new Sequence1L<Double>();
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
                row.add(row.length(), value);
            }

            tempEntries.add(tempEntries.length(), row);
            out.print("Enter the value of equation " + i + ": ");
            double value = 0;
            String tempValue = in.nextLine();
            while (!FormatChecker.canParseDouble(tempValue)) {
                out.print("Error. Enter the value of equation " + i + ": ");
                tempValue = in.nextLine();
            }
            value = Double.parseDouble(tempValue);
            Sequence<Double> element = new Sequence1L<Double>();
            element.add(0, value);
            tempRightSide.add(tempRightSide.length(), element);

            out.println();
        }
        Matrix<Double> result = new Matrix<Double>(tempEntries);
        Matrix<Double> rightSide = new Matrix<Double>(tempRightSide);

        // result.print(out);
        //rightSide.print(out);

        Matrix<Double> reducedResult = Matrix.reduce(result.augment(rightSide));
        //reducedResult.print(out);

        out.println();

        if (Matrix.isConsistent(reducedResult)) {
            for (int i = 1; i <= variables; i++) {
                out.println(variableNames.entry(i - 1) + " = "
                        + reducedResult.element(i, reducedResult.columns()));
            }
        } else {
            out.println("The system has No Solution.");
        }

        reducedResult.print(out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
