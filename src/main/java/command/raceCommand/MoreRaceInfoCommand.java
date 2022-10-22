package command.raceCommand;

import command.Command;

/**
 * Класс команды вывода дополнительной информации в текущей ветке диалога бота
 */
public class MoreRaceInfoCommand extends RaceCommand implements Command {

    @Override
    public String getResult() {
        return "Здесь собрано больше информации по запросу: https://dnd.su" + race.getLink();
    }
}
