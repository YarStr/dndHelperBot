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
        if (availableCommands.contains(message.command())) {
            CommandExecutionResult executionResult = safeExecuteCommand(message);
            if (executionResult.isCorrectlyExecuted()) {
                state = state.nextState(message);
            }
            return executionResult.result();
        }
        return "ОШИБКА! Введённая команда недоступна";
    }

    /**
     * Функция безопасного выполнения команды, описываемой сообщением
     *
     * @param message - сообщение
     * @return возвращает результат выполнения команды с информацией о том, была ли она корректно выполнена
     */
    private CommandExecutionResult safeExecuteCommand(Message message) {
        try {
            return executeCommand(message);
        } catch (Exception e) {
            return new CommandExecutionResult("Ошибка! Неверные аргументы команды", false);
        }
    }

    /**
     * Функция выполнения команды, описываемой сообщением
     *
     * @param message - сообщение
     * @return возвращает результат выполнения команды
     * @throws Exception вызывает исключение в случае ошибки при обработке аргументов команды
     */
    private CommandExecutionResult executeCommand(Message message) throws Exception {
        Command currentCommand = switch (message.command()) {
            case "/help" -> new HelpCommand();
            case "/race" -> new RaceInfoCommand(message.arguments());
            case "/list" -> new ListCommand(message.arguments());
            case "/more" -> new MoreRaceInfoCommand();
            case "/exit" -> new ExitCommand();
            default -> throw new Exception();
        };
        return new CommandExecutionResult(currentCommand.getResult(), true);
    }
}
