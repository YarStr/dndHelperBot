package tests.request;

import org.junit.jupiter.api.Test;
import request.Request;
import request.RequestCreator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

public class RequestCreatorTest {

    RequestCreator creator = new RequestCreator();
    Request result = new Request(null, null);

    @Test
    void testGetRequestWithoutArguments(){
        result = creator.getRequest("help");
        assertEquals("help", result.command());
        assertTrue(result.arguments().isEmpty());
    }

    @Test
    void testGetRequestWithArguments(){
        result = creator.getRequest("a b c");
        assertEquals("a", result.command());
        assertEquals("b", result.arguments().get(0));
        assertEquals("c", result.arguments().get(1));
    }
}
