package botLogic.Analysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;


public class Analyzer {
    public String textAnalysis (String text) throws IOException {
        String result = "";
        ListWords list = new ListWords();
        HashMap<String, ArrayList<String>> listWords = list.getListWords();

        ArrayList<String> message = new ArrayList<>(Arrays.asList(text.split(" ")));

        if (message.get(0).charAt(0) == '/') {
            return text;
        }

        Iterator<String> it = message.iterator();

        while (it.hasNext()) {
            String word = it.next().toLowerCase().replaceAll("[.!?,]", "");
            outerloop:
            for (String key : listWords.keySet()) {
                if (listWords.get(key).contains(word)) {
                    switch (key) {
                        case "race" -> {
                            if (!it.hasNext()) {
                                result = "list race";
                                return result;
                            } else{
                                result = "race";
                                break outerloop;
                            }
                        }
                        case "size", "speed", "link", "score", "alignment", "languages", "age" ->{
                            if(result.isEmpty()){
                                result = "info";
                            }
                            result += " " + key;
                            if (!it.hasNext()){
                                return result;
                            }
                            break outerloop;
                        }
                        default -> {
                            result = key;
                            return result;
                        }
                    }
                }
                else if (result.equals("race")){
                    result += word;
                    if (!it.hasNext()) {
                        return result;
                    }
                    else{
                        break;
                    }
                }
            }
        }
        return "Я тебя не понимать...";
    }
}
