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
     * Функция обработки сообщения и вызова описанной в нём команды
     *
     * @param request - сообщение
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
     * @param request - сообщение
     * @return возвращает результат выполнения команды
     * ПЕРЕПИСАТЬ
     */
    private String executeCommand(Request request) throws InvalidCommandArgumentsException, FailedCommandExecutionException {
        Command currentCommand = switch (request.command()) {
            case "/start" -> new StartCommand();
            case "/help" -> new HelpCommand();
            case "/race" -> new RaceInfoCommand(request.arguments());
            case "/list" -> new ListCommand(request.arguments());
            case "/info" -> new MoreRaceInfoCommand(request.arguments());
            case "/exit" -> new ExitCommand();
            default -> throw new FailedCommandExecutionException();
        };
        return currentCommand.getResult();
    }
}
