package chess.board;

import chess.Color;
import chess.Position;
import chess.Square;
import pieces.Piece;
import pieces.Pieces;

import java.util.List;


public class Board {
    private List<Position> board;
    private final int amountOfRows;
    private final int amountOfColumns;

    public Board(List<Position> board, int rows, int columns) {
        this.board = board;
        this.amountOfRows = rows;
        this.amountOfColumns = columns;
    }
    public List<Position> getBoard(){
        return this.board;
    }

    public Position getPosition(int row, int column){
        for(Position position : board){
            if (row == position.getRow() && column == position.getColumn()) return position;
        }
        return null;
    }
    public Position getPosition(Square square){
        for(Position position : board){
            if (square.row == position.getRow() && square.column == position.getColumn()) return position;
        }
        return null;
    }

    public void addPieceInPosition(Square square, Piece piece){
        Position position = getPosition(square.row, square.column);
        position.setPiece(piece);
    }
    public Position getKingPosition(Color color){
        for (Position position : board){
            if (!position.isEmpty() && position.getPiece().getColor() == color && position.getPiece().getType() == Pieces.KING) return position;
        }
        return null;
    }
    public void deletePieceInPosition(Square square){
        Position position = getPosition(square.row, square.column);
        position.deletePiece();
    }

    public int getAmountOfRows() {
        return amountOfRows;
    }

    public int getAmountOfColumns() {
        return amountOfColumns;
    }
    public int getMaxEdgeRow(){
        return amountOfRows - 1;
    }
    public int getMaxEdgeColumn(){
        return amountOfColumns - 1;
    }
}
