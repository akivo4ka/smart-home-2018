package ru.sbt.mipt.oop.phrases;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomePhrasesLoader {
    public SmartHomePhrases loadSmartHomePhrases() throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("C:\\Users\\Expert\\IdeaProjects\\smart-home-2018\\src\\main\\resources\\smart-home-phrases.js")));
        return gson.fromJson(json, SmartHomePhrases.class);
    }
}
