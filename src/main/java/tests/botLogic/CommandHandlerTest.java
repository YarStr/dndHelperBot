package tests.botLogic;

import botLogic.CommandHandler;
import org.junit.jupiter.api.Test;
import request.Request;
import request.RequestCreator;

import static org.junit.jupiter.api.Assertions.*;

class CommandHandlerTest {
    private static final CommandHandler commandHandler = new CommandHandler();
    private static final RequestCreator requestCreator = new RequestCreator();
    private static Request request;

    private static final String invalidCommandArgumentsMessage = """
            У команды неверно введены аргументы.""";
    private static final String emptyCommandArgumentsMessage = """
            Вы не ввели ничего, кроме названия команды.""";

    private static final String notExistentPageMessage = """
            Страница notExistentPage не была найдена на сайте dnd.su :(""";

    private static final String notExistentSectionMessage = """
            Раздела notExistentSection нет на сайте dnd.su :(""";

    private static final String notExistentRaceFeatureMessage = """
            Не являются особенностями расы: notExistentRaceFeature""";

    @Test
    public void testHandleRequestWithNonExistCommand() {
        request = requestCreator.getRequest("/nonExistCommand");
        String currentMessage = commandHandler.handleRequest(request);
        assertEquals("Введённая команда недоступна.", currentMessage);
    }

    @Test
    public void testHandleRequestWithEmptyCommandArguments() {
        String[] textsWithRequests = {"/race", "/list"};
        for (String text : textsWithRequests) {
            request = requestCreator.getRequest(text);
            String currentMessage = commandHandler.handleRequest(request);
            String expectedMessage = invalidCommandArgumentsMessage + " " + emptyCommandArgumentsMessage;
            assertEquals(expectedMessage, currentMessage);
        }
    }

    @Test
    public void testHandleRequestWithNotExistentPageArgument() {
        request = requestCreator.getRequest("/race notExistentPage");
        String currentMessage = commandHandler.handleRequest(request);
        String expectedMessage = invalidCommandArgumentsMessage + " " + notExistentPageMessage;
        assertEquals(expectedMessage, currentMessage);
    }

    @Test
    public void testHandleRequestWithNotExistentSectionArgument() {
        request = requestCreator.getRequest("/list notExistentSection");
        String currentMessage = commandHandler.handleRequest(request);
        String expectedMessage = invalidCommandArgumentsMessage + " " + notExistentSectionMessage;
        assertEquals(expectedMessage, currentMessage);
    }

    @Test
    public void testHandleRequestWithNotExistentRaceFeature() {
        request = requestCreator.getRequest("/race гном notExistentRaceFeature");
        String currentMessage = commandHandler.handleRequest(request);
        String expectedMessage = invalidCommandArgumentsMessage + " " + notExistentRaceFeatureMessage;
        assertEquals(expectedMessage, currentMessage);
    }
}