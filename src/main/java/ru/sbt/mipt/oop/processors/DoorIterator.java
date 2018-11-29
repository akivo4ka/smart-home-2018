package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeUnits.Door;
import ru.sbt.mipt.oop.homeUnits.Room;
import ru.sbt.mipt.oop.homeUnits.SmartHome;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DoorIterator implements Iterator<Door> {

    private int currentRoomId;
    private Room currentRoom;
    private int currentDoorId;
    private List<Room> rooms;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public DoorIterator(SmartHome smartHome) {
        this.currentDoorId = 0;
        this.currentRoomId = 0;
        this.currentRoom = null;
        this.rooms = new ArrayList<>(smartHome.getRooms());
    }

    @Override
    public boolean hasNext() {
        if (currentRoomId > rooms.size() - 1) return false;
        if (currentRoomId == rooms.size() - 1) {
            Room room  = rooms.get(currentRoomId);
            return currentDoorId <= new ArrayList<>(room.getDoors()).size() - 1;
        }
        return true;
    }

    @Override
    public Door next() {
        List<Door> currentRoomDoors = new ArrayList<>(rooms.get(currentRoomId).getDoors());
        Door door;
        currentRoom = rooms.get(currentRoomId);
        if (currentRoomDoors.size() == 1) {
            if (currentDoorId == 0) {
                door = currentRoomDoors.get(currentDoorId);
                currentRoomId++;
                currentDoorId = 0;
            }
            else throw new IllegalStateException();
        }
        else {
            if (currentDoorId < currentRoomDoors.size() - 1) {
                door = currentRoomDoors.get(currentDoorId);
                currentDoorId++;
            }
            else if (currentDoorId == currentRoomDoors.size() - 1) {
                door = currentRoomDoors.get(currentDoorId);
                currentRoomId++;
                currentDoorId = 0;
            }
            else throw new IllegalStateException();
        }
        return door;
    }
}
