package parser;

public class NonExistentPageException extends Throwable {
    public NonExistentPageException(String message) {
        super(message);
    }
}
