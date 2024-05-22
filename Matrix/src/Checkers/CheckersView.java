package Checkers;

import java.awt.event.ActionListener;

public interface CheckersView extends ActionListener {

    /**
     * Register argument as observer/listener of this; this must be done first,
     * before any other methods of this class are called.
     *
     * @param controller
     *            controller to register
     */
    void registerObserver(CheckersController controller);

    void updateBoard();

}
