package validation.restrictions;

import chess.Move;
import chess.board.Board;

public interface Restriction {
    boolean validate(Board board, Move move);
}
