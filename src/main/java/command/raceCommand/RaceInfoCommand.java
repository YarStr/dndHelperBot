package command.raceCommand;

import command.Command;
import command.exceptions.FailedCommandExecutionException;
import command.exceptions.InvalidCommandArgumentsException;
import pages.PageBuilder;

import java.util.ArrayList;

/**
 * Класс команды получения основной информации о расе
 */
public class RaceInfoCommand extends RaceCommand implements Command {

    private final ArrayList<String> arguments;

    /**
     * Конструктор - создание нового объекта команды
     *
     * @param arguments - аргументы команды
     * @throws FailedCommandExecutionException когда команда вызвана с неправильным набором аргументов
     * @throws InvalidCommandArgumentsException когда не удалось выполнить команду по причинам, не зависящим от пользователя
     */
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
