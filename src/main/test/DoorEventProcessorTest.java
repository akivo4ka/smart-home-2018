import junit.framework.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.homeunits.Door;
import ru.sbt.mipt.oop.homeunits.Room;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.phrases.SmartHomePhrases;
import ru.sbt.mipt.oop.phrases.SmartHomePhrasesLoader;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

public class DoorEventProcessorTest {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    @Test
    public void processDoorEventTest() throws Exception {
        SmartHomePhrases smartHomePhrases = (new SmartHomePhrasesLoader()).loadSmartHomePhrases();
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        smartHome.setSmartHomePhrases(smartHomePhrases, "ru");
        String doorId = "1";
        SensorEvent doorOnEvent = new SensorEvent(SensorEventType.DOOR_OPEN, doorId);
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor(smartHome);
        doorEventProcessor.process(doorOnEvent);

        for (Room room: smartHome.getRooms()) {
            for (Door door: room.getDoors()) {
                if (door.getId().equals(doorId)) {
                    Assert.assertTrue(door.isOpen());
                    break;
                }
            }
        }
    }

}
