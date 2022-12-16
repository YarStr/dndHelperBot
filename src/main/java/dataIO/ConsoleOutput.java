package dataIO;

import packedMessage.FormattedText;
import packedMessage.PackedMessage;

/**
 * Класс, реализующий вывод сообщений в консоль
 */
public class ConsoleOutput implements OutputModule {
    /**
     * Функция отправки сообщения в консоль
     *
     * @param packedMessage - пакет сообщения
     */
    public void sendData(PackedMessage packedMessage) {
        StringBuilder stringBuilder = new StringBuilder();
        for (FormattedText formattedText: packedMessage.information) {
            switch (formattedText.format){
                case ERROR -> stringBuilder.append("!!!").append(formattedText.text);
                case TITLE -> stringBuilder.append("---").append(formattedText.text).append("---");
                case NORMAL -> stringBuilder.append(formattedText.text);
            }
            stringBuilder.append('\n');
        }
        System.out.println(stringBuilder);
    }

}
