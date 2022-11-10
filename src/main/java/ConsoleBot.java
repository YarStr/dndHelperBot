import command.HelpCommand;
import dataIO.*;
import request.Request;
import request.RequestCreator;
import request.RequestHandler;

/**
 * Класс бота, работающего в консоли
 */

public class ConsoleBot {
    /**
     * Поле модуль генератор запросов
     */
    private final RequestCreator requestCreator;
    /**
     * Поле обработчик запросов
     */
    private final RequestHandler requestHandler;
    /**
     * Поле модуль вывода сообщений
     */
    private final OutputModule outputModule;

    /**
     * Конструктор - создание нового объекта
     */
    public ConsoleBot() {
        this.requestCreator = new RequestCreator(new ConsoleInput());
        this.requestHandler = new RequestHandler();
        this.outputModule = new ConsoleOutput();
    }

    /**
     * Функция, запускающая работу бота
     */
    public void start() {
        HelpCommand help = new HelpCommand();
        System.out.println(help.getHelp());
        while (true) {
            Request request = null;
            while (request == null)
                request = requestCreator.getRequest();
            outputModule.sendMessage(requestHandler.handleRequest(request));
        }
    }


}
