package botLogic;

import command.*;
import command.exceptions.FailedCommandExecutionException;
import command.exceptions.InvalidCommandArgumentsException;
import command.raceCommand.MoreRaceInfoCommand;
import command.raceCommand.RaceInfoCommand;
import messagePackage.Format;
import messagePackage.FormattedText;
import messagePackage.MessagePackage;
import messagePackage.MessagePackageBuilder;
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
    private ArrayList<String> availableCommands = state.getAvailableCommands();

    /**
     * Функция обработки запроса и вызова описанной в нём команды
     *
     * @param request - запрос
     * @return возвращает результат вызванной команды
     */
    public MessagePackage handleRequest(Request request) {
        MessagePackageBuilder messagePackageBuilder = new MessagePackageBuilder();
        FormattedText information = new FormattedText();

        if (availableCommands.contains(request.command())) {
            try {
                MessagePackage messagePackage = executeCommand(request);
                messagePackageBuilder.addInformation(messagePackage.information);
                state = state.nextState(request);
            } catch (InvalidCommandArgumentsException e) {
                information.text = e.getMessage();
                information.format = Format.ERROR;
            } catch (FailedCommandExecutionException e) {
                information.text = "Извините, я сломался и не смог выполнить команду. Попробуйте ещё раз.";
                information.format = Format.ERROR;
            }
        } else {
            information.text = "Введённая команда недоступна.";
            information.format = Format.NORMAL;
        }

        availableCommands = state.getAvailableCommands();


        if (information.text != null)
            messagePackageBuilder.addInformation(information);

        return messagePackageBuilder
                .addAvailableCommands(availableCommands)
                .build();
    }

    /**
     * Функция выполнения команды, описываемой сообщением
     *
     * @param request запрос
     * @return возвращает результат выполнения команды
     * @throws InvalidCommandArgumentsException когда команда вызвана с неправильным набором аргументов
     * @throws FailedCommandExecutionException  когда не удалось выполнить команду по причинам, не зависящим от пользователя
     */
    private MessagePackage executeCommand(Request request) throws InvalidCommandArgumentsException, FailedCommandExecutionException {
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
