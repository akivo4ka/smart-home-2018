package ru.sbt.mipt.oop.alarmSystem;

public class AlarmSystemStateAlarm implements AlarmSystemState {

    private final AlarmSystem alarmSystem;

    public AlarmSystemStateAlarm(AlarmSystem alarmSystem) {
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
