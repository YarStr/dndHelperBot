package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleBot {

    public ConsoleBot() {

    }
    public static void start() throws IOException {
        System.out.println("ВЫВОД HELP");
        while(true){
            System.out.println("Введите расу");
            String message = getMessage();
            reactToMessage(message);
        }
    }

    public static String getMessage() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            return new String();
        }
    }

    public static void reactToMessage(String message) throws IOException {
        if (message.isEmpty()) {
            System.out.println("Ввод неверен");
        }
        else if (message.charAt(0)=='/') {
            executeCommand(message);
        }
        else {
            processTheRequest(message);
        }
    }
    public static void processTheRequest(String message) throws IOException {
        if(Parser.isRaceExist(message)){
            System.out.println(Parser.getMainInformation(Parser.getLink(message)));
            System.out.println("Нужно ли больше инфы?");
            String tempMessage = getMessage();
            if(tempMessage.charAt(0) == '/'){
                executeCommand(tempMessage);
            }
            else if(tempMessage.equals("yes")){
                System.out.println("Держи, bro :)");
                System.out.println("https://dnd.su" + Parser.getLink(message));
            }
        }
        else{
            System.out.println("Такой расы не существует, попробуй еще раз)");
        }
    }

    public static void executeCommand(String message){
        if ("/help".equals(message)) {
            System.out.println("ВЫВОД HELP");
        } else {
            System.out.println("Такой команды не существует");
        }
    }
}
