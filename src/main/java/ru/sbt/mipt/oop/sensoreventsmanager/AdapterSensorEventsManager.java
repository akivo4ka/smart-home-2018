package ru.sbt.mipt.oop.sensoreventsmanager;

public class AdapterSensorEventsManager implements SensorEventsManager {

    private com.coolcompany.smarthome.events.SensorEventsManager sensorEventsManager;

    public AdapterSensorEventsManager() {
        this.sensorEventsManager = new com.coolcompany.smarthome.events.SensorEventsManager();
    }

    @Override
    public void start() {
        sensorEventsManager.registerEventHandler(event -> System.out.println("Event type [" + event.getEventType() + "] from object with id=[" + event.getObjectId() + "]"));
        sensorEventsManager.start();
    }
}
