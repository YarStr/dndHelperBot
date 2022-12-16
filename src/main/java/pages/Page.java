package pages;

import packedMessage.FormattedText;
import packedMessage.PackedMessage;
import packedMessage.PackedMessageBuilder;

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
            "Увеличение характеристик.", "score",
            "Возраст.", "age",
            "Мировоззрение.", "alignment",
            "Размер.", "size",
            "Скорость.", "speed",
            "Языки.", "languages"
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
    public PackedMessage getFeatures(ArrayList<String> features) {
        ArrayList<FormattedText> chosenFeatures = new ArrayList<>();
        if (features.contains("all")) {
            chosenFeatures = new ArrayList<>(mainFeatures.keySet());
        } else {
            for (FormattedText key : mainFeatures.keySet()) {
                if (features.contains(FEATURES_NAMES_MATCH.get(key.text)))
                    chosenFeatures.add(key);
            }
        }

        PackedMessageBuilder messagePackageBuilder = new PackedMessageBuilder();
        for (FormattedText title : chosenFeatures) {
            messagePackageBuilder.addInformation(title).addInformation(mainFeatures.get(title));
        }

        return messagePackageBuilder.build();
    }
}
