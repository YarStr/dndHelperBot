package tests.raceCommand;

import command.exceptions.InvalidCommandArgumentsException;
import command.raceCommand.MoreRaceInfoCommand;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.Arrays;

public class MoreRaceInfoCommandTest {

    String text1 = "";
    String text2 = "age";
    String text3 = "score size";
    String text4 = "scr sia";
    String text5 = "age link";


    @BeforeTest
    ArrayList<String> getArguments(String text){
        return new ArrayList<>(Arrays.asList(text.split(" ")));
    }

    @Test
    void getMoreRaceInfoWithoutArguments() throws InvalidCommandArgumentsException {
        ArrayList<String> arguments = getArguments(text1);
        MoreRaceInfoCommand infoCommand = new MoreRaceInfoCommand(arguments);
        infoCommand.getResult();
    }



}
