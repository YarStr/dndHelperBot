package dataIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput implements InputModule {

    /**
     * Метод, который записывает полученные данные от пользователя
     *
     * @return поступивший запрос в успешном случае, иначе - ошибка
     * @throws IOException вызывается, если ввод не был осуществлен
     */


    public String getMessage() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            return "";
        }
    }
}
