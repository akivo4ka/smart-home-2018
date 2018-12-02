package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeunits.Door;
import ru.sbt.mipt.oop.homeunits.Room;
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
        // Надо вынести в декоратор
//        if (smartHome.alarmsystem.checkAlarmOn()) {
//            System.out.println("ALARM! Sending sms.");
//            return;
//        }
        DoorIterator doorIterator = new DoorIterator(smartHome);
        for (Door door : doorIterator) {
            if (processDoorEvent(event, doorIterator.getCurrentRoom(), door)) return;
        }
        System.out.println("Door with doorID = " + event.getObjectId() + " was not found.");
    }

    @Override
    public SmartHome getSmartHome() {
        return smartHome;
    }

    private boolean processDoorEvent(SensorEvent event, Room room, Door door) {
        if (checkDoorIdEqualsEventId(event, door)) {
            boolean b = (event.getType() == DOOR_OPEN);
            door.setOpen(b);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + (b ? "opened." : "closed."));
            return true;
        }
        return false;
    }

    private static boolean isDoorEvent(SensorEvent event) {
        return (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED);
    }

    private static boolean checkDoorIdEqualsEventId(SensorEvent event, Door door) {
        return door.getId().equals(event.getObjectId());
    }
}