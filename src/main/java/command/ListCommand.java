package command;

import Parser.Parser;


public class ListCommand {

    public String getRaceList() {
        return Parser.getPagesList();
    }
}
