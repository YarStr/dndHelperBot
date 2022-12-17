package bots;

import botLogic.Analysis.Analyzer;
import botLogic.CommandHandler;

import dataIO.TelegramOutputBuilder;
import dataIO.TelegramOutputMessage;
import request.Request;
import request.RequestCreator;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

/**
 * Бот, работающий в телеграм
 */
public class TelegramBot extends TelegramLongPollingBot {

    /**
     * Поле имя бота
     */
    @Override
    public String getBotUsername() {
        return Configurations.botName;
    }

    /**
     * Поле токен бота
     */
    @Override
    public String getBotToken() {
        return Configurations.botToken;
    }

    /**
     * Поле модуль генератор запросов
     */
    private final RequestCreator requestCreator;
    /**
     * Поле обработчик запросов
     */
    private final CommandHandler commandHandler;

    /**
     * Поле создателя форматированного вывода
     */
    private final TelegramOutputBuilder telegramOutputBuilder;

    private final Analyzer analyzer;

    /**
     * Конструктор - создание нового объекта
     */
    public TelegramBot() {
        this.requestCreator = new RequestCreator();
        this.commandHandler = new CommandHandler();
        this.telegramOutputBuilder = new TelegramOutputBuilder();
        this.analyzer = new Analyzer();
    }

    /**
     * Метод, запускающий работу бота
     *
     * @param update все обновления чата
     */
    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inputMessage = update.getMessage();
                String analyzedText = analyzer.textAnalysis(inputMessage.getText());
                Request request = requestCreator.getRequest(analyzedText);

                TelegramOutputMessage telegramOutputMessage = telegramOutputBuilder.getSendMessage(
                        commandHandler.handleRequest(request), inputMessage.getChatId()
                );

                if (telegramOutputMessage.sendPhoto != null){
                    telegramOutputMessage.sendPhoto.getPhoto();
                    telegramOutputMessage.sendPhoto.getChatId();
                    execute(telegramOutputMessage.sendPhoto);
                }
                execute(telegramOutputMessage.sendMessage);
            }
        } catch (TelegramApiException | IOException e) {
            e.printStackTrace();
        }
    }
}
