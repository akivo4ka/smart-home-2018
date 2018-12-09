package ru.sbt.mipt.oop.homeunits;

import ru.sbt.mipt.oop.alarmsystem.AlarmSystem;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeUnit {
    private Collection<Room> rooms;

    private AlarmSystem alarmSystem;

    public AlarmSystem getAlarmSystem() {
        return alarmSystem;
    }

    public void setAlarmSystem(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public SmartHome() {
        rooms = new ArrayList<>();
        alarmSystem = new AlarmSystem();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void processAction(Action action) {
        for (Room room: rooms) {
            room.processAction(action);
        }
        action.execute(this);
    }

    public void turnOffAllLights() {
        this.processAction(object1 -> {
            if (object1 instanceof Light) {
                Light light = (Light) object1;
                light.setOn(false);
            }
        });
        System.out.println("All lights were turned off.");
    }

    public void turnOnAllLights() {
        this.processAction(object1 -> {
            if (object1 instanceof Light) {
                Light light = (Light) object1;
                light.setOn(true);
            }
        });
        System.out.println("All lights were turned on.");

    }
}
