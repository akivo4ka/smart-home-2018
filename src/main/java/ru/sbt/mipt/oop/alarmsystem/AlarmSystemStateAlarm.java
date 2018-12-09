package ru.sbt.mipt.oop.alarmsystem;

public class AlarmSystemStateAlarm implements AlarmSystemState {

    private final AlarmSystem alarmSystem;

    AlarmSystemStateAlarm(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn(String code) {
        System.out.println(alarmSystem.getPhrase().getAlarmSystemStateAlarmTurnOnPhrase());
    }

    @Override
    public void turnOff(String code) {
        if (alarmSystem.checkCode(code)) {
            alarmSystem.setAlarmSystemState(new AlarmSystemStateOff(alarmSystem));
            System.out.println(alarmSystem.getPhrase().getAlarmSystemStateAlarmTurnOffTruePhrase());
        } else {
            System.out.println(alarmSystem.getPhrase().getAlarmSystemStateAlarmTurnOffFalsePhrase());
        }
    }

    @Override
    public void setAlarm() {
        System.out.println(alarmSystem.getPhrase().getAlarmSystemStateAlarmSetAlarmPhrase());
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.ALARM;
    }
}
