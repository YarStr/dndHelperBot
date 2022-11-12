package request;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Класс для обработки входных данных и преобразования их в сообщение
 */
public record RequestCreator() {
    /**
     * Функция, реализующая перевод входной строки в сообщение
     *
     * @return обработанное сообщение
     */
    public Request getMessage(String text) {
        ArrayList<String> parsedMessage = new ArrayList<>(Arrays.asList(text.split(" ")));
        String command = parsedMessage.get(0);
        parsedMessage.remove(0);
        return new Request(command, parsedMessage);
    }
}
