package ru.sbt.mipt.oop.sensors;

public class AlarmSensorEvent extends SensorEvent {

    private String code;

    public AlarmSensorEvent(SensorEventType type, String code) {
        super(type, "-1");
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
