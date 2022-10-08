package org.example;

/**
 * Класс, осуществляющий обработку информации
 */

public class InputDataHandler {

    private static Race race;

   /**
     * Метод, осуществляющий обработку функций 
     */
    public static String executeCommand(String message) {
        if ("/help".equals(message))
            return BotCommands.getHelpCommand();
        return "Такой команды не существует";
    }
    
    /**
     * Метод, осуществляющий выбор дальнейшего пути работы в зависимости от статуса состояния бота 
     */

    public static String processRequest(String message) {
        if (BotStatement.statement.equals("main"))
            return processMainRequest(message);
        return processInDialogRequest(message);
    }
    
    /**
     * Метод, который осуществляет обработку основного (первичного) запроса и меняет состояние бота 
     * для работы дополнительной (вторичной) ветки работы, если запрос найден.
     * Если запрос не найден, бот просит ввести его еще раз
     */

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
    
    /**
     * Метод, осуществляющий дополнительную ветку работы - выводит больше информации по запросу, если этого требует пользователль 
     */

    private static String processInDialogRequest(String message) {
        String output = "";
        if (message.equals("да")) {
            output += "Полная ссылка на страницу расы:\n" + "https://dnd.su" + race.getLink() + "\n";
        }
        BotStatement.statement = "main";
        return output;
    }
}
