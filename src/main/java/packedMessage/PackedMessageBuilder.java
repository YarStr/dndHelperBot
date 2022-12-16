package packedMessage;

import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Билдер класса MessagePackage
 */
public class PackedMessageBuilder {
    /**
     * Информация - ответ на команду бота
     */
    private ArrayList<FormattedText> information;

    /**
     * Доступные пользователю команды в текущий момент диалога
     */
    private ArrayList<String> availableCommands;

    /**
     * Дополнительные данные для вывода пользователю
     */
    private InputFile additionalData;

    /**
     * Конструктор
     */
    public PackedMessageBuilder() {
        information = null;
        availableCommands = null;
        additionalData = null;
    }

    /**
     * Метод заполнения поля information
     * @param formattedText блок информации в одном экземпляре
     * @return себя
     */
    public PackedMessageBuilder addInformation(FormattedText formattedText) {
        if (information == null)
            information = new ArrayList<>();
        information.add(formattedText);
        return this;
    }

    /**
     * Метод заполнения поля information
     * @param formattedTextList список блоков информации
     * @return себя
     */
    public PackedMessageBuilder addInformation(ArrayList<FormattedText> formattedTextList) {
        if (information == null)
            information = new ArrayList<>();
        information.addAll(formattedTextList);
        return this;
    }

    /**
     * Метод заполнения поля information
     * @param formattedTextList список блоков информации
     * @return себя
     */
    public PackedMessageBuilder addInformation(HashSet<FormattedText> formattedTextList) {
        if (information == null)
            information = new ArrayList<>();
        information.addAll(formattedTextList);
        return this;
    }

    /**
     * Метод заполнения поля availableCommands
     * @param availableCommands список доступных команд
     * @return себя
     */
    public PackedMessageBuilder addAvailableCommands(ArrayList<String> availableCommands) {
        this.availableCommands = availableCommands;
        return this;
    }

    /**
     * Метод заполнения поля additionalData
     * @param additionalData файл дополнительных данных
     * @return себя
     */
    public PackedMessageBuilder addAdditionalData(InputFile additionalData) {
        this.additionalData = additionalData;
        return this;
    }

    /**
     * Метод построения класса MessagePackage
     * @return экземпляр MessagePackage
     */
    public PackedMessage build() {
        return new PackedMessage(information, availableCommands, additionalData);
    }
}
