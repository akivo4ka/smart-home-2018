package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarmSystem.AlarmSystem;
import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.processors.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    public void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        Application.smartHomeLoader = smartHomeLoader;
    }

    public void main(String... args) throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        observeHomeEvents(smartHome);
    }

    private void observeHomeEvents(SmartHome smartHome) {
        List<EventProcessor> eventProcessorList = createEventProcessorList(smartHome);
        SensorEventProvider sensorEventProvider = new RandomSensorEventProvider();
        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(eventProcessorList, sensorEventProvider);
        homeEventsObserver.observe();
    }

    private List<EventProcessor> createEventProcessorList(SmartHome smartHome) {
        List<EventProcessor> eventProcessorList = new ArrayList<>();
        LightEventProcessor lightEventProcessor = new LightEventProcessor(smartHome);
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor(smartHome);
        HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor(smartHome);
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor(smartHome, new AlarmSystem());
        eventProcessorList.add(lightEventProcessor);
        eventProcessorList.add(doorEventProcessor);
        eventProcessorList.add(hallDoorEventProcessor);
        eventProcessorList.add(alarmEventProcessor);
        return eventProcessorList;
    }
}
