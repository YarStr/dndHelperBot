package dataIO;

import packedMessage.PackedMessage;

/**
 * Интерфейс модуля, выводящего информацию пользователю
 */
public interface OutputModule {
    /**
     * Функция отправки информации
     *
     * @param data - текстовая информация
     */
    void sendData(PackedMessage data);
}
