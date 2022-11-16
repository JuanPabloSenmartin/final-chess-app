package validators.movementValidators;

import chess.Color;
import chess.Position;
import chess.Square;
import chess.board.Board;
import pieces.Piece;

import java.util.List;
import java.util.Optional;

public class MovementValidator {
    private final NoPieceCrashValidator noPieceCrashValidator;
    private final PositionToValidator positionToValidator;
    private final KingCheckValidator kingCheckValidator;

    public MovementValidator() {
        this.noPieceCrashValidator = new NoPieceCrashValidator();
        this.positionToValidator = new PositionToValidator();
        this.kingCheckValidator = new KingCheckValidator();
    }
    public boolean noPieceCrash(Board board, Position positionTo, Position positionFrom, MovementType movementType){
        return noPieceCrashValidator.isMovePossible(board, positionTo, positionFrom, movementType);
    }
    public boolean isPositionToValid(Optional<Piece> optionalPiece, boolean whiteTurn){
        return positionToValidator.isValid(optionalPiece, whiteTurn);
    }
    public boolean isKingInSafePosition(Board b, Square newPosition, Color color){
        return kingCheckValidator.isKingSafeInPosition(b, newPosition, color);
    }
    public List<Square> getThreatsToKing(Board b, int row, int col, Color color){
        return kingCheckValidator.findThreatsToKing(b, row, col, color);
    }


}
