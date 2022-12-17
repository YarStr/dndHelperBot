package botLogic.Analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ListWords {

    public HashMap<String, ArrayList<String>> listWords = new HashMap<>()
    {{
        put("help", new ArrayList<>(Arrays.asList("умеешь?", "знаешь?")));
        put("race", new ArrayList<>(Arrays.asList("раса", "расу", "расе", "расы", "расах")));
        put("size", new ArrayList<>(Arrays.asList("рост", "размер")));
        put("age", new ArrayList<>(Arrays.asList("возраст")));
        put("speed", new ArrayList<>(Arrays.asList("скорость")));
        put("link", new ArrayList<>(Arrays.asList("ссылка", "ссылку")));
        put("score", new ArrayList<>(Arrays.asList("характеристику", "характеристика")));
        put("alignment", new ArrayList<>(Arrays.asList("мировоззрение")));
        put("languages", new ArrayList<>(Arrays.asList("язык", "языке", "языки")));
        put("exit", new ArrayList<>(Arrays.asList("сменим", "смени")));
        put("hello", new ArrayList<>(Arrays.asList("привет")));
        put("thanks", new ArrayList<>(Arrays.asList("спасибо")));
    }};

    public HashMap<String, ArrayList<String>> getListWords() {
        return listWords;
    }
}
