package command;

import java.io.IOException;

/**
 * Интерфейс команды бота
 */
public interface Command {
    /**
     * Функция получения результата выполнения команды
     *
     * @return результат команды
     * @throws IOException вызывает исключение в случае ошибки при получении данных командой
     */
    String getResult() throws IOException;
}
