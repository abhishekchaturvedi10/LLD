package Chess.pieces;

import Chess.Color;

import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color, int row, int col) {
        super(color, color == Color.BLACK ? List.of(List.of(1,0)) : List.of(List.of(-1,0)), false, row, col);
    }
}
