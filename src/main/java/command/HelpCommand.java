package command;

import messagePackage.Format;
import messagePackage.FormattedText;
import messagePackage.MessagePackage;
import messagePackage.MessagePackageBuilder;

/**
 * Класс команды вывода help
 */
public class HelpCommand implements Command {

    private final String COMMAND_MESSAGE = """
            Список доступных команд:
            ------------------------
            /help - узнать умения бота
                        
            /list [race | class] - получить список всех доступных рас/классов
                        
            /race raceName - узнать основную информацию о расе
            """;

    public MessagePackage getResult() {
        FormattedText text = new FormattedText(COMMAND_MESSAGE, Format.NORMAL);
        return new MessagePackageBuilder()
                .addInformation(text)
                .build();
    }
}
