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
    private enum sections { RACE, CLASS };
    /**
     * Функция получения всех доступных страниц секции сайта
     *
     * @return сообщение - список страниц
     */

    private static Document getSection(String sectionName) throws NonExistentSectionException, FailedConnectionException {
        try {
            sections.valueOf(sectionName.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new NonExistentSectionException();
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

    public static String getPageLink(String sectionName, String pageName) throws FailedConnectionException, NonExistentSectionException {
        Document section = getSection(sectionName);
        Elements raceList = section.select("a:has(span[class=\"article_title\"])");
        for (Element raceElement : raceList) {
            if (raceElement.select("span[class=\"article_title\"]").text().equalsIgnoreCase(pageName)) {
                return raceElement.attr("href");
            }
        }
        return "";
    }

    /**
     * Метод, который осуществляет получение основной информации страницы
     *
     * @param link - ссылка на страницу с поисковым запросом
     * @return возвращает основную информацию
     * @throws IOException вызывается, если подключение к странице не произошло
     */
    public static String getMainInfoFromPage(String link) throws FailedConnectionException {
        Document section;
        try {
            section = Jsoup.connect("https://dnd.su" + link).get();
        } catch (IOException e) {
            throw new FailedConnectionException();
        }
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
     * ПРОПИСАТЬ ИСКЛЮЧЕНИЯ
     */


    /*
    ВРЕМЕННАЯ ТОЧКА ВХОДА ДЛЯ ТЕСТИРОВАНИЯ
     */
     public static void main(String[] args) {
         enum sections { RACE, CLASS };
         System.out.println(sections.valueOf("race".toUpperCase()));
     }
}
