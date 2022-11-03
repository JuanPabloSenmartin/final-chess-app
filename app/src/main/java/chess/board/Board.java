package chess.board;

import chess.Position;
import chess.Square;
import pieces.Piece;

import java.util.Optional;

public class Board {
    private Position[][] board;
    private final int amountOfRows;
    private final int amountOfColumns;
    private Square whiteKingPosition;
    private Square blackKingPosition;

    public Board(Position[][] board) {
        this.board = board;
        this.amountOfRows = board.length;
        this.amountOfColumns = board[0].length;
        this.whiteKingPosition = new Square(0,4);
        this.blackKingPosition = new Square(7,4);
    }
    public Position[][] getBoard(){
        return this.board;
    }

    public int getAmountOfRows() {
        return amountOfRows;
    }

    public int getAmountOfColumns() {
        return amountOfColumns;
    }

    public Square getWhiteKingPosition() {
        return whiteKingPosition;
    }

    public void setWhiteKingPosition(Square whiteKingPosition) {
        this.whiteKingPosition = whiteKingPosition;
    }

    public Square getBlackKingPosition() {
        return blackKingPosition;
    }

    public void setBlackKingPosition(Square blackKingPosition) {
        this.blackKingPosition = blackKingPosition;
    }
    public void addPieceInPosition(int row, int col, Piece piece){
        this.board[row][col].setPiece(piece);
    }
    public void deletePieceInPosition(int row, int col){
        this.board[row][col].deletePiece();
    }
}
