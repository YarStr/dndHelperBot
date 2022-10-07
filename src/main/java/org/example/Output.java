package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Output {
    /**
     * метод, реализующий получение сообщения от пользователя, игнорируя пустые строки
     */
    public static String message() throws IOException {
        String line;
        do {
            line = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } while (line.isEmpty());
        return line;
    }
}
