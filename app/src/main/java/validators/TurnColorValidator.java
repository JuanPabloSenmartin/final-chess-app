package validators;

import chess.Color;


public class TurnColorValidator {
    public boolean isColorTurnCorrect(Color pieceColor, boolean whiteTurn){
        return (pieceColor == Color.WHITE && whiteTurn) || (pieceColor == Color.BLACK && !whiteTurn);
    }
}
