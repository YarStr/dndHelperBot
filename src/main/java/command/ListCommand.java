package command;

import Parser.Parser;

/**
 * Класс, реализующий обработку заголовков по видам запроса
 */
public class ListCommand {

    /**
     * Функция возврата заголовков
     *
     * @return сообщение - список заголовков
     */
    public String getRaceList() {
        return Parser.getPagesList();
    }
}
