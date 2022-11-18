package tests.parser;

import org.junit.jupiter.api.Test;
import parser.Parser;
import parser.exceptions.FailedConnectionException;
import parser.exceptions.NonExistentPageException;
import parser.exceptions.NonExistentSectionException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    String wrongSectionName = "raca";
    String rightSectionName = "race";
    String rightPageName = "гном";
    String wrongPageName = "гнос";


    @Test
    void testSearchSectionWithWrongArgument() throws IllegalArgumentException{
        assertThrows(NonExistentSectionException.class, () -> Parser.getPagesListFromSection(wrongSectionName));
    }

    @Test
    void testCorrectPrintListFromSection () throws FailedConnectionException, NonExistentSectionException {
        String list = Parser.getPagesListFromSection(rightSectionName);
        String[] result = list.split("\n");
        assertEquals("Багбир", result[2]);
        assertEquals("Гит", result[6]);
    }

    @Test
    void testCorrectGetPageLink() throws NonExistentPageException, FailedConnectionException, NonExistentSectionException {
        String result = Parser.getPageLink(rightSectionName, rightPageName);
        assertEquals("/race/83-gnome/", result);
    }

    @Test
    void testGetPageLinkWithWrongArgument() throws IllegalArgumentException{
        assertThrows(NonExistentPageException.class, () -> Parser.getPageLink(rightSectionName, wrongPageName));
    }
}
