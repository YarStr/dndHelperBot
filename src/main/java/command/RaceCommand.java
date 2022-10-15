package command;

import directories.Race;

public class RaceCommand {

    public static Race race;

    public String getRaceInfo(String argument) {
        race = new Race(argument);
        return race.getMainInformation();
    }

    public String getMoreRaceInfo(String argument) {
        race = new Race(argument);
        return "Здесь собрано больше информации по запросу: " + race.getLink();
    }
}
