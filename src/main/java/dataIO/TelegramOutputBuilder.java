package dataIO;

import botLogic.KeyboardCreator;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.objects.InputFile;
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
                case ERROR -> stringBuilder.append("<code>").append(formattedText.text).append("</code>");
                case TITLE -> stringBuilder.append("<b>").append(formattedText.text).append("</b>");
                case NORMAL -> stringBuilder.append(formattedText.text).append('\n');
                case LIST_ELEMENT -> stringBuilder.append("• ").append(formattedText.text);
            }
            stringBuilder.append('\n');
        }

        telegramOutputMessage.sendMessage.setChatId(chatID);
        telegramOutputMessage.sendMessage.setParseMode(ParseMode.HTML);
        telegramOutputMessage.sendMessage.setText(stringBuilder.toString());
        telegramOutputMessage.sendMessage.setReplyMarkup(
                keyboardCreator.createKeyboard(packedMessage.availableCommands)
        );


        if (packedMessage.additionalData != null) {
            telegramOutputMessage.sendPhoto.setChatId(chatID);
            telegramOutputMessage.sendPhoto.setPhoto(new InputFile(packedMessage.additionalData, "raceImage"));
        }

        return telegramOutputMessage;
    }
}
