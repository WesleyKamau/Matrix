package VariableParser;

import components.linear.LinearVariable;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class VariableParser {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private VariableParser() {
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    private static void generateElements(String str, Set<Character> charSet) {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: charSet is not null";

        // Clear the initial set
        charSet.clear();

        /*
         * Converts the string to an array of characters and iterates over the
         * array, adding each of the characters to the set
         */
        for (char s : str.toCharArray()) {
            if (!charSet.contains(s)) {
                charSet.add(s);
            }
        }

    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    private static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        if (isOperator(text.charAt(position))) {
            return Character.toString(text.charAt(position));
        }

        String output = "";
        boolean searchingSeparatorString = false;
        for (char c : separators) {
            if (c == text.charAt(position)) {
                searchingSeparatorString = true;
            }
        }

        int length = text.length();
        boolean stopSearching = false;
        for (int i = position; i < length; i++) {
            boolean foundSeparator = false;
            for (char c : separators) {
                if (c == text.charAt(i)) {
                    foundSeparator = true;
                }
            }
            if (!stopSearching) {
                if (searchingSeparatorString) {
                    if (foundSeparator) {
                        output = output
                                .concat(Character.toString(text.charAt(i)));
                    } else {
                        stopSearching = true;
                    }
                } else {
                    if (!foundSeparator) {
                        output = output
                                .concat(Character.toString(text.charAt(i)));
                    } else {
                        stopSearching = true;
                    }
                }
            }
        }

        return output;

    }

    private static String despace(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (!Character.isSpaceChar(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static boolean isOperator(char c) {

        String operators = "*^+-()";
        for (char op : operators.toCharArray()) {
            if (op == c) {
                return true;
            }
        }
        return false;
    }

    private static Queue<String> tokenize(String input) {

        Queue<String> output = new Queue1L<String>();
        /*
         * Define separator characters for test
         */
        final String separatorStr = " \t*^+-()";
        Set<Character> separatorSet = new Set1L<Character>();
        generateElements(separatorStr, separatorSet);

        int position = 0;
        while (position < input.length()) {
            String token = nextWordOrSeparator(input, position, separatorSet);
            if (despace(token).length() > 0) {
                output.enqueue(despace(token));
            }
            position += token.length();
        }

        return output;
    }

    public static LinearVariable parseExpr(String input) {
        Queue<String> tokens = tokenize(despace(input));

        LinearVariable result = new LinearVariable();

        if (tokens.length() > 0) {
            result = parseTerm(tokens);
        }
        while (tokens.length() > 0) {
            if (tokens.dequeue().equals("+")) {
                result = result.add(parseTerm(tokens));
            } else {
                result = result.add(parseTerm(tokens).multiply(-1));
            }
        }

        return result;
    }

    private static LinearVariable parseVariable(Queue<String> tokens) {
        LinearVariable result = new LinearVariable();
        boolean product = false;
        tokens.dequeue(); // Get rid of "("
        if (!tokens.front().equals(")")) {
            result = parseTerm(tokens);
        }
        if (tokens.front().equals("*")) {
            product = true;
        }
        while (!tokens.front().equals(")")) {
            if (product) {
                tokens.dequeue(); // Get rid of "*"
                result = result.multiply(parseTerm(tokens));
            } else {
                if (tokens.dequeue().equals("+")) {
                    result = result.add(parseTerm(tokens));
                } else {
                    result = result.add(parseTerm(tokens).multiply(-1));
                }
            }
        }
        tokens.dequeue(); // Get rid of ")"
        return result;
    }

    private static LinearVariable parseTerm(Queue<String> tokens) {
        LinearVariable result = new LinearVariable();
        if (tokens.length() > 0) {
            if (FormatChecker.canParseDouble(tokens.front())) {
                String front = tokens.dequeue();
                // Either a Number or a variable with a coefficient
                if (tokens.length() > 0 && tokens.front().equals("*")) {
                    // Variable with a coefficient
                    tokens.dequeue(); // Get rid of "*"

                    if (tokens.length() > 0 && tokens.front().equals("(")) {
                        LinearVariable tempResult = parseVariable(tokens);

                        // Variable with coefficient with either an exponent or no exponent
                        if (tokens.length() > 0 && tokens.front().equals("^")) {
                            // Has an exponent
                            tokens.dequeue();
                            int power = Integer.parseInt(tokens.dequeue());
                            result = result.add(tempResult
                                    .multiply(Double.parseDouble(front))
                                    .power(power));
                        } else {
                            // Has no exponent
                            result = result.add(tempResult
                                    .multiply(Double.parseDouble(front)));
                        }
                    } else {
                        String variableName = tokens.dequeue();

                        // Variable with coefficient with either an exponent or no exponent
                        if (tokens.length() > 0 && tokens.front().equals("^")) {
                            // Has an exponent
                            tokens.dequeue();
                            int power = Integer.parseInt(tokens.dequeue());
                            result = result.add(Double.parseDouble(front),
                                    variableName, power);
                        } else {
                            // Has no exponent
                            result = result.add(Double.parseDouble(front),
                                    variableName);
                        }
                    }

                } else {
                    // Just a number
                    result = result.add(Double.parseDouble(front));
                }
            } else if (tokens.front().equals("(")) {
                LinearVariable tempResult = parseVariable(tokens);

                // Variable with coefficient with either an exponent or no exponent
                if (tokens.length() > 0 && tokens.front().equals("^")) {
                    // Has an exponent
                    tokens.dequeue();
                    int power = Integer.parseInt(tokens.dequeue());
                    result = result.add(tempResult.power(power));
                } else {
                    // Has no exponent
                    result = result.add(tempResult);
                }
            } else {
                // A variable with no coefficient
                String variableName = tokens.dequeue();

                // Variable with no coefficient with either an exponent or no exponent
                if (tokens.length() > 0 && tokens.front().equals("^")) {
                    // Has an exponent
                    tokens.dequeue();
                    int power = Integer.parseInt(tokens.dequeue());
                    result = result.add(variableName, power);
                } else {
                    // Has no exponent
                    result = result.add(variableName);
                }
            }
        }
        return result;
    }

    /**
     * Determines and returns if a String is a valid LinearVariable.
     *
     * @param input
     *            the String input
     * @return true if it is a valid LinearVariable.
     */
    public static boolean isValidTerm(String input) {

        Queue<String> tokens = tokenize(input);

        if (tokens.length() > 0) {
            String front = tokens.dequeue();
            if (FormatChecker.canParseDouble(front)) {
                if (tokens.length() > 0 && tokens.front().equals("*")) {
                    tokens.dequeue();

                    if (tokens.length() > 0) {
                        tokens.dequeue();
                    } else {
                        return false;
                    }

                    if (tokens.length() > 0 && tokens.front().equals("^")) {
                        tokens.dequeue();

                        if (tokens.length() > 0) {
                            if (!FormatChecker.canParseInt(tokens.dequeue())) {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }
            } else {
                if (tokens.length() > 0 && tokens.front().equals("^")) {
                    tokens.dequeue();

                    if (tokens.length() > 0) {
                        if (!FormatChecker.canParseInt(tokens.dequeue())) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }

            while (tokens.length() > 0) {
                tokens.dequeue();
                if (tokens.length() > 0) {
                    front = tokens.dequeue();
                    if (FormatChecker.canParseDouble(front)) {
                        if (tokens.length() > 0 && tokens.front().equals("*")) {
                            tokens.dequeue();

                            if (tokens.length() > 0) {
                                tokens.dequeue();
                            } else {
                                return false;
                            }

                            if (tokens.length() > 0
                                    && tokens.front().equals("^")) {
                                tokens.dequeue();

                                if (tokens.length() > 0) {
                                    if (!FormatChecker
                                            .canParseInt(tokens.dequeue())) {
                                        return false;
                                    }
                                } else {
                                    return false;
                                }
                            }
                        }
                    } else {
                        if (tokens.length() > 0 && tokens.front().equals("^")) {
                            tokens.dequeue();

                            if (tokens.length() > 0) {
                                if (!FormatChecker
                                        .canParseInt(tokens.dequeue())) {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        }
                    }

                } else {
                    return false;
                }
            }
        }

        return true;

    }

}
