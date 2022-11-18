package bots;

import botLogic.CommandHandler;
import botLogic.KeyboardCreator;
import request.Request;
import request.RequestCreator;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Бот, работающий в телеграм
 */
public class TelegramBot extends TelegramLongPollingBot {

    /**
     * Поле имя бота
     */
    private final String botName = "dndAdviserBot";
    /**
     * Поле токен бота
     */
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
     * Поле создателя клавиатуры
     */
    private final KeyboardCreator keyboardCreator;

    /**
     * Конструктор - создание нового объекта
     */
    public TelegramBot() {
        this.requestCreator = new RequestCreator();
        this.commandHandler = new CommandHandler();
        this.keyboardCreator = new KeyboardCreator();
    }

    /**
     * Геттер для имени бота
     */
    @Override
    public String getBotUsername() {
        return botName;
    }
    /**
     * Геттер для токена
     */

    public String getBotToken() {
        return botToken;
    }

    /**
     * Метод, запускающий работу бота
     * @param update все обновления чата
     */
    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                Message inputMessage = update.getMessage();
                String chatId = inputMessage.getChatId().toString();
                Request request = requestCreator.getRequest(inputMessage.getText());

                SendMessage outputMessage = new SendMessage();
                outputMessage.setReplyMarkup(keyboardCreator.createKeyboard());
                outputMessage.setText(commandHandler.handleRequest(request));
                outputMessage.setChatId(chatId);
                execute(outputMessage);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
