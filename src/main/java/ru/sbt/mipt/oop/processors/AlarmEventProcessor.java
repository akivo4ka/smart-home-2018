package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.alarmsystem.AlarmSystem;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.sensors.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessor implements EventProcessor {

    private AlarmSystem alarmSystem;

    public AlarmEventProcessor(SmartHome smartHome) {
        this.alarmSystem = smartHome.getAlarmSystem();
        this.alarmSystem.setPhrase(smartHome.getSmartHomePhrases());
    }

    @Override
    public void process(SensorEvent event) {
        if (!isAlarmEvent(event)) return;
        if (event.getType() == ALARM_ACTIVATE) {
            alarmSystem.turnOn(event.getObjectId());
        } else {
            alarmSystem.turnOff(event.getObjectId());
        }
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE);
    }
}
