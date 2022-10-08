package org.example;

import java.io.IOException;

/**
 * Класс, хранящий в себе информацию о расе
 */

public class Race {
    
    /**
     * link - ссылка на страницу
     * mainInformation - блок с основной информацией
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
     * Метод, устанавливающий ссылку на поисковый запрос
     */

    public void setLink(String message) {
        try {
            link = Parser.getPageLink(message);
        } catch (IOException e) {
            link = "unknown link";
        }
    }
    
    /**
     * Метод, устанавливающий основную информацию на поисковый запрос
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
     */

    public String getLink() {
        return link;
    }
    
    /**
     * Метод, осуществляющий получение основной информации на поисковый запрос
     */

    public String getMainInformation() {
        return mainInformation;
    }
}
