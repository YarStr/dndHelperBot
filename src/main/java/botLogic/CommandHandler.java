package botLogic;

import command.*;
import command.exceptions.FailedCommandExecutionException;
import command.exceptions.InvalidCommandArgumentsException;
import command.raceCommand.MoreRaceInfoCommand;
import command.raceCommand.RaceInfoCommand;
import packedMessage.Format;
import packedMessage.FormattedText;
import packedMessage.PackedMessage;
import packedMessage.PackedMessageBuilder;
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
     * Поле со списком возможных команд для данного состояния
     */
    private ArrayList<String> availableCommands = state.getAvailableCommands();

    /**
     * Функция обработки запроса и вызова описанной в нём команды
     *
     * @param request - запрос
     * @return возвращает результат вызванной команды
     */
    public PackedMessage handleRequest(Request request) {
        PackedMessageBuilder packedMessageBuilder = new PackedMessageBuilder();
        FormattedText information = new FormattedText();

        if (availableCommands.contains(request.command())) {
            try {
                PackedMessage packedMessage = executeCommand(request);
                packedMessageBuilder.addInformation(packedMessage.information);

                if (packedMessage.additionalData != null)
                    packedMessageBuilder.addAdditionalData(packedMessage.additionalData);

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
            packedMessageBuilder.addInformation(information);

        return packedMessageBuilder
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
    private PackedMessage executeCommand(Request request) throws InvalidCommandArgumentsException, FailedCommandExecutionException {
        Command currentCommand = switch (request.command()) {
            case Commands.START -> new StartCommand();
            case Commands.HELP -> new HelpCommand();
            case Commands.RACE -> new RaceInfoCommand(request.arguments());
            case Commands.LIST -> new ListCommand(request.arguments());
            case Commands.INFO -> new MoreRaceInfoCommand(request.arguments());
            case Commands.EXIT -> new ExitCommand();
            case Commands.HELLO -> new HelloCommand();
            case Commands.THANKS -> new ThanksCommand();
            default -> throw new FailedCommandExecutionException();
        };
        return currentCommand.getResult();
    }
}
