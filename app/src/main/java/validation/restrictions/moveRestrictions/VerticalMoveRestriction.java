package validation.restrictions.moveRestrictions;

import chess.Move;
import chess.board.Board;
import validation.restrictions.Restriction;

public class VerticalMoveRestriction implements Restriction {
    @Override
    public boolean validate(Board board, Move move) {
        return validateVerticalMovement(move) && validateNoPieceCrash(board, move);
    }

    private boolean validateNoPieceCrash(Board board, Move move) {
        int column = move.finalPosition.column;
        if(move.finalPosition.row < move.initialPosition.row) {
            for (int i = move.initialPosition.row - 1; i > move.finalPosition.row; i--) {
                if(!board.getPosition(i,column).isEmpty()) return false;
            }
        }
        else {
            for (int i = move.initialPosition.row + 1; i < move.finalPosition.row; i++) {
                if(!board.getPosition(i,column).isEmpty()) return false;
            }
        }
        return true;
    }

    private boolean validateVerticalMovement(Move move) {
        return move.initialPosition.column == move.finalPosition.column && move.initialPosition.row != move.finalPosition.row;
    }
}
