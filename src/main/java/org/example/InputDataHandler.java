package org.example;


public class InputDataHandler {

    public static void reactToMessage(String message){
        if (message.charAt(0)=='/'){
            InputDataHandler.executeCommand(message);
        }
        else{
//            processTheRequest();
        }
    }
//    public static void processTheRequest(String message){
//        if(isRaceExist(message)){
//            Race race = getRace();
//            printShotInfo(race);
//            while(true){
//                System.out.println("Нужно ли больше инфы?");
//                if(get)
//            }
//        }
//    }

    public static void executeCommand(String message){
        if ("/help".equals(message)) {
            System.out.println("ВЫВОД HELP");
        } else {
            System.out.println("Такой команды не существует");
        }
    }
}
