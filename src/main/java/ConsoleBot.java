import dataIO.*;

import java.io.IOException;


/**
 * Класс, который осуществляет получение сообщений от пользователя
 */

public class ConsoleBot {

    /**
     * Метод, который запсукает работу консоли
     * Пустые строки ботом игнорируются
     */

    private final OutputModule outputModule;
    private final InputModule inputModule;
    private final CommandHandler commandHandler;

    public ConsoleBot() {
        this.outputModule = new ConsoleOutput();
        this.commandHandler = new CommandHandler(new ConsoleInput());
        this.inputModule = new ConsoleInput();
    }

    public void start(){
        while (true) {
            String message = "";
            while (message.isEmpty())
                message = inputModule.getMessage();
            outputModule.sendMessage(commandHandler.handleCommand(message));
        }
    }


}
