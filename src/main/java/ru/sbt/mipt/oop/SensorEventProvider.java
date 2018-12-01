package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.sensors.SensorEvent;

public interface SensorEventProvider {
    SensorEvent getNextSensorEvent();
}
