package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeunits.Door;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void process(SensorEvent event) {
        if (!isDoorEvent(event)) return;
        smartHome.processAction(object -> {
            if (object instanceof Door) {
                Door door = (Door) object;
                if (checkDoorIdEqualsEventId(event, door)) {
                    boolean b = (event.getType() == DOOR_OPEN);
                    door.setOpen(b);
                    System.out.println("Door " + door.getId() + " was " + (b ? "opened." : "closed."));
                }
            }
        });
        // System.out.println("Door with doorID = " + event.getObjectId() + " was not found.");
    }

    private static boolean isDoorEvent(SensorEvent event) {
        return (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED);
    }

    private static boolean checkDoorIdEqualsEventId(SensorEvent event, Door door) {
        return door.getId().equals(event.getObjectId());
    }
}