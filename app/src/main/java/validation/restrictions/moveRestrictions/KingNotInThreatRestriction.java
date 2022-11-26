package validation.restrictions.moveRestrictions;

import chess.Color;
import chess.Move;
import chess.PieceMover;
import chess.Position;
import chess.board.Board;
import chess.util.CopyBoard;
import validation.Validator;
import validation.restrictions.Restriction;

import java.util.List;

public class KingNotInThreatRestriction implements Restriction {
    private final Validator validator;
    public KingNotInThreatRestriction(Validator validator){
        this.validator = validator;
    }
    @Override
    public boolean validate(Board board, Move move) {
        Board auxBoard = CopyBoard.copyBoard(board);
        PieceMover pieceMover = new PieceMover(auxBoard);
        pieceMover.movePiece(move);

        Color color = move.whiteTurn ? Color.WHITE : Color.BLACK;
        return !isKingInCheck(auxBoard, color, !move.whiteTurn);
    }
    public boolean isKingInCheck(Board board, Color kingColor, boolean turn){
        List<Position> positions = board.getBoard();
        for (Position position : positions){
            if (!position.isEmpty() && position.getPiece().getColor() != kingColor){
                if (isPieceThreateningKing(board, position, kingColor,turn)) return true;
            }
        }
        return false;
    }

    private boolean isPieceThreateningKing(Board board, Position position, Color kingColor, boolean turn) {
        Move move = new Move(position.toSquare(), board.getKingPosition(kingColor).toSquare(), turn);
        return validator.validate(board, move);
    }

}
