package chess;

import chess.board.Board;
import chess.board.BoardGenerator;
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

        if (whiteTurn && isWhiteKingInCheck){

        }

        if (validator.isMoveValid(board, move)){
            board.addPieceInPosition(rowTo, columnTo, initialPosition.getPiece());
            board.deletePieceInPosition(rowFrom, columnFrom);
            whiteTurn = !whiteTurn;

        }
        else {
            return new GameResponse(GameResponseType.INVALID_MOVE, "Invalid move!");
        }
        return new GameResponse(GameResponseType.VALID_MOVE, "");
    }

    public Board getBoard() {
        return board;
    }
    public Color getTurn(){
        if (whiteTurn) return Color.WHITE;
        return Color.BLACK;
    }
}
