import chess.game.Game;
import chess.game.GameMode;
import chess.game.GameResponse;

import chess.game.GameResponseType;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MyGameEngine implements GameEngine {
    Game game = new Game(GameMode.BERLIN);
    Adapter adapter = new Adapter();

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        GameResponse gameResponse =  game.move(adjustForZeroBasedIndex(move.getTo().getRow()), adjustForZeroBasedIndex(move.getTo().getColumn()), adjustForZeroBasedIndex(move.getFrom().getRow()), adjustForZeroBasedIndex(move.getFrom().getColumn()));
        if(gameResponse.getType() == GameResponseType.VALID_MOVE){
            return new NewGameState(adapter.getPieces(game.getBoard()),adapter.getPlayerColorFromColor(game.getTurn()));

        }
        else if(gameResponse.getType() == GameResponseType.INVALID_MOVE)
            return new InvalidMove(gameResponse.getMessage());
        else{
            return new GameOver(adapter.getPlayerColorFromColor(game.getTurn()));
        }
    }

    private static int adjustForZeroBasedIndex(int move) {
        return move - 1;
    }

    @NotNull
    @Override
    public InitialState init() {
        List<ChessPiece> chessPieces =  adapter.getPieces(game.getBoard());
        return new InitialState(new BoardSize(game.getBoard().getAmountOfColumns(),
                game.getBoard().getAmountOfRows()), chessPieces,
                adapter.getPlayerColorFromColor(game.getTurn()));
    }


}
