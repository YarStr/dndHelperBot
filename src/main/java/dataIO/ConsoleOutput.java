package dataIO;

/**
 * Класс, реализующий вывод сообщений в консоль
 */
public class ConsoleOutput implements OutputModule {
    /**
     * Функция отправки сообщения в консоль
     *
     * @param data - сообщение
     */
    public void sendData(String data) {
        System.out.println(data);
    }
}
