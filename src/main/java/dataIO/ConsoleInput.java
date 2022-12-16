package dataIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс, реализующий получение данных от пользователя из консоли
 */
public class ConsoleInput implements InputModule {

    /**
     * Функция получения введённых данных из консоли
     *
     * @return сообщение
     */
    public String getData() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            return "";
        }
    }
}
