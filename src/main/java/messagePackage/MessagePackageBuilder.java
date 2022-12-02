package messagePackage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Билдер класса MessagePackage
 */
public class MessagePackageBuilder {
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
    private File additionalData;

    /**
     * Конструктор
     */
    public MessagePackageBuilder() {
        information = null;
        availableCommands = null;
        additionalData = null;
    }

    /**
     * Метод заполнения поля information
     * @param formattedText блок информации в одном экземпляре
     * @return себя
     */
    public MessagePackageBuilder addInformation(FormattedText formattedText) {
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
    public MessagePackageBuilder addInformation(ArrayList<FormattedText> formattedTextList) {
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
    public MessagePackageBuilder addInformation(HashSet<FormattedText> formattedTextList) {
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
    public MessagePackageBuilder addAvailableCommands(ArrayList<String> availableCommands) {
        this.availableCommands = availableCommands;
        return this;
    }

    /**
     * Метод заполнения поля additionalData
     * @param additionalData файл дополнительных данных
     * @return себя
     */
    public MessagePackageBuilder addAdditionalData(File additionalData) {
        this.additionalData = additionalData;
        return this;
    }

    /**
     * Метод построения класса MessagePackage
     * @return экземпляр MessagePackage
     */
    public MessagePackage build() {
        return new MessagePackage(information, availableCommands, additionalData);
    }
}
