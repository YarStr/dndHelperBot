package pages;

import parser.Parser;

import java.io.IOException;
import java.util.Map;


/**
 * Класс, хранящий в себе информацию о странице сайта dnd.su
 */

public class Page {
    /**
     * Поле ссылка на страницу
     */
    private String link;
    private String name;
    private Map<String, String> mainInfoBlock;
    private Map<String, String> additionInfoBlock;

    /**
     * Конструктор класса страницы
     */
    public Page(String pageName) throws IOException {
        setLink(pageName);
        name = pageName;
        setInfoBlocks(link);
    }

    /**
     * Метод, устанавливающий в поле link ссылку на данную страницу на сайте
     *
     * @param pageName - название страницы
     */
    public void setLink(String pageName) {
        try {
            link = Parser.getPageLink("race", pageName);
        } catch (IOException e) {
            link = "unknown link";
        }
    }

    /**
     * Метод, устанавливающий в поле mainInformation основную информацию страницы
     *
     * @param link - ссылка на страницу
     */
    public void setInfoBlocks(String link) throws IOException {

        mainInformation = Parser.getMainInfoFromPage(link);

    }

    /**
     * Метод получения ссылки на страницу
     *
     * @return возвращает ссылку на страницу
     */
    public String getLink() {
        return link;
    }

    /**
     * Метод получения основной информации страницы
     *
     * @return возвращает основную информацию страницы
     */
    public String getMainInformation() {
        return mainInformation;
    }
}
