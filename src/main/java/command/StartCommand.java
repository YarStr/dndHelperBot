package command;

import packedMessage.Format;
import packedMessage.FormattedText;
import packedMessage.PackedMessage;
import packedMessage.PackedMessageBuilder;

public class StartCommand implements Command {

    private final String COMMAND_MESSAGE = """
            Привет! Я dndHelperBot, помогающий быстро найти информацию о
            правилах игры D&D 5e с сайта https://dnd.su
            Для вывода доступных команд введите /help""";

    @Override
    public PackedMessage getResult() {
        FormattedText text = new FormattedText(COMMAND_MESSAGE, Format.NORMAL);
        return new PackedMessageBuilder()
                .addInformation(text)
                .build();
    }
}
