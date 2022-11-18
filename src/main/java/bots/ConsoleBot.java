package bots;

import dataIO.*;
import botLogic.CommandHandler;
import request.Request;
import request.RequestCreator;

/**
 * Класс бота, работающего в консоли
 */

public class ConsoleBot {

    private final InputModule inputModule;
    /**
     * Поле модуль генератор запросов
     */
    private final RequestCreator requestCreator;
    /**
     * Поле обработчик запросов
     */
    private final CommandHandler commandHandler;
    /**
     * Поле модуль вывода сообщений
     */
    private final OutputModule outputModule;

    /**
     * Конструктор - создание нового объекта
     */
    public ConsoleBot() {
        this.inputModule = new ConsoleInput();
        this.requestCreator = new RequestCreator();
        this.commandHandler = new CommandHandler();
        this.outputModule = new ConsoleOutput();
    }

    /**
     * Функция, запускающая работу бота
     */
    public void start() {
        Request request = requestCreator.getRequest("/start");
        outputModule.sendData(commandHandler.handleRequest(request));
        while (true) {
            String inputLine = null;
            while (inputLine == null)
                inputLine = inputModule.getData();
            request = requestCreator.getRequest(inputLine);
            outputModule.sendData(commandHandler.handleRequest(request));
        }
    }
}
