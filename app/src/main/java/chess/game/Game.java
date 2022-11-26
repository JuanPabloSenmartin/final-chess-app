package chess.game;

import chess.*;
import chess.board.Board;
import chess.board.BoardGenerator;
import pieces.Piece;
import validation.Validator;


public class Game {
    private final Board board;
    private final Validator validator;
    private boolean whiteTurn;
    private final PieceMover pieceMover;
    public Game(GameMode gameMode){
        this.board = BoardGenerator.createBoard(gameMode);
        this.validator = new Validator(gameMode);
        this.pieceMover = new PieceMover(board);
        this.whiteTurn = true;
    }

    public GameResponse move(int rowTo, int columnTo, int rowFrom, int columnFrom){
        Square initialSquare = new Square(rowFrom, columnFrom);
        Square finalSquare = new Square(rowTo, columnTo);
        Move move = new Move(initialSquare, finalSquare, whiteTurn);
        Position initialPosition = board.getPosition(rowFrom, columnFrom);
        Piece piece = initialPosition.getPiece();
        Color color = piece.getColor();

        if (!validator.validate(board, move)){
            return new GameResponse(GameResponseType.INVALID_MOVE, "");
        }
        if (validator.isKingInThreat(board, move)){
            return new GameResponse(GameResponseType.INVALID_MOVE, "Invalid move! King is vulnerable");
        }
        pieceMover.movePiece(move);
        if (validator.isCheckMate(board, move)){
            return new GameResponse(GameResponseType.GAME_OVER, "Game Over");
        }
        manageTurn();
        return new GameResponse(GameResponseType.VALID_MOVE, "");

    }
    private void manageTurn() {
        this.whiteTurn = !whiteTurn;
    }

    public Board getBoard() {
        return board;
    }
    public Color getTurn(){
        if (whiteTurn) return Color.WHITE;
        return Color.BLACK;
    }
}
