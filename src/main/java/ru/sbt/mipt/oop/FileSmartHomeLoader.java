package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.homeUnits.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSmartHomeLoader implements SmartHomeLoader {
    @Override
    public SmartHome loadSmartHome() throws IOException {
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("C:\\Users\\Expert\\IdeaProjects\\smart-home-2018\\src\\main\\resources\\smart-home-1.js")));
        return gson.fromJson(json, SmartHome.class);
    }
}