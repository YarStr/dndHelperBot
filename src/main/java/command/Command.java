package command;

import command.exceptions.FailedCommandExecutionException;
import command.exceptions.InvalidCommandArgumentsException;
import packedMessage.PackedMessage;

/**
 * Интерфейс команды бота
 */
public interface Command {
    /**
     * Функция получения результата выполнения команды
     *
     * @return результат команд
     * @throws InvalidCommandArgumentsException когда команда вызвана с неправильным набором аргументов
     * @throws FailedCommandExecutionException  когда не удалось выполнить команду по причинам, не зависящим от пользователя
     */
    PackedMessage getResult() throws InvalidCommandArgumentsException, FailedCommandExecutionException;
}
