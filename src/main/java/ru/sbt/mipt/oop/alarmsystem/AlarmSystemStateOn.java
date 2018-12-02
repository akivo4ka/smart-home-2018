package ru.sbt.mipt.oop.alarmsystem;

public class AlarmSystemStateOn implements AlarmSystemState {

    private final AlarmSystem alarmSystem;

    AlarmSystemStateOn(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.ON;
    }

    @Override
    public void turnOff() {
        alarmSystem.setAlarmSystemState(new AlarmSystemStateOff(alarmSystem));
        System.out.println("Alarm system is deactivated.");
    }

    @Override
    public void turnOn() { }
}