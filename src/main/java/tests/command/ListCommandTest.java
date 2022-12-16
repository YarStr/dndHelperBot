package tests.command;

import command.ListCommand;
import command.exceptions.InvalidCommandArgumentsException;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListCommandTest {
    ArrayList<String> arguments1 = new ArrayList<>(List.of("raca"));

    @Test
    public void getListCommandWithWrongArgumentsTest() throws InvalidCommandArgumentsException {
        ListCommand listCommand = new ListCommand(arguments1);
        assertThrows(InvalidCommandArgumentsException.class, listCommand::getResult,
                "У команды неверно введены аргументы. Раздела" + arguments1.get(0) + "нет на сайте dnd.su :(");
    }

    ArrayList<String> arguments2 = new ArrayList<>(List.of());

    @Test
    public void getListCommandWithEmptyArgumentsTest() {
        assertThrows(InvalidCommandArgumentsException.class, () -> new ListCommand(arguments2),
                "У команды неверно введены аргументы. Вы не ввели ничего, кроме названия команды.");
    }

}
