package dataIO;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;

public class TelegramOutputMessage {
    /**
     * Текст
     */
    public SendMessage sendMessage;

    /**
     * Фото
     */
    public SendPhoto sendPhoto;

    public TelegramOutputMessage() {
        this.sendMessage = new SendMessage();
        this.sendPhoto = new SendPhoto();
    }
}
