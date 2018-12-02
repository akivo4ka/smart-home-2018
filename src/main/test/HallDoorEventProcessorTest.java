import junit.framework.Assert;
import org.junit.Test;
import ru.sbt.mipt.oop.FileSmartHomeLoader;
import ru.sbt.mipt.oop.SmartHomeLoader;
import ru.sbt.mipt.oop.homeunits.Light;
import ru.sbt.mipt.oop.homeunits.Room;
import ru.sbt.mipt.oop.homeunits.SmartHome;
import ru.sbt.mipt.oop.processors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

public class HallDoorEventProcessorTest {

    private static SmartHomeLoader smartHomeLoader = new FileSmartHomeLoader();

    @Test
    public void processHallDoorEventTest() throws Exception {
        SmartHome smartHome = smartHomeLoader.loadSmartHome();
        String doorId = "4";
        SensorEvent doorOnEvent = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);
        HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor(smartHome);
        hallDoorEventProcessor.process(doorOnEvent);

        for (Room room: smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                Assert.assertFalse(light.isOn());
            }
        }
    }
}
