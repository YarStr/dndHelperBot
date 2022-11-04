package botLogic;

import command.*;
import command.raceCommand.MoreRaceInfoCommand;
import command.raceCommand.RaceInfoCommand;
import message.Message;
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
     * @param message - сообщение
     * @return возвращает результат вызванной команды
     */
    public String handleMessage(Message message) {
        ArrayList<String> availableCommands = state.getAvailableCommands();
        String answer = "ОШИБКА! Введённая команда недоступна.";
        if (availableCommands.contains(message.command())) {
            try {
                answer = executeCommand(message);
                state = state.nextState(message);
            } catch (InvalidCommandArgumentsException e) {
                return "У команды неверно введены аргументы. Попробуй ещё раз!";
            } catch (FailedCommandExecutionException e) {
                return "Извините, я сломался и не смог выполнить команду. Попробуйте ещё раз.";
            }
        }
        return answer;
    }

    /**
     * Функция безопасного выполнения команды, описываемой сообщением
     *
     * @param message - сообщение
     * @return возвращает результат выполнения команды с информацией о том, была ли она корректно выполнена
     */
    private String safeExecuteCommand(Message message) {
        try {
            return executeCommand(message);
        } catch (InvalidCommandArgumentsException e) {
            return "Ошибка! Команде передан неверный аргумент";
        } catch (FailedCommandExecutionException e){
            return "Извините, я сломался и не смог выполнить команду. Попробуйте ещё раз.";
        }
    }

    /**
     * Функция выполнения команды, описываемой сообщением
     *
     * @param message - сообщение
     * @return возвращает результат выполнения команды
     * ПЕРЕПИСАТЬ
     */
    private String executeCommand(Message message) throws InvalidCommandArgumentsException, FailedCommandExecutionException {
        Command currentCommand = switch (message.command()) {
            case "/help" -> new HelpCommand();
            case "/race" -> new RaceInfoCommand(message.arguments());
            case "/list" -> new ListCommand(message.arguments());
            case "/more" -> new MoreRaceInfoCommand();
            case "/exit" -> new ExitCommand();
            default -> throw new FailedCommandExecutionException();
        };
        return currentCommand.getResult();
    }
}
