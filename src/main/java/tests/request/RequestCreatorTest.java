package tests.request;

import org.junit.jupiter.api.Test;
import request.Request;
import request.RequestCreator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.Assert.assertTrue;

public class RequestCreatorTest {

    RequestCreator creator = new RequestCreator();

    Request request1 = creator.getRequest("help");

    @Test
    void testGetCommandFromRequestWithoutArguments(){
        assertEquals("help", request1.command());
    }

    @Test
    void testGetArgumentsFromRequestWithoutArguments(){
        assertTrue(request1.arguments().isEmpty());
    }


    Request request2 = creator.getRequest("a b c");
    @Test
    void testGetCommandFromRequestWithArguments(){
        assertEquals("a", request2.command());
    }

    @Test
    void testGetArgumentsFromRequestWithArguments(){
        assertEquals("b", request2.arguments().get(0));
        assertEquals("c", request2.arguments().get(1));
    }


}
