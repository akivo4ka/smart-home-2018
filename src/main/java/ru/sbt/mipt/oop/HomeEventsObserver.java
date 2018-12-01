package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.processors.EventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.ArrayList;
import java.util.List;

public class HomeEventsObserver {

    List<EventProcessor> eventProcessorList = new ArrayList<>();
    SensorEventProvider sensorEventProvider;

    public HomeEventsObserver(List<EventProcessor> eventProcessorsList, SensorEventProvider sensorEventProvider) {
        this.eventProcessorList = eventProcessorsList;
        this.sensorEventProvider = sensorEventProvider;
    }

    public void addEventProcessor(EventProcessor eventProcessor) {
        eventProcessorList.add(eventProcessor);
    }

    public void observe() {
        SensorEvent event = sensorEventProvider.getNextSensorEvent();
        while (event != null) {
            processEvent(event);
            event = sensorEventProvider.getNextSensorEvent();
        }
    }

    public void processEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        for (EventProcessor eventProcessor : eventProcessorList) {
            eventProcessor.process(event);
        }
    }

}
