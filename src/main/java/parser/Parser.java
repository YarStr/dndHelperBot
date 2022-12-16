package parser;

import org.telegram.telegrambots.meta.api.objects.File;
import packedMessage.FormattedText;
import packedMessage.Format;
import packedMessage.PackedMessage;
import packedMessage.PackedMessageBuilder;
import parser.exceptions.FailedConnectionException;
import parser.exceptions.NonExistentSectionException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.exceptions.NonExistentPageException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс для парсинга сайта dnd.su
 */
public class Parser {
    /**
     * Метод получения секции сайта dnd.su
     *
     * @param sectionName - имя секции
     * @return секцию сайта
     * @throws NonExistentSectionException если не существует запрашиваемой секции
     * @throws FailedConnectionException   когда не получилось подключиться к сайту по причинам, не зависящим от пользователя
     */
    private static Document getSection(String sectionName) throws NonExistentSectionException, FailedConnectionException {
        try {
            Sections.valueOf(sectionName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NonExistentSectionException("Раздела " + sectionName + " нет на сайте dnd.su :(");
        }
        try {
            return Jsoup.connect("https://dnd.su/" + sectionName.toLowerCase())
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .get();
        } catch (IOException e) {
            throw new FailedConnectionException();
        }
    }

    /**
     * Метод получения списка доступных страниц из секции сайта dnd.su
     *
     * @param sectionName название секции сайта
     * @return пакет сообщения с доступными из секции страницами
     * @throws NonExistentSectionException при попытке подключиться к недоступной боту секции сайта dnd.su
     * @throws FailedConnectionException   когда не получилось подключиться к сайту по причинам, не зависящим от пользователя
     */
    public static PackedMessage getPagesListFromSection(String sectionName) throws NonExistentSectionException, FailedConnectionException {
        Document section = getSection(sectionName);
        Elements raceList = section.select("span[class=\"article_title\"]");

        ArrayList<FormattedText> formattedRaceList = new ArrayList<>();
        formattedRaceList.add(new FormattedText("Список всех доступных рас", Format.TITLE));
        for (Element raceListElement : raceList)
            formattedRaceList.add(new FormattedText(raceListElement.text(), Format.NORMAL));

        return new PackedMessageBuilder().addInformation(formattedRaceList).build();
    }

    /**
     * Метод получения ссылки на страницу
     *
     * @param sectionName название секции, в которой находится страница
     * @param pageName    название страницы
     * @return ссылка на страницу
     * @throws FailedConnectionException   когда не получилось подключиться к сайту по причинам, не зависящим от пользователя
     * @throws NonExistentSectionException при попытке подключиться к недоступной боту секции сайта dnd.su
     * @throws NonExistentPageException    при попытке подключиться к несуществующей странице боту сайта dnd.su
     */
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
     * Метод получения основной информации со страницы расы
     *
     * @param link ссылка на страницу расы
     * @return основную информацию, разбитую на блоки
     * @throws FailedConnectionException когда не получилось подключиться к сайту по причинам, не зависящим от пользователя
     */
    public static Map<FormattedText, FormattedText> getRaceFeatures(String link) throws FailedConnectionException {
        Document section;
        try {
            section = Jsoup.connect("https://dnd.su" + link).get();
        } catch (IOException e) {
            throw new FailedConnectionException();
        }

        Elements featureElements = section.select("h3:has(span[id^=\"osobennosti\"]) ~ p:has(strong)");
        Map<FormattedText, FormattedText> featureElementsMap = new HashMap<>();

        for (Element element : featureElements) {
            String titleString = element.select("strong").text();

            FormattedText title = new FormattedText();
            title.format = Format.TITLE;
            FormattedText feature = new FormattedText();
            feature.format = Format.NORMAL;

            if (titleString.equals("Мировоззрение")) {
                title.text = "Мировоззрение.";
                feature.text = element.select("span").text();
            } else {
                title.text = titleString;
                feature.text = element.ownText();
            }

            featureElementsMap.put(title, feature);
        }
        return featureElementsMap;
    }

}
