//package bots;
//
//import botLogic.CommandHandler;
//import botLogic.KeyboardCreator;
//import request.Request;
//import request.RequestCreator;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
///**
// * Бот, работающий в телеграм
// */
//public class TelegramBot extends TelegramLongPollingBot {
//
//    /**
//     * Поле имя бота
//     */
//    @Override
//    public String getBotUsername() {
//        return Configurations.botName;
//    }
//
//    /**
//     * Поле токен бота
//     */
//    @Override
//    public String getBotToken() {
//        return Configurations.botToken;
//    }
//
//    /**
//     * Поле модуль генератор запросов
//     */
//    private final RequestCreator requestCreator;
//    /**
//     * Поле обработчик запросов
//     */
//    private final CommandHandler commandHandler;
//
//    /**
//     * Поле создателя клавиатуры
//     */
//    private final KeyboardCreator keyboardCreator;
//
//    /**
//     * Конструктор - создание нового объекта
//     */
//    public TelegramBot() {
//        this.requestCreator = new RequestCreator();
//        this.commandHandler = new CommandHandler();
//        this.keyboardCreator = new KeyboardCreator();
//    }
//
//    /**
//     * Метод, запускающий работу бота
//     *
//     * @param update все обновления чата
//     */
//    @Override
//    public void onUpdateReceived(Update update) {
//        try {
//            if (update.hasMessage() && update.getMessage().hasText()) {
//                Message inputMessage = update.getMessage();
//                Request request = requestCreator.getRequest(inputMessage.getText());
//
//                execute(getOutputMessage(inputMessage, request));
//            }
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Метод, формирующий ответное сообщение
//     *
//     * @param inputMessage входящее сообщение
//     * @param request      запрос от пользователя
//     */
//    private SendMessage getOutputMessage(Message inputMessage, Request request) {
//        SendMessage outputMessage = new SendMessage();
//        outputMessage.setText(commandHandler.handleRequest(request));
//        outputMessage.setReplyMarkup(keyboardCreator.createKeyboard(request));
//        outputMessage.setChatId(inputMessage.getChatId().toString());
//        return outputMessage;
//    }
//}
