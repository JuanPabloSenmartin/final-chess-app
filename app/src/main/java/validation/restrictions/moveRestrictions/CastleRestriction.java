package validation.restrictions.moveRestrictions;

import chess.Move;
import chess.board.Board;
import pieces.Piece;
import pieces.Pieces;
import validation.restrictions.Restriction;

import java.util.Optional;

public class CastleRestriction implements Restriction {

    @Override
    public boolean validate(Board board, Move move) {
        int initialCol = move.initialPosition.column;
        int finalCol = move.finalPosition.column;
        Optional<Piece> rook;
        if (initialCol > finalCol){
            rook = board.getPosition(move.initialPosition.row, 0).getPossiblePiece();
        }
        else {
            rook = board.getPosition(move.initialPosition.row, board.getMaxEdgeColumn()).getPossiblePiece();
        }
        return board.getPosition(move.initialPosition).getPiece().getAmountOfMoves() == 0 && rook.isPresent() && rook.get().getType() == Pieces.ROOK && rook.get().getAmountOfMoves() == 0;
    }
}
