package ru.sbt.mipt.oop.alarmsystem;

public interface AlarmSystemState {

    void turnOn(String code);

    void turnOff(String code);

    void setAlarm();

    AlarmSystemStateEnum getState();
}
