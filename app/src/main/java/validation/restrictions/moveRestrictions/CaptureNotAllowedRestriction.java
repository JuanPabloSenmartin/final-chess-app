package validation.restrictions.moveRestrictions;

import chess.Move;
import chess.board.Board;
import validation.restrictions.Restriction;

public class CaptureNotAllowedRestriction implements Restriction {
    @Override
    public boolean validate(Board board, Move move) {
        return board.getPosition(move.finalPosition).isEmpty();
    }
}
