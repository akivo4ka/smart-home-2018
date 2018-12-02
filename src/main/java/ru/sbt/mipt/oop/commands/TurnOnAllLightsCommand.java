package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homeunits.Light;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.processors.LightIterator;

public class TurnOnAllLightsCommand implements Command {

    private SmartHome smartHome;

    public TurnOnAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        LightIterator lightIterator = new LightIterator(smartHome);
        for (Light light : lightIterator) {
            light.setOn(true);
        }
        System.out.println("Command 'TurnOnAllLights' was executed.");
    }
}
