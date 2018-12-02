package ru.sbt.mipt.oop.alarmsystem;

public interface AlarmSystemState {

    void turnOn();

    void turnOff();

    AlarmSystemStateEnum getState();
}
