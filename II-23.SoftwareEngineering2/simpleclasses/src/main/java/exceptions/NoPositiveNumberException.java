package exceptions;

public class NoPositiveNumberException extends Exception {
    public NoPositiveNumberException() {}

    public NoPositiveNumberException(String message) {
        super(message);
    }
}
