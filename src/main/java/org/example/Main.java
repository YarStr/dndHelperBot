package org.example;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        while (true) {
            if(Output.message().equals("/start")){
                Bot name = new ConsoleBot();
                name.starting();
            }
        }
    }
}