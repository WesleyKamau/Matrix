package Checkers;

import components.matrix.SimpleMatrix;
import components.matrix.SimpleMatrix1L;

/**
 * Model class.
 *
 * @author Put your name here
 */
public final class CheckersModel1 implements CheckersModel {

    private SimpleMatrix<Piece> board;

    private int BOARD_SIZE = 8;

    /**
     * No argument constructor.
     */
    public CheckersModel1() {
        this.board = new SimpleMatrix1L<Piece>();
        boolean startsOn = false;
        for (int i = 1; i <= this.BOARD_SIZE; i++) {
            boolean on = startsOn;
            for (int j = 1; j <= this.BOARD_SIZE; j++) {
                if (on) {
                    if (i == 1 || i == 2 || i == 3) {
                        this.board.setElement(i, j, new Piece(Piece.Color.RED));
                    } else if (i == 6 || i == 7 || i == 8) {
                        this.board.setElement(i, j,
                                new Piece(Piece.Color.BLACK));
                    } else {
                        this.board.setElement(i, j, new Piece());
                    }
                } else {
                    this.board.setElement(i, j, new Piece());
                }
                on = !on;
            }
            startsOn = !on;
        }
    }

    @Override
    public SimpleMatrix<Piece> board() {
        return this.board;
    }

    @Override
    public void move(Piece pre, Piece post) {
        post.transferFrom(pre);
    }

}
