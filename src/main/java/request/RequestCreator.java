package request;

import dataIO.InputModule;

/**
 * Реализация конструктора Request
 */
public record RequestCreator(InputModule inputModule) {

    /**
     * Функция, получающая аргументы для конструктора
     *
     * @return создание нового Request
     */
    public Request getRequest() {
        String message = inputModule.getMessage();
        String[] parsedMessage = message.split(" ");
        if (parsedMessage.length > 1) {
            String command = parsedMessage[0];
            String argument = parsedMessage[1];
            return new Request(command, argument);
        } else if (parsedMessage.length == 1) {
            return new Request(message, null);
        } else {
            return null;
        }
    }
}
