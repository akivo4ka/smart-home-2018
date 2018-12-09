package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homeunits.SmartHome;

public class TurnOnAllLightsCommand implements Command {

    private SmartHome smartHome;

    public TurnOnAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.turnOnAllLights();
    }
}
