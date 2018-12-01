package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

public class DecoratorCheckAlarmEventProcessor extends AlarmEventProcessor {

    private EventProcessor eventProcessor;

    public DecoratorCheckAlarmEventProcessor(EventProcessor eventProcessor) {
        super(eventProcessor.getSmartHome());
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void process(SensorEvent event) {
        if (super.alarmSystem.checkAlarmOn()) {
            System.out.println("ALARM! Sending sms.");
        } else {
            eventProcessor.process(event);
        }
    }

    @Override
    public SmartHome getSmartHome() {
        return smartHome;
    }

}
