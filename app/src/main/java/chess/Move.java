package chess;

import chess.Position;

public class Move {
    public Square initialPosition;
    public Square finalPosition;
    public boolean whiteTurn;

    public Move(Square initialPosition, Square finalPosition, boolean whiteTurn) {
        this.initialPosition = initialPosition;
        this.finalPosition = finalPosition;
        this.whiteTurn = whiteTurn;
    }
    
}
