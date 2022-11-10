package command;

import directories.Race;

/**
 * Класс, реализующий получение информации
 */
public class RaceCommand {

    /**
     * Поле с данными поступившего запроса
     */
    private Race race;


    /**
     * Функция, получающая основную информацию по запросу
     *
     * @param argument - название расы
     * @return сообщение - ответ на запрос
     */
    public String getRaceInfo(String argument) {
        race = new Race(argument);
        return race.getMainInformation();
    }

    /**
     * Функция, получающая ссылку на запрос
     *
     * @return сообщение - ответ на запрос
     */
    public String getMoreRaceInfo() {
        return "Здесь собрано больше информации по запросу: https://dnd.su" + race.getLink();
    }
}
