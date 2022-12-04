package dataIO;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

public class TelegramOutputMessage {
    /**
     * Поле с текстом сообщения
     */
    public SendMessage sendMessage;

    /**
     * Поле с фотографией к сообщению
     */
    public SendPhoto sendPhoto;

    /**
     * Конструктор
     */
    public TelegramOutputMessage() {
        this.sendMessage = new SendMessage();
        this.sendPhoto = new SendPhoto();
    }
}
