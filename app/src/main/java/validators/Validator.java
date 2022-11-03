package validators;

import chess.Color;
import chess.Move;
import chess.Position;
import chess.board.Board;
import pieces.Piece;
import validators.movementValidators.MovementType;
import validators.movementValidators.MovementValidator;

import java.util.Optional;

public class Validator {
    private final HasPieceValidator hasPieceValidator;
    private final InsideBoardLimitValidator insideBoardLimitValidator;
    private final MovementValidator movementValidator;
    private final TurnColorValidator turnColorValidator;
    private final PieceNotEmptyValidator pieceNotEmptyValidator;

    public Validator() {
        this.hasPieceValidator = new HasPieceValidator();
        this.insideBoardLimitValidator = new InsideBoardLimitValidator();
        this.movementValidator = new MovementValidator();
        this.turnColorValidator = new TurnColorValidator();
        this.pieceNotEmptyValidator = new PieceNotEmptyValidator();
    }
    public boolean isInsideBoardLimit(Move move, Board board){
        return insideBoardLimitValidator.isInsideBoardLimit(move, board);
    }

    public boolean isPieceNotEmpty(Optional<Piece> possiblePiece){
        return pieceNotEmptyValidator.isPiecePresent(possiblePiece);
    }
    public boolean isTurnColorCorrect(Color pieceColor, boolean whiteTurn) {
        return turnColorValidator.isColorTurnCorrect(pieceColor, whiteTurn);
    }
    public boolean isMoveValid(Board board, Move move){
        //checks that piece in positionTo is different color
        if (!movementValidator.isPositionToValid(board.getBoard()[move.finalPosition.row][move.initialPosition.column].getPossiblePiece(),move.whiteTurn)) return false;

        return switch (board.getBoard()[move.initialPosition.row][move.initialPosition.column].getPiece().getType()) {
            case QUEEN -> isQueenMoveValid(board, move);
            case KING -> isKingMoveValid(board, move);
            case BISHOP -> isBishopMoveValid(board, move);
            case HORSE -> isHorseMoveValid(move);
            case ROOK -> isRookMoveValid(board, move);
            case PAWN -> isPawnMoveValid(board, move);
            default -> false;
        };
    }

    private boolean isPawnMoveValid(Board board, Move move) {
        return true;
    }

    private boolean isRookMoveValid(Board board, Move move) {
        //checks if piece crashes
        if(!movementValidator.noPieceCrash(board, new Position(move.finalPosition.row, move.finalPosition.column), new Position(move.initialPosition.row, move.initialPosition.column), MovementType.HORIZONTAL) || !movementValidator.noPieceCrash(board, new Position(move.finalPosition.row, move.finalPosition.column), new Position(move.initialPosition.row, move.initialPosition.column), MovementType.VERTICAL)) return false;
        //checks if movement is not straight
        if (!(move.initialPosition.row == move.finalPosition.row || move.initialPosition.column == move.finalPosition.column)) return  false;

        return true;
    }

    private boolean isHorseMoveValid(Move move) {
        return (Math.abs(move.initialPosition.row - move.finalPosition.row) == 1 && Math.abs(move.initialPosition.column - move.finalPosition.column) == 2) || (Math.abs(move.initialPosition.row - move.finalPosition.row) == 2 && Math.abs(move.initialPosition.column - move.finalPosition.column) == 1);
    }

    private boolean isBishopMoveValid(Board board, Move move) {
        //checks if piece crashes
        if(!movementValidator.noPieceCrash(board, new Position(move.finalPosition.row, move.finalPosition.column), new Position(move.initialPosition.row, move.initialPosition.column), MovementType.DIAGONAL)) return false;
        //checks if movement is not diagonal
        if (Math.abs(move.initialPosition.row-move.finalPosition.row) != Math.abs(move.initialPosition.column-move.finalPosition.column)) return  false;

        return true;
    }

    private boolean isKingMoveValid(Board board, Move move) {
        return true;
    }

    private boolean isQueenMoveValid(Board board, Move move){
        //checks if piece crashes
        if(!movementValidator.noPieceCrash(board, new Position(move.finalPosition.row, move.finalPosition.column), new Position(move.initialPosition.row, move.initialPosition.column), MovementType.DIAGONAL) || !movementValidator.noPieceCrash(board, new Position(move.finalPosition.row, move.finalPosition.column), new Position(move.initialPosition.row, move.initialPosition.column), MovementType.HORIZONTAL) || !movementValidator.noPieceCrash(board, new Position(move.finalPosition.row, move.finalPosition.column), new Position(move.initialPosition.row, move.initialPosition.column), MovementType.VERTICAL)) return false;
        //checks if movement is diagonal or straight
        if (!((Math.abs(move.initialPosition.row-move.finalPosition.row) == Math.abs(move.initialPosition.column-move.finalPosition.column)) || (move.initialPosition.row == move.finalPosition.row || move.initialPosition.column == move.finalPosition.column))) return  false;

        return true;
    }
}
