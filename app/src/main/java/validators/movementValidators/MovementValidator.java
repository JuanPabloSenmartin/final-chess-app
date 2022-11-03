package validators.movementValidators;

import chess.Position;
import chess.board.Board;
import pieces.Piece;

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

}
