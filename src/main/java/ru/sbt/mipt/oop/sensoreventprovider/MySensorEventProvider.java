package ru.sbt.mipt.oop.sensoreventprovider;

import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.List;

public class MySensorEventProvider implements SensorEventProvider {

    private List<SensorEvent> list;

    private int i = 0;

    public MySensorEventProvider(List<SensorEvent> list) {
        this.list = list;
    }

    @Override
    public SensorEvent getNextSensorEvent() {
        if (i == list.size()) return null;
        return list.get(i++);
    }
}
