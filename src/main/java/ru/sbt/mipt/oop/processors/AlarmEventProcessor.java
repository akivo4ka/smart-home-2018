package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.alarmsystem.AlarmSystem;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.sensors.AlarmSensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.sensors.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventProcessor implements EventProcessor {

    SmartHome smartHome;
    private AlarmSystem alarmSystem;

    public AlarmEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
        this.alarmSystem = smartHome.getAlarmSystem();
    }

    @Override
    public void process(SensorEvent event) {
        if (!isAlarmEvent(event)) return;
        if (event.getType() == ALARM_ACTIVATE) {
            alarmSystem.turnOn(((AlarmSensorEvent) event).getCode());
        } else {
            alarmSystem.turnOff(((AlarmSensorEvent) event).getCode());
        }
    }

    @Override
    public SmartHome getSmartHome() {
        return smartHome;
    }

    AlarmSystem getAlarmSystem() {
        return alarmSystem;
    }

    private boolean isAlarmEvent(SensorEvent event) {
        return (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE);
    }
}
