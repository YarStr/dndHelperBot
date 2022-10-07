package org.example;

import java.io.IOException;
import java.util.Map;

public class Race {


    private final String link;
    private String mainInformation;
    public Race(String message) throws IOException {
        link = Parser.getLink(message);
        mainInformation = Parser.getMainInformation(link);
    }

    public String getLink() {
        return link;
    }


}
