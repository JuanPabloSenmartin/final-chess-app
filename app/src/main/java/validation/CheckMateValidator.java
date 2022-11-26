package validation;

import chess.Color;
import chess.Move;
import chess.Position;
import chess.Square;
import chess.board.Board;
import chess.util.CopyBoard;
import pieces.Piece;
import pieces.Pieces;
import validation.restrictions.moveRestrictions.KingNotInThreatRestriction;

import java.util.ArrayList;
import java.util.List;

public class CheckMateValidator {
    private final KingNotInThreatRestriction kingNotInThreatRestriction;
    private final Validator validator;
    public CheckMateValidator(KingNotInThreatRestriction kingNotInThreatRestriction, Validator validator){
        this.kingNotInThreatRestriction = kingNotInThreatRestriction;
        this.validator = validator;
    }
    public boolean isCheckMate(Board board, Color kingColor, boolean turn){
        Position kingPosition = board.getKingPosition(kingColor);
        return !canKingMove(board, kingPosition, !turn) && !canPieceBlock(board, kingColor, !turn);
    }

    private boolean canPieceBlock(Board board, Color kingColor, boolean turn) {
        List<Square> squaresToBlock = getSquaresToBlock(board, kingColor, turn);
        for (Square square : squaresToBlock) {
            for (Position position : board.getBoard()){
                if (!position.isEmpty() && position.getPiece().getColor() == kingColor){
                    if (validator.validate(board, new Move(position.toSquare(), square, !turn))) return true;
                }
            }
        }
        return false;
    }

    private boolean canKingMove(Board board, Position kingPosition, boolean turn) {
        List<Position> positions = board.getBoard();
        for (Position position : positions){
            int rowDif = Math.abs(position.getRow() - kingPosition.getRow());
            int columnDif = Math.abs(position.getColumn() - kingPosition.getColumn());
            if (rowDif == 0 && columnDif == 0) continue;
            if (rowDif <= 1 && columnDif <= 1 && validator.validate(board, new Move(kingPosition.toSquare(), position.toSquare(), turn)) && kingNotInThreatRestriction.validate(board, new Move(kingPosition.toSquare(), position.toSquare(), turn))) return true;
        }
        return false;
    }
    private List<Square> getSquaresToBlock(Board b, Color color, boolean turn) {
        List<Square> squaresToBlock = new ArrayList<>();

        Board auxBoard = CopyBoard.copyBoard(b);
        for (Position position : auxBoard.getBoard()){
            if (position.isEmpty()){
                position.setPiece(new Piece(color, Pieces.PAWN));
                if (!kingNotInThreatRestriction.isKingInCheck(auxBoard, color, turn)) squaresToBlock.add(position.toSquare());
                position.deletePiece();
            }
        }
        return squaresToBlock;
    }
}
