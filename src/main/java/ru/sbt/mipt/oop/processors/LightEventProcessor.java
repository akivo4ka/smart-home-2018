package ru.sbt.mipt.oop.processors;

import ru.sbt.mipt.oop.homeunits.Light;
import ru.sbt.mipt.oop.homeunits.Room;
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
        // Надо вынести в декоратор
//        if (smartHome.alarmsystem.checkAlarmOn()) {
//            System.out.println("ALARM! Sending sms.");
//            return;
//        }
        LightIterator lightIterator = new LightIterator(smartHome);

        for (Light light : lightIterator) {
            if (processLightEvent(event, lightIterator.getCurrentRoom(), light)) return;
        }
        System.out.println("Light with lightID = " + event.getObjectId() + " was not found.");
    }

    @Override
    public SmartHome getSmartHome() {
        return smartHome;
    }

    private boolean isLightEvent(SensorEvent event) {
        return (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF);
    }

    private boolean processLightEvent(SensorEvent event, Room room, Light light) {
        if (checkLightIdEqualsEventId(event, light)) {
            boolean b = (event.getType() == LIGHT_ON);
            light.setOn(b);
            System.out.println("Light " + light.getId() + " in room " + room.getName() + " is turned " + (b ? "on" : "off") + " now.");
            return true;
        }
        return false;
    }

    private boolean checkLightIdEqualsEventId(SensorEvent event, Light light) {
        return light.getId().equals(event.getObjectId());
    }
}
