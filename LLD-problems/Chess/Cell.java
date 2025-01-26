package Chess;

import Chess.pieces.Piece;

public class Cell {

    private Piece piece;

    public Cell() {
        this.piece = null;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
