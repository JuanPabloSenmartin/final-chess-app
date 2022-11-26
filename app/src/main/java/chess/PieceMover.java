package chess;

import chess.board.Board;
import pieces.Piece;
import pieces.Pieces;


public class PieceMover {
    private final Board board;
    public PieceMover(Board board){
        this.board = board;
    }
    public void movePiece(Move move){
        if (isCastle(move)) manageCastle(move);
        if (isPromotion(move)) {
            managePromotion(move);
            return;
        }
        move(move);
    }

    private boolean isPromotion(Move move) {
        return board.getPosition(move.initialPosition).getPiece().getType() == Pieces.PAWN && (move.finalPosition.row == 0 || move.finalPosition.row == board.getMaxEdgeRow());
    }

    private void managePromotion(Move move) {
        Piece pawn = board.getPosition(move.initialPosition).getPiece();
        Piece queen = new Piece(pawn.getColor(), Pieces.QUEEN, pawn.getId());
        pawn.addMove();
        board.addPieceInPosition(move.finalPosition, queen);
        board.deletePieceInPosition(move.initialPosition);
    }

    private void manageCastle(Move move) {
        int initialCol = move.initialPosition.column;
        int finalCol = move.finalPosition.column;
        int row = move.initialPosition.row;
        int dif = Math.abs(move.initialPosition.column - move.finalPosition.column);
        Move rookMove;
        if (initialCol > finalCol){
            rookMove = new Move(new Square(row, 0), new Square(row, dif+1), move.whiteTurn);
        }
        else {
            rookMove = new Move(new Square(row, board.getMaxEdgeColumn()), new Square(row, board.getMaxEdgeColumn()-dif), move.whiteTurn);
        }
        move(rookMove);
    }

    private boolean isCastle(Move move) {
        return board.getPosition(move.initialPosition).getPiece().getType() == Pieces.KING && Math.abs(move.initialPosition.column - move.finalPosition.column) > 1;
    }
    private void move(Move move){
        Piece piece = board.getPosition(move.initialPosition).getPiece();
        piece.addMove();
        board.addPieceInPosition(move.finalPosition, piece);
        board.deletePieceInPosition(move.initialPosition);
    }
}
