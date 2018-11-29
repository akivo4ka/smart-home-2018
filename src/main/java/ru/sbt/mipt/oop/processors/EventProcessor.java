package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.homeUnits.SmartHome;

public interface EventProcessor {
    void process(SmartHome smartHome, SensorEvent event);
}
