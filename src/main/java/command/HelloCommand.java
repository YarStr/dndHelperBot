package command;

import packedMessage.Format;
import packedMessage.FormattedText;
import packedMessage.PackedMessage;
import packedMessage.PackedMessageBuilder;

public class HelloCommand implements Command{

    private final String COMMAND_MESSAGE = "Давно не виделись!";

    public PackedMessage getResult() {
        FormattedText text = new FormattedText(COMMAND_MESSAGE, Format.NORMAL);
        return new PackedMessageBuilder()
                .addInformation(text)
                .build();
    }
}
