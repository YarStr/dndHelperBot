package command.raceCommand;

import command.Command;
import pages.Page;

import java.util.ArrayList;

/**
 * Класс команды получения основной информации о расе
 */
public class RaceInfoCommand extends RaceCommand implements Command {
    /**
     * Конструктор - создание нового объекта команды
     *
     * @param arguments - аргументы команды
     * @throws Exception вызывает исключение в случае невозможности создать команду с данными аргументами
     */
    public RaceInfoCommand(ArrayList<String> arguments) throws Exception {
        race = new Page(arguments.get(0));
    }

    @Override
    public String getResult() {
        return race.getMainInformation();
    }
}
