package dataIO;

import botLogic.KeyboardCreator;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import packedMessage.FormattedText;
import packedMessage.PackedMessage;

/**
 * Класс, реализующий создание форматированного вывода в телеграм
 */
public class TelegramOutputBuilder {
    /**
     * Функция сборки сообщения
     *
     * @param packedMessage - пакет сообщения
     * @param chatID        - ID чата
     * @return готовое к отправке сообщение
     */

    public TelegramOutputMessage getSendMessage(PackedMessage packedMessage, Long chatID) {
        TelegramOutputMessage telegramOutputMessage = new TelegramOutputMessage();
        KeyboardCreator keyboardCreator = new KeyboardCreator();

        StringBuilder stringBuilder = new StringBuilder();
        for (FormattedText formattedText : packedMessage.information) {
            switch (formattedText.format) {
                case ERROR -> stringBuilder.append("<u>").append(formattedText.text).append("</u>");
                case TITLE -> stringBuilder.append("<b>").append(formattedText.text).append("</b>");
                case NORMAL -> stringBuilder.append("<i>").append(formattedText.text).append("</i>");
            }
            stringBuilder.append('\n');
        }

        telegramOutputMessage.sendMessage.setChatId(chatID);
        telegramOutputMessage.sendMessage.setParseMode(ParseMode.HTML);
        telegramOutputMessage.sendMessage.setText(stringBuilder.toString());
        telegramOutputMessage.sendMessage.setReplyMarkup(keyboardCreator.createKeyboard(packedMessage.availableCommands));


        if (packedMessage.additionalData != null) {
            telegramOutputMessage.sendPhoto.setChatId(chatID);
            telegramOutputMessage.sendPhoto.setPhoto(packedMessage.additionalData);
        }

        return telegramOutputMessage;
    }
}
