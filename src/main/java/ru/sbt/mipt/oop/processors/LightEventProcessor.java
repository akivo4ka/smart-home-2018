package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.homeUnits.Light;
import ru.sbt.mipt.oop.homeUnits.Room;
import ru.sbt.mipt.oop.homeUnits.SmartHome;

import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_ON;
import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_OFF;

public class LightEventProcessor implements EventProcessor {

    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) return;
        if (smartHome.alarmSystem.checkAlarmOn()) {
            System.out.println("ALARM! Sending sms.");
            return;
        }
        LightIterator lightIterator = new LightIterator(smartHome);
        while (lightIterator.hasNext()) {
            runEventCycle(event, lightIterator);
        }
    }

    private static boolean isLightEvent(SensorEvent event) {
        return (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF);
    }

    private static void runEventCycle(SensorEvent event, LightIterator lightIterator) {
        Light light = lightIterator.next();
        if (light.getId().equals(event.getObjectId())) {
            boolean b = (event.getType() == LIGHT_ON);
            processLightSwitchEvent(lightIterator.getCurrentRoom(), light, b);
        }
    }

    private static void processLightSwitchEvent(Room room, Light light, boolean b) {
        light.setOn(b);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " is turned " + (b ? "on" : "off") + " now.");
    }
}
