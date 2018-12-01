package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.processors.AlarmEventProcessor;
import ru.sbt.mipt.oop.sensors.AlarmSensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.ALARM_ACTIVATE;

public class ActivateAlaramSystemCommand implements Command {

    private SmartHome smartHome;

    public ActivateAlaramSystemCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    @Override
    public void execute() {
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor(smartHome);
        AlarmSensorEvent alarmSensorEvent = new AlarmSensorEvent(ALARM_ACTIVATE, "1", "qwe123QWE");
        alarmEventProcessor.process(alarmSensorEvent);
    }
}
