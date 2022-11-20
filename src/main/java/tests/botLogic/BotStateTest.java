package tests.botLogic;

import botLogic.state.BotState;
import command.CommandList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import request.Request;
import request.RequestCreator;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BotStateTest {
    private static BotState botState;

    private static Request request;

    private static final RequestCreator requestCreator = new RequestCreator();

    @BeforeEach
    public void setMainState() {
        botState = BotState.Main;
    }

    @Test
    public void testGoNextStateFromMainStateWithCorrectCommand1() {
        request = requestCreator.getRequest("/race");
        BotState currentState = botState.nextState(request);
        assertEquals(BotState.RaceInfo, currentState);
    }

    @Test
    void testGoNextStateFromMainStateWithCorrectCommand2() {
        request = requestCreator.getRequest("/list");
        BotState currentState = botState.nextState(request);
        assertEquals(BotState.Main, currentState);
    }

    @Test
    void testGoNextStateFromMainStateWithIncorrectCommand() {
        request = requestCreator.getRequest("/incorrectCommand");
        BotState currentState = botState.nextState(request);
        assertEquals(BotState.Main, currentState);
    }

    @Test
    void testGoNextStateFromRaceStateWithStatePreservingCommand() {
        botState = BotState.RaceInfo;
        request = requestCreator.getRequest("/info");
        BotState currentState = botState.nextState(request);
        assertEquals(BotState.RaceInfo, currentState);
    }

    @Test
    void testGoNextStateFromRaceStateWithExitStateCommand() {
        botState = BotState.RaceInfo;
        request = new RequestCreator().getRequest("/exit");
        BotState currentState = botState.nextState(request);
        assertEquals(BotState.Main, currentState);
    }

    @Test
    void testGetAvailableCommandsFromMainState() {
        ArrayList<String> currentCommands = botState.getAvailableCommands();
        ArrayList<String> expectedCommands = new ArrayList<>(Arrays.asList(
                CommandList.START,
                CommandList.HELP,
                CommandList.RACE,
                CommandList.LIST
        ));
        assertEquals(expectedCommands, currentCommands);
    }

    @Test
    void testGetAvailableCommandsFromRaceState() {
        botState = BotState.RaceInfo;
        ArrayList<String> currentCommands = botState.getAvailableCommands();
        ArrayList<String> expectedCommands = new ArrayList<>(Arrays.asList(
                CommandList.HELP,
                CommandList.INFO,
                CommandList.EXIT
        ));
        assertEquals(expectedCommands, currentCommands);
    }

    @Test
    void testGetBotStateValues() {
        BotState[] currentValues = BotState.values();
        BotState[] expectedValues = {BotState.Main, BotState.RaceInfo};
        assertArrayEquals(expectedValues, currentValues);
    }

    @Test
    void testBotStateValueOfMainStringInCorrectRegister() {
        BotState result = BotState.valueOf("Main");
        assertEquals(BotState.Main, result);
    }

    @Test
    void testBotStateValueOfRaceInfoStringInCorrectRegister() {
        BotState result = BotState.valueOf("RaceInfo");
        assertEquals(BotState.RaceInfo, result);
    }

    @Test
    void testBotStateValueOfMainInfoStringInIncorrectRegister() {
        assertThrows(IllegalArgumentException.class, () -> BotState.valueOf("main"));
    }

    @Test
    void testBotStateValueOfRaceInfoStringInIncorrectRegister() {
        assertThrows(IllegalArgumentException.class, () -> BotState.valueOf("raceInfo"));
    }

    @Test
    void testBotStateValueOfUnknownState() {
        assertThrows(IllegalArgumentException.class, () -> BotState.valueOf("unknownState"));
    }
}
