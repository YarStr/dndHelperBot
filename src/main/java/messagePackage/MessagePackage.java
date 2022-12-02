package messagePackage;

import java.io.File;
import java.util.ArrayList;

/**
 * Тип данных, хранящий ответ на команду бота и его служебную информацию
 */
public class MessagePackage {
    /**
     * Информация - ответ на команду бота
     */
    public ArrayList<FormattedText> information;

    /**
     * Доступные пользователю команды в текущий момент диалога
     */
    public ArrayList<String> availableCommands;

    /**
     * Дополнительные данные для вывода пользователю
     */
    public File additionalData;
}
