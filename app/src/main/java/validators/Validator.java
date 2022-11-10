package validators;

import chess.Color;
import chess.Move;
import chess.Position;
import chess.Square;
import chess.board.Board;
import chess.util.CopyBoard;
import pieces.Piece;
import pieces.Pieces;
import validators.movementValidators.MovementType;
import validators.movementValidators.MovementValidator;

import java.util.Optional;

public class Validator {
    private final HasPieceValidator hasPieceValidator;
    private final InsideBoardLimitValidator insideBoardLimitValidator;
    private final MovementValidator movementValidator;
    private final TurnColorValidator turnColorValidator;
    private final PieceNotEmptyValidator pieceNotEmptyValidator;

    public Validator() {
        this.hasPieceValidator = new HasPieceValidator();
        this.insideBoardLimitValidator = new InsideBoardLimitValidator();
        this.movementValidator = new MovementValidator();
        this.turnColorValidator = new TurnColorValidator();
        this.pieceNotEmptyValidator = new PieceNotEmptyValidator();
    }
    public boolean isInsideBoardLimit(Move move, Board board){
        return insideBoardLimitValidator.isInsideBoardLimit(move, board);
    }

    public boolean isPieceNotEmpty(Optional<Piece> possiblePiece){
        return pieceNotEmptyValidator.isPiecePresent(possiblePiece);
    }
    public boolean isTurnColorCorrect(Color pieceColor, boolean whiteTurn) {
        return turnColorValidator.isColorTurnCorrect(pieceColor, whiteTurn);
    }
    public boolean isMoveValid(Board board, Move move){
        //checks that piece in positionTo is different color
        if (!movementValidator.isPositionToValid(board.getBoard()[move.finalPosition.row][move.finalPosition.column].getPossiblePiece(),move.whiteTurn)) return false;
        if (board.getBoard()[move.initialPosition.row][move.initialPosition.column].isEmpty()) return false;

        return switch (board.getBoard()[move.initialPosition.row][move.initialPosition.column].getPiece().getType()) {
            case QUEEN -> isQueenMoveValid(board, move);
            case KING -> isKingMoveValid(board, move);
            case BISHOP -> isBishopMoveValid(board, move);
            case HORSE -> isHorseMoveValid(move);
            case ROOK -> isRookMoveValid(board, move);
            case PAWN -> isPawnMoveValid(board, move);
        };
    }

    private boolean isPawnMoveValid(Board b, Move move) {
        int rowTo = move.finalPosition.row;
        int columnTo = move.finalPosition.column;
        int rowFrom = move.initialPosition.row;
        int columnFrom = move.initialPosition.column;
        Position[][] board = b.getBoard();

        int dif = rowTo - rowFrom;
        if ((move.whiteTurn && dif < 0) || (!move.whiteTurn && dif > 0)) return false;

        if (Math.abs(dif) == 2){
            if (board[rowFrom][columnFrom].getPiece().getAmountOfMoves() != 0) return false;
            if (!movementValidator.noPieceCrash(b, new Position(rowTo, columnTo), new Position(rowFrom, columnFrom), MovementType.VERTICAL)) return false;
            if (!board[rowTo][columnTo].isEmpty()) return false;
            return true;
        }
        else if (Math.abs(dif) == 1){
            //vertical
            if (Math.abs(columnFrom-columnTo) == 0){
                return board[rowTo][columnTo].isEmpty();
            }
            //eat
            else if (Math.abs(columnFrom-columnTo) == 1){
                return !board[rowTo][columnTo].isEmpty();
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    private boolean isRookMoveValid(Board board, Move move) {
        //checks if piece crashes
        if (Math.abs(move.initialPosition.row-move.finalPosition.row) == 0 && Math.abs(move.initialPosition.column-move.finalPosition.column) != 0 && !movementValidator.noPieceCrash(board, new Position(move.finalPosition.row, move.finalPosition.column), new Position(move.initialPosition.row, move.initialPosition.column), MovementType.HORIZONTAL)) return false;
        if (Math.abs(move.initialPosition.row-move.finalPosition.row) != 0 && Math.abs(move.initialPosition.column-move.finalPosition.column) == 0 && !movementValidator.noPieceCrash(board, new Position(move.finalPosition.row, move.finalPosition.column), new Position(move.initialPosition.row, move.initialPosition.column), MovementType.VERTICAL)) return false;

        //checks if movement is not straight
        if (!(move.initialPosition.row == move.finalPosition.row || move.initialPosition.column == move.finalPosition.column)) return  false;

        return true;
    }

    private boolean isHorseMoveValid(Move move) {
        return (Math.abs(move.initialPosition.row - move.finalPosition.row) == 1 && Math.abs(move.initialPosition.column - move.finalPosition.column) == 2) || (Math.abs(move.initialPosition.row - move.finalPosition.row) == 2 && Math.abs(move.initialPosition.column - move.finalPosition.column) == 1);
    }

    private boolean isBishopMoveValid(Board board, Move move) {
        //checks if piece crashes
        if(!movementValidator.noPieceCrash(board, new Position(move.finalPosition.row, move.finalPosition.column), new Position(move.initialPosition.row, move.initialPosition.column), MovementType.DIAGONAL)) return false;
        //checks if movement is not diagonal
        if (Math.abs(move.initialPosition.row-move.finalPosition.row) != Math.abs(move.initialPosition.column-move.finalPosition.column)) return  false;

        return true;
    }

    private boolean isKingMoveValid(Board b, Move move) {
        int rowTo = move.finalPosition.row;
        int columnTo = move.finalPosition.column;
        int rowFrom = move.initialPosition.row;
        int columnFrom = move.initialPosition.column;
        Position[][] board = b.getBoard();
        Piece king = board[rowFrom][columnFrom].getPiece();

        //castle
        if (Math.abs(rowFrom-rowTo) == 0 && Math.abs(columnFrom-columnTo) == 2){
            Board auxBoard = CopyBoard.copyBoard(b);
            auxBoard.addPieceInPosition(rowTo, columnTo, king);
            auxBoard.deletePieceInPosition(rowFrom, columnFrom);


            //long castle white
            if (king.getColor() == Color.WHITE && rowFrom == 0 && columnFrom == 4 && rowTo == 0 && columnTo == 2 && board[0][1].isEmpty() && board[0][2].isEmpty() && board[0][3].isEmpty() && king.getAmountOfMoves() == 0 && !board[0][0].isEmpty() && board[0][0].getPiece().getType() == Pieces.ROOK && board[0][0].getPiece().getAmountOfMoves() == 0 && !isKingBeingTargeted(auxBoard, new Square(rowTo, columnTo), king.getColor())){
                b.addPieceInPosition(0,3, board[0][0].getPiece());
                b.deletePieceInPosition(0,0);
                return true;
            }
            //short castle white
            else if (king.getColor() == Color.WHITE && rowFrom == 0 && columnFrom == 4 && rowTo == 0 && columnTo == 6 && board[0][5].isEmpty() && board[0][6].isEmpty() && king.getAmountOfMoves() == 0 && !board[0][7].isEmpty() && board[0][7].getPiece().getType() == Pieces.ROOK && board[0][7].getPiece().getAmountOfMoves() == 0 && !isKingBeingTargeted(auxBoard, new Square(rowTo, columnTo), king.getColor())){
                b.addPieceInPosition(0,5, board[0][7].getPiece());
                b.deletePieceInPosition(0,7);
                return true;
            }
            //short castle black
            else if (king.getColor() == Color.BLACK && rowFrom == 7 && columnFrom == 4 && rowTo == 7 && columnTo == 6 && board[7][5].isEmpty() && board[7][6].isEmpty() && king.getAmountOfMoves() == 0 && !board[7][7].isEmpty() && board[7][7].getPiece().getType() == Pieces.ROOK && board[7][7].getPiece().getAmountOfMoves() == 0 && !isKingBeingTargeted(auxBoard, new Square(rowTo, columnTo), king.getColor())){
                b.addPieceInPosition(7,5, board[7][7].getPiece());
                b.deletePieceInPosition(7,7);
                return true;
            }
            //long castle black
            else if (king.getColor() == Color.BLACK && rowFrom == 7 && columnFrom == 4 && rowTo == 7 && columnTo == 2 && board[7][1].isEmpty() && board[7][2].isEmpty() && board[7][3].isEmpty() && king.getAmountOfMoves() == 0 && !board[7][0].isEmpty() && board[7][0].getPiece().getType() == Pieces.ROOK && board[7][0].getPiece().getAmountOfMoves() == 0 && !isKingBeingTargeted(auxBoard, new Square(rowTo, columnTo), king.getColor())){
                b.addPieceInPosition(7,3, board[7][0].getPiece());
                b.deletePieceInPosition(7,0);
                return true;
            }
            return false;
        }
        else if (Math.abs(rowFrom-rowTo) <= 1 && Math.abs(columnFrom-columnTo) <= 1) return true;
        return false;
    }

    private boolean isQueenMoveValid(Board board, Move move){
        //checks if piece crashes
        if (Math.abs(move.initialPosition.row-move.finalPosition.row) == Math.abs(move.initialPosition.column-move.finalPosition.column) && !movementValidator.noPieceCrash(board, new Position(move.finalPosition.row, move.finalPosition.column), new Position(move.initialPosition.row, move.initialPosition.column), MovementType.DIAGONAL)) return false;
        if (Math.abs(move.initialPosition.row-move.finalPosition.row) == 0 && Math.abs(move.initialPosition.column-move.finalPosition.column) != 0 && !movementValidator.noPieceCrash(board, new Position(move.finalPosition.row, move.finalPosition.column), new Position(move.initialPosition.row, move.initialPosition.column), MovementType.HORIZONTAL)) return false;
        if (Math.abs(move.initialPosition.row-move.finalPosition.row) != 0 && Math.abs(move.initialPosition.column-move.finalPosition.column) == 0 && !movementValidator.noPieceCrash(board, new Position(move.finalPosition.row, move.finalPosition.column), new Position(move.initialPosition.row, move.initialPosition.column), MovementType.VERTICAL)) return false;
        //checks if movement is diagonal or straight
        if (!((Math.abs(move.initialPosition.row-move.finalPosition.row) == Math.abs(move.initialPosition.column-move.finalPosition.column)) || (move.initialPosition.row == move.finalPosition.row || move.initialPosition.column == move.finalPosition.column))) return  false;

        return true;
    }
    public boolean doesKingHaveAnyValidMove(Board board, Square kingPosition, boolean whiteTurn){
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) continue;
                if (isInsideBoardLimit(kingPosition.row + i, kingPosition.column + i, board.getAmountOfRows() - 1, board.getAmountOfColumns() - 1) && isMoveValid(board, new Move(kingPosition, new Square(kingPosition.row + i, kingPosition.column + i), whiteTurn))) return true;

            }
        }
        return false;
    }
    public boolean isKingBeingTargeted(Board board, Square newPosition, Color kingColor){
        return !movementValidator.isKingInSafePosition(board, newPosition, kingColor);
    }
    private boolean isInsideBoardLimit(int row, int col, int maxRow, int maxCol){
        return insideBoardLimitValidator.isInsideBoardLimit(row, col, maxRow, maxCol);
    }

}
