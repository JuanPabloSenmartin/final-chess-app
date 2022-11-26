package validation.restrictions.moveRestrictions;

import chess.Move;
import chess.board.Board;
import validation.restrictions.Restriction;

public class LimitMoveRestriction implements Restriction {
    private final int n;
    public LimitMoveRestriction(int n) {
        this.n = n;
    }

    @Override
    public boolean validate(Board board, Move move) {
        return Math.abs(move.initialPosition.row - move.finalPosition.row) <= n && Math.abs(move.initialPosition.column - move.finalPosition.column) <= n;
    }
}
