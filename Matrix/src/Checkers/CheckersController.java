package Checkers;

public interface CheckersController {

    enum Direction {
        UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT, UP_LEFT_JUMP, UP_RIGHT_JUMP, DOWN_LEFT_JUMP, DOWN_RIGHT_JUMP
    }

    void processHoverEvent();

    void movePiece(Piece pre, Piece post);

    void kill(Piece piece);

    void king(Piece piece);

    public void processClickEvent(Piece p);

}
