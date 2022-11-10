package parser;

import botLogic.FailedConnectionException;
import botLogic.NonExistentSectionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс для парсинга сайта dnd.su
 */
public class Parser {
    private enum sections {RACE, CLASS}

    ;

    /**
     * Функция получения всех доступных страниц секции сайта
     *
     * @return сообщение - список страниц
     */

    private static Document getSection(String sectionName) throws NonExistentSectionException, FailedConnectionException {
        try {
            sections.valueOf(sectionName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NonExistentSectionException("Раздела " + sectionName + " нет на сайте dnd.su :(");
        }
        try {
            return Jsoup.connect("https://dnd.su/" + sectionName.toLowerCase()).get();
        } catch (IOException e) {
            throw new FailedConnectionException();
        }
    }

    public static String getPagesListFromSection(String sectionName) throws NonExistentSectionException, FailedConnectionException {
        Document section = getSection(sectionName);
        Elements raceList = section.select("span[class=\"article_title\"]");
        StringBuilder pagesList = new StringBuilder();
        for (Element raceListElement : raceList) {
            pagesList.append(raceListElement.text()).append("\n");
        }
        return pagesList.toString();
    }

    public static String getPageLink(String sectionName, String pageName) throws FailedConnectionException, NonExistentSectionException, NonExistentPageException {
        Document section = getSection(sectionName);
        Elements raceList = section.select("a:has(span[class=\"article_title\"])");
        for (Element raceElement : raceList) {
            if (raceElement.select("span[class=\"article_title\"]").text().equalsIgnoreCase(pageName)) {
                return raceElement.attr("href");
            }
        }
        throw new NonExistentPageException("Страница " + pageName + " не была найдена на сайте dnd.su :(");
    }

    /**
     * Метод, который осуществляет получение основной информации страницы
     *
     * @param link - ссылка на страницу с поисковым запросом
     * @return возвращает основную информацию
     * !!!ПОЧИНИТЬ ОПИСАНИЕ
     */
    public static Map<String, String> getRaceFeatures(String link) throws FailedConnectionException {
        Document section;
        try {
            section = Jsoup.connect("https://dnd.su" + link).get();
        } catch (IOException e) {
            throw new FailedConnectionException();
        }
        Elements featureElements = section.select("h3:has(span[id^=\"osobennosti\"]) ~ p:has(strong)");
        Map<String, String> featureElementsMap = new HashMap<>();
        for (Element element : featureElements) {
            String title = element.select("strong").text();
            if (title.equals("Мировоззрение"))
                featureElementsMap.put("Мировоззрение.", element.select("span").text());
            else
                featureElementsMap.put(title, element.ownText());
        }
        return featureElementsMap;
    }
}
