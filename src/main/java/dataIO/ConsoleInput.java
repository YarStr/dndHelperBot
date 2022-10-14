package dataIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс, реализующий получение сообщений от пользователя из консоли
 */
public class ConsoleInput implements InputModule {

    /**
     * Функция получения сообщения из консоли
     * @return сообщение
     */
    public String getMessage() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            return "";
        }
    }
}
