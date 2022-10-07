package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Parser {
    public static boolean isPageExists(String message) {
        Document section;
        try {
            section = Jsoup.connect("https://dnd.su/race/").get();
        } catch (IOException e) {
            return false;
        }
        Elements raceList = section.select("span[class=\"article_title\"]");

        for (Element raceListElement : raceList) {
            if (raceListElement.text().equalsIgnoreCase(message)) {
                return true;
            }
        }
        return false;
    }

    public static String getMainInfoFromPage(String link) throws IOException {
        Document section = Jsoup.connect("https://dnd.su" + link).get();
        Elements mainInfoElements = section.select("h3:has(span[id^=\"osobennosti\"]) ~ p:has(strong)");
        String output = "";
        for (Element element : mainInfoElements) {
            output += element.text() + '\n';
        }
        return output;
    }

    public static String getPageLink(String message) throws IOException {
        Document section = Jsoup.connect("https://dnd.su/race/").get();
        Elements raceList = section.select("a:has(span[class=\"article_title\"])");
        for (Element raceElement : raceList) {
            if (raceElement.select("span[class=\"article_title\"]").text().equalsIgnoreCase(message)) {
                return raceElement.attr("href");
            }
        }
        return "";
    }
}