package chess;

import chess.board.Board;
import chess.board.BoardGenerator;
import chess.util.CopyBoard;
import pieces.Piece;
import pieces.Pieces;
import validators.Validator;

import java.sql.Timestamp;


public class Game {
    private Board board;
    private Player playerWhite;
    private Player playerBlack;
    protected Timestamp startTime;
    GameMode gameMode;
    private Validator validator;
    private boolean whiteTurn;
    private boolean isWhiteKingInCheck;
    private boolean isBlackKingInCheck;

    public Game(GameMode gameMode){
        this.board = new Board(BoardGenerator.createBoard(gameMode));
        this.gameMode = gameMode;
        this.validator = new Validator();
    }
    public void startGame(){
        this.startTime =  new Timestamp(System.currentTimeMillis());
        this.playerWhite = new Player("white player", Color.WHITE);
        this.playerBlack = new Player("black player", Color.BLACK);
        this.whiteTurn = true;
        this.isBlackKingInCheck = false;
        this.isWhiteKingInCheck = false;
    }
    public GameResponse move(int rowTo, int columnTo, int rowFrom, int columnFrom){
        Move move = new Move(new Square(rowFrom, columnFrom), new Square(rowTo, columnTo), whiteTurn);
        Position finalPosition = board.getBoard()[rowTo][columnTo];
        Position initialPosition = board.getBoard()[rowFrom][columnFrom];


        if (!validator.isInsideBoardLimit(move, board)) return new GameResponse(GameResponseType.INVALID_MOVE, "Invalid move! move trespasses board limit");
        if (!validator.isPieceNotEmpty(initialPosition.getPossiblePiece()))  return new GameResponse(GameResponseType.INVALID_MOVE, "Invalid move! no piece selected");
        if (!validator.isTurnColorCorrect(initialPosition.getPossiblePiece().get().getColor(), whiteTurn)) return new GameResponse(GameResponseType.INVALID_MOVE, "Invalid move! not your turn");
        Piece piece = initialPosition.getPiece();
        Color color = piece.getColor();
        if (whiteTurn && isWhiteKingInCheck){
            Board auxBoard = CopyBoard.copyBoard(board);
            auxBoard.addPieceInPosition(rowTo, columnTo, piece);
            auxBoard.deletePieceInPosition(rowFrom, columnFrom);
            if (validator.isKingBeingTargeted(auxBoard, auxBoard.getWhiteKingPosition(), Color.WHITE)) return new GameResponse(GameResponseType.INVALID_MOVE, "Invalid move! king is still in check");
            isWhiteKingInCheck = false;
        }
        if (!whiteTurn && isBlackKingInCheck){
            Board auxBoard = CopyBoard.copyBoard(board);
            auxBoard.addPieceInPosition(rowTo, columnTo, piece);
            auxBoard.deletePieceInPosition(rowFrom, columnFrom);
            if (validator.isKingBeingTargeted(auxBoard, auxBoard.getBlackKingPosition(), Color.BLACK)) return new GameResponse(GameResponseType.INVALID_MOVE, "Invalid move! king is still in check");
            isBlackKingInCheck = false;
        }

        if (validator.isMoveValid(board, move)){
            piece.addMove();
            if (piece.getType() == Pieces.PAWN && (rowTo == 0 || rowTo == 7)){
                //promotion
                board.addPieceInPosition(rowTo, columnTo, new Piece(color, Pieces.QUEEN));
                board.deletePieceInPosition(rowFrom, columnFrom);
            }
            else {
                board.addPieceInPosition(rowTo, columnTo, piece);
                board.deletePieceInPosition(rowFrom, columnFrom);
            }
            if (manageCheckingKing()) return new GameResponse(GameResponseType.GAME_OVER, "Game Over! " + color.name() + " is the winner!");
            whiteTurn = !whiteTurn;
        }
        else {
            return new GameResponse(GameResponseType.INVALID_MOVE, "Invalid move!");
        }
        return new GameResponse(GameResponseType.VALID_MOVE, "");
    }

    private boolean manageCheckingKing() {
        if (whiteTurn) {
            if (validator.isKingBeingTargeted(board, board.getBlackKingPosition(), Color.BLACK)){
                if (validator.doesKingHaveAnyValidMove(board, board.getBlackKingPosition(), !whiteTurn)){
                    isBlackKingInCheck = true;
                    return true;
                }
            }
        }
        else{
            if (validator.isKingBeingTargeted(board, board.getWhiteKingPosition(), Color.WHITE)){
                if (validator.doesKingHaveAnyValidMove(board, board.getWhiteKingPosition(), !whiteTurn)){
                    isWhiteKingInCheck = true;
                    return true;
                }
            }
        }
        return false;
    }

    public Board getBoard() {
        return board;
    }
    public Color getTurn(){
        if (whiteTurn) return Color.WHITE;
        return Color.BLACK;
    }
}
