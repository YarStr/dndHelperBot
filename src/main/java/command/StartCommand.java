package command;

public class StartCommand implements Command {

    private final String COMMAND_MESSAGE = """
            Привет! Я dndHelperBot, помогающий быстро найти информацию о
            правилах игры D&D 5e с сайта https://dnd.su
            Для вывода доступных команд введите /help""";

    @Override
    public String getResult() {
        return COMMAND_MESSAGE;
    }
}
