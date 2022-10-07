package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleBot {

    public void start() {
        System.out.println(BotCommands.getHelpCommand());
        while (true) {
            String message = "";
            while (message.isEmpty())
                message = getMessage();
            reactToMessage(message);
        }
    }

    private String getMessage() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            return "";
        }
    }

    private void reactToMessage(String message) {
        if (message.charAt(0) == '/') {
            System.out.println(InputDataHandler.executeCommand(message));
        } else {
            System.out.println(InputDataHandler.processRequest(message));
        }
    }
}
