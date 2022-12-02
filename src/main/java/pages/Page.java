package pages;

import messagePackage.Format;
import messagePackage.FormattedText;
import messagePackage.MessagePackage;
import messagePackage.MessagePackageBuilder;

import java.util.ArrayList;
import java.util.Map;


/**
 * Класс, хранящий в себе информацию о странице сайта dnd.su
 */

public class Page {
    /**
     * Таблица сопоставления блоков основной информации и их названий
     */
    private final Map<String, String> FEATURES_NAMES_MATCH = Map.of(
            "score", "Увеличение характеристик.",
            "age", "Возраст.",
            "alignment", "Мировоззрение.",
            "size", "Размер.",
            "speed", "Скорость.",
            "languages", "Языки."
    );

    /**
     * Ссылка на страницу
     */
    private final FormattedText link;

    /**
     * Основная информация со страницы
     */
    private final Map<FormattedText, FormattedText> mainFeatures;

    /**
     * Конструктор класса страницы
     */
    public Page(FormattedText link, Map<FormattedText, FormattedText> mainFeatures) {
        this.link = link;
        this.mainFeatures = mainFeatures;
    }

    /**
     * Метод получения ссылки на страницу
     *
     * @return возвращает ссылку на страницу
     */
    public FormattedText getLink() {
        return link;
    }

    /**
     * Метод получения блоков основной информации со страницы
     *
     * @param features список блоков
     * @return строка с информацией запрашиваемых блоков
     */
    public MessagePackage getFeatures(ArrayList<String> features) {
        ArrayList<FormattedText> chosenFeatures = new ArrayList<>();
        if (features.contains("all"))
            chosenFeatures = (ArrayList<FormattedText>) mainFeatures.keySet();
        else
            for (String x : features)
                chosenFeatures.add(new FormattedText(FEATURES_NAMES_MATCH.get(x), Format.NORMAL));

        return new MessagePackageBuilder().addInformation(chosenFeatures).build();
    }
}
