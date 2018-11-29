package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.processors.EventProcessor;
import ru.sbt.mipt.oop.processors.LightEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.sbt.mipt.oop.sensors.SensorEventType.*;

public class Application {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        Application.smartHomeLoader = smartHomeLoader;
    }

    public static void main(String... args) throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        observeHomeEvents(smartHome);
    }

    private static void observeHomeEvents(SmartHome smartHome) {
        List<EventProcessor> eventProcessorList = new ArrayList<>();
        LightEventProcessor lightEventProcessor = new LightEventProcessor();
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor();
        eventProcessorList.add(lightEventProcessor);
        eventProcessorList.add(doorEventProcessor);
        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(eventProcessorList);
        homeEventsObserver.observer(smartHome);
    }
}
