package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeUnits.SmartHome;

import java.io.IOException;

public class Application {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    public void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        Application.smartHomeLoader = smartHomeLoader;
    }

    public static void main(String... args) throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
//        SensorEventsManager homeEventsObserver = new HomeEventsObserver(smartHome);
//        homeEventsObserver.start();
        SensorEventsManager sensorEventsManager = new AdapterSensorEventsManager();
        sensorEventsManager.start();
    }

}
