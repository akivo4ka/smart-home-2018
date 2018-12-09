package ru.sbt.mipt.oop.phrases;

import java.util.HashMap;
import java.util.Map;

public class SmartHomePhrases {
    private Phrase en;
    private Phrase ru;
    // private Phrase de;
    // private Phrase fr;
    private String language;

    public void setLanguage(String language) {
        phraseMap.put("en", this.en);
        phraseMap.put("ru", this.ru);
        // phraseMap.put("de", this.de);
        // phraseMap.put("fr", this.fr);
        this.language = language;
    }

    private static Map<String, Phrase> phraseMap = new HashMap<>();

    public Phrase getPhrases() {
        return phraseMap.get(language);
    }
}
