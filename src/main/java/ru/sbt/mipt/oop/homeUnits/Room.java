package ru.sbt.mipt.oop.homeUnits;

import ru.sbt.mipt.oop.Action;

import java.util.Collection;

public class Room implements  HomeUnit {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }

    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    @Override
    public void processAction(Action action) {
        for (Door door: doors) {
            door.processAction(action);
        }
        for (Light light: lights) {
            light.processAction(action);
        }
        action.execute(this);
    }
}
