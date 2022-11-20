package command.exceptions;

/**
 * Исключение, вызываемое при неверно переданных команде аргументах
 */
public class InvalidCommandArgumentsException extends Exception {
    public InvalidCommandArgumentsException(String message) {
        super("У команды неверно введены аргументы. " + message);
    }
}
