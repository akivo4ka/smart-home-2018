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
    public void turnOff(String code) {
        if (alarmSystem.checkCode(code)) {
            alarmSystem.setAlarmSystemState(new AlarmSystemStateOff(alarmSystem));
            System.out.println("Alarm system is deactivated.");
        } else {
            this.setAlarm();
            System.out.println("ALARM! Incorrect code.");
        }
    }

    @Override
    public void setAlarm() {
        alarmSystem.setAlarmSystemState(new AlarmSystemStateAlarm(alarmSystem));
    }

    @Override
    public void turnOn(String code) {
        System.out.println("Alarm system is already activated.");
    }
}
