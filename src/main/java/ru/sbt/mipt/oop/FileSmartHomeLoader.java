package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSmartHomeLoader implements SmartHomeLoader {
    @Override
    public SmartHome loadSmartHome() throws IOException {
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("C:\\Users\\THINK\\IdeaProjects\\smart-home-2018\\src\\main\\resources\\smart-home-1.js")));
        return gson.fromJson(json, SmartHome.class);
    }
}