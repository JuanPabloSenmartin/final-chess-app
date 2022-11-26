package validation;

import chess.Color;
import chess.Move;
import chess.board.Board;
import chess.game.GameMode;
import pieces.Pieces;
import validation.restrictions.BoardLimitRestriction;
import validation.restrictions.MoveTurnRestriction;
import validation.restrictions.NoPieceSelectedRestriction;
import validation.restrictions.moveRestrictions.KingNotInThreatRestriction;

import java.util.Map;

public class Validator {
    private final Map<Pieces, MovementValidator> movements;
    private final NoPieceSelectedRestriction noPieceSelectedRestriction;
    private final BoardLimitRestriction boardLimitRestriction;
    private final MoveTurnRestriction moveTurnRestriction;
    private final KingNotInThreatRestriction kingNotInThreatRestriction;
    private final CheckMateValidator checkMateValidator;


    public Validator(GameMode gameMode) {
        this.movements = Loader.load(gameMode);
        this.moveTurnRestriction = new MoveTurnRestriction();
        this.noPieceSelectedRestriction = new NoPieceSelectedRestriction();
        this.boardLimitRestriction = new BoardLimitRestriction();
        this.kingNotInThreatRestriction = new KingNotInThreatRestriction(this);
        this.checkMateValidator = new CheckMateValidator(kingNotInThreatRestriction, this);
    }
    public boolean validate(Board board, Move move){
        return noPieceSelectedRestriction.validate(board, move)
                && moveTurnRestriction.validate(board, move)
                && boardLimitRestriction.validate(board, move)
                && movements.get(board.getPosition(move.initialPosition).getPiece().getType()).validation(board, move);
    }
    public boolean isKingInThreat(Board board, Move move){
        return !kingNotInThreatRestriction.validate(board, move);
    }
    public boolean isCheckMate(Board board, Move move){
        if (!kingNotInThreatRestriction.isKingInCheck(board, move.whiteTurn ? Color.BLACK : Color.WHITE, move.whiteTurn)) return false;
        return checkMateValidator.isCheckMate(board, move.whiteTurn ? Color.BLACK : Color.WHITE, move.whiteTurn);
    }
}
