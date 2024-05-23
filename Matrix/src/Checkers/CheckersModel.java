package Checkers;

import components.matrix.SimpleMatrix;

public interface CheckersModel {
    SimpleMatrix<Piece> board();

    public void move(Piece pre, Piece post);

    int getX(Piece piece);

    int getY(Piece piece);
}
