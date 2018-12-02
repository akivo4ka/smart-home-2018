package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

public class DecoratorCheckAlarmEventProcessor extends AlarmEventProcessor {

    private EventProcessor eventProcessor;

    public DecoratorCheckAlarmEventProcessor(EventProcessor eventProcessor) {
        super(eventProcessor.getSmartHome());
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void process(SensorEvent event) {
        if (super.getAlarmSystem().checkAlarmOn()) {
            super.getAlarmSystem().setAlarm();
            System.out.println("ALARM! Sending sms.");
        } else if (!super.getAlarmSystem().checkAlarm()) {
            eventProcessor.process(event);
        }
    }

    @Override
    public SmartHome getSmartHome() {
        return smartHome;
    }

}
