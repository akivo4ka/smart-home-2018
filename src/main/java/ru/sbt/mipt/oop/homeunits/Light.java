package ru.sbt.mipt.oop.homeUnits;

import ru.sbt.mipt.oop.Action;

public class Light implements HomeUnit {
    private boolean isOn;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getId() {
        return id;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void processAction(Action action) {
        action.execute(this);
    }
}
