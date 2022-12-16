package parser.exceptions;

/**
 * Исключение, вызываемое при обращении к несуществующей странице на сайте dnd.su
 */
public class NonExistentPageException extends Throwable {
    public NonExistentPageException(String message) {
        super(message);
    }
}
