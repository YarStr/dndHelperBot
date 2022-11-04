package parser;

import botLogic.FailedConnectionException;
import botLogic.NonExistentSectionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Класс для парсинга сайта dnd.su
 */
public class Parser {
    /**
     * Функция получения всех доступных страниц секции сайта
     *
     * @return сообщение - список страниц
     */
    public static String getPagesListFromSection(String sectionName) throws NonExistentSectionException, FailedConnectionException {
        enum sections { RACE, CLASS };
        try {
            sections.valueOf(sectionName.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new NonExistentSectionException();
        }
        Document section;
        try {
            section = Jsoup.connect("https://dnd.su/" + sectionName.toLowerCase()).get();
        } catch (IOException e) {
            throw new FailedConnectionException();
        }
        Elements raceList = section.select("span[class=\"article_title\"]");
        StringBuilder pagesList = new StringBuilder();
        for (Element raceListElement : raceList) {
            pagesList.append(raceListElement.text()).append("\n");
        }
        return pagesList.toString();
    }

    /**
     * Метод, который осуществляет получение основной информации страницы
     *
     * @param link - ссылка на страницу с поисковым запросом
     * @return возвращает основную информацию
     * @throws IOException вызывается, если подключение к странице не произошло
     */
    public static String getMainInfoFromPage(String link) throws IOException {
        Document section = Jsoup.connect("https://dnd.su" + link).get();
        Elements mainInfoElements = section.select("h3:has(span[id^=\"osobennosti\"]) ~ p:has(strong)");
        StringBuilder output = new StringBuilder();
        for (Element element : mainInfoElements) {
            output.append(element.text()).append('\n');
        }
        if (output.toString().equals(""))
            throw new IOException();
        return output.substring(0, output.length() - 1);
    }

    /**
     * Метод, который осуществляет получение ссылки на страницу
     *
     * @param pageName - имя страницы
     * @return возвращает ссылку на страницу
     * @throws IOException вызывается, если подключение к странице не произошло
     */

    public static String getPageLink(String pageName) throws IOException {
        Document section = Jsoup.connect("https://dnd.su/race/").get();
        Elements raceList = section.select("a:has(span[class=\"article_title\"])");
        for (Element raceElement : raceList) {
            if (raceElement.select("span[class=\"article_title\"]").text().equalsIgnoreCase(pageName)) {
                return raceElement.attr("href");
            }
        }
        return "";
    }
     public static void main(String[] args) {
         enum sections { RACE, CLASS };
         System.out.println(sections.valueOf("race".toUpperCase()));
     }
}
