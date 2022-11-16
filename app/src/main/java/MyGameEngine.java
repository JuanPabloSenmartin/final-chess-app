import chess.game.Game;
import chess.game.GameMode;
import chess.game.GameResponse;

import chess.game.GameResponseType;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyGameEngine implements GameEngine {
    Game game = new Game(GameMode.CLASSIC);
    Adapter adapter = new Adapter();

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        GameResponse gameResponse =  game.move(move.getTo().getRow() - 1, move.getTo().getColumn() - 1, move.getFrom().getRow() - 1, move.getFrom().getColumn() - 1);
        if(gameResponse.getType() == GameResponseType.VALID_MOVE){
            return new NewGameState(adapter.getPieces(game.getBoard()),adapter.getPlayerColorFromColor(game.getTurn()));

        }
        else if(gameResponse.getType() == GameResponseType.INVALID_MOVE)
            return new InvalidMove(gameResponse.getMessage());
        else{
            return new GameOver(adapter.getPlayerColorFromColor(game.getTurn()));
        }
    }

    @NotNull
    @Override
    public InitialState init() {
        game.startGame();
        List<ChessPiece> chessPieces =  adapter.getPieces(game.getBoard());
        return new InitialState(new BoardSize(game.getBoard().getAmountOfColumns(),
                game.getBoard().getAmountOfRows()), chessPieces,
                adapter.getPlayerColorFromColor(game.getTurn()));
    }


}
