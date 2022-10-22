package pages;

import parser.Parser;

import java.io.IOException;


/**
 * Класс, хранящий в себе информацию о странице сайта dnd.su
 */

public class Page {
    /**
     * Поле ссылка на страницу
     */
    private String link;

    /**
     * Поле блок с основной информацией страницы
     */
    private String mainInformation;

    /**
     * Конструктор класса страницы
     */
    public Page(String message) throws IOException {
        setLink(message);
        setMainInformation(link);
    }

    /**
     * Метод, устанавливающий в поле link ссылку на данную страницу на сайте
     *
     * @param pageName - название страницы
     */
    public void setLink(String pageName) {
        try {
            link = Parser.getPageLink(pageName);
        } catch (IOException e) {
            link = "unknown link";
        }
    }

    /**
     * Метод, устанавливающий в поле mainInformation основную информацию страницы
     *
     * @param link - ссылка на страницу
     */
    public void setMainInformation(String link) throws IOException {

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
