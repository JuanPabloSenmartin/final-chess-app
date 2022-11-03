package validators.movementValidators;

import chess.Color;
import chess.Position;
import chess.Square;
import chess.board.Board;
import pieces.Piece;
import pieces.Pieces;

import java.util.Optional;

public class KingCheckValidator {
    public boolean isKingSafeInPosition(Board b, Square newPosition, Color color, Square blackKingPosition, Square whiteKingPosition){
        //checks if no enemy piece threatens king in new position
        int row = newPosition.row;
        int col = newPosition.column;
        Position[][] board = b.getBoard();
        int maxRow = b.getAmountOfRows()-1;
        int maxCol = b.getAmountOfColumns()-1;

        Color oppositeColor = Color.WHITE;
        if (color == Color.WHITE) oppositeColor = Color.BLACK;

        //check for opposite king
        if (color == Color.WHITE){
            if (Math.abs(row - blackKingPosition.row) == 1 || Math.abs(col - blackKingPosition.column) == 1) return false;
        }
        else {
            if (Math.abs(row - whiteKingPosition.row) == 1 || Math.abs(col - whiteKingPosition.column) == 1) return false;
        }

        //check for threats in diagonal
        for (int i = row-1, j = col+1; i >= 0 && j < maxCol; i--, j++) {
            Optional<Piece> optionalPiece = board[i][j].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.BISHOP)) return false;
            break;
        }
        for (int i = row+1, j = col+1; i < maxRow && j < maxCol; i++, j++) {
            Optional<Piece> optionalPiece = board[i][j].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.BISHOP)) return false;
            break;
        }
        for (int i = row+1, j = col-1; i < maxRow && j >= 0; i++, j--) {
            Optional<Piece> optionalPiece = board[i][j].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.BISHOP)) return false;
            break;
        }
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            Optional<Piece> optionalPiece = board[i][j].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.BISHOP)) return false;
            break;
        }

        //check for threats vertically
        for (int i = row+1; i < maxRow; i++) {
            Optional<Piece> optionalPiece = board[i][col].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.ROOK)) return false;
            break;
        }
        for (int i = row-1; i >= 0 ; i--) {
            Optional<Piece> optionalPiece = board[i][col].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.ROOK)) return false;
            break;
        }
        for (int j = col+1; j < 8; j++) {
            Optional<Piece> optionalPiece = board[row][j].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.ROOK)) return false;
            break;
        }
        for (int j = col-1; j >= 0; j--) {
            Optional<Piece> optionalPiece = board[row][j].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.ROOK)) return false;
            break;
        }
        //checks pawn threats
        if (color == Color.WHITE) {
            if (!checkThreat(board, row+1, col+1, Pieces.PAWN, oppositeColor, maxRow, maxCol)) return false;
            if (!checkThreat(board, row+1, col-1, Pieces.PAWN, oppositeColor, maxRow, maxCol)) return false;
        }
        else{
            if (!checkThreat(board, row-1, col+1, Pieces.PAWN, oppositeColor, maxRow, maxCol)) return false;
            if (!checkThreat(board, row-1, col-1, Pieces.PAWN, oppositeColor, maxRow, maxCol)) return false;
        }
        //check horse threats
        if (!checkThreat(board, row+2, col-1, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
        if (!checkThreat(board, row+2, col+1, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
        if (!checkThreat(board, row+1, col-2, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
        if (!checkThreat(board, row-1, col-2, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
        if (!checkThreat(board, row-2, col-1, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
        if (!checkThreat(board, row-2, col+1, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
        if (!checkThreat(board, row-1, col+2, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
        if (!checkThreat(board, row+1, col+2, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;

        return true;
    }
    private boolean checkThreat(Position[][] board, int row, int col, Pieces pieceName, Color oppositeColor, int maxRow, int maxCol){
        if (!checkBoundaries(row, col, maxRow, maxCol)) return true;
        if (board[row][col].isEmpty()) return true;
        if (board[row][col].getPiece().getType() == pieceName && board[row][col].getPiece().getColor() == oppositeColor) return false;
        return true;
    }
    private boolean checkBoundaries(int row, int col, int maxRow, int maxCol) {
        return row <= maxRow && row >= 0 && col <= maxCol && col >= 0;
    }
    public boolean doesKingHaveAnyValidMove(Square kingPosition, Color color){
        return true;
    }
}
