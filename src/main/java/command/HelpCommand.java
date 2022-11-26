package command;

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

    public String getResult() {
        return COMMAND_MESSAGE;
    }
}
