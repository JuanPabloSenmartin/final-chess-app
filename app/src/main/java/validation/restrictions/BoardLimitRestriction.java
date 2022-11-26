package validation.restrictions;

import chess.Move;
import chess.board.Board;

public class BoardLimitRestriction implements Restriction{

    @Override
    public boolean validate(Board board, Move move) {
        return !(move.finalPosition.row > board.getMaxEdgeRow() || move.finalPosition.column > board.getMaxEdgeColumn() || move.finalPosition.row < 0 || move.finalPosition.column < 0 );
    }
}
