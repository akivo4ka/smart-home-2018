package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.processors.*;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.ArrayList;
import java.util.List;

public class HomeEventsObserver implements SensorEventsManager {

    List<EventProcessor> eventProcessorList;
    SensorEventProvider sensorEventProvider;

    @Override
    public void start() {
        SensorEvent event = sensorEventProvider.getNextSensorEvent();
        while (event != null) {
            processEvent(event);
            event = sensorEventProvider.getNextSensorEvent();
        }
    }

    public HomeEventsObserver(List<EventProcessor> eventProcessorsList, SensorEventProvider sensorEventProvider) {
        this.eventProcessorList = eventProcessorsList;
        this.sensorEventProvider = sensorEventProvider;
    }

    public HomeEventsObserver(SmartHome smartHome) {
        this.eventProcessorList = createEventProcessorList(smartHome);
        this.sensorEventProvider = new RandomSensorEventProvider();
    }

    public void addEventProcessor(EventProcessor eventProcessor) {
        eventProcessorList.add(eventProcessor);
    }

    public void processEvent(SensorEvent event) {
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
