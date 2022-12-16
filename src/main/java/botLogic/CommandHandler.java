package botLogic;

import command.*;
import command.exceptions.FailedCommandExecutionException;
import command.exceptions.InvalidCommandArgumentsException;
import command.raceCommand.MoreRaceInfoCommand;
import command.raceCommand.RaceInfoCommand;
import request.Request;
import botLogic.state.BotState;

import java.util.ArrayList;

/**
 * Класс обработки входящих сообщений пользователя и вызова соответствующей логики
 */
public class CommandHandler {
    /**
     * Поле состояние диалога бота
     */
    private BotState state = BotState.Main;

    /**
     * Функция обработки запроса и вызова описанной в нём команды
     *
     * @param request - запрос
     * @return возвращает результат вызванной команды
     */
    public String handleRequest(Request request) {
        ArrayList<String> availableCommands = state.getAvailableCommands();
        String answer = "Введённая команда недоступна.";
        if (availableCommands.contains(request.command())) {
            try {
                answer = executeCommand(request);
                state = state.nextState(request);
            } catch (InvalidCommandArgumentsException e) {
                return e.getMessage();
            } catch (FailedCommandExecutionException e) {
                return "Извините, я сломался и не смог выполнить команду. Попробуйте ещё раз.";
            }
        }
        return answer;
    }

    /**
     * Функция выполнения команды, описываемой сообщением
     *
     * @param request запрос
     * @return возвращает результат выполнения команды
     * @throws InvalidCommandArgumentsException когда команда вызвана с неправильным набором аргументов
     * @throws FailedCommandExecutionException  когда не удалось выполнить команду по причинам, не зависящим от пользователя
     */
    private String executeCommand(Request request) throws InvalidCommandArgumentsException, FailedCommandExecutionException {
        Command currentCommand = switch (request.command()) {
            case CommandList.START -> new StartCommand();
            case CommandList.HELP -> new HelpCommand();
            case CommandList.RACE -> new RaceInfoCommand(request.arguments());
            case CommandList.LIST -> new ListCommand(request.arguments());
            case CommandList.INFO -> new MoreRaceInfoCommand(request.arguments());
            case CommandList.EXIT -> new ExitCommand();
            default -> throw new FailedCommandExecutionException();
        };
        return currentCommand.getResult();
    }
}
