package Parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Класс, содержащий в себе методы для парсинга сайта dnd.su
 */

public class Parser {

    /**
     * Метод, который осуществляет получение страницы для ответа на поисковый запрос
     * и дальнейший поиск нужной расы
     *
     * @param message - запрос пользователя
     * @return true, если раса найдена, иначе - false
     * @throws IOException вызывается, если подключение к сайту не произошло
     */

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


    /**
     * Метод, который осуществляет получение краткой сводки на основной (первичный) запрос
     *
     * @param link - ссылка на страницу с поисковым запросом
     * @return возвращает краткую сводку
     * @throws IOException вызывается, если подключение к сайту не произошло
     */

    public static String getMainInfoFromPage(String link) throws IOException {
        Document section = Jsoup.connect("https://dnd.su" + link).get();
        Elements mainInfoElements = section.select("h3:has(span[id^=\"osobennosti\"]) ~ p:has(strong)");
        String output = "";
        for (Element element : mainInfoElements) {
            output += element.text() + '\n';
        }
        return output;
    }

    /**
     * Метод, который осуществляет получение ссылки для ответа на дополнительный(вторичный) запрос
     *
     * @param message - поисковый запрос
     * @return возвращает ссылку на поисковый запрос
     * @throws IOException вызывается, если подключение к сайту не произошло
     */

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
