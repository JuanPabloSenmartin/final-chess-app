package chess.game;

public class GameResponse {
    private final GameResponseType type;
    private final String message;

    public GameResponse(GameResponseType type, String message) {
        this.type = type;
        this.message = message;
    }

    public GameResponseType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

}
