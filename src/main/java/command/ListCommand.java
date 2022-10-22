package command;

import parser.Parser;

import java.io.IOException;
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

    public String getResult() throws IOException {
        return Parser.getPagesList(sectionName);
    }
}
