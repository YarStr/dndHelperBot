import botLogic.state.BotState;
import message.Message;
import org.junit.jupiter.api.Test;

import parser.Parser;


import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BotTest {

    @Test
    void nextState() {
        BotState stateFirst = BotState.Main;
        String command = "/race";
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("гном");
        Message result = new Message(command, arguments);
        stateFirst = stateFirst.nextState(result);
        BotState stateSecond = BotState.RaceInfo;
        assertEquals(stateSecond, stateFirst);
    }

    @Test
    void sameState() {
        BotState stateFirst = BotState.Main;
        String command = "/раса";
        ArrayList<String> arguments = new ArrayList<>();
        arguments.add("гном");
        Message message = new Message(command, arguments);
        stateFirst = stateFirst.nextState(message);
        BotState stateSecond = BotState.Main;
        assertEquals(stateSecond, stateFirst);
    }

    @Test
    void notGetPagesList() {
        assertThrows(Exception.class, () -> Parser.getPagesList("/класс"));
    }

    @Test
    void notGetMainInfoFromPage() {
        assertThrows(Exception.class, () -> Parser.getMainInfoFromPage(""));
    }

    @Test
    void getPageLink() throws IOException {
        String link = Parser.getPageLink("гном");
        assertEquals("/race/83-gnome/", link);
    }


}