package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeunits.Light;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {

    private SmartHome smartHome;

    public LightEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void process(SensorEvent event) {
        if (!isLightEvent(event)) return;
        smartHome.processAction(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                if (checkLightIdEqualsEventId(event, light)) {
                    boolean b = (event.getType() == LIGHT_ON);
                    light.setOn(b);
                    System.out.println("Light " + light.getId() + " is turned " + (b ? "on" : "off") + " now.");
                }
            }
        });
        // System.out.println("Light with lightID = " + event.getObjectId() + " was not found.");
    }
    private boolean isLightEvent(SensorEvent event) {
        return (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF);
    }

    private boolean checkLightIdEqualsEventId(SensorEvent event, Light light) {
        return light.getId().equals(event.getObjectId());
    }
}
