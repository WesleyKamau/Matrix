package Checkers;

import Checkers.Checkers.Direction;

public interface CheckersController {

    void processHoverEvent();

    void movePiece(Piece piece, Direction direct);

    void kill(Piece piece);

    void king(Piece piece);

}
