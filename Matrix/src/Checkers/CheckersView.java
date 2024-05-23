package Checkers;

import java.awt.event.ActionListener;

/**
 * View interface.
 *
 * @author Bruce W. Weide
 */
public interface CheckersView extends ActionListener {

    /**
     * Register argument as observer/listener of this; this must be done first,
     * before any other methods of this class are called.
     *
     * @param controller
     *            controller to register
     */
    void registerObserver(CheckersController controller);

    void showPossibleMoves(int x, int y);

    void clearPossibleMoves();

    void winner(String playerName);

    public void update();

}