package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeUnits.Light;
import ru.sbt.mipt.oop.homeUnits.Room;
import ru.sbt.mipt.oop.homeUnits.SmartHome;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LightIterator implements Iterable<Light> {

    private int currentRoomId;
    private Room currentRoom;
    private int currentLightId;
    private List<Room> rooms;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public LightIterator(SmartHome smartHome) {
        this.currentLightId = 0;
        this.currentRoomId = 0;
        this.currentRoom = null;
        this.rooms = new ArrayList<>(smartHome.getRooms());
    }

    @Override
    public Iterator<Light> iterator() {
        return new Iterator<Light>() {
            @Override
            public boolean hasNext() {
                if (currentRoomId > rooms.size() - 1) return false;
                if (currentRoomId == rooms.size() - 1) {
                    Room room  = rooms.get(currentRoomId);
                    return currentLightId <= new ArrayList<>(room.getLights()).size() - 1;
                }
                return true;
            }

            @Override
            public Light next() {
                List<Light> currentRoomLights = new ArrayList<>(rooms.get(currentRoomId).getLights());
                currentRoom = rooms.get(currentRoomId);
                Light light;
                if (currentRoomLights.size() == 1) {
                    if (currentLightId == 0) {
                        light = currentRoomLights.get(currentLightId);
                        currentRoomId++;
                        currentLightId = 0;
                    }
                    else throw new IllegalStateException();
                }
                else {
                    if (currentLightId < currentRoomLights.size() - 1) {
                        light = currentRoomLights.get(currentLightId);
                        currentLightId++;
                    }
                    else if (currentLightId == currentRoomLights.size() - 1) {
                        light = currentRoomLights.get(currentLightId);
                        currentRoomId++;
                        currentLightId = 0;
                    }
                    else throw new IllegalStateException();
                }
                return light;
            }
        };
    }
}
