package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.processors.EventProcessor;
import ru.sbt.mipt.oop.processors.LightEventProcessor;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    public static void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        Application.smartHomeLoader = smartHomeLoader;
    }

    public static void main(String... args) throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        runEventCycle(smartHome);
    }

    private static void runEventCycle(SmartHome smartHome) {
        SensorEvent event = RandomSensorEventProvider.getNextSensorEvent();
        EventProcessor eventProcessor;
        while (event != null) {
            System.out.println("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                LightEventProcessor.processLightEvent(smartHome, event);
            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                DoorEventProcessor.processDoorEvent(smartHome, event);
            }
            event = RandomSensorEventProvider.getNextSensorEvent();
        }
    }
}
