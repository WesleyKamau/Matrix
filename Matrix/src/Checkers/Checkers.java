package Checkers;

public final class Checkers {

    /**
     * No argument private constructor so this utility class cannot be
     * instantiated.
     */
    private Checkers() {
    }

    /**
     * Main program that sets up main application window and starts user
     * interaction.
     *
     * @param args
     *            command-line arguments; not used
     */
    public static void main(String[] args) {
        /*
         * Create instances of the model, view, and controller objects;
         * controller needs to know about model and view, and view needs to know
         * about controller
         */
        CheckersModel model = new CheckersModel1();
        CheckersView view = new CheckersView1(model);
        CheckersController controller = new CheckersController1(model, view);

        view.registerObserver(controller);
    }

}
