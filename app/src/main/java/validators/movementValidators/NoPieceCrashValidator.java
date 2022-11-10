package validators.movementValidators;

import chess.Position;
import chess.board.Board;

public class NoPieceCrashValidator {
    public boolean isMovePossible(Board board, Position positionTo, Position positionFrom, MovementType movementType) {
        switch (movementType){
            case DIAGONAL -> {
                return isDiagonalMovePossible(board, positionTo, positionFrom);
            }
            case VERTICAL -> {
                return isVerticalMovePossible(board, positionTo, positionFrom);
            }
            case HORIZONTAL -> {
                return isHorizontalMovePossible(board, positionTo, positionFrom);
            }
        }
        return false;
    }
    private boolean isDiagonalMovePossible(Board board, Position positionTo, Position positionFrom){
        int initialRow = positionFrom.getRow();
        int initialCol = positionFrom.getColumn();
        int finalRow = positionTo.getRow();
        int finalCol = positionTo.getColumn();

        //up_left movement
        if(finalRow < initialRow && finalCol < initialCol){
            for (int i = initialRow - 1, j = initialCol - 1; i > finalRow;i-- , j--) {
                if(!board.getBoard()[i][j].isEmpty()) return false;
            }
        }
        //up_right movement
        if(finalRow < initialRow && finalCol > initialCol){
            for (int i = initialRow - 1, j = initialCol + 1; i > finalRow; i--, j++) {
                if(!board.getBoard()[i][j].isEmpty()) return false;
            }
        }
        //down_left movement
        if(finalRow > initialRow && finalCol < initialCol ){
            for (int i = initialRow + 1, j = j = initialCol - 1 ; i < finalRow ; i ++ , j--) {
                if(!board.getBoard()[i][j].isEmpty()) return false;
            }
        }
        //down_right movement
        if(finalRow > initialRow && finalCol > initialCol ){
            for (int i = initialRow + 1, j = j = initialCol + 1 ; i < finalRow ; i ++ , j++) {
                if(!board.getBoard()[i][j].isEmpty()) return false;
            }
        }
        return true;
    }
    private boolean isVerticalMovePossible(Board board, Position positionTo, Position positionFrom){
        int column = positionTo.getColumn();

        //down movement
        if(positionTo.getRow() < positionFrom.getRow()) {
            for (int i = positionFrom.getRow() - 1; i > positionTo.getRow(); i--) {
                if(!board.getBoard()[i][column].isEmpty()) return false;
            }
        }
        //up movement
        else {
            for (int i = positionFrom.getRow() + 1; i < positionTo.getRow(); i++) {
                if(!board.getBoard()[i][column].isEmpty()) return false;
            }
        }
        return true;
    }
    private boolean isHorizontalMovePossible(Board board, Position positionTo, Position positionFrom){
        int row = positionTo.getRow();

        //left movement
        if(positionTo.getColumn() < positionFrom.getColumn()){
            for (int i = positionFrom.getColumn() - 1; i > positionTo.getColumn(); i--) {
                if(!board.getBoard()[row][i].isEmpty()) return false;
            }
        }
        //right movement
        else {
            for (int i = positionFrom.getColumn() + 1; i < positionTo.getColumn(); i++) {
                if(!board.getBoard()[row][i].isEmpty()) return false;
            }
        }
        return true;
    }

}
