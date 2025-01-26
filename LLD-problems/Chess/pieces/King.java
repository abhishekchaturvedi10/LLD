package Chess.pieces;

import Chess.Color;

import java.util.List;

public class King extends Piece {

    public King(Color color, int row, int col) {
        super(color, List.of(List.of(0,1), List.of(0,-1), List.of(1,0), List.of(-1,0), List.of(1,1), List.of(1,-1), List.of(-1,1), List.of(-1,-1)), false, row, col);
    }
}
