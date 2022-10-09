package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Класс, который осуществляет получение сообщений от пользователя
 */

public class ConsoleBot {

    /**
     * Метод, который запсукает работу консоли
     * Пустые строки ботом игнорируются
     */

    public void start() {
        System.out.println(BotCommands.getHelpCommand());
        while (true) {
            String message = "";
            while (message.isEmpty())
                message = getMessage();
            reactToMessage(message);
        }
    }

    /**
     * Метод, который записывает полученные данные от пользователя
     *
     * @return поступивший запрос в успешном случае, иначе - ошибка
     * @throws IOException вызывается, если ввод не был осуществлен
     */

    private String getMessage() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            return "";
        }
    }

    /**
     * Метод, который выводит информацию, вызывая методы реакции,
     * в зависимости от того, функция это или поисковый запрос
     *
     * @param message - запрос пользователя
     */

    private void reactToMessage(String message) {
        if (message.charAt(0) == '/') {
            System.out.println(InputDataHandler.executeCommand(message));
        } else {
            System.out.println(InputDataHandler.processRequest(message));
        }
    }
}
