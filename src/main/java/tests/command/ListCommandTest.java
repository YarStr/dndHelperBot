//package tests.command;
//
//import command.ListCommand;
//import command.exceptions.FailedCommandExecutionException;
//import command.exceptions.InvalidCommandArgumentsException;
//import org.junit.jupiter.api.Test;
//
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class ListCommandTest {
//
//
//    ArrayList<String> arguments2 = new ArrayList<>();
//
//
//    @Test
//    public void getListCommandWithWrongArgumentsTest() throws InvalidCommandArgumentsException {
//        ListCommand listCommand = new ListCommand(arguments1);
//        assertThrows(InvalidCommandArgumentsException.class, listCommand::getResult,
//                "У команды неверно введены аргументы. Раздела" + arguments1.get(0) + "нет на сайте dnd.su :(");
//    }
//
//    String text1 = "raca";
//    ArrayList<String> arguments1 = new ArrayList<>(Arrays.asList(text1.split(" ")));
//
//    @Test
//    public void getListCommandWithEmptyArgumentsTest() {
//        assertThrows(InvalidCommandArgumentsException.class, () -> new ListCommand(arguments2),
//                "У команды неверно введены аргументы. Вы не ввели ничего, кроме названия команды.");
//    }
//
//    String text2 = "race";
//    ArrayList<String> arguments3 = new ArrayList<>(Arrays.asList(text2.split(" ")));
//
//    @Test
//    public void getListCommandWithRightArgumentsTest() throws InvalidCommandArgumentsException, FailedCommandExecutionException {
//        ListCommand listCommand = new ListCommand(arguments3);
//        String[] result = listCommand.getResult().split("\n");
//        assertEquals("Багбир", result[4]);
//        assertEquals("Гит", result[8]);
//    }
//
//}
