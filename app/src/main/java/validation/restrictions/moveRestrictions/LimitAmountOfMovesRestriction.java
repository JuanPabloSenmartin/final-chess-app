package validation.restrictions.moveRestrictions;

import chess.Move;
import chess.board.Board;
import validation.restrictions.Restriction;

public class LimitAmountOfMovesRestriction implements Restriction {
    private final int n;
    public LimitAmountOfMovesRestriction(int n){
        this.n = n;
    }
    @Override
    public boolean validate(Board board, Move move) {
        return board.getPosition(move.initialPosition).getPiece().getAmountOfMoves() <= n;
    }
}
