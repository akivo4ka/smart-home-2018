package ru.sbt.mipt.oop.alarmsystem;

public class AlarmSystemStateAlarm implements AlarmSystemState {

    private final AlarmSystem alarmSystem;

    AlarmSystemStateAlarm(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn() {
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.ALARM;
    }

    @Override
    public void turnOff() {
        alarmSystem.setAlarmSystemState(new AlarmSystemStateOff(alarmSystem));
    }
}
