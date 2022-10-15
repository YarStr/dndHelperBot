package request;

import command.ExeptionCommand;
import command.HelpCommand;
import command.ListCommand;
import command.RaceCommand;
import state.BotState;

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * В ЭТОМ ФАЙЛИКЕ ОНО РУГАЕТСЯ НА ТО ЧТО МЕТОДЫ НЕ ВСЕГДА ЧТО-ТО ВОЗВРАЩВЮТ
 * Хотя это не так.
 * Посмотри, что можно сделать, чтобы не ругалось :)
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * СДЕЛАНООООО
 */

/**
 * Класс обработки запросов пользователя и вызова соответствующей логики
 */
public class RequestHandler {
    /**
     * Поле состояние диалога бота
     */
    BotState state = BotState.Main;

    /**
     * Функция вызова логики по запросу
     *
     * @param request - запрос
     * @return сообщение - ответ на запрос
     */
    public String handleRequest(Request request) {
        switch (state) {
            case Main -> {
                state.nextState(request.command());
                return executeMainCommand(request);
            }
            case RaceInfo -> {
                state.nextState(request.command());
                return executeRaceInfoCommand(request);
            }
            default -> {
                return ("Ошибка в состоянии");
            }
        }
    }

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
                RaceCommand race = new RaceCommand();
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
            case "/help" -> {
                HelpCommand help = new HelpCommand();
                return help.getHelp();
            }
            case "/more" -> {
                RaceCommand race = new RaceCommand();
                return race.getMoreRaceInfo(request.argument());
            }
            default -> {
                ExeptionCommand exeption = new ExeptionCommand();
                return exeption.getExeptionCommand();
            }
        }
    }
}
