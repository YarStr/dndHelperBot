package command;

/**
 * Класс команды вывода help
 */
public class HelpCommand implements Command {
    public String getResult() {
        return """
                ******************************************************************
                Список доступных команд
                ----------------------------------------------------------------—
                /help - узнать умения бота
                /list race - получить список всех доступных рас
                /race *название расы* - узнать основную информацию о расе
                /more - получить больше информации по прошлому запросу расы
                ******************************************************************""";
    }
}
