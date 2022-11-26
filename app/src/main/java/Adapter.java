import chess.Color;
import chess.board.Board;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.PlayerColor;
import edu.austral.dissis.chess.gui.Position;
import pieces.Piece;
import pieces.Pieces;

import java.util.ArrayList;
import java.util.List;

public class Adapter {
    public List<ChessPiece> getPieces(Board board){
        ArrayList<ChessPiece> chessPieces = new ArrayList<>();
        for (chess.Position position : board.getBoard()){
            if(!position.isEmpty()){
                Piece piece = position.getPiece();
                chessPieces.add(new ChessPiece(String.valueOf(piece.getId()), getPlayerColorFromColor(piece.getColor()), new Position(position.getRow()+ 1, position.getColumn() + 1), getStringFromPieceName(piece.getType())));
            }
        }
        return chessPieces;
    }
    public String getStringFromPieceName(Pieces pieceName){
        switch (pieceName){
            case KING -> {
                return "king";
            }
            case QUEEN -> {
                return "queen";
            }
            case ROOK -> {
                return "rook";
            }
            case BISHOP -> {
                return "bishop";
            }
            case PAWN -> {
                return "pawn";
            }
            case HORSE -> {
                return "knight";
            }
            case ARCHBISHOP -> {
                return "archbishop";
            }
            case CHANCELLOR -> {
                return "chancellor";
            }
        }
        return "";
    }

    public PlayerColor getPlayerColorFromColor(Color color) {
        if(color == Color.WHITE) return PlayerColor.WHITE;
        else return PlayerColor.BLACK;
    }
}
