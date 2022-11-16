package pieces;

import chess.Color;

public class Piece {
    private static int count = 0;
    public long getId() {
        return id;
    }
    final long id;
    private int amountOfMoves;
    final Color color;
    Pieces type ;

    public Piece(Color color, Pieces type) {
        this.id = ++count;
        this.color = color;
        this.type = type;
        this.amountOfMoves = 0;
    }
    public Piece(Color color, Pieces type, long id) {
        this.id = id;
        this.color = color;
        this.type = type;
        this.amountOfMoves = 0;
    }

    public Color getColor() {
        return color;
    }

    public Pieces getType() {
        return type;
    }

    public int getAmountOfMoves() {
        return amountOfMoves;
    }

    public void addMove() {
        this.amountOfMoves = amountOfMoves + 1;
    }
}
