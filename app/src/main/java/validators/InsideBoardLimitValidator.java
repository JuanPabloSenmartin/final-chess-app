package validators;

import chess.Move;
import chess.board.Board;

public class InsideBoardLimitValidator {
    public boolean isInsideBoardLimit(Move move, Board board){
        int maxRow = board.getAmountOfRows()-1;
        int maxColumn = board.getAmountOfColumns()-1;
        return move.initialPosition.row <= maxRow && move.initialPosition.column <= maxColumn && move.initialPosition.row >= 0 && move.initialPosition.column >= 0 && move.finalPosition.row <= maxRow && move.finalPosition.column <= maxColumn && move.finalPosition.row >= 0 && move.finalPosition.column >= 0;
    }
    public boolean isInsideBoardLimit(int row, int col, int maxRow, int maxCol){
        return row <= maxRow && col <= maxCol && row >= 0 && col >= 0;
    }
}
