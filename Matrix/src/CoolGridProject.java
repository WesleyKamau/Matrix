import components.matrix.SimpleMatrix;
import components.matrix.SimpleMatrix1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Revamped Cool Grid Project using SimpleMatrix for the grid.
 *
 * @author Wesley Kamau
 *
 */
public final class CoolGridProject {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private CoolGridProject() {
    }

    /**
     * Initializes a grid with asterisks.
     *
     * @param grid
     *            the grid to be initialized.
     */
    private static void clearGrid(SimpleMatrix<Character> grid) {
        for (int y = 1; y <= grid.rows(); y++) {
            for (int x = 1; x <= grid.columns(); x++) {
                grid.setElement(y, x, ' ');
            }
        }
    }

    /**
     * Adds a circle to the grid.
     *
     * @param grid
     *            The grid to be modified
     * @param xPos
     *            the x position of the circle to be added
     * @param yPos
     *            the y position of the circle to be added
     * @param radius
     *            the radius of the circle being added
     */
    private static void addCircle(SimpleMatrix<Character> grid, int xPos,
            int yPos, int radius) {
        final int threshold1 = 1;
        final int threshold2 = 2;
        final int threshold3 = 3;
        for (int y = 1; y <= grid.rows(); y++) {
            for (int x = 1; x <= grid.columns(); x++) {
                if (grid.element(y, x) == ' ') {
                    int equation1 = power((x - xPos), 2) + power((y - yPos), 2);
                    int equation2 = power(radius, 2);
                    if (withinPlusMinusInterval(equation1, equation2,
                            radius / threshold3)) {
                        grid.setElement(y, x, '*');
                    } else if (withinPlusMinusInterval(equation1, equation2,
                            radius / threshold2)) {
                        grid.setElement(y, x, '`');
                    } else if (withinPlusMinusInterval(equation1, equation2,
                            radius / threshold1)) {
                        grid.setElement(y, x, '⋅');
                    }
                }
            }
        }
    }

    /**
     * Adds a circle to the grid.
     *
     * @param grid
     *            The grid to be modified
     * @param slope
     *            the slope of the line to be added
     * @param yIntercept
     *            the y intercept of the line to be added
     *
     */
    private static void addLine(SimpleMatrix<Character> grid, int slope,
            int yIntercept) {
        final int threshold1 = 1;
        final int threshold2 = 2;
        final int threshold3 = 3;
        for (int y = 1; y <= grid.rows(); y++) {
            for (int x = 1; x <= grid.columns(); x++) {
                if (grid.element(y, x) == ' ') {
                    int equation = slope * x + yIntercept;
                    if (withinPlusMinusInterval(equation, y, threshold1)) {
                        grid.setElement(y, x, '*');
                    } else if (withinPlusMinusInterval(equation, y,
                            threshold2)) {
                        grid.setElement(y, x, '`');
                    } else if (withinPlusMinusInterval(equation, y,
                            threshold3)) {
                        grid.setElement(y, x, '⋅');
                    }
                }
            }
        }
    }

    /**
     * Adds a rectangle to the grid.
     *
     * @param grid
     *            the grid Matrix
     * @param xPos
     *            the x position of the center of the rectangle.
     * @param yPos
     *            the y position of the center of the rectangle.
     * @param height
     *            the height of the rectangle.
     * @param length
     *            the length of the rectangle.
     */
    private static void addRectangle(SimpleMatrix<Character> grid, int xPos,
            int yPos, int height, int length) {
        int adjustedHeight = 0;
        int adjustedlength = 0;
        if (height % 2 != 0) {
            adjustedHeight = height + 1;
        } else {
            adjustedHeight = height;
        }

        if (length % 2 != 0) {
            adjustedlength = length + 1;
        } else {
            adjustedlength = length;
        }

        for (int y = yPos - (adjustedHeight / 2); y < yPos
                + (adjustedHeight / 2); y++) {
            for (int x = xPos - (adjustedlength / 2); x < xPos
                    + (adjustedlength / 2); x++) {

                if (y <= grid.rows() && y >= 1 && x < grid.columns()
                        && x >= 1) {
                    if (grid.element(y, x) == ' ') {
                        grid.setElement(y, x, '■');
                    }
                }
            }
        }

    }

    /**
     * Checks to see if the two numbers are equal within an interval.
     *
     * @param firstNumber
     *            The first number to be checked
     * @param secondNumber
     *            The second number to be checked
     * @param interval
     *            The interval
     * @return true if the two numbers are within the interval of each other
     */
    private static boolean withinPlusMinusInterval(int firstNumber,
            int secondNumber, int interval) {
        return ((firstNumber <= secondNumber + interval)
                && (firstNumber >= secondNumber - interval));
    }

    /**
     * Idk why the regular power function.. doesn't work.
     *
     * @param x
     *            The number to compute
     * @param p
     *            the power
     * @return x^p
     */
    private static int power(int x, int p) {
        int result = 1;
        for (int i = 0; i < p; i++) {
            result *= x;
        }
        return result;
    }

    /**
     * Prints the grid to a SimpleWriter terminal.
     *
     * @param grid
     *            The grid to be printed
     * @param out
     *            The stream to print the grid to
     */
    private static void printGrid(SimpleMatrix<Character> grid,
            SimpleWriter out) {
        int maxNumberLength = String.valueOf(grid.rows()).length();
        for (int y = grid.rows(); y >= 1; y--) {

            out.print((y) + numberOfString(
                    maxNumberLength - String.valueOf(y).length() + 1, " ")
                    + "| ");
            for (int x = 1; x <= grid.columns(); x++) {
                out.print(grid.element(y, x) + " ");
            }
            out.println();
        }
        out.print(numberOfString(maxNumberLength, " ") + " |"
                + numberOfString(grid.columns() * 2, "—"));
        out.println();
    }

    /**
     * Returns the number of the string provided as one string.
     *
     * @param numOfSpaces
     *            The number of spaces.
     * @param s
     *            the string to be repeated
     * @return The number of spaces requested.
     */
    private static String numberOfString(int numOfSpaces, String s) {
        String string = "";
        for (int i = 0; i < numOfSpaces; i++) {
            string = string.concat(s);
        }
        return string;
    }

    /**
     * Gathers a valid input from the user.
     *
     * @param out
     *            the SimpleWriter to communicate through.
     * @param in
     *            The input from the user
     * @param dimension
     *            The name of the dimension to be collected
     * @return The value of the user that is valid
     */
    private static int getValidInput(SimpleWriter out, SimpleReader in,
            String dimension) {
        out.print(
                "What would you like the " + dimension + " dimension to be: ");
        String input = in.nextLine();
        while (!FormatChecker.canParseInt(input)) {
            out.print("What would you like the " + dimension
                    + " dimension to be, enter an integer: ");
            input = in.nextLine();
        }
        return Integer.parseInt(input);
    }

    /**
     * Repeatedly asks the user for an input until they enter a positive
     * integer.
     *
     * @param out
     *            The output stream
     * @param in
     *            The input stream
     * @return a positive integer input by the user.
     */
    private static int getPositiveInteger(SimpleWriter out, SimpleReader in) {
        out.print("enter a positive integer: ");
        String input = in.nextLine();
        while (!isParseablePositiveInt(input)) {
            out.print("Enter a positive integer: ");
            input = in.nextLine();
        }
        return Integer.parseInt(input);
    }

    /**
     * Repeatedly asks the user for an input until they enter an integer.
     *
     * @param out
     *            The output stream
     * @param in
     *            The input stream
     * @return an integer input by the user.
     */
    private static int getInteger(SimpleWriter out, SimpleReader in) {
        out.print("enter an integer: ");
        String input = in.nextLine();
        while (!FormatChecker.canParseInt(input)) {
            out.print("Enter a positive integer: ");
            input = in.nextLine();
        }
        return Integer.parseInt(input);
    }

    /**
     * Tests the string to see if it is a parseable positive integer.
     *
     * @param input
     *            The string to be tested
     * @return true if the string is an integer and positive.
     */
    private static boolean isParseablePositiveInt(String input) {
        boolean result = false;
        if (FormatChecker.canParseInt(input)) {
            if (Integer.parseInt(input) >= 0) {
                result = true;
            }
        }
        return result;
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
        int xDimension = getValidInput(out, in, "x") + 1;
        int yDimension = getValidInput(out, in, "y") + 1;
        SimpleMatrix<Character> grid = new SimpleMatrix1L<Character>(yDimension,
                xDimension);
        clearGrid(grid);
        String input = "";

        while (!input.toLowerCase().equals("stop")) {
            out.print("Options: circle, clear, stop, print, rectangle, line: ");
            input = in.nextLine();
            if (input.toLowerCase().equals("circle")) {
                out.print("For the circle's x position, ");
                int xPosition = getInteger(out, in);
                out.print("For the circle's y position, ");
                int yPosition = getInteger(out, in);
                out.print("For the circle's radius, ");
                int radius = getPositiveInteger(out, in);
                addCircle(grid, xPosition, yPosition, radius);
                printGrid(grid, out);
            } else if (input.toLowerCase().equals("clear")) {
                clearGrid(grid);
                printGrid(grid, out);
            } else if (input.toLowerCase().equals("print")) {
                printGrid(grid, out);
            } else if (input.toLowerCase().equals("rectangle")) {
                out.print("For the rectangle's x position, ");
                int xPosition = getInteger(out, in);
                out.print("For the rectangle's y position, ");
                int yPosition = getInteger(out, in);
                out.print("For the rectangle's width, ");
                int length = getPositiveInteger(out, in);
                out.print("For the rectangle's height, ");
                int height = getPositiveInteger(out, in);
                addRectangle(grid, xPosition, yPosition, height, length);
                printGrid(grid, out);
            } else if (input.toLowerCase().equals("line")) {
                out.print("For the line's y-intercept, ");
                int yintercept = getInteger(out, in);
                out.print("For the line's slope, ");
                int slope = getInteger(out, in);
                addLine(grid, slope, yintercept);
                printGrid(grid, out);
            }

        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
