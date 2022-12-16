package packedMessage;

import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.File;
import java.util.ArrayList;

/**
 * Тип данных, хранящий ответ на команду бота и его служебную информацию
 */
public class PackedMessage {
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
    public InputFile additionalData;


    public PackedMessage(ArrayList<FormattedText> information, ArrayList<String> availableCommands, InputFile additionalData) {
        this.information = information;
        this.availableCommands = availableCommands;
        this.additionalData = additionalData;
    }
}
