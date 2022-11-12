package bots;

import botLogic.CommandHandler;
import request.Request;
import request.RequestCreator;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    private final String botName = "dndAdviserBot";
    private final String botToken = "5694306706:AAHq2aAwGr4oEugT0syBw8jabCz_WZRZt7A";

    /**
     * Поле модуль генератор запросов
     */
    private final RequestCreator requestCreator;
    /**
     * Поле обработчик запросов
     */
    private final CommandHandler commandHandler;

    /**
     * Конструктор - создание нового объекта
     */
    public TelegramBot() {
        this.requestCreator = new RequestCreator();
        this.commandHandler = new CommandHandler();
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inputMessage = update.getMessage();
                String chatId = inputMessage.getChatId().toString();
                Request message = requestCreator.getMessage(inputMessage.getText());

                SendMessage outMess = new SendMessage();
                outMess.setText(commandHandler.handleRequest(message));
                outMess.setChatId(chatId);
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
