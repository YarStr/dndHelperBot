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
        KeyboardRow keyboardSecondRow = new KeyboardRow();

        // Добавляем кнопки в первую строчку клавиатуры
        for (String command : availableCommands) {
            if (!command.equals("race") && !command.equals("info") && !command.equals("start")) {
                switch (command) {
                    case "list" -> keyboardFirstRow.add(new KeyboardButton("Вывести все расы"));
                    case "help" -> keyboardFirstRow.add(new KeyboardButton("Что ты умеешь?"));
                    case "exit" -> keyboardSecondRow.add(new KeyboardButton("Сменим тему"));
                    case "thanks" -> keyboardFirstRow.add(new KeyboardButton("Спасибо!"));
                    case "good" -> keyboardSecondRow.add(new KeyboardButton("Ты прекрасен!"));
                }

            }
        }

        // Добавляем все строчки клавиатуры в список
        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        // и устанавливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }
}
