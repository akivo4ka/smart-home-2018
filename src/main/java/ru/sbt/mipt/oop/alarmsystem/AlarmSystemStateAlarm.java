package ru.sbt.mipt.oop.alarmsystem;

public class AlarmSystemStateAlarm implements AlarmSystemState {

    private final AlarmSystem alarmSystem;

    AlarmSystemStateAlarm(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn(String code) {
        System.out.println("Alarm system is in Alarm state.");
    }

    @Override
    public void turnOff(String code) {
        if (alarmSystem.checkCode(code)) {
            alarmSystem.setAlarmSystemState(new AlarmSystemStateOff(alarmSystem));
            System.out.println("Alarm system is deactivated.");
        } else {
            System.out.println("Alarm system is still in Alarm state. Code is incorrect.");
        }
    }

    @Override
    public void setAlarm() {
        System.out.println("Alarm system is already in Alarm state.");
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.ALARM;
    }
}
