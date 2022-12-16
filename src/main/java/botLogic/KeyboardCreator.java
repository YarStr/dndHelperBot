package botLogic;


import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Клавиатура в чате в Телеграм
 */
public class KeyboardCreator {

    /**
     * Создание клавиатуры
     *
     * @param availableCommands список возможных команд
     * @return макет для создания клавиатуры
     */

    public ReplyKeyboardMarkup createKeyboard(ArrayList<String> availableCommands) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        //Создаем нужное количество строк
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        // Добавляем кнопки в первую строчку клавиатуры
        for (String command : availableCommands) {
            if (!command.equals("race") && !command.equals("info") && !command.equals("start")) {
                if (command.equals("list")) {
                    keyboardFirstRow.add(new KeyboardButton("/" + command + " race"));
                    keyboardFirstRow.add(new KeyboardButton("/" + command + " class"));
                    continue;
                }
                keyboardFirstRow.add(new KeyboardButton("/" + command));
            }
        }

        // Добавляем все строчки клавиатуры в список
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(keyboardFirstRow);

        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }
}
