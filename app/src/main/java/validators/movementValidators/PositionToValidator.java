package validators.movementValidators;

import chess.Color;
import pieces.Piece;
import pieces.Pieces;

import java.util.Optional;

public class PositionToValidator {
    //validates everything to do with the piece that is in the final position of a movement

    public boolean isValid(Optional<Piece> optionalPiece, boolean whiteTurn){
        Color turn = Color.BLACK;
        if (whiteTurn) turn = Color.WHITE;

        if (optionalPiece.isEmpty()) return true;
        return optionalPiece.get().getColor() != turn;
    }

}
