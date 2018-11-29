package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeUnits.Door;
import ru.sbt.mipt.oop.homeUnits.Room;
import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {

    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        if (!isDoorEvent(event)) return;
        if (smartHome.alarmSystem.checkAlarmOn()) {
            System.out.println("ALARM! Sending sms.");
            return;
        }
        DoorIterator doorIterator = new DoorIterator(smartHome);
        while (doorIterator.hasNext()) {
            runEventCycle(smartHome, event, doorIterator);
        }
    }

    private static boolean isDoorEvent(SensorEvent event) {
        return (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED);
    }

    private static void runEventCycle(SmartHome smartHome, SensorEvent event, DoorIterator doorIterator) {
        Door door = doorIterator.next();
        if (door.getId().equals(event.getObjectId())) {
            boolean b = (event.getType() == DOOR_OPEN);
            processDoorSwitchEvent(smartHome, doorIterator.getCurrentRoom(), door, b);
            if (doorIterator.getCurrentRoom().getName().equals("hall")) {
                HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor();
                hallDoorEventProcessor.process(smartHome, event);
            }
        }
    }

    private static void processDoorSwitchEvent(SmartHome smartHome, Room room, Door door, boolean b) {
        door.setOpen(b);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was " + (b ? "opened." : "closed."));
    }

//    private static void processDoorCloseEvent(SmartHome smartHome, Room room, Door door) {
//        door.setOpen(false);
//        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
//        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
//        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
//        if (room.getName().equals("hall")) {
//        }
//    }
//
//    private static void processDoorOpenEvent(Room room, Door door) {
//        door.setOpen(true);
//        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
//    }
//
//    private static void processHallDoorEvent(SmartHome smartHome) {
//        for (Room homeRoom : smartHome.getRooms()) {
//            for (Light light : homeRoom.getLights()) {
//                light.setOn(false);
//                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
//                SensorCommandExecuter.executeCommand(command);
//            }
//        }
//    }
}

//class DoorSwitch implements Action {
//
//    @Override
//    public void execute(Object object, SensorEvent event) {
//        if (object instanceof Door) {
//            if (event.getObjectId().equals(((Door) object).getId())) {
//                boolean b = event.getType() == DOOR_OPEN;
//                ((Door) object).setOpen(b);
//                System.out.println("Light " + ((Door) object).getId() + " was " + (b ? "opened" : "closed") + ".");
//            }
//        }
//    }
//}