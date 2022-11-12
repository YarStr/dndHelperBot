package pages;

import parser.exceptions.FailedConnectionException;
import parser.exceptions.NonExistentSectionException;
import command.exceptions.FailedCommandExecutionException;
import command.exceptions.InvalidCommandArgumentsException;
import parser.exceptions.NonExistentPageException;
import parser.Parser;

import java.util.Map;

public class PageBuilder {
    private final String sectionName;
    private final String pageName;
    private String link;
    private Map<String, String> mainFeatures;

    public PageBuilder(String sectionName, String pageName) {
        this.sectionName = sectionName;
        this.pageName = pageName;
    }

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

    public PageBuilder setInfoBlocks() throws FailedCommandExecutionException {
        try {
            mainFeatures = Parser.getRaceFeatures(link);
        } catch (FailedConnectionException e) {
            throw new FailedCommandExecutionException();
        }
        return this;
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

    public Page build(){
        return new Page(link, mainFeatures);
    }
}
