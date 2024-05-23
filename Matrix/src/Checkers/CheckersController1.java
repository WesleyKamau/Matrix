package Checkers;

import Checkers.Checkers.Direction;

/**
 * Controller class.
 *
 * @author Put your name here
 */
public final class CheckersController1 implements CheckersController {

    /**
     * Model object.
     */
    private final CheckersModel model;

    /**
     * View object.
     */
    private final CheckersView view;

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(CheckersModel model,
            CheckersView view) {

        // TODO: fill in body

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public CheckersController1(CheckersModel model, CheckersView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processHoverEvent() {
        // TODO Auto-generated method stub

    }

    @Override
    public void movePiece(Piece piece, Direction direct) {
        // TODO Auto-generated method stub

    }

    @Override
    public void kill(Piece piece) {
        // TODO Auto-generated method stub

    }

    @Override
    public void king(Piece piece) {
        // TODO Auto-generated method stub

    }

}
