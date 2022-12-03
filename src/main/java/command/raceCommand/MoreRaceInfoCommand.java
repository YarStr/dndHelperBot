package command.raceCommand;

import command.Command;
import command.exceptions.InvalidCommandArgumentsException;
import packedMessage.Format;
import packedMessage.FormattedText;
import packedMessage.PackedMessage;
import packedMessage.PackedMessageBuilder;

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
    public PackedMessage getResult() {
        if (arguments.contains("link")) {
            FormattedText title = new FormattedText("Ссылка на страницу расы:", Format.TITLE);
            return new PackedMessageBuilder()
                    .addInformation(title)
                    .addInformation(race.getLink())
                    .build();
        }
        return race.getFeatures(arguments);
    }
}
