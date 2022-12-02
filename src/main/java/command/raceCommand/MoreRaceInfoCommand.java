package command.raceCommand;

import command.Command;
import command.exceptions.InvalidCommandArgumentsException;
import messagePackage.Format;
import messagePackage.FormattedText;
import messagePackage.MessagePackage;
import messagePackage.MessagePackageBuilder;

import java.util.ArrayList;

/**
 * Класс команды вывода дополнительной информации в текущей ветке диалога бота
 */
public class MoreRaceInfoCommand extends RaceCommand implements Command {
    /**
     * Поле аргументы команды
     */

    private final ArrayList<String> arguments;

    /**
     * Конструктор - создание нового объекта команды
     *
     * @param arguments аргументы команды
     * @throws InvalidCommandArgumentsException когда команде переданы неверные аргументы
     */
    public MoreRaceInfoCommand(ArrayList<String> arguments) throws InvalidCommandArgumentsException {
        if (arguments.contains("link"))
            this.arguments = arguments;
        else
            this.arguments = processFeatureArguments(arguments);
    }

    @Override
    public MessagePackage getResult() {
        if (arguments.contains("link")) {
            FormattedText title = new FormattedText("Ссылка на страницу расы:", Format.TITLE);
            return new MessagePackageBuilder()
                    .addInformation(title)
                    .addInformation(race.getLink())
                    .build();
        }
        return race.getFeatures(arguments);
    }
}
