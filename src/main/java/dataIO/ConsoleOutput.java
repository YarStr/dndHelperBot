package dataIO;

public class ConsoleOutput implements OutputModule {

    public void sendMessage(String message) {
        System.out.println(message);
    }
}
