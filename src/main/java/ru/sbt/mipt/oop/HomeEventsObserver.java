package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.processors.EventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.ArrayList;
import java.util.List;

public class HomeEventsObserver {

    public List<EventProcessor> eventProcessorList = new ArrayList<>();

    public HomeEventsObserver(List<EventProcessor> eventProcessorsList) {
        this.eventProcessorList = eventProcessorsList;
    }

    public void addEventProcessor(EventProcessor eventProcessor) {
        this.eventProcessorList.add(eventProcessor);
    }

    public void observer(SmartHome smartHome) {
        SensorEvent event = RandomSensorEventProvider.getNextSensorEvent();
        while (event != null) {
            processEvent(smartHome, event);
            event = RandomSensorEventProvider.getNextSensorEvent();
        }
    }

    public void processEvent(SmartHome smartHome, SensorEvent event) {
        System.out.println("Got event: " + event);
        for (EventProcessor eventProcessor : this.eventProcessorList) {
            eventProcessor.process(smartHome, event);
        }
    }

}
