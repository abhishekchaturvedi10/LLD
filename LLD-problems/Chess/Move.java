package Chess;

import Chess.pieces.Piece;

import static Chess.ChessGame.chessBoard;

public record Move(Piece piece, int fromRow, int fromCol, int toRow, int toCol) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return fromRow == move.fromRow &&
                fromCol == move.fromCol &&
                toRow == move.toRow &&
                toCol == move.toCol &&
                piece.equals(move.piece);
    }

    public boolean isValidMove() {
        return toRow >= 0 && toRow < 8 &&
                toCol >= 0 && toCol < 8 &&
                (chessBoard.getCell(toRow, toCol).getPiece() == null ||
                !chessBoard.getCell(toRow, toCol).getPiece().getColor().equals(this.piece.getColor()));
    }
}
