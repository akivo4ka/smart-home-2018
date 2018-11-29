package ru.sbt.mipt.oop.alarmSystem;

public class AlarmSystemStateOff implements AlarmSystemState {

    private final AlarmSystem alarmSystem;

    public AlarmSystemStateOff(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.OFF;
    }

    @Override
    public void turnOff() { }

    @Override
    public void turnOn() {
        this.alarmSystem.setAlarmSystemState(new AlarmSystemStateOn(this.alarmSystem));
        System.out.println("Alarm system is activated.");
    }
}
