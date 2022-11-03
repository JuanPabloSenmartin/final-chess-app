package chess;

import chess.Position;

public class Move {
    public Square initialPosition;
    public Square finalPosition;

    public Move(Square initialPosition, Square finalPosition) {
        this.initialPosition = initialPosition;
        this.finalPosition = finalPosition;
    }
    
}
