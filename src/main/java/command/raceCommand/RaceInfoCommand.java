package command.raceCommand;

import command.Command;
import command.FailedCommandExecutionException;
import command.InvalidCommandArgumentsException;
import pages.PageBuilder;

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
    private final ArrayList<String> arguments;

    public RaceInfoCommand(ArrayList<String> arguments) throws FailedCommandExecutionException, InvalidCommandArgumentsException {
        if (arguments.isEmpty())
            throw new InvalidCommandArgumentsException("Вы не ввели ничего, кроме названия команды.");
        race = new PageBuilder("race", arguments.get(0))
                .setLink()
                .setInfoBlocks()
                .build();
        arguments.remove(0);
        this.arguments = processFeatureArguments(arguments);
    }

    @Override
    public String getResult() {
        return race.getFeatures(arguments);
    }
}
