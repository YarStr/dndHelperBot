package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserMessageReader {
    public static String getMessage() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            return new String();
        }
    }






//    public void starting() {
//        while (true) {
//            if (Output.message().equals("/start")) {
//                System.out.println("Hi, I'm dnd HelperBot. Write your character. /help - helper, /exit - end of work");
//                work();
//            }
//        }
//    }
//

//
//
//
//    public void work(){
//        while(true) {
//            System.out.println("Write query");
//            try {
//                if (checking(Output.message()).equals("exit")){
//                    break;
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//    }
//
//    public String checking(String input) {
//        switch (input) {
//            case "/help" -> {
//                printHelp();
//                return("help");
//            }
//            case "/exit" -> {
//                return("exit");
//            }
//            default -> {
//                ShapingFormation.doParsingMainInformation(input);
//                return("shape");
//            }
//            ///парсинг сайта и поиск нужного. результат - статья или "неправильный ввод"
//        }
//    }
//
//    public void printHelp() {
//        System.out.println("This is help");
//    }
//

//    public static void printAnswer(String input) {
//        if (input == "") {
//            System.out.println("No data was found. Please repeat your request");
//         }
//         else{
//            System.out.println(input);
//            System.out.println("Is more information needed about this character? If yes, enter one of the lines:");
//            System.out.println(//inf about title);
//         }
//
//        иначе выводим данные, затем запрос, нужна ли им статья(заголовки выводятся на экран)
//    }



}
