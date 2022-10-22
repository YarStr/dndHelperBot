package message;

import dataIO.InputModule;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс для обработки входных данных и преобразования их в сообщение
 */
public record MessageCreator(InputModule inputModule) {
    /**
     * Функция, реализующая перевод входной строки в сообщение
     *
     * @return обработанное сообщение
     */
    public Message getMessage() {
        String inputLine = inputModule.getData();
        ArrayList<String> parsedMessage = new ArrayList<>(Arrays.asList(inputLine.split(" ")));
        String command = parsedMessage.get(0);
        parsedMessage.remove(0);
        return new Message(command, parsedMessage);
    }
}
