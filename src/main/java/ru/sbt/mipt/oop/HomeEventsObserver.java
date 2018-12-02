package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.processors.*;
import ru.sbt.mipt.oop.sensoreventprovider.RandomSensorEventProvider;
import ru.sbt.mipt.oop.sensoreventprovider.SensorEventProvider;
import ru.sbt.mipt.oop.sensoreventsmanager.SensorEventsManager;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.ArrayList;
import java.util.List;

public class HomeEventsObserver implements SensorEventsManager {

    private List<EventProcessor> eventProcessorList;
    private SensorEventProvider sensorEventProvider;

    @Override
    public void start() {
        SensorEvent event = sensorEventProvider.getNextSensorEvent();
        while (event != null) {
            processEvent(event);
            event = sensorEventProvider.getNextSensorEvent();
        }
    }

    public void setEventProcessorList(List<EventProcessor> list) {
        this.eventProcessorList = list;
    }

    public void setSensorEventProvider(SensorEventProvider sensorEventProvider) {
        this.sensorEventProvider = sensorEventProvider;
    }

    public HomeEventsObserver(List<EventProcessor> eventProcessorsList, SensorEventProvider sensorEventProvider) {
        this.eventProcessorList = eventProcessorsList;
        this.sensorEventProvider = sensorEventProvider;
    }

    public HomeEventsObserver(SmartHome smartHome) {
        this.eventProcessorList = createEventProcessorList(smartHome);
        this.sensorEventProvider = new RandomSensorEventProvider();
    }

    HomeEventsObserver(SmartHome smartHome, SensorEventProvider sensorEventProvider) {
        this.eventProcessorList = createEventProcessorList(smartHome);
        this.sensorEventProvider = sensorEventProvider;
    }

    public void addEventProcessor(EventProcessor eventProcessor) {
        eventProcessorList.add(eventProcessor);
    }

    private void processEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        for (EventProcessor eventProcessor : eventProcessorList) {
            eventProcessor.process(event);
        }
    }

    private static List<EventProcessor> createEventProcessorList(SmartHome smartHome) {
        List<EventProcessor> eventProcessorList = new ArrayList<>();
        EventProcessor lightEventProcessor = new DecoratorCheckAlarmEventProcessor(new LightEventProcessor(smartHome));
        EventProcessor doorEventProcessor = new DecoratorCheckAlarmEventProcessor(new DoorEventProcessor(smartHome));
        EventProcessor hallDoorEventProcessor = new DecoratorCheckAlarmEventProcessor(new HallDoorEventProcessor(smartHome));
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor(smartHome);
        eventProcessorList.add(lightEventProcessor);
        eventProcessorList.add(doorEventProcessor);
        eventProcessorList.add(hallDoorEventProcessor);
        eventProcessorList.add(alarmEventProcessor);
        return eventProcessorList;
    }
}
