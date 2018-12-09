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
        System.out.println(alarmSystem.getPhrase().getAlarmSystemStateOffTurnOffPhrase());
    }

    @Override
    public void setAlarm() {
        alarmSystem.setAlarmSystemState(new AlarmSystemStateAlarm(alarmSystem));
    }

    @Override
    public void turnOn(String code) {
        alarmSystem.setCode(code);
        alarmSystem.setAlarmSystemState(new AlarmSystemStateOn(alarmSystem));
        System.out.println(alarmSystem.getPhrase().getAlarmSystemStateOffTurnOnPhrase());
    }
}
