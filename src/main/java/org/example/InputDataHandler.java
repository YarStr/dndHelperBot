package org.example;

public class InputDataHandler {

    private static Race race;

    public static String executeCommand(String message) {
        if ("/help".equals(message))
            return BotCommands.getHelpCommand();
        return "Такой команды не существует";
    }

    public static String processRequest(String message) {
        if (BotStatement.statement.equals("main"))
            return processMainRequest(message);
        return processInDialogRequest(message);
    }

    private static String processMainRequest(String message) {
        String output = "";
        if (Parser.isPageExists(message)) {
            race = new Race(message);
            output += race.getMainInformation();
            output += "\nХотите ли узнать больше информации об этой расе? [да/нет]";
            BotStatement.statement = "in-dialog-branch";
        } else {
            output += "Такой расы не существует, попробуй ввести название еще раз.";
        }
        return output;
    }

    private static String processInDialogRequest(String message) {
        String output = "";
        if (message.equals("да")) {
            output += "Полная ссылка на страницу расы:\n" + "https://dnd.su" + race.getLink() + "\n";
        }
        BotStatement.statement = "main";
        return output;
    }
}
