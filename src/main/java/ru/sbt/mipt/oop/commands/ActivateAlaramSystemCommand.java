package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.processors.AlarmEventProcessor;
import ru.sbt.mipt.oop.sensors.AlarmSensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.ALARM_ACTIVATE;

public class ActivateAlaramSystemCommand implements Command {

    private SmartHome smartHome;
    private static String code = "qwe123QWE";

    public ActivateAlaramSystemCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    @Override
    public void execute() {
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor(smartHome);
        AlarmSensorEvent alarmSensorEvent = new AlarmSensorEvent(ALARM_ACTIVATE, code);
        alarmEventProcessor.process(alarmSensorEvent);
    }
}
