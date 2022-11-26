package validation.restrictions.moveRestrictions;

import chess.Move;
import chess.board.Board;
import validation.restrictions.Restriction;

public class DiagonalMoveRestriction implements Restriction {
    @Override
    public boolean validate(Board board, Move move) {
        return validateDiagonalMovement(move) && validateNoPieceCrash(board, move);
    }

    private boolean validateNoPieceCrash(Board board, Move move) {
        int initialRow = move.initialPosition.row;
        int initialCol = move.initialPosition.column;
        int finalRow = move.finalPosition.row;
        int finalCol = move.finalPosition.column;

        if(finalRow < initialRow && finalCol < initialCol){
            for (int i = initialRow - 1, j = initialCol - 1; i > finalRow;i-- , j--) {
                if(!board.getPosition(i,j).isEmpty()) return false;
            }
        }
        else if(finalRow < initialRow && finalCol > initialCol){
            for (int i = initialRow - 1, j = initialCol + 1; i > finalRow; i--, j++) {
                if(!board.getPosition(i,j).isEmpty()) return false;
            }
        }
        else if(finalRow > initialRow && finalCol < initialCol ){
            for (int i = initialRow + 1, j = j = initialCol - 1 ; i < finalRow ; i ++ , j--) {
                if(!board.getPosition(i,j).isEmpty()) return false;
            }
        }
        else if(finalRow > initialRow && finalCol > initialCol ){
            for (int i = initialRow + 1, j = j = initialCol + 1 ; i < finalRow ; i ++ , j++) {
                if(!board.getPosition(i,j).isEmpty()) return false;
            }
        }
        return true;
    }

    private boolean validateDiagonalMovement(Move move) {
        return  Math.abs(move.initialPosition.row-move.finalPosition.row) == Math.abs(move.initialPosition.column-move.finalPosition.column);
    }


}
