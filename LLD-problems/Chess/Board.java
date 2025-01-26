package Chess;

import Chess.pieces.*;

import java.util.ArrayList;

public class Board {

    private final ArrayList<ArrayList<Cell>> chessBoard = new ArrayList<>();

    public void initializeBoard() {
        for(int i = 0; i < 8; i++) {
            ArrayList<Cell> row = new ArrayList<>();
            for(int j = 0; j < 8; j++) {
                row.add(new Cell());
            }
            chessBoard.add(row);
        }
        chessBoard.getFirst().getFirst().setPiece(new Rook(Color.BLACK, 0, 0));
        chessBoard.getFirst().get(1).setPiece(new Knight(Color.BLACK, 0, 1));
        chessBoard.getFirst().get(2).setPiece(new Bishop(Color.BLACK, 0, 2));
        chessBoard.getFirst().get(3).setPiece(new Queen(Color.BLACK, 0, 3));
        chessBoard.getFirst().get(4).setPiece(new King(Color.BLACK, 0, 4));
        chessBoard.getFirst().get(5).setPiece(new Bishop(Color.BLACK, 0, 5));
        chessBoard.getFirst().get(6).setPiece(new Knight(Color.BLACK, 0, 6));
        chessBoard.getFirst().getLast().setPiece(new Rook(Color.BLACK, 0, 7));
        for(int i = 0; i < 8; i++) {
            chessBoard.get(1).get(i).setPiece(new Pawn(Color.BLACK, 1, i));
        }
        chessBoard.getLast().getFirst().setPiece(new Rook(Color.WHITE, 7, 0));
        chessBoard.getLast().get(1).setPiece(new Knight(Color.WHITE, 7, 1));
        chessBoard.getLast().get(2).setPiece(new Bishop(Color.WHITE, 7, 2));
        chessBoard.getLast().get(3).setPiece(new Queen(Color.WHITE, 7, 3));
        chessBoard.getLast().get(4).setPiece(new King(Color.WHITE, 7, 4));
        chessBoard.getLast().get(5).setPiece(new Bishop(Color.WHITE, 7, 5));
        chessBoard.getLast().get(6).setPiece(new Knight(Color.WHITE, 7, 6));
        chessBoard.getLast().getLast().setPiece(new Rook(Color.WHITE, 7, 7));
        for(int i = 0; i < 8; i++) {
            chessBoard.get(6).get(i).setPiece(new Pawn(Color.WHITE, 6, i));
        }
    }

    public Cell getCell(int x, int y) {
        return chessBoard.get(x).get(y);
    }

    public void printBoard() {
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = chessBoard.get(i).get(j).getPiece();
                if (piece != null) {
                    System.out.print(piece.getColor().equals(Color.BLACK) ? piece.getClass().getSimpleName().charAt(0) + " " : piece.getClass().getSimpleName().toLowerCase().charAt(0) + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------");
    }
}
