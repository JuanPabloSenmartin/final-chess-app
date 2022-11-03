package pieces;

import chess.Color;

public class Piece {
    private static int count = 0;
    public long getId() {
        return id;
    }
    final long id;
    final Color color;
    Pieces type ;

    public Piece(Color color, Pieces type) {
        this.id = ++count;
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public Pieces getType() {
        return type;
    }
}
