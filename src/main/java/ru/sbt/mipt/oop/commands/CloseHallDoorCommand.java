package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.DOOR_CLOSED;

public class CloseHallDoorCommand implements Command {

    private SmartHome smartHome;

    public CloseHallDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor(smartHome);
        SensorEvent hallDoorCloseEvent = new SensorEvent(DOOR_CLOSED, "4");
        hallDoorEventProcessor.process(hallDoorCloseEvent);
        System.out.println("Command 'CloseHallDoor' was executed.");
    }
}
