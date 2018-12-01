package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeUnits.Door;
import ru.sbt.mipt.oop.homeUnits.Room;
import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void process(SensorEvent event) {
        if (!isDoorEvent(event)) return;
        // Надо вынести в декоратор
//        if (smartHome.alarmSystem.checkAlarmOn()) {
//            System.out.println("ALARM! Sending sms.");
//            return;
//        }
        DoorIterator doorIterator = new DoorIterator(smartHome);
        for (Door door : doorIterator) {
            processDoorEvent(event, doorIterator.getCurrentRoom(), door);
        }
    }

    private void processDoorEvent(SensorEvent event, Room room, Door door) {
        if (checkDoorIdEqualsEventId(event, door)) {
            boolean b = (event.getType() == DOOR_OPEN);
            door.setOpen(b);
            System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + (b ? "opened." : "closed."));
        }
    }

    private static boolean isDoorEvent(SensorEvent event) {
        return (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED);
    }

    private static boolean checkDoorIdEqualsEventId(SensorEvent event, Door door) {
        return door.getId().equals(event.getObjectId());
    }
}