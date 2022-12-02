package messagePackage;

/**
 * Тип данных, хранящий текст и стиль его оформления
 */
public class FormattedText {
    /**
     * Текст
     */
    public String text;

    /**
     * Стиль оформления текста
     */
    public Format format;

    /**
     * Конструктор
     * @param text текст
     * @param format стиль оформления текста
     */
    public FormattedText(String text, Format format){
        this.text = text;
        this.format = format;
    }

    /**
     * Конструктор
     */
    public FormattedText() {
        text = null;
        format = null;
    }
}
