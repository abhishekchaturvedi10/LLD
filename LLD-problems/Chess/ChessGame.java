package Chess;

import Chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ChessGame {

    public static final Board chessBoard = new Board();
    private final Player player1;
    private final Player player2;
    private int turn = 0;

    public ChessGame() {
        chessBoard.initializeBoard();
        player1 = new Player(Color.WHITE);
        player2 = new Player(Color.BLACK);
    }

    public void start() {
        System.out.println("Player 1 is white, Player 2 is black");
        while(!gameOver()) {
            if(turn % 2 == 0) {
                System.out.println("White's turn");
                Move move = getPlayerMove(player1);
                player1.makeMove(move);
            } else {
                System.out.println("Black's turn");
                Move move = getPlayerMove(player2);
                player2.makeMove(move);
            }
            turn++;
        }
    }

    private Move getPlayerMove(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter source row: ");
        int sourceRow = scanner.nextInt();
        System.out.print("Enter source column: ");
        int sourceCol = scanner.nextInt();
        System.out.print("Enter destination row: ");
        int destRow = scanner.nextInt();
        System.out.print("Enter destination column: ");
        int destCol = scanner.nextInt();
        Piece piece = chessBoard.getCell(sourceRow, sourceCol).getPiece();
        if (piece == null || !player.getColor().equals(piece.getColor())) {
            throw new InvalidMoveException("You can only move your own pieces / source cell does not contain a piece, try again");
        }
        Move move = new Move(piece, sourceRow, sourceCol, destRow, destCol);
        ArrayList<Move> possibleMoves = piece.possibleMoves();
        while(!possibleMoves.contains(move)) {
            System.out.println("Invalid move, try again");
            move = getPlayerMove(player);
        }
        return move;
    }

    private boolean gameOver() {
        chessBoard.printBoard();
        boolean checkMate = checkMate();
        if(!checkMate) {
            return false;
        }
        Random random = new Random();
        int winner = random.nextInt(2);
        if(winner == 0) {
            System.out.println("White wins");
        } else {
            System.out.println("Black wins");
        }
        return true;
    }

    private boolean checkMate() {
        return turn == 4;
    }
}
