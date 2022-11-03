package chess.board;

import chess.Position;

public class Board {
    private Position[][] board;
    private final int amountOfRows;
    private final int amountOfColumns;

    public Board(Position[][] board) {
        this.board = board;
        this.amountOfRows = board.length;
        this.amountOfColumns = board[0].length;
    }
    public Position[][] getBoard(){
        return this.board;
    }

    public int getAmountOfRows() {
        return amountOfRows;
    }

    public int getAmountOfColumns() {
        return amountOfColumns;
    }
}
