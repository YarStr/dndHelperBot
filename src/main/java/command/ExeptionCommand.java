package command;

/**
 * Класс, реализующий обработку команд ошибок
 */
public class ExeptionCommand {

    /**
     * Функция возврата вида ошибки в команде
     *
     * @return сообщение - строка с типом ошибки
     */
    public String getExeptionCommand() {
        return "Неверный ввод команды";
    }

    /**
     * Функция возврата вида ошибки в аргументе
     *
     * @return сообщение - строка с типом ошибки
     */
    public String getExeptionArgument() {
        return "Неверный ввод расы";
    }
}
