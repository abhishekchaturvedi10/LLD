package Chess.pieces;

import Chess.Color;

import java.util.List;

public class Rook extends Piece {

    public Rook(Color color, int row, int col) {
        super(color, List.of(List.of(0,1), List.of(0,-1), List.of(1,0), List.of(-1,0)), true, row, col);
    }
}
