package chess;

import chess.board.Board;
import chess.board.BoardGenerator;
import validators.MovementValidator;

import java.sql.Timestamp;


public class Game {
    Board board;
    Player playerWhite;
    Player playerBlack;
    protected Timestamp startTime;
    GameMode gameMode;
    private MovementValidator movementValidator;
    private boolean whiteTurn;

    public Game(GameMode gameMode){
        this.board = new Board(BoardGenerator.createBoard(gameMode));
        this.gameMode = gameMode;
        this.movementValidator = new MovementValidator();
    }
    public void startGame(){
        this.startTime =  new Timestamp(System.currentTimeMillis());
        this.playerWhite = new Player("white player", Color.WHITE);
        this.playerBlack = new Player("black player", Color.BLACK);
        this.whiteTurn = true;
    }
    public GameResponse move(int rowTo, int columnTo, int rowFrom, int columnFrom){

    }

    public Board getBoard() {
        return board;
    }
    public Color getTurn(){
        if (whiteTurn) return Color.WHITE;
        return Color.BLACK;
    }
}
