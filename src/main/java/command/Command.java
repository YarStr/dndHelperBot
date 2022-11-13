package command;

import command.exceptions.FailedCommandExecutionException;
import command.exceptions.InvalidCommandArgumentsException;

/**
 * Интерфейс команды бота
 */
public interface Command {
    /**
     * Функция получения результата выполнения команды
     *
     * @return результат команды
     * @throws InvalidCommandArgumentsException когда команда вызвана с неправильным набором аргументов
     * @throws FailedCommandExecutionException когда не удалось выполнить команду по причинам, не зависящим от пользователя
     */
    String getResult() throws InvalidCommandArgumentsException, FailedCommandExecutionException;
}
