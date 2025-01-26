package Chess;

import static Chess.ChessGame.chessBoard;

public record Player(Color color) {

    public void makeMove(Move move) {
        Cell fromCell = chessBoard.getCell(move.fromRow(), move.fromCol());
        fromCell.getPiece().move(move);
        fromCell.setPiece(null);
        Cell toCell = chessBoard.getCell(move.toRow(), move.toCol());
        toCell.setPiece(move.piece());
    }

    public Color getColor() {
        return color;
    }
}
