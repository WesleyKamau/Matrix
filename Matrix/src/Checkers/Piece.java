package Checkers;

import components.standard.Standard;

public final class Piece implements Standard<Piece> {

    enum Color {
        RED, BLACK
    }

    Color color;
    boolean isKing;
    boolean isEmpty;

    public Piece(Color color) {
        this.isKing = false;
        this.color = color;
        this.isEmpty = false;
    }

    public Piece() {
        this.isKing = false;
        this.color = null;
        this.isEmpty = true;
    }

    public boolean isKing() {
        return this.isKing;
    }

    public Color color() {
        return this.color;
    }

    public void king() {
        this.isKing = true;
    }

    @Override
    public void clear() {
        this.isKing = false;
        this.color = null;
        this.isEmpty = true;
    }

    @Override
    public Piece newInstance() {
        return new Piece();
    }

    @Override
    public void transferFrom(Piece source) {
        this.isKing = source.isKing;
        this.color = source.color;
        this.isEmpty = source.isEmpty;
        source.clear();
    }

    @Override
    public String toString() {
        if (this.isEmpty) {
            return "Empty";
        } else {
            if (this.color.equals(Color.RED)) {
                return "Red Piece";
            } else {
                return "Black Piece";
            }
        }
    }

}
