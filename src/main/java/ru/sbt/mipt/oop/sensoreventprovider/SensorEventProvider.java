package ru.sbt.mipt.oop.sensoreventprovider;

import ru.sbt.mipt.oop.sensors.SensorEvent;

public interface SensorEventProvider {
    SensorEvent getNextSensorEvent();
}
