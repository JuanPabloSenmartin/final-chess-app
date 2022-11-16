package validators.movementValidators;

import chess.Color;
import chess.Position;
import chess.Square;
import chess.board.Board;
import pieces.Piece;
import pieces.Pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KingCheckValidator {
    public boolean isKingSafeInPosition(Board b, Square newPosition, Color color){

//        Square blackKingPosition = b.getBlackKingPosition();
//        Square whiteKingPosition = b.getWhiteKingPosition();
        //checks if no enemy piece threatens king in new position

//        Position[][] board = b.getBoard();
//        int maxRow = b.getAmountOfRows()-1;
//        int maxCol = b.getAmountOfColumns()-1;

        //        Color oppositeColor = Color.WHITE;
//        if (color == Color.WHITE){
//            oppositeColor = Color.BLACK;
//        }

        int row = newPosition.row;
        int col = newPosition.column;

        List<Square> threats = findThreatsToKing(b, row, col, color);
        return threats.isEmpty();

//        //check for opposite king
//        if (color == Color.WHITE){
//            if (checkOppositeKing(blackKingPosition, row, col)) return false;
//        }
//        else {
//            if (checkOppositeKing(whiteKingPosition, row, col)) return false;
//        }
//
//        //check for threats in diagonal
//        for (int i = row-1, j = col+1; i >= 0 && j <= maxCol; i--, j++) {
//            Optional<Piece> optionalPiece = board[i][j].getPossiblePiece();
//            if (optionalPiece.isEmpty()) continue;
//            Piece piece = optionalPiece.get();
//            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.BISHOP)) return false;
//            break;
//        }
//        for (int i = row+1, j = col+1; i <= maxRow && j <= maxCol; i++, j++) {
//            Optional<Piece> optionalPiece = board[i][j].getPossiblePiece();
//            if (optionalPiece.isEmpty()) continue;
//            Piece piece = optionalPiece.get();
//            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.BISHOP)) return false;
//            break;
//        }
//        for (int i = row+1, j = col-1; i <= maxRow && j >= 0; i++, j--) {
//            Optional<Piece> optionalPiece = board[i][j].getPossiblePiece();
//            if (optionalPiece.isEmpty()) continue;
//            Piece piece = optionalPiece.get();
//            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.BISHOP)) return false;
//            break;
//        }
//        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
//            Optional<Piece> optionalPiece = board[i][j].getPossiblePiece();
//            if (optionalPiece.isEmpty()) continue;
//            Piece piece = optionalPiece.get();
//            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.BISHOP)) return false;
//            break;
//        }
//
//        //check for threats vertically
//        for (int i = row+1; i <= maxRow; i++) {
//            Optional<Piece> optionalPiece = board[i][col].getPossiblePiece();
//            if (optionalPiece.isEmpty()) continue;
//            Piece piece = optionalPiece.get();
//            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.ROOK)) return false;
//            break;
//        }
//        for (int i = row-1; i >= 0 ; i--) {
//            Optional<Piece> optionalPiece = board[i][col].getPossiblePiece();
//            if (optionalPiece.isEmpty()) continue;
//            Piece piece = optionalPiece.get();
//            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.ROOK)) return false;
//            break;
//        }
//        //check for threats horizontally
//        for (int j = col+1; j <= maxCol; j++) {
//            Optional<Piece> optionalPiece = board[row][j].getPossiblePiece();
//            if (optionalPiece.isEmpty()) continue;
//            Piece piece = optionalPiece.get();
//            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.ROOK)) return false;
//            break;
//        }
//        for (int j = col-1; j >= 0; j--) {
//            Optional<Piece> optionalPiece = board[row][j].getPossiblePiece();
//            if (optionalPiece.isEmpty()) continue;
//            Piece piece = optionalPiece.get();
//            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.ROOK)) return false;
//            break;
//        }
//        //checks pawn threats
//        if (color == Color.WHITE) {
//            if (!checkThreat(board, row+1, col+1, Pieces.PAWN, oppositeColor, maxRow, maxCol)) return false;
//            if (!checkThreat(board, row+1, col-1, Pieces.PAWN, oppositeColor, maxRow, maxCol)) return false;
//        }
//        else{
//            if (!checkThreat(board, row-1, col+1, Pieces.PAWN, oppositeColor, maxRow, maxCol)) return false;
//            if (!checkThreat(board, row-1, col-1, Pieces.PAWN, oppositeColor, maxRow, maxCol)) return false;
//        }
//        //check horse threats
//        if (!checkThreat(board, row+2, col-1, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
//        if (!checkThreat(board, row+2, col+1, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
//        if (!checkThreat(board, row+1, col-2, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
//        if (!checkThreat(board, row-1, col-2, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
//        if (!checkThreat(board, row-2, col-1, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
//        if (!checkThreat(board, row-2, col+1, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
//        if (!checkThreat(board, row-1, col+2, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
//        if (!checkThreat(board, row+1, col+2, Pieces.HORSE, oppositeColor, maxRow, maxCol)) return false;
    }

    public List<Square> findThreatsToKing(Board b, int row, int col, Color color) {
        List<Square> threats = new ArrayList<>();
        Square blackKingPosition = b.getBlackKingPosition();
        Square whiteKingPosition = b.getWhiteKingPosition();
        Position[][] board = b.getBoard();
        int maxRow = b.getAmountOfRows()-1;
        int maxCol = b.getAmountOfColumns()-1;

        Color oppositeColor = Color.WHITE;
        if (color == Color.WHITE){
            oppositeColor = Color.BLACK;
        }

        //check for opposite king
        if (color == Color.WHITE){
            if (checkOppositeKing(blackKingPosition, row, col)) threats.add(blackKingPosition);
        }
        else {
            if (checkOppositeKing(whiteKingPosition, row, col)) threats.add(whiteKingPosition);
        }

        //check for threats in diagonal
        for (int i = row-1, j = col+1; i >= 0 && j <= maxCol; i--, j++) {
            Optional<Piece> optionalPiece = board[i][j].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.BISHOP)) threats.add(new Square(i,j));
            break;
        }
        for (int i = row+1, j = col+1; i <= maxRow && j <= maxCol; i++, j++) {
            Optional<Piece> optionalPiece = board[i][j].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.BISHOP)) threats.add(new Square(i,j));
            break;
        }
        for (int i = row+1, j = col-1; i <= maxRow && j >= 0; i++, j--) {
            Optional<Piece> optionalPiece = board[i][j].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.BISHOP)) threats.add(new Square(i,j));
            break;
        }
        for (int i = row-1, j = col-1; i >= 0 && j >= 0; i--, j--) {
            Optional<Piece> optionalPiece = board[i][j].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.BISHOP)) threats.add(new Square(i,j));
            break;
        }

        //check for threats vertically
        for (int i = row+1; i <= maxRow; i++) {
            Optional<Piece> optionalPiece = board[i][col].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.ROOK)) threats.add(new Square(i,col));
            break;
        }
        for (int i = row-1; i >= 0 ; i--) {
            Optional<Piece> optionalPiece = board[i][col].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.ROOK)) threats.add(new Square(i,col));
            break;
        }
        //check for threats horizontally
        for (int j = col+1; j <= maxCol; j++) {
            Optional<Piece> optionalPiece = board[row][j].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.ROOK)) threats.add(new Square(row,j));
            break;
        }
        for (int j = col-1; j >= 0; j--) {
            Optional<Piece> optionalPiece = board[row][j].getPossiblePiece();
            if (optionalPiece.isEmpty()) continue;
            Piece piece = optionalPiece.get();
            if (piece.getColor() == oppositeColor && (piece.getType() == Pieces.QUEEN || piece.getType() == Pieces.ROOK)) threats.add(new Square(row,j));
            break;
        }
        //checks pawn threats
        if (color == Color.WHITE) {
            if (!checkThreat(board, row+1, col+1, Pieces.PAWN, oppositeColor, maxRow, maxCol)) threats.add(new Square(row+1,col+1));
            if (!checkThreat(board, row+1, col-1, Pieces.PAWN, oppositeColor, maxRow, maxCol)) threats.add(new Square(row+1, col-1));
        }
        else{
            if (!checkThreat(board, row-1, col+1, Pieces.PAWN, oppositeColor, maxRow, maxCol)) threats.add(new Square(row-1, col+1));
            if (!checkThreat(board, row-1, col-1, Pieces.PAWN, oppositeColor, maxRow, maxCol)) threats.add(new Square(row-1, col-1));
        }
        //check horse threats
        if (!checkThreat(board, row+2, col-1, Pieces.HORSE, oppositeColor, maxRow, maxCol)) threats.add(new Square(row+2, col-1));
        if (!checkThreat(board, row+2, col+1, Pieces.HORSE, oppositeColor, maxRow, maxCol)) threats.add(new Square(row+2, col+1));
        if (!checkThreat(board, row+1, col-2, Pieces.HORSE, oppositeColor, maxRow, maxCol)) threats.add(new Square(row+1, col-2));
        if (!checkThreat(board, row-1, col-2, Pieces.HORSE, oppositeColor, maxRow, maxCol)) threats.add(new Square(row-1, col-2));
        if (!checkThreat(board, row-2, col-1, Pieces.HORSE, oppositeColor, maxRow, maxCol)) threats.add(new Square(row-2, col-1));
        if (!checkThreat(board, row-2, col+1, Pieces.HORSE, oppositeColor, maxRow, maxCol)) threats.add(new Square(row-2, col+1));
        if (!checkThreat(board, row-1, col+2, Pieces.HORSE, oppositeColor, maxRow, maxCol)) threats.add(new Square(row-1, col+2));
        if (!checkThreat(board, row+1, col+2, Pieces.HORSE, oppositeColor, maxRow, maxCol)) threats.add(new Square(row+1, col+2));
        return threats;
    }

    private boolean checkOppositeKing(Square oppositeKing, int row, int col) {
        if ((Math.abs(row - oppositeKing.row) == 1 && Math.abs(col - oppositeKing.column) == 1) || (Math.abs(row - oppositeKing.row) == 0 && Math.abs(col - oppositeKing.column) == 1) || Math.abs(row - oppositeKing.row) == 1 && Math.abs(col - oppositeKing.column) == 0)
            return true;
        return false;
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

}
