package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.processors.*;
import ru.sbt.mipt.oop.sensoreventprovider.RandomSensorEventProvider;
import ru.sbt.mipt.oop.sensoreventprovider.SensorEventProvider;
import ru.sbt.mipt.oop.sensoreventsmanager.SensorEventsManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfiguration {

    private SmartHome smartHome;
    private SensorEventProvider sensorEventProvider;
    private SensorEventsManager sensorEventsManager;
    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    @Bean
    public SensorEventsManager getSensorEventsManager() {
        return sensorEventsManager;
    }


    public MyConfiguration() throws IOException {
        smartHome = smartHomeLoader.loadSmartHome();
        sensorEventProvider = new RandomSensorEventProvider();
        sensorEventsManager = new HomeEventsObserver(createEventProcessorList(smartHome), sensorEventProvider);
        // If we want to use external library:
        // sensorEventsManager = new AdapterSensorEventsManager();
    }

    public void setSmartHomeLoader(SmartHomeLoader smartHomeLoader) {
        MyConfiguration.smartHomeLoader = smartHomeLoader;
    }

    private static List<EventProcessor> createEventProcessorList(SmartHome smartHome) {
        List<EventProcessor> eventProcessorList = new ArrayList<>();
        EventProcessor lightEventProcessor = new DecoratorCheckAlarmEventProcessor(new LightEventProcessor(smartHome), smartHome);
        EventProcessor doorEventProcessor = new DecoratorCheckAlarmEventProcessor(new DoorEventProcessor(smartHome), smartHome);
        EventProcessor hallDoorEventProcessor = new DecoratorCheckAlarmEventProcessor(new HallDoorEventProcessor(smartHome), smartHome);
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor(smartHome);
        eventProcessorList.add(lightEventProcessor);
        eventProcessorList.add(doorEventProcessor);
        eventProcessorList.add(hallDoorEventProcessor);
        eventProcessorList.add(alarmEventProcessor);
        return eventProcessorList;
    }
}
