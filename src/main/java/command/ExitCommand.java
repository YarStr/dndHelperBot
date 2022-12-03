package command;

import packedMessage.Format;
import packedMessage.FormattedText;
import packedMessage.PackedMessage;
import packedMessage.PackedMessageBuilder;

/**
 * Класс команды выхода из текущей ветки диалога бота
 */
public class ExitCommand implements Command {
    @Override
    public PackedMessage getResult() {
        FormattedText text = new FormattedText("Что хочешь ещё узнать? :)", Format.NORMAL);
        return new PackedMessageBuilder()
                .addInformation(text)
                .build();
    }
}
