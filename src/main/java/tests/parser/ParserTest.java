package tests.parser;

import org.junit.jupiter.api.Test;
import packedMessage.Format;
import packedMessage.FormattedText;
import packedMessage.PackedMessage;
import parser.Parser;
import parser.exceptions.FailedConnectionException;
import parser.exceptions.NonExistentPageException;
import parser.exceptions.NonExistentSectionException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    String wrongSectionName = "Wrong section name";
    String rightSectionName = "race";
    String rightPageName = "гном";
    String wrongPageName = "Wrong page name";


    @Test
    void testSearchSectionWithWrongArgument() throws IllegalArgumentException {
        assertThrows(NonExistentSectionException.class, () -> Parser.getPagesListFromSection(wrongSectionName),
                "Раздела " + wrongSectionName + " нет на сайте dnd.su :(");
    }

    @Test
    void testCorrectPrintListFromSection() throws FailedConnectionException, NonExistentSectionException {
        PackedMessage list = Parser.getPagesListFromSection(rightSectionName);
        assertEquals("Список всех доступных рас", list.information.get(0).text);
        assertEquals(Format.TITLE, list.information.get(0).format);
        assertEquals("Багбир", list.information.get(5).text);
        assertEquals(Format.NORMAL, list.information.get(5).format);
        assertEquals("Гит", list.information.get(9).text);
        assertEquals(Format.NORMAL, list.information.get(9).format);
    }

    @Test
    void testCorrectGetPageLink() throws NonExistentPageException, FailedConnectionException, NonExistentSectionException {
        String result = Parser.getPageLink(rightSectionName, rightPageName);
        assertEquals("/race/83-gnome/", result);
    }

    @Test
    void testGetPageLinkWithWrongArgument() throws IllegalArgumentException {
        assertThrows(NonExistentPageException.class, () -> Parser.getPageLink(rightSectionName, wrongPageName),
                "Страница " + wrongPageName + " не была найдена на сайте dnd.su :(");
    }

    @Test
    void testGetMainInformationFromPage() throws FailedConnectionException {
        Map<FormattedText, FormattedText> result = Parser.getRaceFeatures("/race/83-gnome/");
        FormattedText title1 = new FormattedText("Увеличение характеристик.", Format.TITLE);
        System.out.println(result.get(title1));

        assertEquals("Значение вашего Интеллекта увеличивается на 2.", result.get(title1).text);
        assertEquals(Format.NORMAL, result.get(title1).format);
        FormattedText title2 = new FormattedText("Скорость.", Format.TITLE);
        assertEquals("Ваша базовая скорость ходьбы составляет 25 футов.", result.get(title2).text);
        assertEquals(Format.NORMAL, result.get(title2).format);
    }

}
