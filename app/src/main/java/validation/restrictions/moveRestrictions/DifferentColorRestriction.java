package validation.restrictions.moveRestrictions;

import chess.Color;
import chess.Move;
import chess.board.Board;
import pieces.Piece;
import validation.restrictions.Restriction;

import java.util.Optional;

public class DifferentColorRestriction implements Restriction {
    @Override
    public boolean validate(Board board, Move move) {
        Optional<Piece> piece = board.getPosition(move.finalPosition).getPossiblePiece();
        Color color = move.whiteTurn ? Color.WHITE : Color.BLACK;
        return piece.isEmpty() || piece.get().getColor() != color;
    }
}
