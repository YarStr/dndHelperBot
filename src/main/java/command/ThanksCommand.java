package command;

import command.exceptions.FailedCommandExecutionException;
import command.exceptions.InvalidCommandArgumentsException;
import packedMessage.Format;
import packedMessage.FormattedText;
import packedMessage.PackedMessage;
import packedMessage.PackedMessageBuilder;

public class ThanksCommand implements Command{
    private final String COMMAND_MESSAGE = "Всегда пожалуйста!";

    public PackedMessage getResult() {
        FormattedText text = new FormattedText(COMMAND_MESSAGE, Format.NORMAL);
        return new PackedMessageBuilder()
                .addInformation(text)
                .build();
    }
}
