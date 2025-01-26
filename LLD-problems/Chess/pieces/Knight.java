package Chess.pieces;

import Chess.Color;

import java.util.List;

public class Knight extends Piece {

    public Knight(Color color, int row, int col) {
        super(color, List.of(List.of(-2,-1), List.of(-2,1), List.of(2,-1), List.of(2,1), List.of(-1,-2), List.of(1,-2), List.of(-1,2), List.of(1,2)), false, row, col);
    }
}
