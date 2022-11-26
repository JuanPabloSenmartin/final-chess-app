package validation.restrictions.moveRestrictions;

import chess.Move;
import chess.board.Board;
import validation.restrictions.Restriction;

public class HorseMoveRestriction implements Restriction {

    @Override
    public boolean validate(Board board, Move move) {
        return (Math.abs(move.initialPosition.row - move.finalPosition.row) == 1 && Math.abs(move.initialPosition.column - move.finalPosition.column) == 2) || (Math.abs(move.initialPosition.row - move.finalPosition.row) == 2 && Math.abs(move.initialPosition.column - move.finalPosition.column) == 1);
    }
}
