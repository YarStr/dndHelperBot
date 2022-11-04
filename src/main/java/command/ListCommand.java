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
    public ListCommand(ArrayList<String> arguments) {
        sectionName = arguments.get(0);
    }

    public String getResult() throws InvalidCommandArgumentsException, FailedCommandExecutionException {
        try {
            return Parser.getPagesListFromSection(sectionName);
        } catch (NonExistentSectionException e){
            throw new InvalidCommandArgumentsException();
        } catch (FailedConnectionException e){
            throw new FailedCommandExecutionException();
        }
    }
}
