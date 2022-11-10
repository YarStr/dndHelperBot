package pages;

import botLogic.FailedConnectionException;
import botLogic.NonExistentSectionException;
import command.FailedCommandExecutionException;
import command.InvalidCommandArgumentsException;
import parser.NonExistentPageException;
import parser.Parser;

import java.util.ArrayList;
import java.util.Map;


/**
 * Класс, хранящий в себе информацию о странице сайта dnd.su
 */

public class Page {
    /**
     * Поле ссылка на страницу
     */
    private String link;
    private Map<String, String> mainFeatures;
//    private Map<String, String> additionFeatures;

    /**
     * Конструктор класса страницы
     */
    public Page(String sectionName, String pageName) throws FailedCommandExecutionException, InvalidCommandArgumentsException {
        setLink(sectionName, pageName);
        setInfoBlocks(link);
    }

    /**
     * Метод, устанавливающий в поле link ссылку на данную страницу на сайте
     *
     * @param pageName - название страницы
     */
    public void setLink(String sectionName, String pageName) throws FailedCommandExecutionException, InvalidCommandArgumentsException {
        try {
            link = Parser.getPageLink(sectionName, pageName);
        } catch (FailedConnectionException | NonExistentSectionException e) {
            throw new FailedCommandExecutionException();
        } catch (NonExistentPageException e) {
            throw new InvalidCommandArgumentsException(e.getMessage());
        }
    }

    /**
     * Метод, устанавливающий в поле mainInformation основную информацию страницы
     *
     * @param link - ссылка на страницу
     */
    public void setInfoBlocks(String link) throws FailedCommandExecutionException {
        try {
            mainFeatures = Parser.getRaceFeatures(link);
        } catch (FailedConnectionException e) {
            throw new FailedCommandExecutionException();
        }
//         ArrayList<String> MAIN_RACE_INFO = new ArrayList<>(Arrays.asList(
//                "Увеличение характеристик.",
//                "Возраст.",
//                "Мировоззрение.",
//                "Размер.",
//                "Скорость.",
//                "Языки."
//        ));
//        for (String title : allInfoMap.keySet()){
//            if (MAIN_RACE_INFO.contains(title))
//                mainFeatures.put(title, allInfoMap.get(title));
//            else
//                additionFeatures.put(title, allInfoMap.get(title));
//        }
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
    public String getAllFeatures() {
        StringBuilder answer = new StringBuilder();
        for (String title : mainFeatures.keySet()) {
            answer.append(title)
                    .append(" ")
                    .append(mainFeatures.get(title))
                    .append("\n");
        }
        return String.valueOf(answer);
    }

    public String getSelectedFeatures(ArrayList<String> features) {
        StringBuilder answer = new StringBuilder();
        for (String title : features) {
            answer.append(title)
                    .append(" ")
                    .append(mainFeatures.get(title))
                    .append("\n");
        }
        return String.valueOf(answer);
    }
}
