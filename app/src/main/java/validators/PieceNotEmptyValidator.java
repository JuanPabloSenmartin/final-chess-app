package validators;

import pieces.Piece;

import java.util.Optional;

public class PieceNotEmptyValidator {
    public boolean isPiecePresent(Optional<Piece> possiblePiece){
        return possiblePiece.isPresent();
    }
}
