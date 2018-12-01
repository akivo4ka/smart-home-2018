import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.alarmSystem.AlarmSystem;
import ru.sbt.mipt.oop.homeUnits.Door;
import ru.sbt.mipt.oop.homeUnits.Room;
import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.processors.*;
import ru.sbt.mipt.oop.sensors.AlarmSensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.sbt.mipt.oop.alarmSystem.AlarmSystemStateEnum.*;

class AlarmSystemTestSensorEventProvider implements SensorEventProvider {

    List<SensorEvent> list;

    int i = 0;

    AlarmSystemTestSensorEventProvider(List<SensorEvent> list) {
        this.list = list;
    }

    @Override
    public SensorEvent getNextSensorEvent() {
        if (i == list.size()) return null;
        return list.get(i++);
    }
}

public class AlarmSystemTest {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    @Test
    public void testNewSystemIsOff() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        Assert.assertEquals(OFF, smartHome.getAlarmSystem().getSystemState());
    }

    @Test
    public void turnOnSetsCodeAndTurnOffChecksCode() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();

        List<SensorEvent> list = new ArrayList<>();
        AlarmSensorEvent alarmActivateSensorEvent = new AlarmSensorEvent(SensorEventType.ALARM_ACTIVATE, "qwe123QWE");
        AlarmSensorEvent alarmDeactivateSensorEvent = new AlarmSensorEvent(SensorEventType.ALARM_DEACTIVATE, "qwe");
        list.add(alarmActivateSensorEvent);
        list.add(alarmDeactivateSensorEvent);
        SensorEventProvider sensorEventProvider = new AlarmSystemTestSensorEventProvider(list);

        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(createEventProcessorList(smartHome), sensorEventProvider);
        homeEventsObserver.start();

        assertEquals(ALARM, smartHome.getAlarmSystem().getSystemState());
    }

    @Test
    public void turnOnSetsCodeAndTurnOff() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        List<SensorEvent> list = new ArrayList<>();
        AlarmSensorEvent alarmActivateSensorEvent = new AlarmSensorEvent(SensorEventType.ALARM_ACTIVATE, "qwe123QWE");
        AlarmSensorEvent alarmDeactivateSensorEvent = new AlarmSensorEvent(SensorEventType.ALARM_DEACTIVATE, "qwe123QWE");
        list.add(alarmActivateSensorEvent);
        list.add(alarmDeactivateSensorEvent);
        SensorEventProvider sensorEventProvider = new AlarmSystemTestSensorEventProvider(list);

        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(createEventProcessorList(smartHome), sensorEventProvider);
        homeEventsObserver.start();
        assertEquals(OFF, smartHome.getAlarmSystem().getSystemState());
    }

    @Test
    public void turnOnAlarmAndRunSensorEvent() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        AlarmSystem alarmSystem = new AlarmSystem();

        List<EventProcessor> eventProcessorList = createEventProcessorList(smartHome);

        List<SensorEvent> list = new ArrayList<>();
        String doorId = "3";
        AlarmSensorEvent alarmSensorEvent = new AlarmSensorEvent(SensorEventType.ALARM_ACTIVATE, "qwe123QWE");
        SensorEvent doorOnEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);
        list.add(alarmSensorEvent);
        list.add(doorOnEvent);
        SensorEventProvider sensorEventProvider = new AlarmSystemTestSensorEventProvider(list);

        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(eventProcessorList, sensorEventProvider);
        homeEventsObserver.start();

        for (Room room: smartHome.getRooms()) {
            if (room.getName().equals("bedroom")) {
                for (Door door : room.getDoors()) {
                    assertTrue(door.isOpen());
                }
            }
        }
    }

    private List<EventProcessor> createEventProcessorList(SmartHome smartHome) {
        List<EventProcessor> eventProcessorList = new ArrayList<>();
        EventProcessor lightEventProcessor = new DecoratorCheckAlarmEventProcessor(new LightEventProcessor(smartHome));
        EventProcessor doorEventProcessor = new DecoratorCheckAlarmEventProcessor(new DoorEventProcessor(smartHome));
        EventProcessor hallDoorEventProcessor = new DecoratorCheckAlarmEventProcessor(new HallDoorEventProcessor(smartHome));
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor(smartHome);
        eventProcessorList.add(lightEventProcessor);
        eventProcessorList.add(doorEventProcessor);
        eventProcessorList.add(hallDoorEventProcessor);
        eventProcessorList.add(alarmEventProcessor);
        return eventProcessorList;
    }

}
