package bots;

import dataIO.*;
import botLogic.CommandHandler;
import message.Message;
import message.MessageCreator;

/**
 * Класс бота, работающего в консоли
 */

public class ConsoleBot {
    /**
     * Поле модуль генератор запросов
     */
    private final MessageCreator messageCreator;
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
        this.messageCreator = new MessageCreator(new ConsoleInput());
        this.commandHandler = new CommandHandler();
        this.outputModule = new ConsoleOutput();
    }

    /**
     * Функция, запускающая работу бота
     */
    public void start() {
        outputModule.sendData("""
                Привет! Я dndHelperBot, помогающий быстро найти информацию о правилах игры D&D 5e с сайта https://dnd.su
                Для вывода доступных команд введите /help""");
        while (true) {
            Message message = null;
            while (message == null)
                message = messageCreator.getMessage();
            outputModule.sendData(commandHandler.handleMessage(message));
        }
    }
}
