package command;

import packedMessage.Format;
import packedMessage.FormattedText;
import packedMessage.PackedMessage;
import packedMessage.PackedMessageBuilder;

/**
 * Класс команды вывода help
 */
public class HelpCommand implements Command {

    private final String COMMAND_MESSAGE = """
            Список доступных команд:
            ------------------------
            help - узнать умения бота
                        
            list [race | class] - получить список всех доступных рас/классов
                        
            race raceName - узнать основную информацию о расе
            
            info [link | score | age | alignment | size | speed | languages | all ] - узнать конкретную информацию о расе (ВОЗМОЖНО ТОЛЬКО ПОСЛЕ КОМАНДЫ race)
            
            exit - закончить поиск в команде INFO
            """;

    public PackedMessage getResult() {
        FormattedText text = new FormattedText(COMMAND_MESSAGE, Format.NORMAL);
        return new PackedMessageBuilder()
                .addInformation(text)
                .build();
    }
}
