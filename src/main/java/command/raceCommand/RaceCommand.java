package command.raceCommand;

import command.exceptions.InvalidCommandArgumentsException;
import pages.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Суперкласс команд вывода информации о расе, реализующий хранение общей для них информации со страницы расы
 */
public class RaceCommand {
    /**
     * Поле страница расы
     */
    protected static Page race;
    protected final ArrayList<String> AVAILABLE_ARGUMENTS = new ArrayList<>(Arrays.asList(
            "score", "age", "alignment", "size", "speed", "languages", "all"
    ));

    protected ArrayList<String> processFeatureArguments(ArrayList<String> arguments) throws InvalidCommandArgumentsException {
        if (arguments.isEmpty())
            arguments.add("all");

        else if (!AVAILABLE_ARGUMENTS.containsAll(arguments)) {
            String notAvailableArguments = arguments
                    .stream()
                    .filter(x -> !AVAILABLE_ARGUMENTS.contains(x))
                    .collect(Collectors.joining(", "));
            throw new InvalidCommandArgumentsException("Не являются особенностями расы: " + notAvailableArguments);
        }
        return arguments;
    }
}
