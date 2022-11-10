package chess.util;

import chess.Position;
import chess.board.Board;
import pieces.Piece;

public class CopyBoard {
    public static Board copyBoard(Board board){
        Position[][] b = board.getBoard();
        Position[][] positions = new Position[board.getAmountOfRows()][board.getAmountOfColumns()];
        for (int i = 0; i < board.getAmountOfRows(); i++) {
            for (int j = 0; j < board.getAmountOfColumns(); j++) {
                Position p = b[i][j];
                if (p.isEmpty()) positions[i][j] = new Position(i,j);
                else positions[i][j] = new Position(i,j,new Piece(p.getPiece().getColor(), p.getPiece().getType()));
            }
        }
        return new Board(positions);
    }
}
