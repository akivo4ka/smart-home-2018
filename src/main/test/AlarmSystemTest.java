import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.FileSmartHomeLoader;
import ru.sbt.mipt.oop.SmartHomeLoader;
import ru.sbt.mipt.oop.homeUnits.Door;
import ru.sbt.mipt.oop.homeUnits.Room;
import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.processors.AlarmEventProcessor;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.sensors.AlarmSensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.sbt.mipt.oop.alarmSystem.AlarmSystemStateEnum.*;

public class AlarmSystemTest {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    @Test
    public void testNewSystemIsOff() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        Assert.assertEquals(OFF, smartHome.alarmSystem.getSystemState());
    }

    @Test
    public void turnOnSetsTurnedOnState() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        smartHome.alarmSystem.turnOn("qwe123QWE");
        assertEquals(ON, smartHome.alarmSystem.getSystemState());
    }

    @Test
    public void turnOnSetsCodeAndTurnOffChecksCode() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        smartHome.alarmSystem.turnOn("qwe123QWE");
        smartHome.alarmSystem.turnOff("qwerty");
        assertEquals(ALARM, smartHome.alarmSystem.getSystemState());
    }

    @Test
    public void turnOnSetsCodeAndTurnOff() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        smartHome.alarmSystem.turnOn("qwe123QWE");
        smartHome.alarmSystem.turnOff("qwe123QWE");
        assertEquals(OFF, smartHome.alarmSystem.getSystemState());
    }

    @Test
    public void turnOnAlarmAndRunSensorEvent() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        String doorId = "3";
        AlarmSensorEvent alarmSensorEvent = new AlarmSensorEvent(SensorEventType.ALARM_ACTIVATE, "0", "qwe123QWE");
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor();
        alarmEventProcessor.process(smartHome, alarmSensorEvent);
        SensorEvent doorOnEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor();
        doorEventProcessor.process(smartHome, doorOnEvent);
//        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(new ArrayList<EventProcessor>());
//        homeEventsObserver.addEventProcessor(doorEventProcessor);
//        homeEventsObserver.observer(smartHome);

        for (Room room: smartHome.getRooms()) {
            if (room.getName().equals("bedroom")) {
                for (Door door : room.getDoors()) {
                    assertTrue(door.isOpen());
                }
            }
        }
    }

}
