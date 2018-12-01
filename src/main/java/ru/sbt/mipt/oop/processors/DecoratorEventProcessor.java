package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.alarmSystem.AlarmSystem;
import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

abstract public class DecoratorEventProcessor implements EventProcessor {
    EventProcessor eventProcessor;
    SmartHome smartHome;
    AlarmSystem alarmSystem;

    public DecoratorEventProcessor(EventProcessor eventProcessor, AlarmSystem alarmSystem) {
        this.eventProcessor = eventProcessor;
        this.smartHome = eventProcessor.getSmartHome();
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void process(SensorEvent event) {
        eventProcessor.process(event);
    }
}
