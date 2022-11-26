package chess;

import pieces.Piece;

import java.util.Optional;

public class Position {
    private Optional<Piece> possiblePiece;
    private final int row;
    private final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.possiblePiece = Optional.empty();
    }
    public Position(int row, int column, Piece piece) {
        this.row = row;
        this.column = column;
        this.possiblePiece = Optional.of(piece);
    }

    public String toString(){
        if(this.possiblePiece.isPresent()) return possiblePiece.get().toString();
        else return "";
    }
    public boolean isEmpty(){
        return possiblePiece.isEmpty();
    }
    public Piece getPiece(){
        return possiblePiece.get();
    }
    public Optional<Piece> getPossiblePiece() {
        return possiblePiece;
    }

    public void setPiece(Piece piece) {
        this.possiblePiece = Optional.of(piece);
    }
    public void deletePiece(){
        this.possiblePiece = Optional.empty();
    }


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    public Square toSquare(){
        return new Square(row,column);
    }
}
