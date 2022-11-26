package chess.board;

import chess.Color;
import chess.game.GameMode;
import chess.Position;
import pieces.Piece;
import pieces.Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BoardGenerator {
    public static Board createBoard(GameMode mode){
        List<Position> positions;
        int rows;
        int columns;
        switch (mode){
            case PRACTICE -> {
                rows = 8;
                columns = 8;
                positions = generateEmptyBoard(rows,columns);
            }
            case DISPLACED -> {
                rows = 8;
                columns = 8;
                positions = generateDisplacedBoard(rows,columns);
            }
            case FISCHER_RANDOM -> {
                rows = 8;
                columns = 8;
                positions = generateFischerRandomBoard(rows,columns);
            }
            case RETIRED -> {
                rows = 8;
                columns = 8;
                positions = generateRetiredBoard(rows,columns);
            }
            case CAPABLANCA -> {
                rows = 8;
                columns = 10;
                positions = generateCapablancaBoard(rows,columns);
            }
            default -> {
                rows = 8;
                columns = 8;
                positions = generateClassicBoard(rows,columns);
            }
        }
        return new Board(positions, rows, columns);
    }

    private static List<Position> generateEmptyBoard(int rows, int columns) {
        List<Position> emptyBoard = new ArrayList<>(rows * columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                emptyBoard.add(new Position(i,j));
            }
        }
        return emptyBoard;
    }

    private static List<Position> generateClassicBoard(int rows, int columns){
        List<Position> board = generateEmptyBoard(rows, columns);
        Position[][] positions = {
                {new Position(0,0, new Piece(Color.WHITE, Pieces.ROOK)),new Position(0,1, new Piece(Color.WHITE, Pieces.HORSE)), new Position(0,2, new Piece(Color.WHITE, Pieces.BISHOP)),new Position(0,3, new Piece(Color.WHITE, Pieces.QUEEN)),new Position(0,4, new Piece(Color.WHITE, Pieces.KING)), new Position(0,5, new Piece(Color.WHITE, Pieces.BISHOP)), new Position(0,6, new Piece(Color.WHITE, Pieces.HORSE)),new Position(0,7, new Piece(Color.WHITE, Pieces.ROOK))},
                {new Position(1,0, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,1, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,2, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,3, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,4, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,5, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,6, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,7, new Piece(Color.WHITE, Pieces.PAWN))},
                {new Position(2,0),new Position(2,1),new Position(2,2),new Position(2,3),new Position(2,4),new Position(2,5),new Position(2,6),new Position(2,7)},
                {new Position(3,0),new Position(3,1),new Position(3,2),new Position(3,3),new Position(3,4),new Position(3,5),new Position(3,6),new Position(3,7)},
                {new Position(4,0),new Position(4,1),new Position(4,2),new Position(4,3),new Position(4,4),new Position(4,5),new Position(4,6),new Position(4,7)},
                {new Position(5,0),new Position(5,1),new Position(5,2),new Position(5,3),new Position(5,4),new Position(5,5),new Position(5,6),new Position(5,7)},
                {new Position(6,0, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,1, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,2, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,3, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,4, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,5, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,6, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,7, new Piece(Color.BLACK, Pieces.PAWN))},
                {new Position(7,0, new Piece(Color.BLACK, Pieces.ROOK)),new Position(7,1, new Piece(Color.BLACK, Pieces.HORSE)), new Position(7,2, new Piece(Color.BLACK, Pieces.BISHOP)), new Position(7,3, new Piece(Color.BLACK, Pieces.QUEEN)), new Position(7,4, new Piece(Color.BLACK, Pieces.KING)), new Position(7,5, new Piece(Color.BLACK, Pieces.BISHOP)),new Position(7,6, new Piece(Color.BLACK, Pieces.HORSE)),new Position(7,7, new Piece(Color.BLACK, Pieces.ROOK))}
        };
        insertPieces(positions, board);
        return board;
    }
    private static List<Position> generateDisplacedBoard(int rows, int columns){
        List<Position> board = generateEmptyBoard(rows, columns);
        Position[][] positions ={
                {new Position(0,0, new Piece(Color.WHITE, Pieces.ROOK)),new Position(0,1, new Piece(Color.WHITE, Pieces.BISHOP)), new Position(0,2, new Piece(Color.WHITE, Pieces.BISHOP)),new Position(0,3, new Piece(Color.WHITE, Pieces.QUEEN)),new Position(0,4, new Piece(Color.WHITE, Pieces.KING)), new Position(0,5, new Piece(Color.WHITE, Pieces.HORSE)), new Position(0,6, new Piece(Color.WHITE, Pieces.HORSE)),new Position(0,7, new Piece(Color.WHITE, Pieces.ROOK))},
                {new Position(1,0, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,1, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,2, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,3, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,4, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,5, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,6, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,7, new Piece(Color.WHITE, Pieces.PAWN))},
                {new Position(2,0),new Position(2,1),new Position(2,2),new Position(2,3),new Position(2,4),new Position(2,5),new Position(2,6),new Position(2,7)},
                {new Position(3,0),new Position(3,1),new Position(3,2),new Position(3,3),new Position(3,4),new Position(3,5),new Position(3,6),new Position(3,7)},
                {new Position(4,0),new Position(4,1),new Position(4,2),new Position(4,3),new Position(4,4),new Position(4,5),new Position(4,6),new Position(4,7)},
                {new Position(5,0),new Position(5,1),new Position(5,2),new Position(5,3),new Position(5,4),new Position(5,5),new Position(5,6),new Position(5,7)},
                {new Position(6,0, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,1, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,2, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,3, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,4, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,5, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,6, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,7, new Piece(Color.BLACK, Pieces.PAWN))},
                {new Position(7,0, new Piece(Color.BLACK, Pieces.ROOK)),new Position(7,1, new Piece(Color.BLACK, Pieces.BISHOP)), new Position(7,2, new Piece(Color.BLACK, Pieces.BISHOP)), new Position(7,3, new Piece(Color.BLACK, Pieces.QUEEN)), new Position(7,4, new Piece(Color.BLACK, Pieces.KING)), new Position(7,5, new Piece(Color.BLACK, Pieces.HORSE)),new Position(7,6, new Piece(Color.BLACK, Pieces.HORSE)),new Position(7,7, new Piece(Color.BLACK, Pieces.ROOK))}
        };
        insertPieces(positions, board);
        return board;
    }
    private static List<Position> generateFischerRandomBoard(int rows, int columns){
        List<Position> board = generateEmptyBoard(rows, columns);
        Position[][] positions ={
                {new Position(0,0, new Piece(Color.WHITE, Pieces.ROOK)),new Position(0,1, new Piece(Color.WHITE, Pieces.BISHOP)), new Position(0,2, new Piece(Color.WHITE, Pieces.QUEEN)),new Position(0,3, new Piece(Color.WHITE, Pieces.KING)),new Position(0,4, new Piece(Color.WHITE, Pieces.BISHOP)), new Position(0,5, new Piece(Color.WHITE, Pieces.HORSE)), new Position(0,6, new Piece(Color.WHITE, Pieces.ROOK)),new Position(0,7, new Piece(Color.WHITE, Pieces.HORSE))},
                {new Position(1,0, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,1, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,2, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,3, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,4, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,5, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,6, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,7, new Piece(Color.WHITE, Pieces.PAWN))},
                {new Position(2,0),new Position(2,1),new Position(2,2),new Position(2,3),new Position(2,4),new Position(2,5),new Position(2,6),new Position(2,7)},
                {new Position(3,0),new Position(3,1),new Position(3,2),new Position(3,3),new Position(3,4),new Position(3,5),new Position(3,6),new Position(3,7)},
                {new Position(4,0),new Position(4,1),new Position(4,2),new Position(4,3),new Position(4,4),new Position(4,5),new Position(4,6),new Position(4,7)},
                {new Position(5,0),new Position(5,1),new Position(5,2),new Position(5,3),new Position(5,4),new Position(5,5),new Position(5,6),new Position(5,7)},
                {new Position(6,0, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,1, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,2, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,3, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,4, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,5, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,6, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,7, new Piece(Color.BLACK, Pieces.PAWN))},
                {new Position(7,0, new Piece(Color.BLACK, Pieces.ROOK)),new Position(7,1, new Piece(Color.BLACK, Pieces.BISHOP)), new Position(7,2, new Piece(Color.BLACK, Pieces.QUEEN)), new Position(7,3, new Piece(Color.BLACK, Pieces.KING)), new Position(7,4, new Piece(Color.BLACK, Pieces.BISHOP)), new Position(7,5, new Piece(Color.BLACK, Pieces.HORSE)),new Position(7,6, new Piece(Color.BLACK, Pieces.ROOK)),new Position(7,7, new Piece(Color.BLACK, Pieces.HORSE))}
        };
        insertPieces(positions, board);
        return board;
    }
    private static List<Position> generateRetiredBoard(int rows, int columns){
        List<Position> board = generateEmptyBoard(rows, columns);
        Position[][] positions ={
                {new Position(0,0),new Position(0,1), new Position(0,2),new Position(0,3),new Position(0,4, new Piece(Color.WHITE, Pieces.KING)), new Position(0,5), new Position(0,6),new Position(0,7)},
                {new Position(1,0, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,1, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,2, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,3, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,4, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,5, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,6, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,7, new Piece(Color.WHITE, Pieces.PAWN))},
                {new Position(2,0),new Position(2,1),new Position(2,2),new Position(2,3),new Position(2,4),new Position(2,5),new Position(2,6),new Position(2,7)},
                {new Position(3,0),new Position(3,1),new Position(3,2),new Position(3,3),new Position(3,4),new Position(3,5),new Position(3,6),new Position(3,7)},
                {new Position(4,0),new Position(4,1),new Position(4,2),new Position(4,3),new Position(4,4),new Position(4,5),new Position(4,6),new Position(4,7)},
                {new Position(5,0),new Position(5,1),new Position(5,2),new Position(5,3),new Position(5,4),new Position(5,5),new Position(5,6),new Position(5,7)},
                {new Position(6,0, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,1, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,2, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,3, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,4, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,5, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,6, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,7, new Piece(Color.BLACK, Pieces.PAWN))},
                {new Position(7,0),new Position(7,1), new Position(7,2), new Position(7,3), new Position(7,4, new Piece(Color.BLACK, Pieces.KING)), new Position(7,5),new Position(7,6),new Position(7,7)}
        };
        insertPieces(positions, board);
        return board;
    }
    private static List<Position> generateCapablancaBoard(int rows, int columns){
        List<Position> board = generateEmptyBoard(rows, columns);
        Position[][] positions = {
                {new Position(0,0, new Piece(Color.WHITE, Pieces.ROOK)),new Position(0,1, new Piece(Color.WHITE, Pieces.HORSE)), new Position(0,2, new Piece(Color.WHITE, Pieces.ARCHBISHOP)), new Position(0,3, new Piece(Color.WHITE, Pieces.BISHOP)),new Position(0,4, new Piece(Color.WHITE, Pieces.QUEEN)),new Position(0,5, new Piece(Color.WHITE, Pieces.KING)), new Position(0,6, new Piece(Color.WHITE, Pieces.BISHOP)),new Position(0,7, new Piece(Color.WHITE, Pieces.CHANCELLOR)), new Position(0,8, new Piece(Color.WHITE, Pieces.HORSE)),new Position(0,9, new Piece(Color.WHITE, Pieces.ROOK))},
                {new Position(1,0, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,1, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,2, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,3, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,4, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,5, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,6, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,7, new Piece(Color.WHITE, Pieces.PAWN)), new Position(1,8, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,9, new Piece(Color.WHITE, Pieces.PAWN))},
                {new Position(2,0),new Position(2,1),new Position(2,2),new Position(2,3),new Position(2,4),new Position(2,5),new Position(2,6),new Position(2,7),new Position(2,8),new Position(2,9)},
                {new Position(3,0),new Position(3,1),new Position(3,2),new Position(3,3),new Position(3,4),new Position(3,5),new Position(3,6),new Position(3,7),new Position(3,8),new Position(3,9)},
                {new Position(4,0),new Position(4,1),new Position(4,2),new Position(4,3),new Position(4,4),new Position(4,5),new Position(4,6),new Position(4,7),new Position(4,8),new Position(4,9)},
                {new Position(5,0),new Position(5,1),new Position(5,2),new Position(5,3),new Position(5,4),new Position(5,5),new Position(5,6),new Position(5,7),new Position(5,8),new Position(5,9)},
                {new Position(6,0, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,1, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,2, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,3, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,4, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,5, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,6, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,7, new Piece(Color.BLACK, Pieces.PAWN)), new Position(6,8, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,9, new Piece(Color.BLACK, Pieces.PAWN))},
                {new Position(7,0, new Piece(Color.BLACK, Pieces.ROOK)),new Position(7,1, new Piece(Color.BLACK, Pieces.HORSE)), new Position(7,2, new Piece(Color.BLACK, Pieces.ARCHBISHOP)), new Position(7,3, new Piece(Color.BLACK, Pieces.BISHOP)),new Position(7,4, new Piece(Color.BLACK, Pieces.QUEEN)),new Position(7,5, new Piece(Color.BLACK, Pieces.KING)), new Position(7,6, new Piece(Color.BLACK, Pieces.BISHOP)),new Position(7,7, new Piece(Color.BLACK, Pieces.CHANCELLOR)), new Position(7,8, new Piece(Color.BLACK, Pieces.HORSE)),new Position(7,9, new Piece(Color.BLACK, Pieces.ROOK))}        };
        insertPieces(positions, board);
        return board;
    }
    private static void insertPieces(Position[][] positions, List<Position> board){
        int i = 0;
        int j = 0;
        for (int n = 0; n < board.size(); n++, j++) {
            Optional<Piece> optionalPiece = positions[i][j].getPossiblePiece();
            if (optionalPiece.isPresent()) board.get(n).setPiece(optionalPiece.get());
            if (j == positions[0].length-1){
                i++;
                j = -1;
            }
        }
    }
}
