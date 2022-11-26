package validation.restrictions;

import chess.Color;
import chess.Move;
import chess.board.Board;

public class MoveTurnRestriction implements Restriction{
    @Override
    public boolean validate(Board board, Move move) {
        Color color = board.getPosition(move.initialPosition).getPiece().getColor();
        return move.whiteTurn ? color == Color.WHITE : color == Color.BLACK;
    }
}
