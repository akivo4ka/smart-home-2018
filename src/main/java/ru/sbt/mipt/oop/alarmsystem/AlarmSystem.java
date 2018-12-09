package ru.sbt.mipt.oop.alarmsystem;

import ru.sbt.mipt.oop.phrases.Phrase;

import static ru.sbt.mipt.oop.alarmsystem.AlarmSystemStateEnum.ALARM;

public class AlarmSystem {

    private AlarmSystemState alarmSystemState;
    private Phrase phrase;

    public void setPhrase(Phrase phrase) {
        this.phrase = phrase;
    }

    public Phrase getPhrase() {
        return phrase;
    }

    public AlarmSystem() {
        this.alarmSystemState = new AlarmSystemStateOff(this);
    }

    public void setAlarm() {
        alarmSystemState.setAlarm();
    }

    public void turnOn(String code) {
        alarmSystemState.turnOn(code);
    }

    public void turnOff(String code) {
        alarmSystemState.turnOff(code);
    }

    void setAlarmSystemState(AlarmSystemState alarmSystemState) {
        this.alarmSystemState = alarmSystemState;
    }

    public AlarmSystemStateEnum getSystemState() {
        return alarmSystemState.getState();
    }

    private int hashCode;

    void setCode(String code) {
        this.hashCode = code.hashCode();
    }

    boolean checkCode(String code) {
        return this.hashCode == code.hashCode();
    }

    public boolean checkAlarmOn() {
        return this.alarmSystemState.getState().equals(AlarmSystemStateEnum.ON);
    }

    public boolean checkAlarm() {
        return this.alarmSystemState.getState().equals(ALARM);
    }
}
