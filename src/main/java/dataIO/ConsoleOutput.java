package dataIO;

/**
 * Класс, реализующий вывод сообщений в консоль
 */
public class ConsoleOutput implements OutputModule {
    /**
     * Функция отправки сообщения в консоль
     * @param message - сообщение
     */
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
