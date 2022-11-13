package pages;

import parser.exceptions.FailedConnectionException;
import parser.exceptions.NonExistentSectionException;
import command.exceptions.FailedCommandExecutionException;
import command.exceptions.InvalidCommandArgumentsException;
import parser.exceptions.NonExistentPageException;
import parser.Parser;

import java.util.Map;

/**
 * Класс строитель класса страницы
 */

public class PageBuilder {
    /**
     * Название секции сайта, на которой находится старица
     */
    private final String sectionName;

    /**
     * Название страницы
     */
    private final String pageName;

    /**
     * Ссылка на страницу
     */
    private String link;

    /**
     * Блоки с основной информацией страницы
     */
    private Map<String, String> mainFeatures;

    /**
     * Конструктор класса
     * @param sectionName название секции сайта
     * @param pageName название страницы
     */
    public PageBuilder(String sectionName, String pageName) {
        this.sectionName = sectionName;
        this.pageName = pageName;
    }

    /**
     * Метод установки ссылки на создаваемую страницу
     * @return экземпляр класса PageBuilder
     * @throws FailedCommandExecutionException когда не удалось построить класс с указанным названием секции или страницы
     * @throws InvalidCommandArgumentsException когда не удалось выполнить команду по причинам, не зависящим от пользователя
     */
    public PageBuilder setLink() throws FailedCommandExecutionException, InvalidCommandArgumentsException {
        try {
            link = Parser.getPageLink(sectionName, pageName);
        } catch (FailedConnectionException e) {
            throw new FailedCommandExecutionException();
        } catch (NonExistentPageException | NonExistentSectionException e) {
            throw new InvalidCommandArgumentsException(e.getMessage());
        }
        return this;
    }

    /**
     * Метод установки блоков основной информации страницы
     * @return экземпляр класса PageBuilder
     * @throws FailedCommandExecutionException когда не удалось выполнить команду по причинам, не зависящим от пользователя
     */
    public PageBuilder setInfoBlocks() throws FailedCommandExecutionException {
        try {
            mainFeatures = Parser.getRaceFeatures(link);
        } catch (FailedConnectionException e) {
            throw new FailedCommandExecutionException();
        }
        return this;
    }

    /**
     * Метод завершения построения класса страницы
     * @return экземпляр класса с установленными билдером полями
     */
    public Page build(){
        return new Page(link, mainFeatures);
    }
}
