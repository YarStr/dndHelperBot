//package tests.telegram;
//
//import botLogic.KeyboardCreator;
//import org.junit.jupiter.api.Test;
//import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
//
//import java.util.List;
//
//import static org.testng.Assert.assertEquals;
//
//public class CreateKeyboardTest {
//
//
//    KeyboardCreator keyboardCreator = new KeyboardCreator();
//    List<KeyboardRow> keyboard =  keyboardCreator.createKeyboard().getKeyboard();
//
//
//    @Test
//    void testCorrectCreatorFirstRowInKeyboard(){
//        KeyboardRow result = keyboard.get(0);
//        assertEquals("/list class", result.get(0).getText());
//        assertEquals("/list race", result.get(1).getText());
//    }
//
//    @Test
//    void testCorrectCreatorSecondRowInKeyboard(){
//        KeyboardRow result = keyboard.get(1);
//        assertEquals("/help", result.get(0).getText());
//    }
//
//}
