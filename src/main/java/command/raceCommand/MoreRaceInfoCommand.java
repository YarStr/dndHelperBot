package command.raceCommand;

import command.Command;
import command.InvalidCommandArgumentsException;

import java.util.ArrayList;


/**
 * Класс команды вывода дополнительной информации в текущей ветке диалога бота
 */
public class MoreRaceInfoCommand extends RaceCommand implements Command {

    private final ArrayList<String> arguments;
    public MoreRaceInfoCommand(ArrayList<String> arguments) throws InvalidCommandArgumentsException {
        if (arguments.contains("link"))
            this.arguments = arguments;
        else
            this.arguments = processFeatureArguments(arguments);
    }

    @Override
    public String getResult() {
        if (arguments.contains("link"))
            return "Ссылка на страницу расы: https://dnd.su" + race.getLink();
        return race.getFeatures(arguments);
    }
}
