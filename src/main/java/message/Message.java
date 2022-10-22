package message;

import java.util.ArrayList;

/**
 * Запись, хранящая в себе информацию о поступившем сообщении
 */
public record Message(String command, ArrayList<String> arguments) {
}
