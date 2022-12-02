package command;

import messagePackage.Format;
import messagePackage.FormattedText;
import messagePackage.MessagePackage;
import messagePackage.MessagePackageBuilder;

public class StartCommand implements Command {

    private final String COMMAND_MESSAGE = """
            Привет! Я dndHelperBot, помогающий быстро найти информацию о
            правилах игры D&D 5e с сайта https://dnd.su
            Для вывода доступных команд введите /help""";

    @Override
    public MessagePackage getResult() {
        FormattedText text = new FormattedText(COMMAND_MESSAGE, Format.NORMAL);
        return new MessagePackageBuilder()
                .addInformation(text)
                .build();
    }
}
