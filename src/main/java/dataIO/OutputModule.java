package dataIO;

/**
 * Интерфейс модуля, выводящего сообщения пользователю
 */
public interface OutputModule {
    /**
     * Функция отправки сообщения
     * @param message - сообщение
     */
    void sendMessage(String message);
}
