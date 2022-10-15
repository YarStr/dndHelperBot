package directories;

import Parser.Parser;

import java.io.IOException;

/**
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * НАСТЬ, СДЕЛАЙ ТАК, ЧТОБЫ ВСЕ МЕТОДЫ БЫЛИ НЕ СТАТИЧЕСКИЕ
 * Пусть командам для их вывода надо будет хранить в себе экземпляр Расы
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * СДЕЛАНОООО
 */

/**
 * Класс, хранящий в себе информацию о расе
 */

public class Race {

    /**
     * link - ссылка на страницу
     * mainInformation - блок с краткой сводкой
     */
    private String link;
    private String mainInformation;

    /**
     * Конструктор класса расы
     */

    public Race(String message) {
        setLink(message);
        setMainInformation(link);
    }

    /**
     * Метод, устанавливающий в поле link ссылку на поисковый запрос
     *
     * @param message - поисковый запрос
     * @throws IOException вызывается, если ссылка не была получена
     */

    public void setLink(String message) {
        try {
            link = Parser.getPageLink(message);
        } catch (IOException e) {
            link = "unknown link";
        }
    }


    /**
     * Метод, устанавливающий в поле mainInformation краткую сводку
     *
     * @param link - ссылка на страницу с поисковым запросом
     * @throws IOException вызывается, если информация не была получена
     */

    public void setMainInformation(String link) {
        try {
            mainInformation = Parser.getMainInfoFromPage(link);
        } catch (IOException e) {
            mainInformation = "unknown main information";
        }
    }

    /**
     * Метод, осуществляющий получение ссылки на страницу поискового запроса
     *
     * @return возвращает ссылку на запрос
     */

    public String getLink() {
        return link;
    }

    /**
     * Метод, осуществляющий получение краткой сводки на поисковый запрос
     *
     * @return возвращает краткую сводку на запрос
     */

    public String getMainInformation() {
        return mainInformation;
    }
}
