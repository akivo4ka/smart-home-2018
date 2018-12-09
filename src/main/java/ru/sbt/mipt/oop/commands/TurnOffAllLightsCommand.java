package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homeunits.SmartHome;

public class TurnOffAllLightsCommand implements Command {

    private SmartHome smartHome;

    public TurnOffAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.turnOffAllLights();
    }
}
