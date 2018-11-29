package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.sensors.AlarmSensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.sensors.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessor implements EventProcessor {

    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        if (!isAlarmEvent(event)) return;
        if (event.getType() == ALARM_ACTIVATE) {
            smartHome.alarmSystem.turnOn(((AlarmSensorEvent) event).getCode());
        } else {
            smartHome.alarmSystem.turnOff(((AlarmSensorEvent) event).getCode());
        }
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE);
    }
}
