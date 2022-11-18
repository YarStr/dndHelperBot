package pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Класс, хранящий в себе информацию о странице сайта dnd.su
 */

public class Page {
    /**
     * Поле ссылка на страницу
     */
    private final Map<String, String> FEATURES_NAMES_MATCH = Map.of(
            "score", "Увеличение характеристик.",
            "age", "Возраст.",
            "alignment", "Мировоззрение.",
            "size", "Размер.",
            "speed", "Скорость.",
            "languages", "Языки."
    );
    private final String link;
    private final Map<String, String> mainFeatures;

    /**
     * Конструктор класса страницы
     */
    public Page(String link, Map<String, String> mainFeatures) {
        this.link = link;
        this.mainFeatures = mainFeatures;
    }

    /**
     * Метод получения ссылки на страницу
     *
     * @return возвращает ссылку на страницу
     */
    public String getLink() {
        return link;
    }

    public String getFeatures(ArrayList<String> features){
        Set<String> chosenFeatures = new HashSet<>();
        if (features.contains("all"))
            chosenFeatures = mainFeatures.keySet();
        else
            for (String x : features)
                chosenFeatures.add(FEATURES_NAMES_MATCH.get(x));

        StringBuilder answer = new StringBuilder();
        for (String title : chosenFeatures) {
            answer.append(title)
                    .append(" ")
                    .append(mainFeatures.get(title))
                    .append("\n");
        }
        return String.valueOf(answer);
    }
}
