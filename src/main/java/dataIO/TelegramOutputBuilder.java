package dataIO;

import botLogic.KeyboardCreator;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import packedMessage.FormattedText;
import packedMessage.PackedMessage;

public class TelegramOutputBuilder {

    public TelegramOutputMessage getSendMessage(PackedMessage packedMessage, Long chatID) {
        TelegramOutputMessage telegramOutputMessage = new TelegramOutputMessage();
        KeyboardCreator keyboardCreator = new KeyboardCreator();

        StringBuilder stringBuilder = new StringBuilder();
        for (FormattedText formattedText: packedMessage.information) {
            switch (formattedText.format){
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


        if(packedMessage.additionalData != null) {
            telegramOutputMessage.sendPhoto.setChatId(chatID);
            telegramOutputMessage.sendPhoto.setPhoto(packedMessage.additionalData);
        }

        return telegramOutputMessage;
    }
}
