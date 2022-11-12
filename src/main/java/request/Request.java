package request;

import java.util.ArrayList;

/**
 * Запись, хранящая в себе информацию о поступившем сообщении
 */
public record Request(String command, ArrayList<String> arguments) {
}
