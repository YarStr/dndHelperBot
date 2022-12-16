package parser.exceptions;

/**
 * Исключение, вызываемое при обращении к несуществующей секции на сайте dnd.su
 */
public class NonExistentSectionException extends Exception {
    public NonExistentSectionException(String message) {
        super(message);
    }
}
