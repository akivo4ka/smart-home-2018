package ru.sbt.mipt.oop.sensoreventsmanager;

import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.processors.EventProcessor;

import java.util.List;

public class AdapterSensorEventsManager implements EventsManager {

    private SensorEventsManager sensorEventsManager;

    public AdapterSensorEventsManager(List<EventProcessor> eventProcessorsList) {
        this.sensorEventsManager = new SensorEventsManager();
        for (EventProcessor eventProcessor : eventProcessorsList) {
            this.sensorEventsManager.registerEventHandler(new AdapterEventHandler(eventProcessor));
        }
    }

    @Override
    public void start() {
        sensorEventsManager.registerEventHandler(event ->
                System.out.println("Event type [" + event.getEventType() + "] from object with id=[" + event.getObjectId() + "]")
        );
        sensorEventsManager.start();
    }
}
