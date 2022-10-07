package org.example;

import java.io.IOException;

public class Race {
    private String link;
    private String mainInformation;

    public Race(String message) {
        setLink(message);
        setMainInformation(link);
    }

    public void setLink(String message) {
        try {
            link = Parser.getPageLink(message);
        } catch (IOException e) {
            link = "unknown link";
        }
    }

    public void setMainInformation(String link) {
        try {
            mainInformation = Parser.getMainInfoFromPage(link);
        } catch (IOException e) {
            mainInformation = "unknown main information";
        }
    }

    public String getLink() {
        return link;
    }

    public String getMainInformation() {
        return mainInformation;
    }
}
