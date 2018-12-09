package ru.sbt.mipt.oop.alarmsystem;

public class AlarmSystemStateOff implements AlarmSystemState {

    private final AlarmSystem alarmSystem;

    AlarmSystemStateOff(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.OFF;
    }

    @Override
    public void turnOff(String code) {
        System.out.println("Alarm system is already deactivated.");
    }

    @Override
    public void setAlarm() {
        alarmSystem.setAlarmSystemState(new AlarmSystemStateAlarm(alarmSystem));
    }

    @Override
    public void turnOn(String code) {
        alarmSystem.setCode(code);
        this.alarmSystem.setAlarmSystemState(new AlarmSystemStateOn(this.alarmSystem));
        System.out.println("Alarm system is activated.");
    }
}
