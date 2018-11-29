package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.homeUnits.Light;
import ru.sbt.mipt.oop.homeUnits.SmartHome;

public class HallDoorEventProcessor implements EventProcessor {
    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        LightIterator lightIterator = new LightIterator(smartHome);
        while (lightIterator.hasNext()) {
            Light light = lightIterator.next();
            light.setOn(false);
        }
        System.out.println("Light is turned off everywhere.");
    }
}
