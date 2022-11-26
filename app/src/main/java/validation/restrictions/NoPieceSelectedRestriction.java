package validation.restrictions;

import chess.Move;
import chess.board.Board;

public class NoPieceSelectedRestriction implements Restriction{
    @Override
    public boolean validate(Board board, Move move) {
        return !board.getPosition(move.initialPosition).isEmpty();
    }
}
