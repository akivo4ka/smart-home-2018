package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventProcessor {
    public static void processLightEvent(SmartHome smartHome, SensorEvent event) {
        // событие от источника света
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        processLightSwitchEvent(room, light, true, " was turned on.");
                    } else {
                        processLightSwitchEvent(room, light, false, " was turned off.");
                    }
                }
            }
        }
    }

    private static void processLightSwitchEvent(Room room, Light light, boolean b, String s) {
        light.setOn(b);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + s);
    }
}

