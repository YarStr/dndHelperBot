package command;

import botLogic.FailedConnectionException;
import botLogic.NonExistentSectionException;
import parser.Parser;

import java.util.ArrayList;

/**
 * Класс команды вывода списка доступных элементов секции сайта
 */
public class ListCommand implements Command {
    /**
     * Поле название секции сайта
     */
    private final String sectionName;

    /**
     * Конструктор - создание нового объекта команды
     *
     * @param arguments - аргументы команды
     */
    public ListCommand(ArrayList<String> arguments) throws InvalidCommandArgumentsException {
        if (arguments.size() != 1)
            throw new InvalidCommandArgumentsException("У команды /list должен быть 1 аргумент.");
        sectionName = arguments.get(0);
    }

    public String getResult() throws InvalidCommandArgumentsException, FailedCommandExecutionException {
        try {
            return Parser.getPagesListFromSection(sectionName);
        } catch (NonExistentSectionException e){
            throw new InvalidCommandArgumentsException(e.getMessage());
        } catch (FailedConnectionException e){
            throw new FailedCommandExecutionException();
        }
    }
}
