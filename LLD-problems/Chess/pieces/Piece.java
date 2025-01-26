package Chess.pieces;

import Chess.Color;
import Chess.Move;

import java.util.ArrayList;
import java.util.List;


public abstract class Piece {

        private final Color color;
        private final List<List<Integer>> directions;
        private final boolean freeMover;
        protected int row;
        protected int col;

        public Piece(Color color, List<List<Integer>> directions, boolean freeMover, int row, int col) {
            this.color = color;
            this.directions = directions;
            this.freeMover = freeMover;
            this.row = row;
            this.col = col;
        }

        public Color getColor() {
            return color;
        }

        public ArrayList<Move> possibleMoves() {
            ArrayList<Move> moves = new ArrayList<>();
            for(List<Integer> direction : directions) {
                int newRow = this.row + direction.get(0);
                int newCol = this.col + direction.get(1);
                Move move = new Move(this, this.row, this.col, newRow, newCol);
                if (this.freeMover) {
                    while(move.isValidMove()) {
                        moves.add(move);
                        newRow += direction.get(0);
                        newCol += direction.get(1);
                        move = new Move(this, this.row, this.col, newRow, newCol);
                    }
                } else {
                    if(move.isValidMove()) {
                        moves.add(move);
                    }
                }
            }
            return moves;
        }

        public void move(Move move) {
            this.row = move.toRow();
            this.col = move.toCol();
        }
}
