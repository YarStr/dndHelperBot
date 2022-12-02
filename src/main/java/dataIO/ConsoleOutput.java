package dataIO;

import messagePackage.FormattedText;
import messagePackage.MessagePackage;

/**
 * Класс, реализующий вывод сообщений в консоль
 */
public class ConsoleOutput implements OutputModule {
    /**
     * Функция отправки сообщения в консоль
     *
     * @param messagePackage - пакет сообщения
     */
    public void sendData(MessagePackage messagePackage) {
        StringBuilder stringBuilder = new StringBuilder();
        for (FormattedText formattedText : messagePackage.information) {
            switch (formattedText.format) {
                case ERROR -> stringBuilder.append("!!!").append(formattedText.text);
                case TITLE -> stringBuilder.append("---").append(formattedText.text).append("---");
                case NORMAL -> stringBuilder.append(formattedText.text);
            }
            stringBuilder.append('\n');
        }
        System.out.println(stringBuilder);
    }

}
