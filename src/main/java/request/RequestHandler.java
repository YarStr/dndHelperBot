package request;

import command.ExeptionCommand;
import command.HelpCommand;
import command.ListCommand;
import command.RaceCommand;
import state.BotState;

/**
 * Класс обработки запросов пользователя и вызова соответствующей логики
 */
public class RequestHandler {
    /**
     * Поле состояние диалога бота
     */
    private BotState state = BotState.Main;

    /**
     * Функция вызова логики по запросу
     *
     * @param request - запрос
     * @return сообщение - ответ на запрос
     */
    public String handleRequest(Request request) {
        switch (state) {
            case Main -> {
                state = state.nextState(request.command());
                return executeMainCommand(request);
            }
            case RaceInfo -> {
                state = state.nextState(request.command());
                return executeRaceInfoCommand(request);
            }
            default -> {
                state = state.nextState("Error");
                return "Error";
            }
        }
    }

    /**
     * Поле с данными расы для команд
     */
    private final RaceCommand race = new RaceCommand();

    /**
     * Функция вызова по запросу команды, доступной из состояния диалога Main
     *
     * @param request - запрос
     * @return - сообщение - результат выполнение команды
     */
    private String executeMainCommand(Request request) {
        switch (request.command()) {
            case "/help" -> {
                HelpCommand help = new HelpCommand();
                return help.getHelp();
            }
            case "/list" -> {
                ListCommand list = new ListCommand();
                return list.getRaceList();
            }
            case "/info" -> {
                if (race.getRaceInfo(request.argument()).equals("Error")) {
                    state = state.nextState("Error");
                    ExeptionCommand exeption = new ExeptionCommand();
                    return exeption.getExeptionArgument();
                }
                return race.getRaceInfo(request.argument());
            }
            default -> {
                ExeptionCommand exeption = new ExeptionCommand();
                return exeption.getExeptionCommand();
            }
        }
    }

    /**
     * Функция вызова по запросу команды, доступной из состояния диалога RaceInfo
     *
     * @param request - запрос
     * @return - сообщение - результат выполнение команды
     */
    private String executeRaceInfoCommand(Request request) {
        switch (request.command()) {
            case "/more" -> {
                return race.getMoreRaceInfo();
            }
            default -> {
                return handleRequest(request);
            }
        }
    }
}
