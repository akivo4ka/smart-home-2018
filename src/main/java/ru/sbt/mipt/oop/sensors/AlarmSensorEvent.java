package ru.sbt.mipt.oop.sensors;

public class AlarmSensorEvent extends SensorEvent {

    private String code;

    public AlarmSensorEvent(SensorEventType type, String objectId, String code) {
        super(type, objectId);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
