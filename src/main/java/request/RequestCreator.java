package request;

import dataIO.InputModule;
import request.Request;

public record RequestCreator(InputModule inputModule) {
    public Request getRequest() {
        String message = inputModule.getMessage();
        String[] parsedMessage = message.split(" ");
        if (parsedMessage.length>1){
            String command = parsedMessage[0];
            String argument = parsedMessage[1];
            return new Request(command, argument);
        }
        else if (parsedMessage.length==1){
            return new Request(message, null);
        }
        else{
            return null;
        }
    }
}
