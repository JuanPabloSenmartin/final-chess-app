package exceptions;

public class InvalidPositionException extends Exception{
    public  InvalidPositionException(){
        super("position doesn't exist");
    }
}
