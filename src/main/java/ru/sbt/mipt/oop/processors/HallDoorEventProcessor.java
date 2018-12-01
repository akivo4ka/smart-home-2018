package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeUnits.Door;
import ru.sbt.mipt.oop.homeUnits.Light;
import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

public class HallDoorEventProcessor implements EventProcessor {

    SmartHome smartHome;

    public HallDoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void process(SensorEvent event) {
        if (!isHallDoorEvent(event)) return;
        LightIterator lightIterator = new LightIterator(smartHome);
        for (Light light : lightIterator) {
            light.setOn(false);
        }
        System.out.println("Light is turned off everywhere.");
    }

    private boolean isHallDoorEvent(SensorEvent event) {
        DoorIterator doorIterator = new DoorIterator(smartHome);
        for (Door door : doorIterator) {
            if (checkDoorIdEqualsEventId(event, door)) {
                if (event.getType() == SensorEventType.DOOR_CLOSED) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDoorIdEqualsEventId(SensorEvent event, Door door) {
        return door.getId().equals(event.getObjectId());
    }
}


