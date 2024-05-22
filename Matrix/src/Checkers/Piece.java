package Checkers;
public final class Piece {

    enum Color {
        RED, BLACK
    }

    Color color;
    boolean isKing;

    /**
     * No argument constructor--private to prevent instantiation.
     */
    public Piece(Color color) {
        this.isKing = false;
        this.color = color;
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
}
