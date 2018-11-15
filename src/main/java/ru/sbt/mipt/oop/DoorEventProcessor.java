package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor {
    public static void processDoorEvent(SmartHome smartHome, SensorEvent event) {
        // событие от двери
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        processDoorOpenEvent(room, door);
                    } else {
                        processDoorCloseEvent(smartHome, room, door);
                    }
                }
            }
        }
    }

    private static void processDoorCloseEvent(SmartHome smartHome, Room room, Door door) {
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        if (room.getName().equals("hall")) {
            processHallDoorEvent(smartHome);
        }
    }

    private static void processDoorOpenEvent(Room room, Door door) {
        door.setOpen(true);
        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
    }

    private static void processHallDoorEvent(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                SensorCommandExecuter.executeCommand(command);
            }
        }
    }
}

