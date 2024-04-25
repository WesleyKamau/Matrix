import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Cool Grid...
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
    private static void clearGrid(char[][] grid) {
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                grid[x][y] = ' ';
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
    private static void addCircle(char[][] grid, int xPos, int yPos,
            int radius) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == ' ') {
                    int equation1 = power((x - xPos), 2) + power((y - yPos), 2);
                    int equation2 = power(radius, 2);
                    if (withinPlusMinusInterval(equation1, equation2,
                            radius / 3)) {
                        grid[y][x] = '*';
                    } else if (withinPlusMinusInterval(equation1, equation2,
                            radius / 2)) {
                        grid[y][x] = '`';
                    } else if (withinPlusMinusInterval(equation1, equation2,
                            radius)) {
                        grid[y][x] = '⋅';
                    }
                }
            }
        }
    }

    private static void addRectangle(char[][] grid, int xPos, int yPos,
            int height, int length) {
        int adjustedHeight = 0;
        int adjustedlength = 0;
        if (height % 2 == 1) {
            adjustedHeight = height + 1;
        } else {
            adjustedHeight = height;
        }

        if (length % 2 == 1) {
            adjustedlength = length + 1;
        } else {
            adjustedlength = length;
        }

        for (int y = yPos - (adjustedHeight / 2); y < yPos
                + (adjustedHeight / 2); y++) {
            for (int x = xPos - (adjustedlength / 2); x < xPos
                    + (adjustedlength / 2); x++) {

                if (y < grid.length && y >= 0 && x < grid[y].length && x >= 0) {
                    if (grid[y][x] == ' ') {
                        grid[y][x] = '■';
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
    private static void printGrid(char[][] grid, SimpleWriter out) {
        int maxNumberLength = String.valueOf(grid.length).length();
        for (int y = grid.length - 1; y >= 0; y--) {

            out.print((y) + numberOfString(
                    maxNumberLength - String.valueOf(y).length() + 1, " ")
                    + "| ");
            for (int x = 0; x < grid[y].length; x++) {
                out.print(grid[y][x] + " ");
            }
            out.println();
        }
        out.print(numberOfString(maxNumberLength, " ") + " |"
                + numberOfString(grid[0].length * 2, "—"));
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
        char[][] grid = new char[yDimension][xDimension];
        clearGrid(grid);
        String input = "";

        while (!input.toLowerCase().equals("stop")) {
            out.print("Options: circle, clear, stop, print, rectangle: ");
            input = in.nextLine();
            if (input.toLowerCase().equals("circle")) {
                out.print("For the circle's x position, ");
                int xPosition = getPositiveInteger(out, in);
                out.print("For the circle's y position, ");
                int yPosition = getPositiveInteger(out, in);
                out.print("For the circle's radius, ");
                int radius = getPositiveInteger(out, in);
                addCircle(grid, xPosition, yPosition, radius);
                printGrid(grid, out);
                out.println();
            } else if (input.toLowerCase().equals("clear")) {
                clearGrid(grid);
                printGrid(grid, out);
                out.println();
            } else if (input.toLowerCase().equals("print")) {
                printGrid(grid, out);
                out.println();
            } else if (input.toLowerCase().equals("rectangle")) {
                out.print("For the rectangle's x position, ");
                int xPosition = getPositiveInteger(out, in);
                out.print("For the rectangle's y position, ");
                int yPosition = getPositiveInteger(out, in);
                out.print("For the rectangle's length, ");
                int length = getPositiveInteger(out, in);
                out.print("For the rectangle's height, ");
                int height = getPositiveInteger(out, in);
                addRectangle(grid, xPosition, yPosition, height, length);
                printGrid(grid, out);
                out.println();
            }

        }

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
