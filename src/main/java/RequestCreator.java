import dataIO.InputModule;

public record RequestCreator(InputModule inputModule) {
    public Request getRequest(){
        String message = inputModule.getMessage();
        String[] parsedMessage = message.split(" ");
        String command = parsedMessage[0];
        String argument = parsedMessage[1];
        return new Request(command, argument);
    }
}
