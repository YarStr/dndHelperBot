package command;

public class InvalidCommandArgumentsException extends Exception {
    public InvalidCommandArgumentsException(String message) {
        super("У команды неверно введены аргументы. " + message);
    }
}
