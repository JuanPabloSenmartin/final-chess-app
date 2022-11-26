package validation.restrictions.moveRestrictions;

import chess.Move;
import chess.board.Board;
import validation.restrictions.Restriction;

public class ForwardRestriction implements Restriction {
    @Override
    public boolean validate(Board board, Move move) {
        if (move.whiteTurn){
            return move.initialPosition.row < move.finalPosition.row;
        }
        else{
            return move.initialPosition.row > move.finalPosition.row;
        }
    }
}
