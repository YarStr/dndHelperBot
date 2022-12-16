package tests.pages;

import command.exceptions.InvalidCommandArgumentsException;
import org.junit.jupiter.api.Test;
import pages.PageBuilder;

import static org.junit.jupiter.api.Assertions.*;

class PageBuilderTest {

    @Test
    public void testSetLinkWithInvalidPageName() {
        PageBuilder pageBuilder = new PageBuilder("race", "invalidPageName");
        assertThrows(InvalidCommandArgumentsException.class, pageBuilder::setLink);
    }
}