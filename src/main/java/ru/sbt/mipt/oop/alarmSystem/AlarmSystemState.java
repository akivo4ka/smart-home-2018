package ru.sbt.mipt.oop.alarmSystem;

public interface AlarmSystemState {

    public void turnOn();

    public void turnOff();

    public AlarmSystemStateEnum getState();
}
