import org.junit.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.FileSmartHomeLoader;
import ru.sbt.mipt.oop.SmartHomeLoader;
import ru.sbt.mipt.oop.homeunits.Door;
import ru.sbt.mipt.oop.homeunits.Room;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.phrases.SmartHomePhrases;
import ru.sbt.mipt.oop.phrases.SmartHomePhrasesLoader;
import ru.sbt.mipt.oop.processors.*;
import ru.sbt.mipt.oop.sensoreventprovider.MySensorEventProvider;
import ru.sbt.mipt.oop.sensoreventprovider.SensorEventProvider;
import ru.sbt.mipt.oop.sensoreventsmanager.HomeEventsObserver;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.sbt.mipt.oop.alarmsystem.AlarmSystemStateEnum.ALARM;
import static ru.sbt.mipt.oop.alarmsystem.AlarmSystemStateEnum.OFF;

public class AlarmSystemTest {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    @Test
    public void testNewSystemIsOff() throws IOException {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        Assert.assertEquals(OFF, smartHome.getAlarmSystem().getSystemState());
    }

    @Test
    public void turnOnSetsCodeAndTurnOffChecksCode() throws IOException {
        SmartHomePhrases smartHomePhrases = (new SmartHomePhrasesLoader()).loadSmartHomePhrases();
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        smartHome.setSmartHomePhrases(smartHomePhrases, "ru");

        List<SensorEvent> list = new ArrayList<>();
        SensorEvent alarmActivateSensorEvent = new SensorEvent(SensorEventType.ALARM_ACTIVATE, "qwe123QWE");
        SensorEvent alarmDeactivateSensorEvent = new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "qwe");
        list.add(alarmActivateSensorEvent);
        list.add(alarmDeactivateSensorEvent);
        SensorEventProvider sensorEventProvider = new MySensorEventProvider(list);

        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(createEventProcessorList(smartHome), sensorEventProvider);
        homeEventsObserver.start();

        assertEquals(ALARM, smartHome.getAlarmSystem().getSystemState());
    }

    @Test
    public void turnOnSetsCodeAndTurnOff() throws IOException {
        SmartHomePhrases smartHomePhrases = (new SmartHomePhrasesLoader()).loadSmartHomePhrases();
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        smartHome.setSmartHomePhrases(smartHomePhrases, "ru");
        List<SensorEvent> list = new ArrayList<>();
        SensorEvent alarmActivateSensorEvent = new SensorEvent(SensorEventType.ALARM_ACTIVATE, "qwe123QWE");
        SensorEvent alarmDeactivateSensorEvent = new SensorEvent(SensorEventType.ALARM_DEACTIVATE, "qwe123QWE");
        list.add(alarmActivateSensorEvent);
        list.add(alarmDeactivateSensorEvent);
        SensorEventProvider sensorEventProvider = new MySensorEventProvider(list);

        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(createEventProcessorList(smartHome), sensorEventProvider);
        homeEventsObserver.start();
        assertEquals(OFF, smartHome.getAlarmSystem().getSystemState());
    }

    @Test
    public void turnOnAlarmAndRunSensorEvent() throws IOException {
        SmartHomePhrases smartHomePhrases = (new SmartHomePhrasesLoader()).loadSmartHomePhrases();
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        smartHome.setSmartHomePhrases(smartHomePhrases, "ru");

        List<EventProcessor> eventProcessorList = createEventProcessorList(smartHome);

        List<SensorEvent> list = new ArrayList<>();
        String doorId = "3";
        SensorEvent alarmSensorEvent = new SensorEvent(SensorEventType.ALARM_ACTIVATE, "qwe123QWE");
        SensorEvent doorOnEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);
        list.add(alarmSensorEvent);
        list.add(doorOnEvent);
        SensorEventProvider sensorEventProvider = new MySensorEventProvider(list);

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
        EventProcessor lightEventProcessor = new DecoratorCheckAlarmEventProcessor(new LightEventProcessor(smartHome), smartHome);
        EventProcessor doorEventProcessor = new DecoratorCheckAlarmEventProcessor(new DoorEventProcessor(smartHome), smartHome);
        EventProcessor hallDoorEventProcessor = new DecoratorCheckAlarmEventProcessor(new HallDoorEventProcessor(smartHome), smartHome);
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor(smartHome);
        eventProcessorList.add(lightEventProcessor);
        eventProcessorList.add(doorEventProcessor);
        eventProcessorList.add(hallDoorEventProcessor);
        eventProcessorList.add(alarmEventProcessor);
        return eventProcessorList;
    }

}
