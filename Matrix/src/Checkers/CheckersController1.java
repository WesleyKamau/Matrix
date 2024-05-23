package Checkers;

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

    Piece selected = null;

    @Override
    public void processClickEvent(Piece p) {
        if (this.selected == null) {
            if (!p.isEmpty) {
                this.selected = p;
            }
        } else {
            this.movePiece(this.selected, p);
            this.selected = null;
        }
        this.view.update();

    }

    Direction parseDirection(Piece pre, Piece post) {
        int i = this.model.getX(pre);
        int j = this.model.getY(pre);
        int iPost = this.model.getX(post);
        int jPost = this.model.getY(post);

        if (iPost == i + 1 && j - 1 == jPost) {
            return Direction.DOWN_LEFT;
        } else if (iPost == i + 1 && j + 1 == jPost) {
            return Direction.DOWN_RIGHT;
        } else if (iPost == i - 1 && j - 1 == jPost) {
            return Direction.UP_LEFT;
        } else if (iPost == i - 1 && j + 1 == jPost) {
            return Direction.UP_RIGHT;
        } else if (iPost == i + 2 && j - 2 == jPost) {
            return Direction.DOWN_LEFT_JUMP;
        } else if (iPost == i + 2 && j + 2 == jPost) {
            return Direction.DOWN_RIGHT_JUMP;
        } else if (iPost == i - 2 && j - 2 == jPost) {
            return Direction.UP_LEFT_JUMP;
        } else if (iPost == i - 2 && j + 2 == jPost) {
            return Direction.UP_RIGHT_JUMP;
        }
        return null;
    }

    private boolean isValidMove(Piece pre, Piece post) {
        int i = this.model.getX(pre);
        int j = this.model.getY(pre);
        int iPost = this.model.getX(post);
        int jPost = this.model.getY(post);

        if (iPost == i + 1 && j - 1 == jPost) {
            return true;
        } else if (iPost == i + 1 && j + 1 == jPost) {
            return true;
        } else if (iPost == i - 1 && j - 1 == jPost) {
            return true;
        } else if (iPost == i - 1 && j + 1 == jPost) {
            return true;
        } else if (iPost == i + 2 && j - 2 == jPost) {
            return true;
        } else if (iPost == i + 2 && j + 2 == jPost) {
            return true;
        } else if (iPost == i - 2 && j - 2 == jPost) {
            return true;
        } else if (iPost == i - 2 && j + 2 == jPost) {
            return true;
        }
        return false;

    }

    @Override
    public void movePiece(Piece pre, Piece post) {
        if (this.isValidMove(pre, post)) {
            this.model.move(pre, post);
        }
    }

    @Override
    public void kill(Piece piece) {
        piece.clear();
    }

    @Override
    public void king(Piece piece) {
        piece.king();
    }

}
