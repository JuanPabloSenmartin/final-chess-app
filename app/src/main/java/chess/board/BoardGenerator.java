package chess.board;

import chess.Color;
import chess.GameMode;
import chess.Position;
import pieces.Piece;
import pieces.Pieces;

public class BoardGenerator {
    public static Position[][] createBoard(GameMode mode){
        switch (mode){
            case CLASSIC -> {
                return generateClassicBoard();
            }
            case PRACTICE -> {
                return generateEmptyBoard();
            }
            default -> {
                return generateClassicBoard();
            }
        }
    }

    private static Position[][] generateEmptyBoard() {
        return new Position[][]{
                {new Position(0,0),new Position(0,1), new Position(0,2),new Position(0,3),new Position(0,4), new Position(0,5), new Position(0,6),new Position(0,7)},
                {new Position(1,0),new Position(1,1),new Position(1,2),new Position(1,3),new Position(1,4),new Position(1,5),new Position(1,6),new Position(1,7)},
                {new Position(2,0),new Position(2,1),new Position(2,2),new Position(2,3),new Position(2,4),new Position(2,5),new Position(2,6),new Position(2,7)},
                {new Position(3,0),new Position(3,1),new Position(3,2),new Position(3,3),new Position(3,4),new Position(3,5),new Position(3,6),new Position(3,7)},
                {new Position(4,0),new Position(4,1),new Position(4,2),new Position(4,3),new Position(4,4),new Position(4,5),new Position(4,6),new Position(4,7)},
                {new Position(5,0),new Position(5,1),new Position(5,2),new Position(5,3),new Position(5,4),new Position(5,5),new Position(5,6),new Position(5,7)},
                {new Position(6,0),new Position(6,1),new Position(6,2),new Position(6,3),new Position(6,4),new Position(6,5),new Position(6,6),new Position(6,7)},
                {new Position(7,0),new Position(7,1), new Position(7,2), new Position(7,3), new Position(7,4), new Position(7,5),new Position(7,6),new Position(7,7)}
        };
    }

    private static Position[][] generateClassicBoard(){
        return new Position[][]{
                {new Position(0,0, new Piece(Color.WHITE, Pieces.ROOK)),new Position(0,1, new Piece(Color.WHITE, Pieces.HORSE)), new Position(0,2, new Piece(Color.WHITE, Pieces.BISHOP)),new Position(0,3, new Piece(Color.WHITE, Pieces.QUEEN)),new Position(0,4, new Piece(Color.WHITE, Pieces.KING)), new Position(0,5, new Piece(Color.WHITE, Pieces.BISHOP)), new Position(0,6, new Piece(Color.WHITE, Pieces.HORSE)),new Position(0,7, new Piece(Color.WHITE, Pieces.ROOK))},
                {new Position(1,0, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,1, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,2, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,3, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,4, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,5, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,6, new Piece(Color.WHITE, Pieces.PAWN)),new Position(1,7, new Piece(Color.WHITE, Pieces.PAWN))},
                {new Position(2,0),new Position(2,1),new Position(2,2),new Position(2,3),new Position(2,4),new Position(2,5),new Position(2,6),new Position(2,7)},
                {new Position(3,0),new Position(3,1),new Position(3,2),new Position(3,3),new Position(3,4),new Position(3,5),new Position(3,6),new Position(3,7)},
                {new Position(4,0),new Position(4,1),new Position(4,2),new Position(4,3),new Position(4,4),new Position(4,5),new Position(4,6),new Position(4,7)},
                {new Position(5,0),new Position(5,1),new Position(5,2),new Position(5,3),new Position(5,4),new Position(5,5),new Position(5,6),new Position(5,7)},
                {new Position(6,0, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,1, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,2, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,3, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,4, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,5, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,6, new Piece(Color.BLACK, Pieces.PAWN)),new Position(6,7, new Piece(Color.BLACK, Pieces.PAWN))},
                {new Position(7,0, new Piece(Color.BLACK, Pieces.ROOK)),new Position(7,1, new Piece(Color.BLACK, Pieces.HORSE)), new Position(7,2, new Piece(Color.BLACK, Pieces.BISHOP)), new Position(7,3, new Piece(Color.BLACK, Pieces.QUEEN)), new Position(7,4, new Piece(Color.BLACK, Pieces.KING)), new Position(7,5, new Piece(Color.BLACK, Pieces.BISHOP)),new Position(7,6, new Piece(Color.BLACK, Pieces.HORSE)),new Position(7,7, new Piece(Color.BLACK, Pieces.ROOK))}
        };
    }
}
