package Chess.pieces;

import Chess.Color;

import java.util.List;

public class Bishop extends Piece {

    public Bishop(Color color, int row, int col) {
        super(color, List.of(List.of(-1,-1), List.of(-1,1), List.of(1,-1), List.of(1,1)), true, row, col);
    }
}
