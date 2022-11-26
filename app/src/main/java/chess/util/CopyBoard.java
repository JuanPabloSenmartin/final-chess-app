package chess.util;

import chess.Position;
import chess.board.Board;
import pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class CopyBoard {
    public static Board copyBoard(Board board){
        List<Position> b = board.getBoard();
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < b.size(); i++) {
            Position p = b.get(i);
            if (p.isEmpty()) positions.add(new Position(p.getRow(), p.getColumn()));
            else positions.add(new Position(p.getRow(), p.getColumn(),new Piece(p.getPiece().getColor(), p.getPiece().getType())));
        }
        return new Board(positions, board.getAmountOfRows(), board.getAmountOfColumns());
    }
}
