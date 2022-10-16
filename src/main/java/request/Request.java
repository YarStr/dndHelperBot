package request;

/**
 * Конструктор, хранящий в себе информацию о поступившем сообщении
 */
public record Request(String command, String argument) {
}
