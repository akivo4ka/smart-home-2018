package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.homeUnits.SmartHome;

public class ActivateAlarmStateCommand implements Command {

    private SmartHome smartHome;

    public ActivateAlarmStateCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getAlarmSystem().setAlarm();
    }
}
