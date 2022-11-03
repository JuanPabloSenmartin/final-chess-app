package exceptions;

import chess.Color;

public class VictoryException extends Exception{
    public VictoryException(String playerName, Color color){
        super(color.name() + " is the winner! Congratulations " + playerName);
    }
}
