package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.alarmsystem.AlarmSystem;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

public class DecoratorCheckAlarmEventProcessor implements EventProcessor {

    private AlarmSystem alarmSystem;
    private SmartHome smartHome;

    private EventProcessor eventProcessor;

    public DecoratorCheckAlarmEventProcessor(EventProcessor eventProcessor, SmartHome smartHome) {
        this.smartHome = smartHome;
        this.eventProcessor = eventProcessor;
        this.alarmSystem = smartHome.getAlarmSystem();
    }

    @Override
    public void process(SensorEvent event) {
        if (alarmSystem.checkAlarmOn()) {
            alarmSystem.setAlarm();
            System.out.println(alarmSystem.getPhrase().getAlarmSms());
        } else if (!alarmSystem.checkAlarm()) {
            eventProcessor.process(event);
        }
    }

}
