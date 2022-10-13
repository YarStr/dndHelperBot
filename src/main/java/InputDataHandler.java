import Parser.Parser;

/**
 * Класс, осуществляющий обработку информации
 */

public class InputDataHandler {

    private static Race race;

    /**
     * Метод, осуществляющий обработку функций
     *
     * @param message - запрос пользователя
     * @return возвращает вывод выполняемой команды, если такая есть
     */
    public static String executeCommand(String message) {
        if ("/help".equals(message))
            return BotAPI.getHelp();
        return "Такой команды не существует";
    }

    /**
     * Метод, осуществляющий выбор дальнейшего пути работы в зависимости от статуса состояния бота
     *
     * @param message - запрос пользователя
     * @return вызов метода в зависимости от статуса бота
     */

    public static String processRequest(String message) {
        if (BotStatement.statement.equals("main"))
            return processMainRequest(message);
        return processInDialogRequest(message);
    }

    /**
     * Метод, который осуществляет обработку основного (первичного) запроса и меняет состояние бота
     * для работы дополнительной (вторичной) ветки работы, если запрос найден.
     *
     * @param message - запрос пользователя
     * @return вывод краткой сводки о расе, если такая существует,
     * затем запрос о необходимости подробностей и ожидание ответа (да/нет),
     * и дальнейшее изменение состояние бота на дополнительную ветку работы.
     * Если такой расы нет, то выводится просьба о вводе поиска еще раз
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
     * Метод, осуществляющий дополнительную ветку работы - выводит больше информации
     * по запросу (ссылку на страницу), если этого требует пользователль
     *
     * @param message - запрос пользователя
     * @return ссылка на расу на сайте, если пользователю потребовалось больше
     * информации о поисковом запросе, и перевод статуса бота на главную ветку работы
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
