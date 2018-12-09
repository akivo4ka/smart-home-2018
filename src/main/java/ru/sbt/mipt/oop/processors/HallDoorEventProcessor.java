package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeunits.Door;
import ru.sbt.mipt.oop.homeunits.Room;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.Collection;

import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    public HallDoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void process(SensorEvent event) {
        if (!isHallDoorEvent(event)) return;
        smartHome.processAction(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("hall")) {
                    Collection<Door> doors = room.getDoors();
                    for (Door door : doors) {
                        if (checkDoorIdEqualsEventId(event, door)) {
                            door.setOpen(false);
                            System.out.println("Hall door was closed...");
                            smartHome.turnOffAllLights();
                            break;
                        }
                    }
                }
            }
        });
    }

    private boolean isHallDoorEvent(SensorEvent event) {
        return (event.getType() == DOOR_CLOSED);
    }

    private boolean checkDoorIdEqualsEventId(SensorEvent event, Door door) {
        return door.getId().equals(event.getObjectId());
    }
}


