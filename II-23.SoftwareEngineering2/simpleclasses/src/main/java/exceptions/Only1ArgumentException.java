package exceptions;

public class Only1ArgumentException extends Exception {
    public Only1ArgumentException() {}

    public Only1ArgumentException(String message) {
        super(message);
    }
}
