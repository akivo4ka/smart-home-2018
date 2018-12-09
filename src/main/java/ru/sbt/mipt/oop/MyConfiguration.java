package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.phrases.SmartHomePhrases;
import ru.sbt.mipt.oop.phrases.SmartHomePhrasesLoader;
import ru.sbt.mipt.oop.processors.*;
import ru.sbt.mipt.oop.sensoreventprovider.RandomSensorEventProvider;
import ru.sbt.mipt.oop.sensoreventprovider.SensorEventProvider;
import ru.sbt.mipt.oop.sensoreventsmanager.EventsManager;
import ru.sbt.mipt.oop.sensoreventsmanager.HomeEventsObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfiguration {

    private SmartHomePhrases smartHomePhrases;
    private SmartHome smartHome;
    private SensorEventProvider sensorEventProvider;
    private EventsManager sensorEventsManager;
    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();
    private static SmartHomePhrasesLoader smartHomePhrasesLoader = new SmartHomePhrasesLoader();

    @Bean
    public EventsManager getSensorEventsManager() {
        return sensorEventsManager;
    }

    public MyConfiguration() throws IOException {
        smartHomePhrases = smartHomePhrasesLoader.loadSmartHomePhrases();
        smartHome = smartHomeLoader.loadSmartHome();
        smartHome.setSmartHomePhrases(smartHomePhrases, "ru");
        sensorEventProvider = new RandomSensorEventProvider();
        sensorEventsManager = new HomeEventsObserver(createEventProcessorList(smartHome), sensorEventProvider);
        // sensorEventsManager = new AdapterSensorEventsManager(createEventProcessorList(smartHome));
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
