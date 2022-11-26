package validation.restrictions.moveRestrictions;

import chess.Move;
import chess.board.Board;
import validation.restrictions.Restriction;

public class HorizontalMoveRestriction implements Restriction {

    @Override
    public boolean validate(Board board, Move move) {
        return validateHorizontalMovement(move) && validateNoPieceCrash(board, move);
    }

    private boolean validateNoPieceCrash(Board board, Move move) {
        int row = move.finalPosition.row;
        //left movement
        if(move.finalPosition.column < move.initialPosition.column){
            for (int i = move.initialPosition.column - 1; i > move.finalPosition.column; i--) {
                if(!board.getPosition(row,i).isEmpty()) return false;
            }
        }
        //right movement
        else {
            for (int i = move.initialPosition.column + 1; i < move.finalPosition.column; i++) {
                if(!board.getPosition(row,i).isEmpty()) return false;
            }
        }
        return true;
    }

    private boolean validateHorizontalMovement(Move move) {
        return move.initialPosition.row == move.finalPosition.row && move.initialPosition.column != move.finalPosition.column;
    }
}
