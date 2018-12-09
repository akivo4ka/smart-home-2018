package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeunits.Door;
import ru.sbt.mipt.oop.homeunits.Light;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor {

    private SmartHome smartHome;

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
        System.out.println("... and light is turned off everywhere.");
    }

    private boolean isHallDoorEvent(SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) return false;
        DoorIterator doorIterator = new DoorIterator(smartHome);
        for (Door door : doorIterator) {
            if (doorIterator.getCurrentRoom().getName().equals("hall")) {
                if (checkDoorIdEqualsEventId(event, door)) {
                    door.setOpen(false);
                    System.out.println("Hall door was closed ...");
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


