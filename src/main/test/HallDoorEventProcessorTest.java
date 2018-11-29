import junit.framework.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.homeUnits.Light;
import ru.sbt.mipt.oop.homeUnits.Room;
import ru.sbt.mipt.oop.homeUnits.SmartHome;
import ru.sbt.mipt.oop.processors.DoorEventProcessor;

public class HallDoorEventProcessorTest {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    @Test
    public void processHallDoorEventTest() throws Exception {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        String doorId = "4";
        SensorEvent doorOnEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);
        DoorEventProcessor.processDoorEvent(smartHome, doorOnEvent);

        for (Room room: smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                Assert.assertFalse(light.isOn());
            }
        }
    }
}
