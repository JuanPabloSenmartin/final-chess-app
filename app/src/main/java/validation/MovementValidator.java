package validation;

import chess.Move;
import chess.board.Board;
import validation.restrictions.Restriction;

import java.util.List;

public class MovementValidator implements Restriction{
    private final List<Restriction> restrictions;

    public MovementValidator(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    @Override
    public boolean validate(Board board, Move move) {
        for (Restriction restriction : restrictions) {
            if(!restriction.validate(board, move)) {
                return false;
            }
        }
        return true;
    }
    public boolean validation(Board board, Move move){
        if(restrictions.isEmpty()) return true;
        for (Restriction restriction : restrictions) {
            if(restriction.validate(board, move)) {
                return true;
            }
        }
        return false;
    }
}
