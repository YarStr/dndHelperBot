package bots;

import botLogic.CommandHandler;

import dataIO.TelegramOutputBuilder;
import dataIO.TelegramOutputMessage;
import request.Request;
import request.RequestCreator;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
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
     * Поле создателя клавиатуры
     */
    private final TelegramOutputBuilder telegramOutputBuilder;

    /**
     * Конструктор - создание нового объекта
     */
    public TelegramBot() {
        this.requestCreator = new RequestCreator();
        this.commandHandler = new CommandHandler();
        this.telegramOutputBuilder = new TelegramOutputBuilder();
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
                Request request = requestCreator.getRequest(inputMessage.getText());

                TelegramOutputMessage telegramOutputMessage = telegramOutputBuilder.getSendMessage(commandHandler.handleRequest(request), inputMessage.getChatId());
                execute(telegramOutputMessage.sendMessage);
                execute(telegramOutputMessage.sendPhoto);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

     /*
      Метод, формирующий ответное сообщение

      @param inputMessage входящее сообщение
     * @param request      запрос от пользователя
     */
//    private SendMessage getOutputMessage(Message inputMessage, Request request) {
//        SendMessage outputMessage = new SendMessage();
//
//        StringBuilder stringBuilder = new StringBuilder();
//        for (FormattedText formattedText: commandHandler.handleRequest(request).information) {
//            switch (formattedText.format){
//                case ERROR -> stringBuilder.append("<u>").append(formattedText.text).append("</u>");
//                case TITLE -> stringBuilder.append("<b>").append(formattedText.text).append("</b>");
//                case NORMAL -> stringBuilder.append("<i>").append(formattedText.text).append("</i>");
//            }
//            stringBuilder.append('\n');
//        }
//
//        outputMessage.setParseMode(ParseMode.HTML);
//        outputMessage.setText(stringBuilder.toString());
//        outputMessage.setReplyMarkup(keyboardCreator.createKeyboard());
//        outputMessage.setChatId(inputMessage.getChatId().toString());
//        return outputMessage;
//    }
//}
