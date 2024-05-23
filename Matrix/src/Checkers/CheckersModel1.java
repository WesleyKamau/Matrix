package Checkers;

import components.matrix.SimpleMatrix;
import components.matrix.SimpleMatrix1L;

/**
 * Model class.
 *
 * @author Put your name here
 */
public final class CheckersModel1 implements CheckersModel {

    private static

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
    public int getX(Piece piece) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (piece.equals(this.board().element(i, j))) {
                    return i;
                }
            }
        }
        return 0;
    }

    @Override
    public int getY(Piece piece) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (piece.equals(this.board().element(i, j))) {
                    return j;
                }
            }
        }
        return 0;
    }

    @Override
    public void move(Piece pre, Piece post) {
        Piece temp = new Piece();
        temp.transferFrom(pre);
        pre.transferFrom(post);
        post.transferFrom(temp);
        System.out.print(this.getY(post));

        if (!post.isKing) {
            int j = this.getY(post);
            if (post.color.equals(Piece.Color.RED)) {
                if (j == 8) {
                    post.king();
                }
            } else {
                if (j == 1) {
                    post.king();
                }
            }
        }

    }

}
