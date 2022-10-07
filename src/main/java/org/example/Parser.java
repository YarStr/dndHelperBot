package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class Parser {

    public static Elements getShortInfo(Document section) {
        Elements spetialitiesOfSection = section.select("h3:has(span[id^=\"osobennosti\"]) ~ p:has(strong)");
        return spetialitiesOfSection;
    }


    public static boolean isRaceExist(String message) throws IOException {
        Document section = Jsoup.connect("https://dnd.su/race/").get();
        Elements raceList = section.select("span[class=\"article_title\"]");

        for (Element raceListElement : raceList) {
            if (raceListElement.text().toLowerCase().equals(message.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static String getMainInformation(String link) throws IOException {
        Document section = Jsoup.connect("https://dnd.su" + link).get();
        Elements mainInformationList = section.select("h3:has(span[id^=\"osobennosti\"]) ~ p:has(strong)");
        String output = "";
        for (Element temp : mainInformationList) {
            output += temp.text() + '\n';
        }
        return output;
    }

    public static String getLink(String message) throws IOException {
        Document section = Jsoup.connect("https://dnd.su/race/").get();
        Elements raceList = section.select("a:has(span[class=\"article_title\"])");

        for (Element temp : raceList) {
            if (temp.select("span[class=\"article_title\"]").text().toLowerCase()
                    .equals(message.toLowerCase())) {
                return temp.attr("href");
            }
        }
        return "";
    }
}
//
//    public static void main(String[] args) throws IOException {
//        System.out.println(getLink(""));
//    }
//}