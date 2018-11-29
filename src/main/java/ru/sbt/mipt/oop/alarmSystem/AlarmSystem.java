package ru.sbt.mipt.oop.alarmSystem;

public class AlarmSystem {

    private AlarmSystemState alarmSystemState;

    public AlarmSystem() {
        this.alarmSystemState = new AlarmSystemStateOff(this);
    }

    public void setAlarm() {
        setAlarmSystemState(new AlarmSystemStateAlarm(this));
    }

    public void turnOn(String code) {
        this.code = code;
        alarmSystemState.turnOn();
    }

    public void turnOff(String code) {
        if (this.code.equals(code)) {
            alarmSystemState.turnOff();
        } else {
            setAlarmSystemState(new AlarmSystemStateAlarm(this));
            System.out.println("ALARM! Code is incorrect!");
        }
    }

    public void setAlarmSystemState(AlarmSystemState alarmSystemState) {
        this.alarmSystemState = alarmSystemState;
    }

    public AlarmSystemStateEnum getSystemState() {
        return alarmSystemState.getState();
    }

    private String code = "qwe123QWE!";

    public boolean checkAlarmOn() {
        return this.alarmSystemState.getState().equals(AlarmSystemStateEnum.ON);
    }
}
