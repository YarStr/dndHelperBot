import Parser.Parser;
import dataIO.OutputModule;

import java.io.IOException;


public class CommandHandler {

    static void handleCommand(String message) throws IOException {
        String[] message_split = message.split(" ");
        if (message.equals("/help")) {
            printHelp();
        }
        if (message.equals("/race list")){
            printListRace();
        }
        if (message_split[0].equals("/race") && message_split[1].equals("info")){
            printInfo(message_split[2]);
        }
    }

    private static void printInfo(String message) throws IOException {
        return Parser.getMainInfoFromPage(message);
    }

    private static void printHelp(){
        return """
                    ******************************************************************
                    Привет, я dndHelperBot и помогаю быстро искать информацию на сайте
                    dnd.su. Введите название расы, о которой хотите узнать информацию.
                    ----------------------------------------------------------------—
                    /help - узнать умения бота.
                    ******************************************************************""");
    }

    private static void printListRace(){
        return Parser.getListRace();
    }
}
